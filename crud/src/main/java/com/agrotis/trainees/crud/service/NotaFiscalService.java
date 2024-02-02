package com.agrotis.trainees.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.repository.notafiscal.cabecalho.CabecalhoNotaRepository;
import com.agrotis.trainees.crud.repository.notafiscal.tipo.TipoNotaRepository;

@Service
public class NotaFiscalService {
	
	private CabecalhoNotaRepository cabecalhoRepository;
	private TipoNotaRepository tipoRepository;
	
	@Autowired
	public NotaFiscalService(CabecalhoNotaRepository cabecalhoRepository,
			TipoNotaRepository tipoRepository) {
		this.cabecalhoRepository = cabecalhoRepository;
		this.tipoRepository = tipoRepository;
	}
	
	public CabecalhoNota salvar(CabecalhoNota cabecalho) {
		return cabecalhoRepository.salvar(cabecalho);
	}
	
	public NotaFiscalTipo salvar(NotaFiscalTipo tipo) {
		return tipoRepository.salvar(tipo);
	}
	
	public CabecalhoNota buscarPor(long idCabecalho) {
		return cabecalhoRepository.buscarPor(idCabecalho);
	}
	
	public NotaFiscalTipo buscarPor(int idTipo) {
		return tipoRepository.buscarPor(idTipo);
	}
	
	public List<CabecalhoNota> buscarTodosCabecalhos() {
		return cabecalhoRepository.buscarTodos();
	}
	
	public List<NotaFiscalTipo> buscarTodosTipos() {
		return tipoRepository.buscarTodos();
	}
	
	public void deletarCabecalho(long id) {
		cabecalhoRepository.deletar(id);
	}
	
	public void deletarTipo(int id) {
		tipoRepository.deletar(id);
	}
}
