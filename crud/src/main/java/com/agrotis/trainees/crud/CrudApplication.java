package com.agrotis.trainees.crud;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;
import com.agrotis.trainees.crud.service.ProdutoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory
			.getLogger(CrudApplication.class);

	private final ParceiroNegocioService parceiroNegocioService;
	private final ProdutoService produtoService;
	
	@Autowired
	public CrudApplication(ParceiroNegocioService parceiroNegocioService, 
			ProdutoService produtoService) {
		this.parceiroNegocioService = parceiroNegocioService;
		this.produtoService = produtoService;
	}

	public static void main(String[] args) {
		LOG.info("Iniciado com sucesso!");
		SpringApplication.run(CrudApplication.class, args);
	}

	@Override
	public void run(String... args){
		testarParceiroNegocio();
		testarProduto();
	}

	private void testarParceiroNegocio() {
		ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
		parceiroNegocio.setNome("nomeTeste");
		parceiroNegocio.setInscricaoFiscal("inscricaoTeste");
		parceiroNegocio.setEndereco("enderecoTeste");
		parceiroNegocio.setTelefone("12345");
		parceiroNegocio = parceiroNegocioService.salvar(parceiroNegocio);
		
		ParceiroNegocio parceiroPorId = parceiroNegocioService.buscarPorId(parceiroNegocio.getId());
		
		List<ParceiroNegocio> parceiros = parceiroNegocioService.listarTodos();
		
		parceiroPorId.setNome("coxinha cabulosa");
		parceiroNegocioService.salvar(parceiroPorId);
		
		parceiros.forEach((parceiro) -> {
			parceiroNegocioService.deletar(parceiro.getId());
		});
	}
	
	private void testarProduto() {
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
		
		Produto produtoPorId = produtoService.buscarPor(produto.getId());
		List<Produto> produtos = produtoService.listarTodos();
		
		produtoPorId.setNome("moranguinho");
		produtoService.salvar(produtoPorId);
		
		produtos.forEach((p) -> {
			produtoService.deletar(p.getId());
		});
	}
}
