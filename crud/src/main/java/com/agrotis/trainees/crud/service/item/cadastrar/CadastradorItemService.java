package com.agrotis.trainees.crud.service.item.cadastrar;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.agrotis.trainees.crud.dto.item.ItemCadastroDto;
import com.agrotis.trainees.crud.dto.item.ItemRetornoDto;
import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.TipoNota;
import com.agrotis.trainees.crud.mapper.item.ItemMapper;
import com.agrotis.trainees.crud.repository.cabecalho.CabecalhoNotaRepository;
import com.agrotis.trainees.crud.repository.item.ItemNotaRepository;
import com.agrotis.trainees.crud.repository.produto.ProdutoRepository;
import com.agrotis.trainees.crud.service.item.util.CalculadoraItem;

@Component
public class CadastradorItemService {
	
	private ItemMapper mapper;
	private CalculadoraItem calculadora;
	private CabecalhoNotaRepository cabecalhoRepository;
	private ProdutoRepository produtoRepository;
	private ItemNotaRepository itemRepository;
	private ItemNota item;
	
	@Autowired
	public CadastradorItemService(ItemMapper mapper, 
			CalculadoraItem calculadora,
			CabecalhoNotaRepository cabecalhoRepository,
			ProdutoRepository produtoRepository,
			ItemNotaRepository itemRepository) {
		this.mapper = mapper;
		this.calculadora = calculadora;
		this.cabecalhoRepository = cabecalhoRepository;
		this.produtoRepository = produtoRepository;
		this.itemRepository = itemRepository;
	}
	
	@Transactional(readOnly = false)
	public ItemRetornoDto cadastrar(ItemCadastroDto cadastroDto) {
		item = mapper.converterParaEntidade(cadastroDto);
		atualizarValorTotalItem();
		atualizarValorTotalCabecalho();
		atualizarEstoqueProduto();
		return mapper.converterParaDto(item);
	}
	
	private void atualizarValorTotalItem() {
		Integer quantidade = item.getQuantidade();
		BigDecimal precoUnitario = item.getPrecoUnitario();
		BigDecimal novoValorTotal = calculadora.calcularValorTotal(quantidade, precoUnitario);
		item.setValorTotal(novoValorTotal);
		itemRepository.salvar(item);
	}
	
	private void atualizarValorTotalCabecalho() {
		CabecalhoNota cabecalho = item.getCabecalhoNota();
		BigDecimal valorCabecalho = cabecalho.getValorTotal();
		BigDecimal valorItem = item.getValorTotal();
		BigDecimal novoValorCabecalho = valorCabecalho.add(valorItem);
		cabecalho.setValorTotal(novoValorCabecalho);
		cabecalhoRepository.salvar(cabecalho);
	} 
	
	private void atualizarEstoqueProduto() {
		Produto produto = item.getProduto();
		Integer produtoEstoque = produto.getEstoque();
		Integer itemQuantidade = item.getQuantidade();
		CabecalhoNota cabecalho = item.getCabecalhoNota();
		TipoNota tipoCabecalho = cabecalho.getTipo();
		Integer novoEstoque;
		if (tipoCabecalho == TipoNota.ENTRADA) {
			novoEstoque = produtoEstoque + itemQuantidade;
		} else {
			novoEstoque = produtoEstoque - itemQuantidade;
		}
		produto.setEstoque(novoEstoque);
		produtoRepository.salvar(produto);
	}
}
