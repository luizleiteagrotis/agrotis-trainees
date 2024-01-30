package com.agrotis.trainees.crud.service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.PaceiroNegocioRepository;


@Service
public class ParceiroNegocioService {
	
	private static final Logger LOG = LoggerFactory
			.getLogger(NotaFiscalTipoService.class);
	
	private final PaceiroNegocioRepository repository;
	
	public ParceiroNegocioService(PaceiroNegocioRepository repository) {
		super();
		this.repository = repository;
	}
	
	public ParceiroNegocio salvar(ParceiroNegocio entidade) {
		//TO-DO validar se a inscricao fiscal já está cadastrada
		if(entidade.getTelefone() == null) {
			return repository.save(entidade);
		}
		if(entidade.getInscricaoFiscal().length() > 14 || entidade.getInscricaoFiscal().length() < 14 || entidade.getTelefone().length() > 14) {
			LOG.error("Falha de validação: Não foi possivel salvar no banco de dados.");
	        throw new IllegalArgumentException("Invalid input data");

		}
		return repository.save(entidade);
	
	}
	
	
	
	
}
