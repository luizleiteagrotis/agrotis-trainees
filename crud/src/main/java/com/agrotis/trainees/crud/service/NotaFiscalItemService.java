package com.agrotis.trainees.crud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;

@Service
public class NotaFiscalItemService {

	private static final Logger LOG = LoggerFactory
			.getLogger(NotaFiscalItemService.class);
	
	private final NotaFiscalItemRepository repository;

	public NotaFiscalItemService(NotaFiscalItemRepository repository) {
		this.repository = repository;
	}

	public NotaFiscalItem salvar(NotaFiscalItem entidade) {
		return repository.save(entidade);
	}
	
	public List<NotaFiscalItem> buscarTodos(){
		return repository.findAll();
	}
	
	public NotaFiscalItem buscarPorId(Integer id) {
		return repository.findById(id).orElseGet(() -> {
			LOG.info("Não foi possível encontrar a nota fiscal pelo ID {}", id);
			return null;
		});
	}
	
	
	
}

	

