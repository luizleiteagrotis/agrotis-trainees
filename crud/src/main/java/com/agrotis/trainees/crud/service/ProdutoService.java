package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ProdutoRepository;

import java.util.List;

@Service
public class ProdutoService {
	
	private static final Logger LOG = LoggerFactory
			.getLogger(Produto.class);
	
	private final ProdutoRepository repository;

	public ProdutoService(ProdutoRepository repository) {
		super();
		this.repository = repository;
	}
	
	public Produto salvar(Produto entidade) {
		return repository.save(entidade);
	}
	
	public Produto buscarPorId(Integer id) {
		return repository.findById(id).orElseGet(() -> {
			LOG.error("Informações não encontradas para o id {}. ", id );
			return null;
		});
	}
	
	public Produto buscarPorDescricao(String descricao) {
		return repository.findByDescricao(descricao).orElseGet(() -> {
			LOG.error("Informações não encontradas para a descrição {}. ", descricao );
			return null;
		});
	}
	
	public void deletarPorId(Integer id) {
		repository.deleteById(id);
		LOG.info("id: {} deletado com sucesso ", id);
	}
	
	public List<Produto> listarTodos(){
		return repository.findAll();
	}
}
