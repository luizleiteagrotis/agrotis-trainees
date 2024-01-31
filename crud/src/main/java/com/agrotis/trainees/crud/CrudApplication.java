package com.agrotis.trainees.crud;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.service.NotaFiscalTipoService;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;
import com.agrotis.trainees.crud.service.ProdutoService;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(CrudApplication.class);

	private final NotaFiscalTipoService notaFiscalTipoService;
	private final ParceiroNegocioService parceiroNegocioService;
	private final ProdutoService produtoService;

	public CrudApplication(NotaFiscalTipoService notaFiscalTipoService,
			ParceiroNegocioService parceiroNegocioService, ProdutoService produtoService) {
		this.notaFiscalTipoService = notaFiscalTipoService;
		this.parceiroNegocioService = parceiroNegocioService;
		this.produtoService = produtoService;
	}

	public static void main(String[] args) {
		LOG.info("Iniciado com sucesso!");
		SpringApplication.run(CrudApplication.class, args);
	}

	@Override
	public void run(String... args) {
		NotaFiscalTipo notaFiscalTipo = new NotaFiscalTipo();
		notaFiscalTipo.setNome("nomeTeste");
		NotaFiscalTipo notaFiscalTipo2 = notaFiscalTipoService.salvar(notaFiscalTipo);
		LOG.info("id inserido: {}", notaFiscalTipo2.getId());

		NotaFiscalTipo porId = notaFiscalTipoService.buscarPorId(notaFiscalTipo2.getId());
		LOG.info("Busca por id. Nome {} id {} ", porId.getNome(), porId.getId());

		List<NotaFiscalTipo> todosSalvos = notaFiscalTipoService.listarTodos();
		LOG.info("Salvos no total de {} tipos de notas", todosSalvos.size());

		NotaFiscalTipo porNome = notaFiscalTipoService.buscarPorNome(notaFiscalTipo.getNome());
		porNome.setNome("nomeAlterado");
		notaFiscalTipoService.salvar(porNome);
		LOG.info("Busca por nome. Nome {} id {} ", porNome.getNome(), porNome.getId());

		// notaFiscalTipoService.deletarPorId(porId.getId());
		notaFiscalTipoService.buscarPorId(notaFiscalTipo2.getId());
		notaFiscalTipoService.buscarPorNome(notaFiscalTipo.getNome());
		
		LOG.info("----------------PDN------------------");

		ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
		parceiroNegocio.setNome("Copersucar");
		parceiroNegocio.setInscricaoFiscal("22.338.624/0002-37");
		parceiroNegocio.setEndereco("Rua dos Sonhadores, 67");
		parceiroNegocio.setTelefone("41988556544");
		parceiroNegocioService.salvar(parceiroNegocio);
		LOG.info("id inserido: {}", parceiroNegocio.getId());
		
		ParceiroNegocio buscaParceiroID = parceiroNegocioService.buscarPorId(parceiroNegocio.getId());
		LOG.info("Busca por id. Nome {} id {} ", buscaParceiroID.getNome(), buscaParceiroID.getId());
		
		ParceiroNegocio buscaParceiroNome = parceiroNegocioService.buscarPorNome(parceiroNegocio.getNome());
		LOG.info("Busca por nome. Nome {} id {} ", buscaParceiroNome.getNome(), buscaParceiroNome.getId());
		
		List<ParceiroNegocio> parceirosSalvos = parceiroNegocioService.listarTodos();
		LOG.info("Salvos no total de {} parceiros de negocio.", parceirosSalvos.size());
		
		LOG.info("----------------PRODUTO------------------");
		
		Produto produto = new Produto();
		ParceiroNegocio parceiroNegocio2 = new ParceiroNegocio();
		parceiroNegocio2.setNome("Copacol");
		parceiroNegocio2.setInscricaoFiscal("22.338.624/0002-37");
		parceiroNegocio2.setEndereco("Rua dos Sonhadores, 67");
		parceiroNegocio2.setTelefone("41988556544");
		parceiroNegocioService.salvar(parceiroNegocio2);
		LOG.info("id inserido: {}", parceiroNegocio2.getId());

		produto.setDataFabricacao(LocalDate.parse("12/12/2015", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		produto.setDataValidade(LocalDate.parse("12/12/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		produto.setDescricao("Fertilizante Quebra Nózes");
		produto.setFabricante(parceiroNegocio2);
		produtoService.salvar(produto);
		LOG.info("id inserido: {}", produto.getId());
		
		List<Produto> buscarTodos = produtoService.buscarTodos();
		LOG.info("Salvos no total de {} parceiros de negocio.", buscarTodos.size());
		
		Produto produtoPorId = produtoService.buscaPeloId(produto.getId());
		LOG.info("Buscando pelo id: {}", produtoPorId.getId());
		
		Produto produtoAtualizado = new Produto();
		produto.setDescricao("Rolo de Algodão");
		
		produtoService.atualizar(produtoPorId.getId(), produtoAtualizado);
		LOG.info("Atualizando o produto {}", produtoPorId.getId() );
		
		produtoService.deletarPorId(produtoPorId.getId());
		LOG.info("Deletando o produto {}", produtoPorId.getId() );
		
		
	}
}
