package com.agrotis.trainees.crud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.produto.ProdutoJpaRepository;
import com.agrotis.trainees.crud.repository.produto.ProdutoRepository;

@Service
public class ProdutoService {
	
	private ProdutoRepository repository;
	
	@Autowired
	public ProdutoService(ProdutoRepository repository) {
		this.repository = repository;
	}
	
	public Produto salvar(Produto produto) {
		return repository.salvar(produto);
	}
	
	public Produto buscarPor(long idProduto) {
		return repository.buscarPor(idProduto);
	}
	
	public List<Produto> listarTodos() {
		return repository.buscarTodos();
	}
	
	public void deletar(long idProduto) {
		repository.deletar(idProduto);
	}
}
