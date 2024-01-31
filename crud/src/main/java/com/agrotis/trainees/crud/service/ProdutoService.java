package com.agrotis.trainees.crud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	private final Logger LOG = LoggerFactory
			.getLogger(ProdutoService.class);
	
	private ProdutoRepository repository;
	
	@Autowired
	public ProdutoService(ProdutoRepository repository) {
		this.repository = repository;
	}
	
	public Produto salvar(Produto produto) {
		LOG.info("Tentando salvar produto");
		produto = repository.save(produto);
		LOG.info("Produto salvo com sucesso. ID: {}", produto.getId());
		return produto;
	}
	
	public Produto buscarPor(long id) {
		LOG.info("Tentando buscar produto por id {}", id);
		Produto produto = repository.findById(id).orElseGet(() -> {
			LOG.error("Produto nao encontrado. ID: {}", id);
			return null;
		});
		LOG.info("Produto encontrado com sucesso");
		return produto;
	}
	
	public List<Produto> listarTodos() {
		LOG.info("Tentando buscar todos os produtos");
		List<Produto> produtos = repository.findAll();
		LOG.info("Produtos buscados com sucesso. Quantidade: {}", produtos.size());
		return produtos;
	}
}
