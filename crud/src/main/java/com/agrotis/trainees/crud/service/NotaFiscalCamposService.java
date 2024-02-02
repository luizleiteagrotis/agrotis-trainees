package com.agrotis.trainees.crud.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.NotaFiscalCampos;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.repository.NotaFiscalCamposRepository;

@Service
public class NotaFiscalCamposService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private static final Logger LOG = LoggerFactory
			.getLogger(NotaFiscalCampos.class);
	
	private final NotaFiscalCamposRepository repository;
	
		
	public NotaFiscalCamposService(NotaFiscalCamposRepository repository) {
		super();
		this.repository = repository;
	}	
	
	
	public NotaFiscalCampos salvar(NotaFiscalCampos entidade) {	
			return repository.save(entidade);
	}
	
	public NotaFiscalCampos buscarPorId(Integer id) {
		return repository.findById(id).orElseGet(() -> {
			LOG.error("Informações não encontradas para o id {}", id);
			return null;
		});
	}
	
	public NotaFiscalCampos buscarPorTipoeNumero(NotaFiscalTipo tipo, Integer numero) {
		return repository.findByTipoAndNumero(tipo, numero).orElseGet(() -> {
			LOG.error("Informações não encontradas para o id {} e numero de nota {}"
					, tipo, numero);
			return null;
		});
	}
	
	public void deletarPorId(Integer id) {
		repository.deleteById(id);
		LOG.info("id: {} deletado com sucesso", id);
	}
	
	public List<NotaFiscalCampos> listarTodos(){
		return repository.findAll();}
	
	public void gerarNumero(NotaFiscalCampos notaFiscalCampos) {
		int tipo = notaFiscalCampos.getTipo().getId();
		if(notaFiscalCampos.getTipo() != null ) {			
			
			if(tipo == 1 || tipo == 2) {				
				
				Integer ultimoNumero = obterUltimoNumeroPorTipo(notaFiscalCampos.getTipo());	
				notaFiscalCampos.setNumero((ultimoNumero != null) ? ultimoNumero+1 : 1);
			}
				
		} 
	}
	
	@Transactional
	public Integer obterUltimoNumeroPorTipo(NotaFiscalTipo tipo) {
		try {
			return repository.findMaxNumeroByTipo(tipo).orElse(null);
		} catch (NoResultException e) {
			return null;
		}
	}
}
