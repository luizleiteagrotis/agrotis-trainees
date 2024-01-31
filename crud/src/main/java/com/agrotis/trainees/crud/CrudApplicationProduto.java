package com.agrotis.trainees.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.service.ProdutoService;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;


@SpringBootApplication
public class CrudApplicationProduto {
	
	private static final Logger LOG = LoggerFactory
			.getLogger(CrudApplicationProduto.class);
	private final ProdutoService produtoService;

	public CrudApplicationProduto(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	public static void main(String[] args) {
		LOG.info("Iniciado com sucesso!");
		SpringApplication.run(CrudApplicationProduto.class, args);
	}
	
	public void run(String... args){
		
		/*--------------------------
		Inicio CREATE*/
		Produto produto = new Produto();
		produto.setDescricao("Copo Stanley");
		produto.setDataFabricacao(null);
		produto.setDataValidade(null);
		Produto produto2 = produtoService.salvar(produto);
		LOG.info("id inserido: {}", ((ApplicationContext) produto2).getId());	
	}
}
