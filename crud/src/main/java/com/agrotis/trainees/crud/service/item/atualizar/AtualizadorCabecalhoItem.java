package com.agrotis.trainees.crud.service.item.atualizar;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.dto.item.ItemAtualizacaoDto;
import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.TipoNota;
import com.agrotis.trainees.crud.repository.cabecalho.CabecalhoNotaRepository;
import com.agrotis.trainees.crud.service.item.util.CalculadoraItem;

@Component
class AtualizadorCabecalhoItem {
	
	private final CabecalhoNotaRepository CABECALHO_REPOSITORY;
	private final CalculadoraItem CALCULADORA_ITEM;
	private ItemNota item;
	private ItemAtualizacaoDto itemAtualizacaoDto;
	private BigDecimal diferencaValorItem;
	private BigDecimal novoValorCabecalho;
	
	@Autowired
	public AtualizadorCabecalhoItem(CabecalhoNotaRepository cabecalhoRepository, CalculadoraItem calculadoraItem) {
		CABECALHO_REPOSITORY = cabecalhoRepository;
		CALCULADORA_ITEM = calculadoraItem;
	}
	
	public void atualizarValorTotal(ItemNota item, ItemAtualizacaoDto itemAtualizacaoDto) {
		this.item = item;
		this.itemAtualizacaoDto = itemAtualizacaoDto;
		calcularDiferencaValorItem();
		calcularNovoValorCabecalho();
		atualizarCabecalho();
	}

	private void calcularDiferencaValorItem() {
		BigDecimal atualValorItem = item.getValorTotal();
		BigDecimal novoValorItem = calcularNovoValorItem();
		diferencaValorItem = novoValorItem.subtract(atualValorItem);
	}

	private BigDecimal calcularNovoValorItem() {
		Integer novaQuantidade = pegarNovaQuantidade();
		BigDecimal novoPrecoUnitario = pegarNovoPrecoUnitario();
		BigDecimal novoValorItem = CALCULADORA_ITEM.calcularValorTotal(novaQuantidade, novoPrecoUnitario);
		return novoValorItem;
	}

	private Integer pegarNovaQuantidade() {
		Integer quantidade = itemAtualizacaoDto.getQuantidade();
		if (quantidade == null) {
			quantidade = item.getQuantidade();
		}
		return quantidade;
	}
	
	private BigDecimal pegarNovoPrecoUnitario() {
		BigDecimal precoUnitario = itemAtualizacaoDto.getPrecoUnitario();
		if (precoUnitario == null) {
			precoUnitario = item.getPrecoUnitario();
		}
		return precoUnitario;
	}
	
	private void calcularNovoValorCabecalho() {
		CabecalhoNota cabecalho = item.getCabecalhoNota();
		BigDecimal atualValorCabecalho = cabecalho.getValorTotal();
		TipoNota tipoCabecalho = cabecalho.getTipo();
		if (tipoCabecalho == TipoNota.ENTRADA) {
			novoValorCabecalho = atualValorCabecalho.add(diferencaValorItem);
		} else {
			novoValorCabecalho = atualValorCabecalho.subtract(diferencaValorItem);
		}
	}
	
	private void atualizarCabecalho() {
		CabecalhoNota cabecalho = item.getCabecalhoNota();
		cabecalho.setValorTotal(novoValorCabecalho);
		CABECALHO_REPOSITORY.salvar(cabecalho);
	}
}
