package com.agrotis.trainees.crud;

import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.service.NotaFiscalTipoService;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(CrudApplication.class);

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
	public void run(String... args) {

//		NotaFiscalTipo notaFiscalTipo = new NotaFiscalTipo();
//		notaFiscalTipo.setNome("nomeTeste");
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

		
		
		ParceiroNegocio parceiroNegocio = new ParceiroNegocio();

		parceiroNegocio.setNome("nome errado3");
		parceiroNegocio.setInscricaoFiscal("202020");
		parceiroNegocio.setEndereco("Aracaju, rua Torres, 15");
		parceiroNegocio.setTelefone("79 9 5454 5454");
	

		ParceiroNegocio parceiroNegocio2 = parceiroNegocioService.salvar(parceiroNegocio);
		LOG.info("id inserido: {}", parceiroNegocio2.getId());
		
		
		ParceiroNegocio parceiroPorId = parceiroNegocioService.buscarPorId(parceiroNegocio2.getId()); 
		LOG.info("Busca por id. Nome {} Inscricao Fiscal {} Endereco {} Telefone {} id {}", 
				parceiroPorId.getNome(), parceiroPorId.getInscricaoFiscal(), parceiroPorId.getEndereco(), parceiroPorId.getTelefone(), parceiroPorId.getId());
		
		ParceiroNegocio parceiroPorNome = parceiroNegocioService.buscarPorNome(parceiroNegocio2.getNome());
		LOG.info("Busca por nome. Nome {} id {}", parceiroPorNome.getNome(), parceiroPorNome.getId());
		
		List<ParceiroNegocio> todosParceiros = parceiroNegocioService.listarTodos();
		LOG.info("Salvos no total de {} tipos de parceiros", todosParceiros.size() );
		
		
		ParceiroNegocio parceiroPorNome2 = parceiroNegocioService.buscarPorNome(parceiroNegocio2.getNome());
		parceiroPorNome2.setNome("Cafe de Sergipe");
		parceiroPorNome2.setEndereco("Espirito Santo, Rua Sao Pedro, 333");
		parceiroPorNome2.setTelefone("27 9 7777 6666");
		parceiroNegocioService.salvar(parceiroPorNome2);
		LOG.info("Nome, endereco e telefone atualizado com sucesso, novo nome: {}, novo endereco: {}, novo telefone: {} ", 
				parceiroPorNome2.getNome(), parceiroPorNome2.getEndereco(), parceiroPorNome2.getTelefone() );
		
	}
}
