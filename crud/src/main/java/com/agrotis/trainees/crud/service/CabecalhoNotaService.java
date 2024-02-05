package com.agrotis.trainees.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.repository.notafiscal.cabecalho.CabecalhoNotaRepository;

@Service
public class CabecalhoNotaService {
	
	private CabecalhoNotaRepository repository;
	
	@Autowired
	public CabecalhoNotaService(CabecalhoNotaRepository repository) {
		this.repository = repository;
	}
	
	public CabecalhoNota salvar(CabecalhoNota cabecalho) {
		return repository.salvar(cabecalho);
	}
	
	public CabecalhoNota buscar(long id) {
		return repository.buscarPor(id);
	}

	public List<CabecalhoNota> buscarTodos() {
		return repository.buscarTodos();
	}

	public void deletar(long id) {
		repository.deletar(id);
	}
}
