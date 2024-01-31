package com.agrotis.trainees.crud.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;


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
	public ParceiroNegocio buscarPorNome(String nome) {
		return repository.findByNome(nome).orElseGet(() -> {
			LOG.error("Nota não encontrada para o nome {}.", nome);
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

	
	public ParceiroNegocio update(Integer id, ParceiroNegocio parceiro) {
		repository.findById(id).orElseGet(() -> {
			LOG.error("Parceiro de Negócio não encontrado para o Id {}.", parceiro.getNome());
			return null;
		});
		return repository.save(parceiro);
		
		
	}
	
}
