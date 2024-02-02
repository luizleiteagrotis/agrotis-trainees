package com.agrotis.trainees.crud.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;
import com.agrotis.trainees.crud.repository.ProdutoRepository;


@Service
public class NotaFiscalService {
	
private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalService.class);
	
	private final NotaFiscalRepository repository;
	
	public NotaFiscalService(NotaFiscalRepository repository) {
		super();
		this.repository = repository;
	}
	
	public NotaFiscal salvar(NotaFiscal entidade) {
		return repository.save(entidade);
	}
	
	
	


}
