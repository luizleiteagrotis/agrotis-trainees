package com.agrotis.trainees.crud.service;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.TipoNota;
import com.agrotis.trainees.crud.repository.item.ItemNotaRepository;

@Service
public class ItemNotaService {
	
	private ItemNotaRepository repository;
	private CabecalhoNotaService cabecalhoService;
	private ProdutoService produtoService;
	private final Logger LOGGER;
	
	@Autowired
	public ItemNotaService(ItemNotaRepository itemRepository, 
			CabecalhoNotaService cabecalhoService,
			ProdutoService produtoService) {
		this.repository = itemRepository;
		this.cabecalhoService = cabecalhoService;
		this.produtoService = produtoService;
		LOGGER = LoggerFactory.getLogger(ItemNotaService.class);
	}
	
	public ItemNota salvar(ItemNota item) {
		if (repository.existe(item)) item = atualizar(item);
		else item = criar(item);
		return item;
	}
	 
	public ItemNota buscar(long id) {
		return repository.buscarPor(id);
	}
		
	public List<ItemNota> buscarTodos() {
		return repository.buscarTodos();
	}
	
	public void deletar(long id) {
		ItemNota item = repository.buscarPor(id);
		deletar(item);
	}
	
	public void deletar(ItemNota item) {
		retirarTotalCabecalho(item);
		retirarEstoqueProduto(item);
		repository.deletar(item.getId());
	}
	
	private ItemNota criar(ItemNota item) {
		calcularValorTotal(item);
		adicionarTotalCabecalho(item);
		adicionarEstoqueProduto(item);
		return repository.salvar(item);
	}
	
	private ItemNota atualizar(ItemNota item) {
		calcularValorTotal(item);
		atualizarTotalCabecalho(item);
		atualizarEstoqueProduto(item);
		return repository.salvar(item);
	}
	
	private void calcularValorTotal(ItemNota item) {
		String nomeClasseItem = item.getClass().getSimpleName();
		BigDecimal quantidade = new BigDecimal(item.getQuantidade());
		BigDecimal precoUnitario = item.getPrecoUnitario();
		LOGGER.info("Calculando valor total de {} com id {}", nomeClasseItem, item.getId());
		item.setValorTotal(quantidade.multiply(precoUnitario));
		LOGGER.info("Calculado valor total de {} com id {}: {} x {} = {}", 
				nomeClasseItem, item.getId(), quantidade, precoUnitario, item.getValorTotal());
	}
	
	private void adicionarTotalCabecalho(ItemNota item) {
		BigDecimal comNovoTotal = calcularAdicaoTotal(item); 
		CabecalhoNota cabecalho = item.getCabecalhoNota();
		salvar(cabecalho, comNovoTotal);
		emitirLogTotalAtualizado(cabecalho);
	}
	
	private BigDecimal calcularAdicaoTotal(ItemNota item) {
		CabecalhoNota cabecalho = item.getCabecalhoNota();
		BigDecimal totalItem = item.getValorTotal();
		BigDecimal totalCabecalho = cabecalho.getValorTotal();
		String nomeCabecalho = cabecalho.getClass().getSimpleName();
		emitirLogSomaValor(totalItem, totalCabecalho, nomeCabecalho);
		return totalCabecalho.add(totalItem); 
	}
	
	private void atualizarTotalCabecalho(ItemNota item) { 
		BigDecimal comNovoTotal = calcularAtualizacaoTotal(item);
		CabecalhoNota cabecalho = item.getCabecalhoNota();
		salvar(cabecalho, comNovoTotal);
		emitirLogTotalAtualizado(cabecalho);
	}
	
	private BigDecimal calcularAtualizacaoTotal(ItemNota item) {
		BigDecimal antigoTotalItem = repository.getValorTotal(item.getId());
		BigDecimal novoTotalItem = item.getValorTotal();
		BigDecimal diferencaItem = antigoTotalItem.subtract(novoTotalItem);
		CabecalhoNota cabecalho = item.getCabecalhoNota();
		BigDecimal totalCabecalho = cabecalho.getValorTotal();
		String nomeCabecalho = cabecalho.getClass().getSimpleName();
		emitirLogSubtracaoValor(diferencaItem, totalCabecalho, nomeCabecalho); 
		return totalCabecalho.subtract(diferencaItem);
	}
	
	private void retirarTotalCabecalho(ItemNota item) {
		BigDecimal comNovoTotal = calcularRetiradaTotal(item);
		CabecalhoNota cabecalho = item.getCabecalhoNota();
		salvar(cabecalho, comNovoTotal);
		emitirLogTotalAtualizado(cabecalho);
	}
	
	private BigDecimal calcularRetiradaTotal(ItemNota item) {
		CabecalhoNota cabecalho = item.getCabecalhoNota();
		BigDecimal totalItem = item.getValorTotal();
		BigDecimal totalCabecalho = cabecalho.getValorTotal();
		String nomeCabecalho = cabecalho.getClass().getSimpleName();
		emitirLogSubtracaoValor(totalItem, totalCabecalho, nomeCabecalho);
		return totalCabecalho.subtract(totalItem);
	}
	
	private void emitirLogSomaValor(BigDecimal valorInserir, BigDecimal valorDestinatario, String nomeDestinatario) {
		LOGGER.info("Somando {} a {} que possui valor total {}", 
				valorInserir, nomeDestinatario, valorDestinatario);
	}
	
