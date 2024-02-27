package com.agrotis.trainees.crud.util;

import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.repository.item.ItemNotaRepository;

@Component
public class ItemNotaFactory {
	
	private ItemNotaRepository itemRepository;
	
	public ItemNotaFactory(ItemNotaRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
	
	public ItemNota criarClone(Long idItem) {
		ItemNota item = itemRepository.buscarPor(idItem);
		return criarClone(item);
	}
	
	public ItemNota criarClone(ItemNota item) {
		ItemNota itemClone = new ItemNota();
		itemClone.setQuantidade(item.getQuantidade());
		itemClone.setPrecoUnitario(item.getPrecoUnitario());
		itemClone.setValorTotal(item.getValorTotal());
		return itemClone;
	}
}
