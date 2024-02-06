package com.agrotis.trainees.crud.service;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.repository.item.ItemNotaRepository;

@Service
public class ItemNotaService {
	
	private ItemNotaRepository repository;
	private final Logger LOGGER;
	
	@Autowired
	public ItemNotaService(ItemNotaRepository itemRepository) {
		this.repository = itemRepository;
		LOGGER = LoggerFactory.getLogger(ItemNotaService.class);
	}
	
	public ItemNota salvar(ItemNota item) {
		atualizarValorTotal(item);
		atualizarValorTotal(item.getCabecalhoNota(), item.getValorTotal());
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
	
	private void atualizarValorTotal(ItemNota item) {
		String nomeClasse = item.getClass().getSimpleName();
		BigDecimal quantidade = new BigDecimal(item.getQuantidade());
		BigDecimal precoUnitario = item.getPrecoUnitario();
		LOGGER.info("Atualizando valor total de {} com id {}", nomeClasse, item.getId());
		item.setValorTotal(quantidade.multiply(precoUnitario));
		LOGGER.info("Atualizado valor total de {} com id {}: {} x {} = {}", 
				nomeClasse, item.getId(), quantidade, precoUnitario, item.getValorTotal());
	}
	
	private void atualizarValorTotal(CabecalhoNota cabecalho, BigDecimal valorSomar) {
		BigDecimal valorTotalResultado = cabecalho.getValorTotal().add(valorSomar);
		String cabecalhoNome = cabecalho.getClass().getSimpleName();
		LOGGER.info("Tentando somar {} a {} com valor total {}", 
				valorSomar, cabecalhoNome, cabecalho.getValorTotal());
		cabecalho.setValorTotal(cabecalho.getValorTotal().add(valorSomar));
		LOGGER.info("Valor total de {} atualizada com sucesso. Valor total = {}",
				cabecalhoNome, cabecalho.getValorTotal());
	}
}
