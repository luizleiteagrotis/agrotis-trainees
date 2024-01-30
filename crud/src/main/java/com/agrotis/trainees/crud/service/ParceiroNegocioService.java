package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;

@Service
public class ParceiroNegocioService {
	
	private static final Logger LOG = LoggerFactory
			.getLogger(NotaFiscalTipoService.class);
	
	private final ParceiroNegocioRepository repository;
	
	public ParceiroNegocioService(ParceiroNegocioRepository repository) {
		this.repository = repository;
	}

	
	public ParceiroNegocio create(ParceiroNegocio negocio) {
		return repository.save(negocio);
	}

}
