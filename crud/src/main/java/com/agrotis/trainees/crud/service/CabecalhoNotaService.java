package com.agrotis.trainees.crud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;

@Service
public class CabecalhoNotaService {

	private static final Logger LOG = LoggerFactory.getLogger(CabecalhoNotaService.class);

	private final NotaFiscalRepository repository;

	public CabecalhoNotaService(NotaFiscalRepository repository) {
		this.repository = repository;
	}

	public CabecalhoNota salvar(CabecalhoNota entidade) {
		return repository.save(entidade);
	}

	public CabecalhoNota buscarPorId(Integer id) {
		return repository.findById(id).orElseGet(() -> {
			LOG.error("Nota não encontrada para id {}.", id);
			return null;
		});
	}

	public CabecalhoNota atualizar(Integer id, CabecalhoNota negocio) {
		CabecalhoNota byId = repository.findById(id).orElseGet(() -> {
			LOG.info("Não foi possível encontrar a nota fiscal pelo ID {}", id);
			return null;
		});
		return repository.save(byId);
	}

	public List<CabecalhoNota> listarTodos() {
		return repository.findAll();
	}

	public void deletarPorId(Integer id) {
		repository.deleteById(id);
		LOG.info("Deletado com sucesso");
	}

}
