package com.agrotis.trainees.crud.service;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.CrudException;
import com.agrotis.trainees.crud.exception.DescricaoExisteException;
import com.agrotis.trainees.crud.repository.ProdutoRepository;
import com.agrotis.trainees.crud.wrapper.ProdutoWrapper;

@Service
public class ProdutoService {

    private static final Logger LOG = LoggerFactory.getLogger(Produto.class);

    private final ProdutoRepository repository;
    private final ProdutoWrapper produtoWrapper;

    public ProdutoService(ProdutoRepository repository, ProdutoWrapper produtoWrapper) {
        super();
        this.repository = repository;
        this.produtoWrapper = produtoWrapper;
    }

    public ProdutoDto salvar(ProdutoDto dto) {
        try {
            Produto entidade = produtoWrapper.converterParaEntidade(dto);
            verificarDescricao(entidade);
            entidade = repository.save(entidade);
            return produtoWrapper.converterParaDto(entidade);
        } catch (DescricaoExisteException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    public ProdutoDto atualizar(Produto entidade) {
        if (entidade.getId() == null) {
            throw new CrudException("Obrigatório preencher o id do produto.");
        }

        Produto produto = buscarPorId(entidade.getId());

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        modelMapper.map(entidade, produto);

        try {
            verificarDescricaoEId(produto);
        } catch (DescricaoExisteException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
        return produtoWrapper.converterParaDto(repository.save(produto));
    }

    public Produto buscarPorId(Integer id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Informações não encontradas para o id {}. ", id);
            return null;
        });
    }

    public Produto buscarPorDescricao(String descricao) {
        return repository.findByDescricao(descricao).orElseGet(() -> {
            LOG.error("Informações não encontradas para a descrição {}. ", descricao);
            return null;
        });
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("id: {} deletado com sucesso ", id);
    }

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public void verificarDescricaoEId(Produto entidade) throws DescricaoExisteException {
        if (repository.existsByDescricaoAndIdNot(entidade.getDescricao(), entidade.getId()) == true) {
            throw new DescricaoExisteException("A descrição já existe");
        }

    }

    public void verificarDescricao(Produto entidade) throws DescricaoExisteException {
        if (repository.existsByDescricao(entidade.getDescricao()) == true) {
            throw new DescricaoExisteException("A descrição ja existe");
        }
    }

}
