package com.agrotis.trainees.crud.service.item.atualizar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.dto.item.ItemAtualizacaoDto;
import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.TipoNota;
import com.agrotis.trainees.crud.repository.produto.ProdutoRepository;

@Component
class AtualizadorProdutoItem {
	
	private final ProdutoRepository PRODUTO_REPOSITORY;
	private ItemNota item;
	private ItemAtualizacaoDto itemAtualizacaoDto;
	private Integer diferencaQuantidadeItem;
	private Integer novoEstoqueProduto;
	
	@Autowired
	public AtualizadorProdutoItem(ProdutoRepository produtoRepository) {
		PRODUTO_REPOSITORY = produtoRepository;
	}
	
	public void atualizarEstoque(ItemNota item, ItemAtualizacaoDto itemAtualizacaoDto) {
		this.item = item;
		this.itemAtualizacaoDto = itemAtualizacaoDto;
		calcularDiferencaQuantidadeItem();
		calcularNovoEstoqueProduto();
		atualizarEstoqueProduto();
	}

	private void calcularDiferencaQuantidadeItem() {
		Integer atualQuantidadeItem = item.getQuantidade();
		Integer novaQuantidadeItem = pegarNovaQuantidadeItem();
		diferencaQuantidadeItem = novaQuantidadeItem - atualQuantidadeItem;
	}

	private Integer pegarNovaQuantidadeItem() {
		Integer novaQuantidadeItem = itemAtualizacaoDto.getQuantidade();
		if (novaQuantidadeItem == null) {
			novaQuantidadeItem = item.getQuantidade();
		}
		return novaQuantidadeItem;
	}
	
	private void calcularNovoEstoqueProduto() {
		CabecalhoNota cabecalho = item.getCabecalhoNota();
		TipoNota tipoCabecalho = cabecalho.getTipo();
		Produto produto = item.getProduto();
		novoEstoqueProduto = produto.getEstoque();
		if (tipoCabecalho == TipoNota.ENTRADA) {
			novoEstoqueProduto += diferencaQuantidadeItem;
		} else {
			novoEstoqueProduto -= diferencaQuantidadeItem;
		}
	} 
	
	private void atualizarEstoqueProduto() {
		Produto produto = item.getProduto();
		produto.setEstoque(novoEstoqueProduto);
		PRODUTO_REPOSITORY.salvar(produto);
	}
}
