package com.agrotis.trainees.crud.service;


import com.agrotis.trainees.crud.entity.ParceiroNegocios;
import com.agrotis.trainees.crud.repository.ParceiroNegociosRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParceiroNegociosService {

	private static final Logger LOG = LoggerFactory
			.getLogger(ParceiroNegociosService.class);
	
	private final ParceiroNegociosRepository repository;

	public ParceiroNegociosService(ParceiroNegociosRepository repository) {
		super();
		this.repository = repository;
	}
	
	//create e update 
	public ParceiroNegocios salvar(ParceiroNegocios entidade) {
		if(repository.existsByInscricaoFiscal(entidade.getInscricaoFiscal())) {
			
			return entidade;
		}
		return repository.save(entidade);
	}
	
	public ParceiroNegocios buscarPorId(Integer id) {
		return repository.findById(id).orElseGet(() -> {
			LOG.error("Nota não encontrada para o id {}. ", id);
			return null;
		});
	}
	
	public ParceiroNegocios buscarPorNome(String nome) {
		return repository.findByNome(nome).orElseGet(() -> {
			LOG.error("Nota não encontrada para o nome {}. ", nome);
			return null;
		});
	}
	
	public ParceiroNegocios buscarPorInscricaoFiscal(String inscricaoFiscal) {
		return repository.findByInscricaoFiscal(inscricaoFiscal).orElseGet(() -> {
			LOG.error("Nota não encontrada para a inscrição {}.", inscricaoFiscal);
			return null;
		});
	}
	
	public void deletarPorId(Integer id) {
		repository.deleteById(id);
		LOG.info("Deletado com sucesso");
	}
	
	public List<ParceiroNegocios> listarTodos(){
		return repository.findAll();
	}
	
	
}
