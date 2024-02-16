package com.agrotis.trainees.crud.service.item.atualizar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.agrotis.trainees.crud.dto.item.ItemAtualizacaoDto;
import com.agrotis.trainees.crud.dto.item.ItemRetornoDto;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.mapper.item.ItemMapper;
import com.agrotis.trainees.crud.repository.item.ItemNotaRepository;

@Component
public class AtualizadorItemService {
	
	private final ItemMapper MAPPER;
	private final ItemNotaRepository ITEM_REPOSITORY;
	private final AtualizadorCabecalhoItem ATUALIZADOR_CABECALHO;
	private final AtualizadorProdutoItem ATUALIZADOR_PRODUTO;
	private final AtualizadorItem ATUALIZADOR_ITEM;
	private ItemNota item;
	private ItemAtualizacaoDto atualizacaoDto;
	
	@Autowired
	public AtualizadorItemService(ItemMapper mapper, 
			ItemNotaRepository itemRepository, 
			AtualizadorCabecalhoItem atualizarCabecalho,
			AtualizadorProdutoItem atualizarProduto,
			AtualizadorItem atualizadorItem) {
		MAPPER = mapper;
		ITEM_REPOSITORY = itemRepository;
		ATUALIZADOR_CABECALHO = atualizarCabecalho;
		ATUALIZADOR_PRODUTO = atualizarProduto;
		ATUALIZADOR_ITEM = atualizadorItem;
	}
	
	@Transactional(readOnly = false)
	public ItemRetornoDto atualizar(ItemAtualizacaoDto atualizacaoDto) {
		this.atualizacaoDto = atualizacaoDto;
		buscarItem();
		atualizarValorCabecalho();
		atualizarEstoqueProduto();
		atualizarValorItem(); 
		return MAPPER.converterParaDto(item);
	}
	
	private void buscarItem() {
		Long idItem = atualizacaoDto.getId();
		item = ITEM_REPOSITORY.buscarPor(idItem);
	}
	
	private void atualizarValorCabecalho() {
		ATUALIZADOR_CABECALHO.atualizarValorTotal(item, atualizacaoDto);
	}
	
	private void atualizarEstoqueProduto() {
		ATUALIZADOR_PRODUTO.atualizarEstoque(item, atualizacaoDto);
	}
	
	private void atualizarValorItem() {
		ATUALIZADOR_ITEM.atualizarValor(item, atualizacaoDto);
	}
}
