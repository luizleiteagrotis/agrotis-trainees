package com.agrotis.trainees.crud;

import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.service.NotaFiscalTipoService;
import com.agrotis.trainees.crud.service.ParceiroNegocioTipoService;
import com.agrotis.trainees.crud.service.ProdutoTipoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(CrudApplication.class);

	private final NotaFiscalTipoService notaFiscalTipoService;
	private final ParceiroNegocioTipoService parceiroNegocioTipoService;
	private final ProdutoTipoService produtoTipoService;
	
	public CrudApplication(NotaFiscalTipoService notaFiscalTipoService,
	        ParceiroNegocioTipoService parceiroNegocioTipoService, ProdutoTipoService produtoTipoService) {
	    this.notaFiscalTipoService = notaFiscalTipoService;
	    this.parceiroNegocioTipoService = parceiroNegocioTipoService;
	    this.produtoTipoService = produtoTipoService;  

	}

	public static void main(String[] args) {
		LOG.info("Iniciado com sucesso!");
		SpringApplication.run(CrudApplication.class, args);
	}
	    @Override
	    public void run(String... args) { {
	        // Operações com a entidade ParceiroNegocio
	
	        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
	        parceiroNegocio.setNome("Agrotis Informatica ");
	        parceiroNegocio.setInscricaoFiscal("82.413.816/0001-01");
	        parceiroNegocio.setEndereco("Rua 13 de maio");
	        parceiroNegocio.setTelefone("4135238200");
	
	        // Create
	        ParceiroNegocio parceiroNegocioSalvo = parceiroNegocioTipoService.salvar(parceiroNegocio);
	
	        if (parceiroNegocioSalvo != null) {
	            LOG.info("Parceiro de Negócio criado com sucesso. ID: {}", parceiroNegocioSalvo.getId());
	        } else {
	            LOG.error("Falha ao criar o Parceiro de Negócio.");
	        }
	        ParceiroNegocio parceiroPorId =  parceiroNegocioTipoService.buscarPorId(parceiroNegocio.getId());
			LOG.info("Busca por id. Nome {} id {} ", parceiroPorId.getNome(), parceiroPorId.getId());

			ParceiroNegocio parceiroPorNome =  parceiroNegocioTipoService.buscarPorNome(parceiroNegocio.getNome());
			LOG.info("Busca por nome. Nome {} id {} ", parceiroPorNome.getNome(), parceiroPorNome.getId());

			List<ParceiroNegocio> todosParceirosSalvos = parceiroNegocioTipoService.listarTodos();
			LOG.info("Salvos no total de {} tipos de Parceiros", todosParceirosSalvos.size());
			
			ParceiroNegocio porNome = parceiroNegocioTipoService.buscarPorNome(parceiroNegocio.getNome());
			porNome.setNome("Agrotis informática");
			parceiroNegocioTipoService.salvar(porNome);
			LOG.info("Busca por nome. Nome {} id {} ", porNome.getNome(), porNome.getId());
	    
	   
			parceiroNegocioTipoService.deletarPorId(parceiroPorId.getId());
			
			Produto produto = new Produto();
			ParceiroNegocio parceiroNegocio2 = new ParceiroNegocio();
			parceiroNegocio2.setNome("Agrotis");
			parceiroNegocio2.setInscricaoFiscal("82.413.816/0001-01");
			parceiroNegocio2.setEndereco("Rua 13 de maio");
			parceiroNegocio2.setTelefone("4135238200");
			parceiroNegocioTipoService.salvar(parceiroNegocio2);
			LOG.info("id inserido: {}", parceiroNegocio2.getId());

			produto.setDataFabricacao(LocalDate.parse("08/01/2025", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			produto.setDataValidade(LocalDate.parse("09/12/2014", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			produto.setDescricao("Algodão");
			produto.setFabricante(parceiroNegocio2);
			produtoTipoService.salvar(produto);
			LOG.info("id inserido: {}", produto.getId());		

			Produto produtoPorId =  produtoTipoService.buscarPorId(produto.getId());
			LOG.info("Busca por id. Descrição {} id {} ", produtoPorId.getDescricao(), produtoPorId.getId());

			Produto produtoPorDescricao =  produtoTipoService.buscarPorDescricao(produto.getDescricao());
			LOG.info("Busca por descrição. Nome {} id {} ", produtoPorDescricao.getDescricao(), produtoPorDescricao.getId());

			List<Produto> todosProdutosSalvos = produtoTipoService.listarTodos();
			LOG.info("Salvos no total de {} tipos de produtos", todosProdutosSalvos.size());
			
			
	    }
	}
}