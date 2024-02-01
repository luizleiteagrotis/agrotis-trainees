package com.agrotis.trainees.crud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ProdutoTipoRepository;

@Service
public class ProdutoTipoService {

    private static final Logger LOG = LoggerFactory.getLogger(ProdutoTipoService.class);

    private ProdutoTipoRepository repository;

    public ProdutoTipoService(ProdutoTipoRepository repository) {
        super();
        this.repository = repository;
    }

    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }
    public Produto buscarPorId(Integer id) {
		return repository.findById(id).orElseGet(() -> {
			LOG.error("Produto não encontrado para id {}.", id);
			return null;
		});
	}
	
	public Produto buscarPorDescricao(String descricao) {
		return repository.findBydescricao(descricao).orElseGet(() -> {
			LOG.error("Parceiro não encontrado para a descrição {}.", descricao);
			return null;
		});
	}
	
	public List<Produto> listarTodos() {
	return repository.findAll();
	}
	
	public void deletarProdutoPorId(Integer id){
		repository.deleteById(id);
		LOG.info("Deletado com sucesso");
	}
}
