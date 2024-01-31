package com.agrotis.trainees.crud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ProdutoRepository;

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
	
	public List<Produto> buscarTodos(){
		return repository.findAll();
	}
	
	public Produto buscaPeloId(Integer id) {
		return repository.findById(id).orElseGet(() -> {
			LOG.info("Não foi possível buscar pelo id {}", id);
			return null;
		});
	}
	
	public Produto atualizar(Integer id, Produto produto) {
		Produto byId = repository.findById(id).orElseGet(() -> {
			LOG.info("Não foi possível buscar pelo id {}", id);
			return null;
		});
		return repository.save(produto);
		
	}
	
	
	
	

}
