package com.agrotis.trainees.crud.service;

import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;

@Service
public class NotaFiscalItemService {

    private final NotaFiscalItemRepository repository;

	public NotaFiscalItemService(NotaFiscalItemRepository repository) {
		this.repository = repository;
	
	}

	public NotaFiscalItem salvar(NotaFiscalItem entidade) {
		return repository.save(entidade);		
	}

	
}