package com.agrotis.trainees.crud.service;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.Produto;
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
		subtrairValorTotalCabecalho(item);
		subtrairEstoqueProduto(item);
		repository.deletar(item.getId());
	}
	
	private ItemNota criar(ItemNota item) {
		calcularValorTotal(item);
		adicionarValorTotalCabecalho(item);
		adicionarEstoqueProduto(item);
		return repository.salvar(item);
	}
	
	private ItemNota atualizar(ItemNota item) {
		calcularValorTotal(item);
		atualizarValorTotalCabecalho(item);
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
	
	// TODO: REFATORAR TODOS OS METODOS ABAIXO
	
	private void adicionarValorTotalCabecalho(ItemNota item) {
		CabecalhoNota cabecalho = item.getCabecalhoNota();
		BigDecimal valorTotalItem = item.getValorTotal();
		BigDecimal valorTotalCabecalho = cabecalho.getValorTotal();
		String nomeClasseCabecalho = cabecalho.getClass().getSimpleName();
		LOGGER.info("Somando {} a {} que possui valor total {}", 
				valorTotalItem, nomeClasseCabecalho, valorTotalCabecalho);
		cabecalho.setValorTotal(valorTotalCabecalho.add(valorTotalItem));
		cabecalhoService.salvar(cabecalho);
		LOGGER.info("Atualizado valor total de {} com sucesso. ValorTotal = {}",
				nomeClasseCabecalho, cabecalho.getValorTotal());
	}
	
	private void atualizarValorTotalCabecalho(ItemNota item) {
		CabecalhoNota cabecalho = item.getCabecalhoNota();
		BigDecimal antigoValorTotalItem = repository.getValorTotal(item.getId());
		BigDecimal novoValorTotalItem = item.getValorTotal();
		BigDecimal diferencaItem = antigoValorTotalItem.subtract(novoValorTotalItem);
		BigDecimal valorTotalCabecalho = cabecalho.getValorTotal();
		String nomeClasseCabecalho = cabecalho.getClass().getSimpleName();
		LOGGER.info("Subtraindo {} a {} que possui valor total {}", 
				diferencaItem, nomeClasseCabecalho, valorTotalCabecalho);
		cabecalho.setValorTotal(valorTotalCabecalho.subtract(diferencaItem));
		cabecalhoService.salvar(cabecalho);
		LOGGER.info("Atualizado valor total de {} com sucesso. ValorTotal = {}",
				nomeClasseCabecalho, cabecalho.getValorTotal());
	}
	
	private void subtrairValorTotalCabecalho(ItemNota item) {
		CabecalhoNota cabecalho = item.getCabecalhoNota();
		BigDecimal valorTotalItem = item.getValorTotal();
		BigDecimal valorTotalCabecalho = cabecalho.getValorTotal();
		String nomeClasseCabecalho = cabecalho.getClass().getSimpleName();
		LOGGER.info("Subtraindo {} a {} que possui valor total {}", 
				valorTotalItem, nomeClasseCabecalho, valorTotalCabecalho);
		cabecalho.setValorTotal(valorTotalCabecalho.subtract(valorTotalItem));
		cabecalhoService.salvar(cabecalho);
		LOGGER.info("Atualizado valor total de {} com sucesso. ValorTotal = {}",
				nomeClasseCabecalho, cabecalho.getValorTotal());
	}
	
	private void subtrairEstoqueProduto(ItemNota item) {
		Integer quantidadeItem = item.getQuantidade();
		String tipoNota = item.getCabecalhoNota().getTipo().getNome();
		Produto produto = item.getProduto();
		Integer estoqueResultante;
		String nomeClasseProduto = produto.getClass().getSimpleName();
		if (tipoNota.equalsIgnoreCase("entrada")) {
			LOGGER.info("Subtraindo {} a {} que possui em estoque {}", 
					quantidadeItem, nomeClasseProduto, produto.getEstoque());
			estoqueResultante = produto.getEstoque() - quantidadeItem;
		} else {
			LOGGER.info("Somando {} a {} que possui em estoque {}", 
					quantidadeItem, nomeClasseProduto, produto.getEstoque());
			estoqueResultante = produto.getEstoque() + quantidadeItem;
		}
		produto.setEstoque(estoqueResultante);
		produtoService.salvar(produto);
		LOGGER.info("Atualizado estoque de {} com sucesso. Estoque = {}", 
				nomeClasseProduto, produto.getEstoque());
	}
	
	private void adicionarEstoqueProduto(ItemNota item) {
		Integer quantidadeItem = item.getQuantidade();
		String tipoNota = item.getCabecalhoNota().getTipo().getNome();
		Produto produto = item.getProduto();
		String nomeClasseProduto = produto.getClass().getSimpleName();
		Integer estoqueResultante;
		if (tipoNota.equalsIgnoreCase("entrada")) {
			LOGGER.info("Somando {} a {} que possui em estoque {}", 
					quantidadeItem, nomeClasseProduto, produto.getEstoque());
			estoqueResultante = produto.getEstoque() + quantidadeItem;
		} else {
			LOGGER.info("Subtraindo {} a {} que possui em estoque {}", 
					quantidadeItem, nomeClasseProduto, produto.getEstoque());
			estoqueResultante = produto.getEstoque() - quantidadeItem;
		}
		produto.setEstoque(estoqueResultante);
		produtoService.salvar(produto);
		LOGGER.info("Atualizado estoque de {} com sucesso. Estoque = {}", 
				nomeClasseProduto, produto.getEstoque());
	}
	
	private void atualizarEstoqueProduto(ItemNota item) {
		Integer quantidadeNova = item.getQuantidade();
		Integer quantidadeAntiga = produtoService.pegarEstoque(item.getId());
		Integer diferencaQuantidade = quantidadeNova - quantidadeAntiga;
		String tipoNota = item.getCabecalhoNota().getTipo().getNome();
		Produto produto = item.getProduto();
		String nomeClasseProduto = produto.getClass().getSimpleName();
		Integer estoqueResultante;
		if (tipoNota.equalsIgnoreCase("entrada")) {
			LOGGER.info("Somando {} a {} que possui em estoque {}", 
					diferencaQuantidade, nomeClasseProduto, produto.getEstoque());
			estoqueResultante = produto.getEstoque() + diferencaQuantidade;
		} else {
			LOGGER.info("Subtraindo {} a {} que possui em estoque {}", 
					diferencaQuantidade, nomeClasseProduto, produto.getEstoque());
			estoqueResultante = produto.getEstoque() - diferencaQuantidade;
		}
		produto.setEstoque(estoqueResultante);
		produtoService.salvar(produto);
		LOGGER.info("Atualizado estoque de {} com sucesso. Estoque = {}", 
				nomeClasseProduto, produto.getEstoque());
	}
}
