package com.agrotis.trainees.crud.service.item.atualizar;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.dto.item.ItemAtualizacaoDto;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.repository.item.ItemNotaRepository;
import com.agrotis.trainees.crud.service.item.util.CalculadoraItem;

@Component
class AtualizadorItem {

	private CalculadoraItem CALCULADORA;
	private ItemNotaRepository ITEM_REPOSITORY;
	
	@Autowired
	public AtualizadorItem(CalculadoraItem calculadora, ItemNotaRepository itemRepository) {
		this.CALCULADORA = calculadora;
		this.ITEM_REPOSITORY = itemRepository;
	}

	public void atualizarValor(ItemNota item, ItemAtualizacaoDto atualizacaoDto) {
		if (atualizacaoDto.getQuantidade() != null) {
			item.setQuantidade(atualizacaoDto.getQuantidade());
		}
		if (atualizacaoDto.getPrecoUnitario() != null) {
			item.setPrecoUnitario(atualizacaoDto.getPrecoUnitario());
		}
		Integer quantidadeItem = item.getQuantidade();
		BigDecimal precoUnitarioItem = item.getPrecoUnitario();
		BigDecimal novoValorTotal = CALCULADORA.calcularValorTotal(quantidadeItem, precoUnitarioItem);
		item.setValorTotal(novoValorTotal);
		ITEM_REPOSITORY.salvar(item);
	}
}
