package com.agrotis.trainees.crud;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.service.NotaFiscalService;
import com.agrotis.trainees.crud.service.NotaFiscalTipoService;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;
import com.agrotis.trainees.crud.service.ProdutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory
			.getLogger(CrudApplication.class);

	private final NotaFiscalTipoService notaFiscalTipoService;
	private final ParceiroNegocioService parceiroNegocioService;
	private final ProdutoService produtoService;
	private final NotaFiscalService notaFiscalService;

	public CrudApplication(NotaFiscalTipoService notaFiscalTipoService, ParceiroNegocioService parceiroNegocioService, ProdutoService produtoService, NotaFiscalService notaFiscalService) {
		this.notaFiscalTipoService = notaFiscalTipoService;
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
		NotaFiscalTipo notaFiscalTipo = new NotaFiscalTipo();
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
		notaFiscalTipoService.buscarPorNome(notaFiscalTipo.getNome()); 
		 
LOG.info("-------------------------------------------------PARCEIRO DE NEGOCIOS---------------------------------------------------------------------------------------");
		 	

		ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
		parceiroNegocio.setNome("outro");
		parceiroNegocio.setInscricaoFiscal("135694442");
		parceiroNegocio.setEndereco("Rua Rio Xingu");
		parceiroNegocio.setTelefone("41996483268");
		
		ParceiroNegocio parceiroNegocio2 = parceiroNegocioService.salvar(parceiroNegocio);
		LOG.info("ID Inserido: {}", parceiroNegocio2.getId());
		
		ParceiroNegocio porId1 =  parceiroNegocioService.buscarPorId(parceiroNegocio2.getId());
		LOG.info("Busca por id. Nome {} id {} ", porId1.getNome(), porId1.getId());
		
		List<ParceiroNegocio> todosSalvos2 = parceiroNegocioService.listarTodos();
		LOG.info("Salvos no total de {} Parceiros de Negocios", todosSalvos2.size());
		
		ParceiroNegocio porNome2 = parceiroNegocioService.buscarPorNome(parceiroNegocio.getNome());
		porNome.setNome("Nome alterado");
		
		parceiroNegocioService.salvar(porNome2);
		LOG.info("Busca por Nome. Nome {} id {} ", porNome2.getNome(), porNome2.getId());
		
		parceiroNegocioService.buscarPorId(parceiroNegocio2.getId());
		parceiroNegocioService.buscarPorNome(parceiroNegocio2.getNome());
		parceiroNegocioService.deletarPorId(parceiroNegocio2.getId());
		
		
 LOG.info("------------------------------------------------------PRODUTO---------------------------------------------------------------------------------");	
		
		Produto produto = new Produto();
	
		produto.setDescricao("Grao Plantado");
		produto.setNome("outro");
		produto.setFabricante(parceiroNegocio);
		produto.setDataFabricacao(LocalDate.of(2024, 12, 1));
		produto.setDataValidade(LocalDate.of(2025, 11, 2));
		produtoService.salvar(produto);
	
		List<Produto> buscarTodos = produtoService.listarTodos();
		LOG.info("Salvos no total de {} parceiros de negocio.", buscarTodos.size());
	
		Produto novoProduto = new Produto();
		novoProduto.setDescricao("Trigo");
		novoProduto.setNome("outro");
		novoProduto.setFabricante(parceiroNegocio);
		novoProduto.setDataFabricacao(LocalDate.of(2024, 12, 1));
		novoProduto.setDataValidade(LocalDate.of(2025, 11, 2));	
		produtoService.update(produto.getId(), novoProduto);
		LOG.info("Produto Update {}", produto.getId());
	
		produtoService.deletarPorId(produto.getId());
		LOG.info("Produto Deletado {}", produto.getId());
	
	
	
 LOG.info("---------------------------------------------------NOTA FISCAL------------------------------------------------------------------------------------");	
	
		NotaFiscal notaFiscal = new NotaFiscal();
	
		notaFiscal.setDataNota(LocalDate.of(2024, 01, 2));
		notaFiscal.setNotaFiscalTipo("Tipo da Nota: Entrada");
		notaFiscal.setNumeroNota(210798);
		notaFiscal.setParceiroNegocio(parceiroNegocio2);
		notaFiscalService.salvar(notaFiscal);
		LOG.info("Nota Fiscal Salva: {}", notaFiscal.getId());
		
		NotaFiscal notaFiscalSaida = new NotaFiscal();
		notaFiscalSaida.setDataNota(LocalDate.of(2024, 01, 2));
		notaFiscalSaida.setNotaFiscalTipo("Tipo da Nota: Saida");
		notaFiscalSaida.setNumeroNota(6193007);
		notaFiscalSaida.setParceiroNegocio(parceiroNegocio2);
		notaFiscalService.salvar(notaFiscalSaida);
		LOG.info("Nota Fiscal Salva: {}", notaFiscalSaida.getId());
		
		notaFiscalService.buscarPorId(notaFiscal.getId());
		LOG.info("Em processo de busca da nota por ID {}", notaFiscal.getId());
		
		notaFiscalService.update(notaFiscalSaida.getId(), notaFiscalSaida);
		LOG.info("Atualizando a Nota Fiscal pelo ID {}", notaFiscalSaida.getId());
		
		
		
	
 
 
 
 
	

	
	}
	
	
}
