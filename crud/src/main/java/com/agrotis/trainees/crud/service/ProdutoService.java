package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.DataValidadeInvalidaException;
import com.agrotis.trainees.crud.exception.ProdutoDuplicadoException;
import com.agrotis.trainees.crud.exception.ProdutoNaoEncontradoException;
import com.agrotis.trainees.crud.repository.ProdutoRepository;
import com.agrotis.trainees.crud.utils.ProdutoDTOMapper;

@Service
public class ProdutoService {

    private static final Logger LOG = LoggerFactory.getLogger(ProdutoService.class);

    private final ProdutoRepository repository;

    private final ProdutoDTOMapper mapper;

    public ProdutoService(ProdutoRepository repository, ProdutoDTOMapper mapper) {
        super();
        this.repository = repository;
        this.mapper = mapper;
    }

    public ProdutoDto salvar(ProdutoDto dto) {
        Produto entidade = mapper.converterParaEntidade(dto);

        if (!verificarValidade(entidade)) {
            throw new DataValidadeInvalidaException("A data de validade deve ser após a data de fabricação");
        }
        if (repository.existsByDescricao(entidade.getDescricao())) {
            throw new ProdutoDuplicadoException("Descrição do produto já existe");
        }

        return mapper.converterParaDto(repository.save(entidade));
    }

    public ProdutoDto atualizar(ProdutoDto dto) {
        Produto entidade = mapper.converterParaEntidade(dto);

        if (repository.existsByDescricaoAndIdNot(entidade.getDescricao(), entidade.getId())) {
            throw new ProdutoDuplicadoException("Descrição do produto já existe");
        }

        return mapper.converterParaDto(repository.save(entidade));
    }

    public ProdutoDto buscarPorId(Integer id) {
        return repository.findById(id).map(mapper::converterParaDto)
                        .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado para o id " + id));
    }

    public ProdutoDto buscarPorDescricao(String descricao) {
        return repository.findByDescricao(descricao).map(mapper::converterParaDto)
                        .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrada para o nome " + descricao));
    }

    public List<ProdutoDto> listarTodos() {
        List<Produto> entidades = repository.findAll();
        return entidades.stream().map(mapper::converterParaDto).collect(Collectors.toList());
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
    }

    private boolean verificarValidade(Produto entidade) {
        return entidade.getDataFabricacao().before(entidade.getDataValidade());
    }
}
