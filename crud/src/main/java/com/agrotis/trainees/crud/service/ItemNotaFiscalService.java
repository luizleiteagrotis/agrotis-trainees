package com.agrotis.trainees.crud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	  

}
