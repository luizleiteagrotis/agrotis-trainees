package com.agrotis.trainees.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.repository.notafiscal.cabecalho.CabecalhoNotaRepository;
import com.agrotis.trainees.crud.repository.notafiscal.item.ItemNotaRepository;
import com.agrotis.trainees.crud.repository.notafiscal.tipo.TipoNotaRepository;

@Service
public class NotaFiscalService {
	
	private CabecalhoNotaRepository cabecalhoRepository;
	private TipoNotaRepository tipoRepository;
	private ItemNotaRepository itemRepository;
	
	@Autowired
	public NotaFiscalService(CabecalhoNotaRepository cabecalhoRepository,
			TipoNotaRepository tipoRepository,
			ItemNotaRepository itemRepository) {
		this.cabecalhoRepository = cabecalhoRepository;
		this.tipoRepository = tipoRepository;
		this.itemRepository = itemRepository;
	}
	
	public CabecalhoNota salvar(CabecalhoNota cabecalho) {
		return cabecalhoRepository.salvar(cabecalho);
	}
	
	public NotaFiscalTipo salvar(NotaFiscalTipo tipo) {
		return tipoRepository.salvar(tipo);
	}
	
	public ItemNota salvar(ItemNota item) {
		return itemRepository.salvar(item);
	}
	
	public CabecalhoNota buscarCabecalhoPor(long id) {
		return cabecalhoRepository.buscarPor(id);
	}
	
	public NotaFiscalTipo buscarTipoPor(int id) {
		return tipoRepository.buscarPor(id);
	}
	
	public ItemNota bucarItemPor(long id) {
		return itemRepository.buscarPor(id);
	}
	
	public List<CabecalhoNota> buscarTodosCabecalhos() {
		return cabecalhoRepository.buscarTodos();
	}
	
	public List<NotaFiscalTipo> buscarTodosTipos() {
		return tipoRepository.buscarTodos();
	}
	
	public List<ItemNota> buscarTodosItens() {
		return itemRepository.buscarTodos();
	}
	
	public void deletarCabecalho(long id) {
		cabecalhoRepository.deletar(id);
	}
	
	public void deletarTipo(int id) {
		tipoRepository.deletar(id);
	}
	
	public void deletarItem(long id) {
		itemRepository.deletar(id);
	}
}
