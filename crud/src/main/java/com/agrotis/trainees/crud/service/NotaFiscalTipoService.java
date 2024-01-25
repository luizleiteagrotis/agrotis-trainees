package com.agrotis.trainees.crud.service;

import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.repository.NotaFiscalTipoRepository;

@Service
public class NotaFiscalTipoService {
	
	private final NotaFiscalTipoRepository repository;

	public NotaFiscalTipoService(NotaFiscalTipoRepository repository) {
		super();
		this.repository = repository;
	}
	
	public NotaFiscalTipo inserir(NotaFiscalTipo entidade) {
		
	}
	
}
