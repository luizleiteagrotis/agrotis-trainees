package com.agrotis.trainees.crud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.CrudApplication;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;

@Service
public class ParceiroNegocioService {
	
	private ParceiroNegocioRepository repository;
	
	private static final Logger LOG = LoggerFactory
			.getLogger(CrudApplication.class);
	
	@Autowired
	public ParceiroNegocioService(ParceiroNegocioRepository repository) {
		this.repository = repository;
	}
	
	public ParceiroNegocio salvar(ParceiroNegocio entidade) {
		return repository.save(entidade);
	}
	
	public ParceiroNegocio buscarPorId(long id) {
		
		return repository.findById(id).orElseGet(() -> {
			LOG.error("Nota não encontrada para id {}.", id);
			return null;
		});
	}
	
	public List<ParceiroNegocio> listarTodos() {
		return repository.findAll();
	}
}
