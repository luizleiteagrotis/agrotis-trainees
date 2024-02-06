package com.agrotis.trainees.crud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.repository.cabecalho.CabecalhoNotaRepository;

@Service
public class CabecalhoNotaService {
	
	private CabecalhoNotaRepository repository;
	private final Logger LOGGER;
	
	@Autowired
	public CabecalhoNotaService(CabecalhoNotaRepository repository) {
		this.repository = repository;
		LOGGER = LoggerFactory.getLogger(CabecalhoNotaService.class);
	}
	
	public CabecalhoNota salvar(CabecalhoNota cabecalho) {
		verificar(cabecalho);
		return repository.salvar(cabecalho);
	}
	
	private void verificar(CabecalhoNota cabecalho) {
		boolean naoFoiPersistido = cabecalho.getId() == null;
		boolean existeComTipoENumeroIgual = repository.existeInstanciaCom(cabecalho.getTipo(), cabecalho.getNumero());
		if (naoFoiPersistido && existeComTipoENumeroIgual) {
			String nomeClasseCabecalho = cabecalho.getClass().getSimpleName();
			String mensagemErro = "Ja existe " 
									+ nomeClasseCabecalho
									+ " com tipo " 
									+ cabecalho.getTipo().getNome()
									+ " e numero "
									+ cabecalho.getNumero();
			LOGGER.error(mensagemErro);
			throw new CabecalhoNotaServiceException(mensagemErro);
		}
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
