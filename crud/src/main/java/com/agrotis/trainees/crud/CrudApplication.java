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
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public void run(String... args) {

		ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
		parceiroNegocio.setNome("Flut");
		parceiroNegocio.setInscricaoFiscal("5045648889231");
		parceiroNegocio.setEndereco("Rua Eunice Prado, 5648");
		parceiroNegocio.setTelefone("41998775223");
		parceiroNegocioService.salvar(parceiroNegocio);
		LOG.info("id inserido: {}", parceiroNegocio.getId());
		
		Produto produto = new Produto();
		ParceiroNegocio parceiroNegocio2 = new ParceiroNegocio();
		parceiroNegocio.setNome("Flut");
		parceiroNegocio.setInscricaoFiscal("5045648889231");
		parceiroNegocio.setEndereco("Rua Eunice Prado, 5648");
		parceiroNegocio.setTelefone("41998775223");
		parceiroNegocioService.salvar(parceiroNegocio2);
		LOG.info("id inserido: {}", parceiroNegocio2.getId());
		produto.setDataFabricacao(LocalDate.parse("11/11/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		produto.setDataValidade(LocalDate.parse("11/11/2025", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		produto.setDescricao("Morango");
		produto.setFabricante(parceiroNegocio2);
		produtoService.salvar(produto);
		LOG.info("id inserido: {}", produto.getId());
		
	}
}