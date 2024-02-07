package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.DataValidadeInvalidaException;
import com.agrotis.trainees.crud.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private static final Logger LOG = LoggerFactory.getLogger(ProdutoService.class);

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        super();
        this.repository = repository;
    }

    public Produto salvar(Produto entidade) {
        if (!verificarValidade(entidade)) {
            throw new DataValidadeInvalidaException("A data de validade deve ser após a data de fabricação");
        }
        return repository.save(entidade);
    }

    public Produto buscarPorId(Integer id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Produto não encontrado para id {}. ", id);
            return null;
        });
    }

    public Produto buscarPorDescricao(String descricao) {
        return repository.findByDescricao(descricao).orElseGet(() -> {
            LOG.error("Produto não encontrado para descricao {}. ", descricao);
            return null;
        });
    }

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("Produto deletado com sucesso");
    }

    private boolean verificarValidade(Produto entidade) {
        return entidade.getDataFabricacao().before(entidade.getDataValidade());
    }
}
