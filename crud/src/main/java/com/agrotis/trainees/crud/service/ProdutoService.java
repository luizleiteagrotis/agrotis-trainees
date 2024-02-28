package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private static final Logger LOG = LoggerFactory.getLogger(ProdutoService.class);

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto salvar(Produto entidade) {
        return repository.save(entidade);
    }

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public Produto buscarPorId(Integer id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Produto não encontrado para o id {}.", id);
            return null;
        });
    }

    public Produto buscarPorDescricao(String descricao) {
        return repository.findByDescricao(descricao).orElseGet(() -> {
            LOG.error("Produto não encontrado para a descricao {}.", descricao);
            return null;
        });
    }

    public Produto atualizar(Integer id) {
        Produto produto = buscarPorId(id);
        return repository.save(produto);
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
    }

}