package com.agrotis.trainees.crud.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;

@Service
public class NotaFiscalService {

	private static final Logger LOG = LoggerFactory
			.getLogger(ParceiroNegocioService.class);
	
	private final NotaFiscalRepository repository;

	public NotaFiscalService(NotaFiscalRepository repository) {
		super();
		this.repository = repository;
	}
	
	public NotaFiscal salvar(NotaFiscal entidade) {
		return repository.save(entidade);
	}

	public NotaFiscal buscarPorId(Integer id) {
		return repository.findById(id).orElseGet(() -> {
			LOG.error("Nota Fiscal não encontrada para id {}.", id);
			return null;
		});
	}
	
	public NotaFiscal buscarPorNotaFiscalTipo(NotaFiscalTipo tipoNota) {
		return repository.findByNotaFiscalTipo(tipoNota).orElseGet(() -> {
			LOG.error("Nota Fiscal não encontrada para o tipo de {}.", tipoNota);
			return null;
		});
	}
	
	public NotaFiscal buscarPorParceiroNegocio(ParceiroNegocio parceiroNegocio) {
	    return repository.findByParceiroNegocio(parceiroNegocio).orElseGet(() -> {
	        LOG.error("Nota Fiscal não encontrada para o parceiro {}.", parceiroNegocio);
	        return null;
	    });
	}
	
	public NotaFiscal buscarPorNumero(Integer numeroNota) {
		return repository.findByNumeroNota(numeroNota).orElseGet(() -> {
			LOG.error("Nota Fiscal não encontrada para o número {}.", numeroNota);
			return null;
		});
	}
	
	public NotaFiscal buscarPorData(LocalDate dataNota) {
		return repository.findByDataNota(dataNota).orElseGet(() -> {
			LOG.error("Nota Fiscal não encontrada para a data de {}.", dataNota);
			return null;
		});
	}
	
	public List<NotaFiscal> listarTodos() {
		return repository.findAll();
	}
	
	public void deletarPorId(Integer id){
		repository.deleteById(id);
		LOG.info("Deletado com sucesso");
	}
	
}
