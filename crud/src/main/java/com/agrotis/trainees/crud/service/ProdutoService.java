package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ProdutoRepository;


@Service
public class ProdutoService {
	
	private static final Logger LOG = LoggerFactory
			.getLogger(ProdutoService.class);
	
	private final ProdutoRepository repository;

	public ProdutoService(ProdutoRepository repository) {
		super();
		this.repository = repository;
	}
	//Create
	public Produto salvar(Produto entidade) {
		return repository.save(entidade);	
	}

    //READ
	public Produto buscarPorId(Long id) {
		return repository.findById(id).orElseGet(() -> {
			LOG.error("Produto não encontrado para este id {}.", id);
			return null;
		});
	}
	//UPDATE

	public Produto buscarPorNome(String nomeProduto, Produto produto) {
		Produto byNome = repository.findByNome(nomeProduto).orElseGet(() -> {
			LOG.info("Não foi possível buscar pelo nome {}", nomeProduto);
			return null;
		});
	return repository.save(produto);

	}	
}