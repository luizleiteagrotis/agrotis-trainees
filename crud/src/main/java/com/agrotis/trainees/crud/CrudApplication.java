package com.agrotis.trainees.crud;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

	public CrudApplication(NotaFiscalTipoService notaFiscalTipoService, ParceiroNegocioService parceiroNegocioService, ProdutoService produtoService) {
		this.notaFiscalTipoService = notaFiscalTipoService;
		this.parceiroNegocioService = parceiroNegocioService;
		this.produtoService = produtoService;
	}

	public static void main(String[] args) {
		LOG.info("Iniciado com sucesso!");
		SpringApplication.run(CrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws ParseException {

//		NotaFiscalTipo notaFiscalTipo = new NotaFiscalTipo();
//		notaFiscalTipo.setNome("nomeTeste5");
//		NotaFiscalTipo notaFiscalTipo2 = notaFiscalTipoService.salvar(notaFiscalTipo);
//		LOG.info("id inserido: {}", notaFiscalTipo2.getId());
//
//		NotaFiscalTipo porId =  notaFiscalTipoService.buscarPorId(notaFiscalTipo2.getId());
//		LOG.info("Busca por id. Nome {} id {} ", porId.getNome(), porId.getId());
//
//		List<NotaFiscalTipo> todosSalvos = notaFiscalTipoService.listarTodos();
//		LOG.info("Salvos no total de {} tipos de notas", todosSalvos.size());
//
//		NotaFiscalTipo porNome = notaFiscalTipoService.buscarPorNome(notaFiscalTipo.getNome());
//		porNome.setNome("nomeAlterado");
//		notaFiscalTipoService.salvar(porNome);
//		LOG.info("Busca por nome. Nome {} id {} ", porNome.getNome(), porNome.getId());
//
//		//notaFiscalTipoService.deletarPorId(porId.getId());
//		notaFiscalTipoService.buscarPorId(notaFiscalTipo2.getId());
//		 notaFiscalTipoService.buscarPorNome(notaFiscalTipo.getNome());
//
//		
//		
		ParceiroNegocio parceiroNegocio = new ParceiroNegocio();

		parceiroNegocio.setNome("Cafe de Minas");
		parceiroNegocio.setInscricaoFiscal("020924");
		parceiroNegocio.setEndereco("Aracaju, rua Torres, 15");
		parceiroNegocio.setTelefone("79 9 5454 5454");
	

		ParceiroNegocio parceiroNegocio2 = parceiroNegocioService.salvar(parceiroNegocio);
		LOG.info("id inserido: {}", parceiroNegocio2.getId());
//		
//		
//		ParceiroNegocio parceiroPorId = parceiroNegocioService.buscarPorId(parceiroNegocio2.getId()); 
//		LOG.info("Busca por id. Nome {} Inscricao Fiscal {} Endereco {} Telefone {} id {}", 
//				parceiroPorId.getNome(), parceiroPorId.getInscricaoFiscal(), parceiroPorId.getEndereco(), parceiroPorId.getTelefone(), parceiroPorId.getId());
//		
//		ParceiroNegocio parceiroPorNome = parceiroNegocioService.buscarPorNome(parceiroNegocio2.getNome());
//		LOG.info("Busca por nome. Nome {} id {}", parceiroPorNome.getNome(), parceiroPorNome.getId());
//		
//		List<ParceiroNegocio> todosParceiros = parceiroNegocioService.listarTodos();
//		LOG.info("Salvos no total de {} tipos de parceiros", todosParceiros.size() );
//		
//		
//		ParceiroNegocio parceiroPorNome2 = parceiroNegocioService.buscarPorNome(parceiroNegocio2.getNome());
//		parceiroPorNome2.setNome("Racao de Campo Largo");
//		parceiroPorNome2.setEndereco("Campo Largo, Bom jesus, 333");
//		parceiroPorNome2.setTelefone("41 9 2222 1111");
//		parceiroNegocioService.salvar(parceiroPorNome2);
//		LOG.info("Nome, endereco e telefone atualizado com sucesso, novo nome: {}, novo endereco: {}, novo telefone: {} ", 
//				parceiroPorNome2.getNome(), parceiroPorNome2.getEndereco(), parceiroPorNome2.getTelefone() );
		
//		parceiroNegocioService.deletarPorId(parceiroPorId.getId());
		
		Produto produto = new Produto();
		produto.setDescricao("Cafe");
		produto.setParceiroNegocio(parceiroNegocio);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		Date fabricacaoDate = dateFormat.parse("03-07-2008");
		produto.setDataFabricacao(fabricacaoDate);
		
		Date validaDate = dateFormat.parse("08-08-2018");
		produto.setDataValidade(validaDate);
		
		Produto produto2 = produtoService.salvar(produto);
		
		
	}
}
