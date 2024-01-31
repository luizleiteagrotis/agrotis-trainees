package com.agrotis.trainees.crud.service;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParceiroNegocioService {

	private static final Logger LOG = LoggerFactory
			.getLogger(ParceiroNegocioService.class);
	
	private final ParceiroNegocioRepository repository;

	public ParceiroNegocioService(ParceiroNegocioRepository repository) {
		super();
		this.repository = repository;
	}
	
	public ParceiroNegocio salvar(ParceiroNegocio entidade) {
		return repository.save(entidade);
	}

	public ParceiroNegocio buscarPorId(Integer id) {
		return repository.findById(id).orElseGet(() -> {
			LOG.error("Parceiro de negócio não encontrado para id {}.", id);
			return null;
		});
	}
	
	public ParceiroNegocio buscarPorInscricaoFiscal(String inscricaoFiscal) {
		return repository.findByInscricaoFiscal(inscricaoFiscal).orElseGet(() -> {
			LOG.error("Parceiro de negócio não encontrado para a inscrição fiscal {}.", inscricaoFiscal);
			return null;
		});
	}
	
	public ParceiroNegocio buscarPorNome(String nome) {
		return repository.findByNome(nome).orElseGet(() -> {
			LOG.error("Parceiro de negócio não encontrado pelo nome {}.", nome);
			return null;
		});
	}
	
	
	public void deletarPorId(Integer id){
		repository.deleteById(id);
		LOG.info("Deletado com sucesso");
	}

	public List<ParceiroNegocio> listarTodos() {
		return repository.findAll();
	}
	
}
