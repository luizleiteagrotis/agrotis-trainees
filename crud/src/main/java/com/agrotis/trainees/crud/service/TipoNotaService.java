package com.agrotis.trainees.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.repository.tipo.TipoNotaRepository;

@Service
public class TipoNotaService {
	
	private TipoNotaRepository repository;

	@Autowired
	public TipoNotaService(TipoNotaRepository repository) {
		this.repository = repository;
	}
	
	public NotaFiscalTipo salvar(NotaFiscalTipo tipo) {
		return repository.salvar(tipo);
	}

	public NotaFiscalTipo buscar(int id) {
		return repository.buscarPor(id);
	}

	public List<NotaFiscalTipo> buscarTodos() {
		return repository.buscarTodos();
	}

	public void deletar(int id) {
		repository.deletar(id);
	}
}
