package com.agrotis.trainees.crud.service;

import java.lang.System.Logger;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.NotaFiscalC;
import com.agrotis.trainees.crud.repository.NotaFiscalCRepository;

@Service
public class NotaFiscalCService {

	private static final Logger LOG = (Logger) LoggerFactory.getLogger(NotaFiscalCService.class);

	private final NotaFiscalCRepository repository;

	public NotaFiscalCService(NotaFiscalCRepository repository) {
		this.repository = repository;

	}

	public NotaFiscalC salvar(NotaFiscalC entidade) {
		return repository.save(entidade);
	}

	
	}


