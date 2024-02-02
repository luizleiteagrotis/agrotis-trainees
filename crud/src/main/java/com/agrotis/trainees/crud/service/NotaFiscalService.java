package com.agrotis.trainees.crud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.exception.NotaFiscalDuplicadaException;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;

@Service
public class NotaFiscalService {

	private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalService.class);
	
	private final NotaFiscalRepository repository;

	public NotaFiscalService(NotaFiscalRepository repository) {
		super();
		this.repository = repository;
	}
	
	public NotaFiscal salvar(NotaFiscal entidade) {
		
		if (repository.hasDuplicates(entidade.getNumero(), entidade.getNotaFiscalTipo()) ) {
			throw new NotaFiscalDuplicadaException("Nota fiscal já existe");	
		} 
		return repository.save(entidade);
	}
	
	public NotaFiscal buscarPorId(Integer id) {
		return repository.findById(id).orElseGet(() -> {
			LOG.error("Nota não encontrada para id {}", id);
			return null;
		});
	}
	
	public List<NotaFiscal> buscarPorNumero(String numero) {
		return repository.findAllByNumero(numero);
	}
	
	public List<NotaFiscal> listarTodos() {
		return repository.findAll();
	}
	
}