	private void emitirLogSubtracaoValor(BigDecimal valorSubtrair, BigDecimal valorDestinatario, String nomeDestinatario) {
		LOGGER.info("Subtraindo {} a {} que possui valor total {}", 
				valorSubtrair, nomeDestinatario, valorDestinatario);
	}
	
	private void emitirLogTotalAtualizado(CabecalhoNota cabecalho) {
		String nomeCabecalho = cabecalho.getClass().getSimpleName();
		BigDecimal valorTotal = cabecalho.getValorTotal();
		LOGGER.info("Atualizado valor total de {} com sucesso. ValorTotal = {}",
				nomeCabecalho, valorTotal);
	}
	
	private void salvar(CabecalhoNota cabecalho, BigDecimal comNovoTotal) {
		cabecalho.setValorTotal(comNovoTotal);
		cabecalhoService.salvar(cabecalho);;
	}
	
	private void retirarEstoqueProduto(ItemNota item) {
		Integer comEstoque = calcularRetiradaEstoque(item);
		Produto produto = item.getProduto();
		salvar(produto, comEstoque);
		emitirLogEstoqueAtualizado(produto);
	}
	
	private Integer calcularRetiradaEstoque(ItemNota item) {
		Integer quantidadeItem = item.getQuantidade();
		Produto produto = item.getProduto();
		Integer estoque;
		TipoNota tipoNota = item.getCabecalhoNota().getTipo();
		String nomeClasseProduto = produto.getClass().getSimpleName();
		if (tipoNota == TipoNota.ENTRADA) {
			emitirLogSubtracaoEstoque(quantidadeItem, produto.getEstoque(), nomeClasseProduto); 
			estoque = produto.getEstoque() - quantidadeItem;
		} else {
			emitirLogSomaEstoque(quantidadeItem, produto.getEstoque(), nomeClasseProduto);
			estoque = produto.getEstoque() + quantidadeItem;
		}
		return estoque;
	}
	
	private void adicionarEstoqueProduto(ItemNota item) {
		Integer comEstoque = calcularAdicaoEstoque(item);
		Produto produto = item.getProduto();
		salvar(produto, comEstoque);
		emitirLogEstoqueAtualizado(produto);
	}
	
	private Integer calcularAdicaoEstoque(ItemNota item) {
		Integer quantidadeItem = item.getQuantidade();
		TipoNota tipoNota = item.getCabecalhoNota().getTipo();
		Produto produto = item.getProduto();
		String nomeClasseProduto = produto.getClass().getSimpleName();
		Integer estoqueAntigo = produto.getEstoque();
		Integer estoqueNovo;
		if (tipoNota == TipoNota.ENTRADA) {
			emitirLogSomaEstoque(quantidadeItem, estoqueAntigo, nomeClasseProduto);
			estoqueNovo = produto.getEstoque() + quantidadeItem;
		} else {
			emitirLogSubtracaoEstoque(quantidadeItem, estoqueAntigo, nomeClasseProduto);
			estoqueNovo = produto.getEstoque() - quantidadeItem;
		}
		return estoqueNovo;
	}
	
	private void atualizarEstoqueProduto(ItemNota item) {
		Integer comEstoque = calcularAtualizacaoEstoque(item);
		Produto produto = item.getProduto();
		salvar(produto, comEstoque);
		emitirLogEstoqueAtualizado(produto);
	}
	
	private Integer calcularAtualizacaoEstoque(ItemNota item) {
		Integer quantidadeNova = item.getQuantidade();
		Integer quantidadeAntiga = repository.getQuantidade(item.getId());
		Integer diferencaQuantidade = quantidadeNova - quantidadeAntiga;
		TipoNota tipoNota = item.getCabecalhoNota().getTipo();
		Produto produto = item.getProduto();
		String nomeClasseProduto = produto.getClass().getSimpleName();
		Integer antigoEstoque = produto.getEstoque();
		Integer novoEstoque;
		if (tipoNota == TipoNota.ENTRADA) {
			emitirLogSomaEstoque(diferencaQuantidade, antigoEstoque, nomeClasseProduto);
			novoEstoque = antigoEstoque + diferencaQuantidade;
		} else {
			emitirLogSubtracaoEstoque(diferencaQuantidade, quantidadeAntiga, nomeClasseProduto);
			novoEstoque = antigoEstoque - diferencaQuantidade;
		}
		return novoEstoque;
	}
	
	private void emitirLogSubtracaoEstoque(Integer quantidadeSubtrair, Integer quantidadeDestinatario, String nomeDestinatario) {
		LOGGER.info("Subtraindo {} a {} que possui em estoque {}", 
				quantidadeSubtrair, nomeDestinatario, quantidadeDestinatario);
	}
	
	private void emitirLogSomaEstoque(Integer quantidadeInserir, Integer quantidadeDestinatario, String nomeDestinatario) {
		LOGGER.info("Somando {} a {} que possui em estoque {}", 
				quantidadeInserir, nomeDestinatario, quantidadeDestinatario);
	}
	
	private void emitirLogEstoqueAtualizado(Produto produto) {
		String nome = produto.getClass().getSimpleName();
		Integer estoque = produto.getEstoque();
		LOGGER.info("Atualizado estoque de {} com sucesso. Estoque = {}", nome, estoque);
	}
	
	private void salvar(Produto produto, Integer comNovoEstoque) {
		produto.setEstoque(comNovoEstoque);
		produtoService.salvar(produto);
	}
}
