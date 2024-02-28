package com.agrotis.trainees.crud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.agrotis.trainees.crud.entity.CabecalhoNotaFiscal;
import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ItemNotaFiscalRepository;

public class ItemNotaFiscalService {

	  private static final Logger LOG = LoggerFactory.getLogger(ItemNotaFiscalService.class);

	  private final ItemNotaFiscalRepository repository;

	  public ItemNotaFiscalService(ItemNotaFiscalRepository repository) {
	      this.repository = repository;
	  }

	  public ItemNotaFiscal salvar(ItemNotaFiscal entidade) {
	      return repository.save(entidade);
	  }
	  
	  public List<ItemNotaFiscal> listarTodos() {
	        return repository.findAll();
	    }

	    public ItemNotaFiscal buscarPorId(Integer id) {
	        return repository.findById(id).orElseGet(() -> {
	            LOG.error("Item Nota Fiscal não encontrada para o id {}.", id);
	            return null;
	        });
	    }
	    
	    public ItemNotaFiscal buscarPorProduto(Produto produto) {
	    	return repository.findByProduto(produto.getId()).orElseGet(() -> {
	    		LOG.error("Item Nota Fiscal não encontrada para o produto {}", produto);
	    		return null;
	    	});
	    }
	    
	    public ItemNotaFiscal atualizar(Integer id) {
	    	ItemNotaFiscal nota = buscarPorId(id);
	    	return repository.save(nota);
	    } 
}
