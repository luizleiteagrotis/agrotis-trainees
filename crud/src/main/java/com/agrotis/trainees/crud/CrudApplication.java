package com.agrotis.trainees.crud;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.notafiscal.cabecalho.CabecalhoNotaRepositoryException;
import com.agrotis.trainees.crud.service.CabecalhoNotaService;
import com.agrotis.trainees.crud.service.ItemNotaService;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;
import com.agrotis.trainees.crud.service.ProdutoService;
import com.agrotis.trainees.crud.service.TipoNotaService;

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
	private final CabecalhoNotaService cabecalhoService;
	private final TipoNotaService tipoService;
	private final ItemNotaService itemService;
	
	@Autowired
	public CrudApplication(ParceiroNegocioService parceiroNegocioService, 
			ProdutoService produtoService,
			CabecalhoNotaService cabecalhoService,
			TipoNotaService tipoService,
			ItemNotaService itemService) {
		this.parceiroNegocioService = parceiroNegocioService;
		this.produtoService = produtoService;
		this.cabecalhoService = cabecalhoService;
		this.tipoService = tipoService;
		this.itemService = itemService;
	}

	public static void main(String[] args) {
		LOG.info("Iniciado com sucesso!");
		SpringApplication.run(CrudApplication.class, args);
	}

	@Override
	public void run(String... args){
		testarParceiroNegocio();
		testarProduto();
		testarCabecalhoNota();
		testarTipoNota();
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
		tipoService.salvar(tipoNota);
		
		CabecalhoNota cabecalhoNota = new CabecalhoNota();
		cabecalhoNota.setNumero(123456L);
		cabecalhoNota.setParceiro(parceiro);
		cabecalhoNota.setTipo(tipoNota);
		cabecalhoNota.setDataEmissao(LocalDate.now());
		cabecalhoService.salvar(cabecalhoNota);
		
		// READ
		CabecalhoNota cabecalhoPorId = cabecalhoService.buscar(cabecalhoNota.getId());
		
		List<CabecalhoNota> cabecalhos = cabecalhoService.buscarTodos();
		
		// UPDATE
		cabecalhoPorId.setNumero(111111L);
		cabecalhoService.salvar(cabecalhoPorId);
		
		// DELETE
		cabecalhos.forEach((cabecalho) -> {
			cabecalhoService.deletar(cabecalho.getId());
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
		tipoService.salvar(tipoNota);
		
		cabecalhoNota = new CabecalhoNota();
		cabecalhoNota.setNumero(123456L);
		cabecalhoNota.setParceiro(parceiro);
		cabecalhoNota.setTipo(tipoNota);
		cabecalhoNota.setDataEmissao(LocalDate.now());
		cabecalhoService.salvar(cabecalhoNota);
		
		cabecalhoNota = new CabecalhoNota();
		cabecalhoNota.setNumero(123456L);
		cabecalhoNota.setParceiro(parceiro);
		cabecalhoNota.setTipo(tipoNota);
		cabecalhoNota.setDataEmissao(LocalDate.now());
		try {
			cabecalhoService.salvar(cabecalhoNota);
		} catch (CabecalhoNotaRepositoryException e) {}
		
		
		tipoNota = new NotaFiscalTipo();
		tipoNota.setNome("saida");
		tipoService.salvar(tipoNota);
		cabecalhoNota.setTipo(tipoNota);
		try {
			cabecalhoService.salvar(cabecalhoNota);
		} catch (CabecalhoNotaRepositoryException e) {}
		
		
		cabecalhos = cabecalhoService.buscarTodos();
		cabecalhos.forEach((cabecalho) -> {
			cabecalhoService.deletar(cabecalho.getId());
		});
		
		List<NotaFiscalTipo> tipos = tipoService.buscarTodos();
		tipos.forEach((tipo) -> {
			tipoService.deletar(tipo.getId());
		});
		parceiroNegocioService.deletar(parceiro.getId());
	}
	
	private void testarTipoNota() {
		// CREATE
		NotaFiscalTipo tipoNota = new NotaFiscalTipo();
		tipoNota.setNome("entrada");
		tipoService.salvar(tipoNota);
		
		// READ
		NotaFiscalTipo tipoNotaPorId = tipoService.buscar(tipoNota.getId());
		
		List<NotaFiscalTipo> tipos = tipoService.buscarTodos();
		
		// UPDATE
		tipoNotaPorId.setNome("entrada e saida");
		tipoService.salvar(tipoNotaPorId);
		
		// DELETE
		tipos.forEach((tipo) -> {
			tipoService.deletar(tipo.getId());
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
		tipoService.salvar(tipoNota);
		
		CabecalhoNota cabecalhoNota = new CabecalhoNota();
		cabecalhoNota.setNumero(123456L);
		cabecalhoNota.setParceiro(parceiro);
		cabecalhoNota.setTipo(tipoNota);
		cabecalhoNota.setDataEmissao(LocalDate.now());
		cabecalhoService.salvar(cabecalhoNota);
		
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
		itemService.salvar(item);
		
		// READ
		ItemNota itemPorId = itemService.buscar(item.getId());
		
		List<ItemNota> itens = itemService.buscarTodos();
		
		//UPDATE
		itemPorId.setPrecoUnitario(new BigDecimal("1.99"));
		itemService.salvar(itemPorId);
		
		// TESTA VALOR TOTAL
		BigDecimal valorTotal = itemService.calcularValorTotal(cabecalhoNota);
		
		//DELETE
		itens.forEach((i) -> {
			itemService.deletar(i.getId());
		});
		
		produtoService.deletar(produto.getId());
		cabecalhoService.deletar(cabecalhoNota.getId());
		parceiroNegocioService.deletar(parceiro.getId());
		tipoService.deletar(tipoNota.getId());
	}
}
