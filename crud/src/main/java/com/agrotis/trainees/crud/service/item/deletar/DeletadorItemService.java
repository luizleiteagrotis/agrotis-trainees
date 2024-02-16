package com.agrotis.trainees.crud.service.item.deletar;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.TipoNota;
import com.agrotis.trainees.crud.repository.cabecalho.CabecalhoNotaRepository;
import com.agrotis.trainees.crud.repository.item.ItemNotaRepository;
import com.agrotis.trainees.crud.repository.produto.ProdutoRepository;

@Component
public class DeletadorItemService {
	
	private CabecalhoNotaRepository CABECALHO_REPOSITORY;
	private ProdutoRepository PRODUTO_REPOSITORY;
	private ItemNotaRepository ITEM_REPOSITORY;
	private ItemNota item;
	
	@Autowired
	public DeletadorItemService(CabecalhoNotaRepository cabecalhoRepository, ProdutoRepository produtoRepository,
			ItemNotaRepository itemRepository) {
		this.CABECALHO_REPOSITORY = cabecalhoRepository;
		this.PRODUTO_REPOSITORY = produtoRepository;
		this.ITEM_REPOSITORY = itemRepository;
	}

	@Transactional(readOnly = false)
	public void deletar(Long idItem) {
		buscarItem(idItem);
		atualizarValorCabecalho();
		atualizarEstoqueProduto();
		deletarItem();
	}
	
	private void buscarItem(Long idItem) {
		item = ITEM_REPOSITORY.buscarPor(idItem);
	}
	
	private void atualizarValorCabecalho() {
		BigDecimal valorTotalItem = item.getValorTotal();
		CabecalhoNota cabecalho = item.getCabecalhoNota();
		TipoNota tipoCabecalho = cabecalho.getTipo();
		BigDecimal atualValorCabecalho = cabecalho.getValorTotal();
		BigDecimal novoValorCabecalho;
		if (tipoCabecalho == TipoNota.ENTRADA) {
			novoValorCabecalho = atualValorCabecalho.subtract(valorTotalItem);
		} else {
			novoValorCabecalho = atualValorCabecalho.add(valorTotalItem);
		}
		cabecalho.setValorTotal(novoValorCabecalho);
		CABECALHO_REPOSITORY.salvar(cabecalho);
	}
	
	private void atualizarEstoqueProduto() {
		Integer quantidadeItem = item.getQuantidade();
		CabecalhoNota cabecalho = item.getCabecalhoNota();
		TipoNota tipoCabecalho = cabecalho.getTipo();
		Produto produto = item.getProduto();
		Integer novoEstoque = produto.getEstoque();
		if (tipoCabecalho == TipoNota.ENTRADA) {
			novoEstoque -= quantidadeItem;
		} else {
			novoEstoque += quantidadeItem;
		}
		produto.setEstoque(novoEstoque);
		PRODUTO_REPOSITORY.salvar(produto);
	}
	
	private void deletarItem() {
		ITEM_REPOSITORY.deletar(item.getId());
	}
}
