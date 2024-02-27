package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ProdutoTipoRepository;

@Service
public class ProdutoTipoService {

    private static final Logger LOG = LoggerFactory.getLogger(ProdutoTipoService.class);

    private ProdutoTipoRepository repository;
    private ProdutoConversaoService conversao;

    public ProdutoTipoService(ProdutoTipoRepository repository) {
        super();
        this.repository = repository;
    }

    public ProdutoDto salvar(ProdutoDto dto) {
        Produto entidade = conversao.converterParaEntidade(dto);
        repository.save(entidade);
        LOG.info("Salvo Produto {}", entidade.getDescricao());
        return conversao.converterParaDto(entidade);
    }

    public ProdutoDto buscarPorId(Integer id) throws NotFoundException {
        Produto entidade = repository.findById(id).orElseThrow(() -> new NotFoundException());
        return conversao.converterParaDto(entidade);
    }

    public Produto buscarPorFabricante(String fabricante) {
        return repository.findByFabricante(fabricante).orElseGet(() -> {
            LOG.error("Produto não encontrado para o fabricante {}.", fabricante);
            return null;
        });
    }

    public Produto buscarPorDataFabricacao(LocalDate dataFabricacao) {
        return repository.findByDataFabricacao(dataFabricacao).orElseGet(() -> {
            LOG.error("Produto não encontrado para data de fabricacao {}.", dataFabricacao);
            return null;
        });
    }

    public Produto buscarPorDataValidade(LocalDate dataValidade) {
        return repository.findByDataValidade(dataValidade).orElseGet(() -> {
            LOG.error("Produto não encontrado para data de validade {}.", dataValidade);
            return null;
        });
    }

    public List<ProdutoDto> listarTodos() {
        List<Produto> entidades = repository.findAll();
        return entidades.stream().map(entidade -> conversao.converterParaDto(entidade)).collect(Collectors.toList());
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("Produto deletado com sucesso");
    }

    public Produto inserir(ProdutoDto dto) {
        Produto entidade = conversao.converterParaEntidade(dto);
        Produto produtoSalvo = repository.save(entidade);
        if (produtoSalvo == null) {
            throw new RuntimeException("Falha ao salvar o produto");
        }
        return produtoSalvo;
    }

    public ProdutoDto atualizar(ProdutoDto dto) {
        Produto entidade = conversao.converterParaEntidade(dto);
        return conversao.converterParaDto(repository.save(entidade));
    }

}