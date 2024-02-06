package com.agrotis.trainees.crud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ProdutoRepository;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;

@Service
public class ProdutoService {

    private static final Logger LOG = LoggerFactory.getLogger(ProdutoService.class);

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    public List<Produto> buscarTodos() {
        return repository.findAll();
    }

    public Produto buscaPeloId(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada com o ID: " + id));
    }

    public Produto atualizar(Integer id, Produto produto) {
        return repository.findById(id).map(produtoExistente -> {
            produtoExistente.setDescricao(produto.getDescricao());
            produtoExistente.setFabricante(produto.getFabricante());
            produtoExistente.setQuantidadeEstoque(produto.getQuantidadeEstoque());
            produtoExistente.setDataValidade(produto.getDataValidade());
            produtoExistente.setDataFabricacao(produto.getDataFabricacao());
            return repository.save(produto);
        }).orElseThrow(() -> {
            LOG.info("Não foi possível encontrar o produto pelo ID {}", id);
            return new EntidadeNaoEncontradaException("Produto com o ID " + id + " não encontrado");
        });
    }

    public void deletarPorId(Integer id) {
        repository.findById(id).map(produto -> {
            repository.deleteById(id);
            LOG.info("Deletado com sucesso");
            return produto;
        }).orElseThrow(() -> new EntidadeNaoEncontradaException("Produto com o ID " + id + " não encontrado"));
    }
}
