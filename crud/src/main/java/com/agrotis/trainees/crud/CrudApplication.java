package com.agrotis.trainees.crud;

import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.service.NotaFiscalTipoService;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;
import com.agrotis.trainees.crud.service.ProdutoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory
			.getLogger(CrudApplication.class);

	private final NotaFiscalTipoService notaFiscalTipoService;
	private final ParceiroNegocioService parceiroNegocioService;

	public CrudApplication(NotaFiscalTipoService notaFiscalTipoService, ParceiroNegocioService parceiroNegocioService) {
		this.notaFiscalTipoService = notaFiscalTipoService;
		this.parceiroNegocioService = parceiroNegocioService;
		
	} 

	public static void main(String[] args) {
		LOG.info("Iniciado com sucesso!");
		SpringApplication.run(CrudApplication.class, args);
	}

	@Override
	public void run(String... args){
		/*NotaFiscalTipo notaFiscalTipo = new NotaFiscalTipo();
		notaFiscalTipo.setNome("nomeTeste");
		NotaFiscalTipo notaFiscalTipo2 = notaFiscalTipoService.salvar(notaFiscalTipo);
		LOG.info("id inserido: {}", notaFiscalTipo2.getId());

		NotaFiscalTipo porId =  notaFiscalTipoService.buscarPorId(notaFiscalTipo2.getId());
		LOG.info("Busca por id. Nome {} id {} ", porId.getNome(), porId.getId());

		List<NotaFiscalTipo> todosSalvos = notaFiscalTipoService.listarTodos();
		LOG.info("Salvos no total de {} tipos de notas", todosSalvos.size());

		NotaFiscalTipo porNome = notaFiscalTipoService.buscarPorNome(notaFiscalTipo.getNome());
		porNome.setNome("nomeAlterado");
		notaFiscalTipoService.salvar(porNome);
		LOG.info("Busca por nome. Nome {} id {} ", porNome.getNome(), porNome.getId());

		//notaFiscalTipoService.deletarPorId(porId.getId());
		notaFiscalTipoService.buscarPorId(notaFiscalTipo2.getId());
		 notaFiscalTipoService.buscarPorNome(notaFiscalTipo.getNome());*/
		 
		 LOG.info("---------------------PN------------------------");
		 
		 ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
		 parceiroNegocio.setNome("nome");
		 parceiroNegocio.setInscricaoFiscal("112.456.798");
		 parceiroNegocio.setEndereco("Rua Berlim");
		 parceiroNegocio.setTelefone("40028922");
		 ParceiroNegocio parceiroNegocio2 = parceiroNegocioService.salvar(parceiroNegocio);
		 LOG.info("id inserido: {}", parceiroNegocio2.getId());
		 
		 ParceiroNegocio porId1 = parceiroNegocioService.buscarPorId(parceiroNegocio2.getId());
		 LOG.info("Buscar por id. Nome {} id {} ", porId1.getNome(), porId1.getId());
		
		 List<ParceiroNegocio> todosSalvos1 = parceiroNegocioService.listarTodos();
		 LOG.info("Salvos no total de {}} tipos de notas", todosSalvos1.size());
		 
		
		 ParceiroNegocio porNome1 = parceiroNegocioService.buscarPorNome(parceiroNegocio.getNome());
		 porNome1.setNome("Nome Alterado");
		 parceiroNegocioService.salvar(porNome1);
		 LOG.info("Buscar por nome, Nome {} id {} ", porNome1.getNome(), porNome1.getId());
		 
	
				 porNome1 = parceiroNegocioService.buscarPorNome(parceiroNegocio.getNome());
				 porNome1.setNome("Kleiton");
				 porNome1.setEndereco("Babilonia");
				 porNome1.setTelefone("40028922");
				 porNome1.setInscricaoFiscal("2135498231");
				 LOG.info("Dados alterados com sucesso");
				 
				 LOG.info("---------------------Produto------------------------");
				 
				 
				 Produto produto = new Produto();
				 ParceiroNegocio parceiroNegocio1 = new ParceiroNegocio();
				 parceiroNegocio1.setNome("Kleber");
				 parceiroNegocio1.setEndereco("Rua do além");
				 parceiroNegocio1.setInscricaoFiscal("123.465.7984");
				 parceiroNegocio1.setTelefone("40028923");
				 parceiroNegocioService.salvar(parceiroNegocio1);
				 LOG.info("id inserido: {}", parceiroNegocio1.getId());
				 
				 produto.setDataFabricacao(LocalDate.parse("23/062/2003", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
				 produto.setDataValidade(LocalDate.parse("02/08/2004", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
				 produto.setDescricao("Produto Random");
				 produto.setFabricante(parceiroNegocio1);
				 ProdutoService.salvar(produto);
				 LOG.info("if inserido: {}", produto.getId());
				 
				 List<Produto> buscarTodos = ProdutoService.buscarTodos();
					LOG.info("Salvos no total de {} parceiros de negocio.", buscarTodos.size());
				 
				 
				 
				 
				 
				 
				 

				 
				 
				 
				 
				 
				 
				 
				 
				 
				 
				 
	}
}



























