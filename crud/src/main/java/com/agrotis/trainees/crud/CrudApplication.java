package com.agrotis.trainees.crud;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.service.NotaFiscalTipoService;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(CrudApplication.class);

	private final NotaFiscalTipoService notaFiscalTipoService;
	private final ParceiroNegocioService parceiroNegocioService;

	public CrudApplication(NotaFiscalTipoService notaFiscalTipoService,
			ParceiroNegocioService parceiroNegocioService) {
		this.notaFiscalTipoService = notaFiscalTipoService;
		this.parceiroNegocioService = parceiroNegocioService;
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
		
	}
}
