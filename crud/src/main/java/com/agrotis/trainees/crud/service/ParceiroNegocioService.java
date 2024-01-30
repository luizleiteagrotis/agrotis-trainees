package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;

@Service
public class ParceiroNegocioService {

	private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalTipoService.class);

	private final ParceiroNegocioRepository repository;

	public ParceiroNegocioService(ParceiroNegocioRepository repository) {
		this.repository = repository;
	}

	public ParceiroNegocio salvar(ParceiroNegocio negocio) {
		return repository.save(negocio);
	}

	public ParceiroNegocio buscarPorId(Integer id) {
		return repository.findById(id).orElseGet(() -> {
			LOG.info("Não foi possível buscar pelo id {}", id);
			return null;
		});
	}

	public ParceiroNegocio buscarPorNome(String nome) {
		return repository.findByNome(nome).orElseGet(() -> {
			LOG.info("Não foi possível encontrar o parceiro de negocio pelo nome {}", nome);
			return null;
		});
	}
	
	

}
