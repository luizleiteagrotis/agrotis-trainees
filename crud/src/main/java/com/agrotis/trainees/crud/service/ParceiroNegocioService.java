package com.agrotis.trainees.crud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.CrudApplication;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.parceiro.ParceiroJpaRepository;
import com.agrotis.trainees.crud.repository.parceiro.ParceiroRepository;
import com.agrotis.trainees.crud.repository.wrapper.JpaRepositoryWrapper;

@Service
public class ParceiroNegocioService {
	
	private ParceiroRepository repository;
	
	@Autowired
	public ParceiroNegocioService(ParceiroRepository repository) {
		this.repository = repository;
	}
	
	public ParceiroNegocio salvar(ParceiroNegocio entidade) {
		return repository.salvar(entidade);
	}
	
	public ParceiroNegocio buscarPorId(long idParceiro) {
		return repository.buscarPor(idParceiro);
	}
	
	public List<ParceiroNegocio> listarTodos() {
		return repository.buscarTodos();
	}
	
	public void deletar(long idParceiro) {
		repository.deletar(idParceiro);
	}
}
