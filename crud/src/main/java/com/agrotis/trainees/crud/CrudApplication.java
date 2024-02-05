package com.agrotis.trainees.crud;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.service.NotaFiscalItemService;
import com.agrotis.trainees.crud.service.NotaFiscalService;
import com.agrotis.trainees.crud.service.NotaFiscalTipoService;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;
import com.agrotis.trainees.crud.service.ProdutoService;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(CrudApplication.class);

	private final ParceiroNegocioService parceiroNegocioService;
	private final ProdutoService produtoService;
	private final NotaFiscalService notaFiscalService;
	private final NotaFiscalItemService notaFiscalItemService;

	public CrudApplication(ParceiroNegocioService parceiroNegocioService,
			ProdutoService produtoService, 
			NotaFiscalService notaFiscalService,
			NotaFiscalItemService notaFiscalItemService) {
		this.parceiroNegocioService = parceiroNegocioService;
		this.produtoService = produtoService;
		this.notaFiscalService = notaFiscalService;
		this.notaFiscalItemService = notaFiscalItemService;
	}

	public static void main(String[] args) {
		LOG.info("Iniciado com sucesso!");
		SpringApplication.run(CrudApplication.class, args);
	}

	@Override
	public void run(String... args) {
		
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
		
		ParceiroNegocio parceiroNegocio2 = new ParceiroNegocio();
		parceiroNegocio2.setNome("Copacol");
		parceiroNegocio2.setInscricaoFiscal("22.338.624/0002-37");
		parceiroNegocio2.setEndereco("Rua dos Sonhadores, 67");
		parceiroNegocio2.setTelefone("41988556544");
		parceiroNegocioService.salvar(parceiroNegocio2);
		LOG.info("id inserido: {}", parceiroNegocio2.getId());

		Produto produto = new Produto();
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
		
		ParceiroNegocio parceiroNegocio3 = new ParceiroNegocio();
		parceiroNegocio3.setNome("Coamo");
		parceiroNegocio3.setInscricaoFiscal("22.338.624/0002-37");
		parceiroNegocio3.setEndereco("Rua dos Sonhadores, 67");
		parceiroNegocio3.setTelefone("41988556544");
		parceiroNegocioService.salvar(parceiroNegocio3);
		LOG.info("id inserido: {}", parceiroNegocio3.getId());
		
		
		Produto produtoAtualizado = new Produto();
		produtoAtualizado.setDataFabricacao(LocalDate.parse("12/12/2015", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		produtoAtualizado.setDataValidade(LocalDate.parse("12/12/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		produtoAtualizado.setDescricao("Xalabacataia");
		produtoAtualizado.setFabricante(parceiroNegocio3);
		produtoService.salvar(produtoAtualizado);
		
		Produto produtoAtualizado2 = new Produto();
		produtoAtualizado2.setDataFabricacao(LocalDate.parse("12/12/2015", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		produtoAtualizado2.setDataValidade(LocalDate.parse("12/12/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		produtoAtualizado2.setDescricao("Mathias O Brabo");
		produtoAtualizado2.setFabricante(parceiroNegocio3);
		produtoService.salvar(produtoAtualizado2);
		
		produtoService.atualizar(produtoPorId.getId(), produtoAtualizado);
		LOG.info("Atualizando o produto {}", produtoPorId.getId() );
		
		//produtoService.deletarPorId(produtoPorId.getId());
		//LOG.info("Deletando o produto {}", produtoPorId.getId() );
		
		LOG.info("----------------Nota Fiscal------------------");
		
		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setData(LocalDateTime.of(2024, Month.JANUARY, 25, 0, 0));
		notaFiscal.setNotaFiscalTipo(NotaFiscalTipo.ENTRADA);
		notaFiscal.setNumeroDaNota(159753);
		notaFiscal.setParceiroNegocio(parceiroNegocio3);
		notaFiscalService.salvar(notaFiscal);
		LOG.info("Salvando nota fiscal: {}", notaFiscal.getId());
		
		NotaFiscal notaFiscal2 = new NotaFiscal();
		notaFiscal2.setData(LocalDateTime.of(2024, Month.JANUARY, 25, 0, 0));
		notaFiscal2.setNotaFiscalTipo(NotaFiscalTipo.SAIDA);
		notaFiscal2.setNumeroDaNota(159753);
		notaFiscal2.setParceiroNegocio(parceiroNegocio3);
		notaFiscalService.salvar(notaFiscal2);
		LOG.info("Salvando nota fiscal: {}", notaFiscal2.getId());
		
		notaFiscalService.buscarPorId(notaFiscal.getId());
		LOG.info("Buscando nota pelo id {}", notaFiscal.getId() );
		
		notaFiscal2.setNumeroDaNota(777777);

		notaFiscalService.atualizar(notaFiscal2.getId(), notaFiscal2);
		LOG.info("Atualizando pelo id {}", notaFiscal2.getId());
		
		List<NotaFiscal> notas = notaFiscalService.listarTodos();
		LOG.info("Tamanho da lista: ", notas.size());
		
		//notaFiscalService.deletarPorId(notaFiscal2.getId());
		//LOG.info("Deletando a nota {}", notaFiscal2.getId() );


		LOG.info("---------------- Nota Fiscal Item ------------------");
		
		NotaFiscalItem fiscalItem = new NotaFiscalItem();
		fiscalItem.setNotaFiscal(notaFiscal2);
		fiscalItem.setProduto(produto);
		fiscalItem.setPrecoUnitario(25.99);
		fiscalItem.setQuantidade(5);
		System.out.println(fiscalItem.getValorTotal());

		notaFiscalItemService.salvar(fiscalItem);
		
		List<NotaFiscalItem> itens = notaFiscalItemService.buscarTodos();
		LOG.info("Buscando Nota Fiscal Item {}", itens.size());
		
		notaFiscalItemService.buscarPorId(fiscalItem.getId());
		
		NotaFiscalItem fiscalItemAtualizado = new NotaFiscalItem();
		fiscalItem.setNotaFiscal(notaFiscal);
		fiscalItem.setProduto(produto);
		fiscalItem.setPrecoUnitario(25.99);
		fiscalItem.setQuantidade(5);
		
		notaFiscalItemService.atualizar(fiscalItem.getId(), fiscalItemAtualizado);
		
		
		
		
		
		
		
	}
}
