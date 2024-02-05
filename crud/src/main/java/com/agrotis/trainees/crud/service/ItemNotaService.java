package com.agrotis.trainees.crud.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.repository.notafiscal.item.ItemNotaRepository;

@Service
public class ItemNotaService {
	
	private ItemNotaRepository repository;
	
	@Autowired
	public ItemNotaService(ItemNotaRepository itemRepository) {
		this.repository = itemRepository;
	}
	
	public ItemNota salvar(ItemNota item) {
		return repository.salvar(item);
	}
	
	public ItemNota buscar(long id) {
		return repository.buscarPor(id);
	}
		
	public List<ItemNota> buscarTodos() {
		return repository.buscarTodos();
	}
	
	public void deletar(long id) {
		repository.deletar(id);
	}
	
	public BigDecimal calcularValorTotal(CabecalhoNota cabecalho) {
		return repository.calcularValorTotal(cabecalho);
	}
}
