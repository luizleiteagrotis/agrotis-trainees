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
		crudParceiroNegocio();
		crudProduto();
	}

	private void crudParceiroNegocio() {
	/*--------------------------
	Inicio CREATE*/
	ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
	parceiroNegocio.setNome("SR Photo");
	parceiroNegocio.setEndereco("Rua Arlindo Nogueira, 166, Centro - Curitiba, Paraná");
	parceiroNegocio.setInscricaoFiscal("11789600040");
	parceiroNegocio.setTelefone("4840046315");
	ParceiroNegocio parceiroNegocio1 = parceiroNegocioService.salvar(parceiroNegocio);
	LOG.info("id inserido: {}", parceiroNegocio1.getId());
	
	
	/*--------------------------
	Inicio READ*/
	ParceiroNegocio porId =  parceiroNegocioService.buscarPorId(parceiroNegocio1.getId());
	porId.setNome("VR Engenharia");
	LOG.info("Busca por id. Nome {} id {} ", porId.getNome(), porId.getId());
			
	List<ParceiroNegocio> todosSalvos = parceiroNegocioService.listarTodos();
	LOG.info("Salvos no total de {} parceiros", todosSalvos.size());
	
	
	/*--------------------------
	Inicio UPDATE*/
	parceiroNegocioService.buscarPorNome(parceiroNegocio.getNome());
	
	//Insira o nome da empresa que voce quer alterar entre parenteses (depois da função "buscarPorNome");
	ParceiroNegocio porNome = parceiroNegocioService.buscarPorNome(parceiroNegocio.getNome());
	//Insira o novo nome;
	porNome.setNome("SR Photo");
	parceiroNegocioService.salvar(porNome);
	LOG.info("Nome alterado: novo nome {}", porNome.getNome() );

	
	/*-------------------
	Inicio DELETE
	Insira o ID do parceiro que você quer deletar na linha abaixo*/
	parceiroNegocioService.deletarPorId(4);
	//Insira o nome do parceiro que você quer deletar na linha abaixo;
	porId.setNome("VR Engenharia");
}

	private void crudProduto() {
		ParceiroNegocio parceiroNegocio1 = new ParceiroNegocio();
		parceiroNegocio1.setNome("Gaspar");
		parceiroNegocio1.setInscricaoFiscal("43246546546");
		parceiroNegocio1.setEndereco("Rua Ubaldino Amaral");
		parceiroNegocio1.setTelefone("4132226849");
		parceiroNegocio1 = parceiroNegocioService.salvar(parceiroNegocio1);
		LOG.info("id inserido: {}", parceiroNegocio1.getId());
		Produto produto = new Produto();
		produto.setNomeProduto("Semente");
		produto.setDescricao("Semente de girassol");
		produto.setFabricante(parceiroNegocio1);
		produto = produtoService.salvar(produto);
		LOG.info("id inserido: {}", produto.getId());
		
	}
}