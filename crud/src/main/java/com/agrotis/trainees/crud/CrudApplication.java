package com.agrotis.trainees.crud;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.notafiscal.cabecalho.CabecalhoNotaRepositoryException;
import com.agrotis.trainees.crud.service.NotaFiscalService;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;
import com.agrotis.trainees.crud.service.ProdutoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory
			.getLogger(CrudApplication.class);

	private final ParceiroNegocioService parceiroNegocioService;
	private final ProdutoService produtoService;
	private final NotaFiscalService notaFiscalService;
	
	@Autowired
	public CrudApplication(ParceiroNegocioService parceiroNegocioService, 
			ProdutoService produtoService,
			NotaFiscalService notaFiscalService) {
		this.parceiroNegocioService = parceiroNegocioService;
		this.produtoService = produtoService;
		this.notaFiscalService = notaFiscalService;
	}

	public static void main(String[] args) {
		LOG.info("Iniciado com sucesso!");
		SpringApplication.run(CrudApplication.class, args);
	}

	@Override
	public void run(String... args){
		/*
		testarParceiroNegocio();
		testarProduto();
		testarCabecalhoNota();
		testarTipoNota();
		*/
		testarItemNota();
	}

	private void testarParceiroNegocio() {
		// CREATE
		ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
		parceiroNegocio.setNome("nomeTeste");
		parceiroNegocio.setInscricaoFiscal("inscricaoTeste");
		parceiroNegocio.setEndereco("enderecoTeste");
		parceiroNegocio.setTelefone("12345");
		parceiroNegocio = parceiroNegocioService.salvar(parceiroNegocio);
		
		// READ
		ParceiroNegocio parceiroPorId = parceiroNegocioService.buscarPorId(parceiroNegocio.getId());
		
		List<ParceiroNegocio> parceiros = parceiroNegocioService.listarTodos();
		
		// UPDATE
		parceiroPorId.setNome("coxinha cabulosa");
		parceiroNegocioService.salvar(parceiroPorId);
		
		// DELETE
		parceiros.forEach((parceiro) -> {
			parceiroNegocioService.deletar(parceiro.getId());
		});
	}
	
	private void testarProduto() {
		// CREATE
		ParceiroNegocio fabricante = new ParceiroNegocio();
		fabricante.setNome("nomeTeste");
		fabricante.setInscricaoFiscal("inscricaoTeste");
		fabricante.setEndereco("enderecoTeste");
		fabricante.setTelefone("12345");
		fabricante = parceiroNegocioService.salvar(fabricante);
		LOG.info("id inserido: {}", fabricante.getId());
		Produto produto = new Produto();
		produto.setNome("maca");
		produto.setDescricao("maca vermelha muito boa, tem seu nome dentro");
		produto.setFabricante(fabricante);
		produto = produtoService.salvar(produto);
		LOG.info("id inserido: {}", produto.getId());
		
		// READ
		Produto produtoPorId = produtoService.buscarPor(produto.getId());
		List<Produto> produtos = produtoService.listarTodos();
		
		// UPDATE
		produtoPorId.setNome("moranguinho");
		produtoService.salvar(produtoPorId);
		
		// DELETE
		produtos.forEach((p) -> {
			produtoService.deletar(p.getId());
		});
		parceiroNegocioService.deletar(fabricante.getId());
	}
	
	private void testarCabecalhoNota() {
		// CREATE
		ParceiroNegocio parceiro = new ParceiroNegocio();
		parceiro.setNome("nomeTeste");
		parceiro.setInscricaoFiscal("inscricaoTeste");
		parceiro.setEndereco("enderecoTeste");
		parceiro.setTelefone("12345");
		parceiro = parceiroNegocioService.salvar(parceiro);
		
		NotaFiscalTipo tipoNota = new NotaFiscalTipo();
		tipoNota.setNome("entrada");
		notaFiscalService.salvar(tipoNota);
		
		CabecalhoNota cabecalhoNota = new CabecalhoNota();
		cabecalhoNota.setNumero(123456L);
		cabecalhoNota.setParceiro(parceiro);
		cabecalhoNota.setTipo(tipoNota);
		cabecalhoNota.setDataEmissao(LocalDate.now());
		notaFiscalService.salvar(cabecalhoNota);
		
		// READ
		CabecalhoNota cabecalhoPorId = notaFiscalService.buscarCabecalhoPor(cabecalhoNota.getId());
		
		List<CabecalhoNota> cabecalhos = notaFiscalService.buscarTodosCabecalhos();
		
		// UPDATE
		cabecalhoPorId.setNumero(111111L);
		notaFiscalService.salvar(cabecalhoPorId);
		
		// DELETE
		cabecalhos.forEach((cabecalho) -> {
			notaFiscalService.deletarCabecalho(cabecalho.getId());
		});
		
		// TESTA NUMERO E TIPO
		// TESTA CRIACAO COM O MESMO NUMERO E TIPO
		parceiro = new ParceiroNegocio();
		parceiro.setNome("nomeTeste");
		parceiro.setInscricaoFiscal("inscricaoTeste");
		parceiro.setEndereco("enderecoTeste");
		parceiro.setTelefone("12345");
		parceiro = parceiroNegocioService.salvar(parceiro);
		
		tipoNota = new NotaFiscalTipo();
		tipoNota.setNome("entrada");
		notaFiscalService.salvar(tipoNota);
		
		cabecalhoNota = new CabecalhoNota();
		cabecalhoNota.setNumero(123456L);
		cabecalhoNota.setParceiro(parceiro);
		cabecalhoNota.setTipo(tipoNota);
		cabecalhoNota.setDataEmissao(LocalDate.now());
		notaFiscalService.salvar(cabecalhoNota);
		
		cabecalhoNota = new CabecalhoNota();
		cabecalhoNota.setNumero(123456L);
		cabecalhoNota.setParceiro(parceiro);
		cabecalhoNota.setTipo(tipoNota);
		cabecalhoNota.setDataEmissao(LocalDate.now());
		try {
			notaFiscalService.salvar(cabecalhoNota);
		} catch (CabecalhoNotaRepositoryException e) {}
		
		tipoNota = new NotaFiscalTipo();
		tipoNota.setNome("saida");
		notaFiscalService.salvar(tipoNota);
		cabecalhoNota.setTipo(tipoNota);
		try {
			notaFiscalService.salvar(cabecalhoNota);
		} catch (CabecalhoNotaRepositoryException e) {}
		
		cabecalhos = notaFiscalService.buscarTodosCabecalhos();
		cabecalhos.forEach((cabecalho) -> {
			notaFiscalService.deletarCabecalho(cabecalho.getId());
		});
		List<NotaFiscalTipo> tipos = notaFiscalService.buscarTodosTipos();
		tipos.forEach((tipo) -> {
			notaFiscalService.deletarTipo(tipo.getId());
		});
		parceiroNegocioService.deletar(parceiro.getId());
	}
	
	private void testarTipoNota() {
		// CREATE
		NotaFiscalTipo tipoNota = new NotaFiscalTipo();
		tipoNota.setNome("entrada");
		notaFiscalService.salvar(tipoNota);
		
		// READ
		NotaFiscalTipo tipoNotaPorId = notaFiscalService.buscarTipoPor(tipoNota.getId());
		
		List<NotaFiscalTipo> tipos = notaFiscalService.buscarTodosTipos();
		
		// UPDATE
		tipoNotaPorId.setNome("entrada e saida");
		notaFiscalService.salvar(tipoNotaPorId);
		
		// DELETE
		tipos.forEach((tipo) -> {
			notaFiscalService.deletarTipo(tipo.getId());
		});
	}
	
	private void testarItemNota() {
		// CREATE
		ParceiroNegocio parceiro = new ParceiroNegocio();
		parceiro.setNome("nomeTeste");
		parceiro.setInscricaoFiscal("inscricaoTeste");
		parceiro.setEndereco("enderecoTeste");
		parceiro.setTelefone("12345");
		parceiro = parceiroNegocioService.salvar(parceiro);
		
		NotaFiscalTipo tipoNota = new NotaFiscalTipo();
		tipoNota.setNome("entrada");
		notaFiscalService.salvar(tipoNota);
		
		CabecalhoNota cabecalhoNota = new CabecalhoNota();
		cabecalhoNota.setNumero(123456L);
		cabecalhoNota.setParceiro(parceiro);
		cabecalhoNota.setTipo(tipoNota);
		cabecalhoNota.setDataEmissao(LocalDate.now());
		notaFiscalService.salvar(cabecalhoNota);
		
		Produto produto = new Produto();
		produto.setNome("maca");
		produto.setDescricao("maca vermelha muito boa, tem seu nome dentro");
		produto.setFabricante(parceiro);
		produto = produtoService.salvar(produto);
		
		ItemNota item = new ItemNota();
		item.setProduto(produto);
		item.setQuantidade(5);
		item.setPrecoUnitario(new BigDecimal("20.01"));
		item.setDataFabricacao(LocalDate.now());
		item.setDataValidade(LocalDate.now());
		item.setCabecalhoNota(cabecalhoNota);
		notaFiscalService.salvar(item);
		
		// READ
		ItemNota itemPorId = notaFiscalService.bucarItemPor(item.getId());
		
		List<ItemNota> itens = notaFiscalService.buscarTodosItens();
		
		//UPDATE
		itemPorId.setPrecoUnitario(new BigDecimal("1.99"));
		notaFiscalService.salvar(itemPorId);
	}
}
