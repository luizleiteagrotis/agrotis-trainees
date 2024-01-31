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

	private static final Logger LOG = LoggerFactory
			.getLogger(CrudApplication.class);

	private final NotaFiscalTipoService notaFiscalTipoService;
	private final ParceiroNegocioService parceiroNegocioService;

	public CrudApplication(ParceiroNegocioService parceiroNegocioService, NotaFiscalTipoService notaFiscalTipoService) {
		this.notaFiscalTipoService = notaFiscalTipoService;
		this.parceiroNegocioService = parceiroNegocioService;
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
		 
		 
		 
		
		ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
		parceiroNegocio.setNome("Coamo");		
		parceiroNegocio.setInscricaoFiscal("32.785.081/0001-22");
		parceiroNegocio.setEndereco("Rua Manaus. Manaus - Amazonas");
		parceiroNegocio.setTelefone("(71)3298-6789");
		ParceiroNegocio parceiroNegocio2 = parceiroNegocioService.salvar(parceiroNegocio);
		LOG.info("id inserido: {}", parceiroNegocio2.getId());
		
		
		ParceiroNegocio porIdParceiro = parceiroNegocioService.buscarPorId(parceiroNegocio2.getId());
		LOG.info("Busca por id. Nome {} id {} inscricaoFiscal {} endereco {} telefone {}", porIdParceiro.getNome(), porIdParceiro.getId(), porIdParceiro.getInscricaoFiscal(), porIdParceiro.getEndereco(), porIdParceiro.getTelefone());
		
		ParceiroNegocio porNomeParceiro = parceiroNegocioService.buscarPorNome(parceiroNegocio2.getNome());
		LOG.info("Busca por id. Nome {} id {} inscricaoFiscal {} endereco {} telefone {}", porNomeParceiro.getNome(), porNomeParceiro.getId(), porNomeParceiro.getInscricaoFiscal(), porNomeParceiro.getEndereco(), porNomeParceiro.getTelefone());
		
		ParceiroNegocio porInscricaoFiscalParceiro = parceiroNegocioService.buscarPorInscricaoFiscal(parceiroNegocio2.getInscricaoFiscal());
		LOG.info("Busca por id. Nome {} id {} inscricaoFiscal {} endereco {} telefone {}", porInscricaoFiscalParceiro.getNome(), porInscricaoFiscalParceiro.getId(), porInscricaoFiscalParceiro.getInscricaoFiscal(), porInscricaoFiscalParceiro.getEndereco(), porInscricaoFiscalParceiro.getTelefone());
		

		List<ParceiroNegocio> todosSalvosParceiro = parceiroNegocioService.listarTodos();
		LOG.info("Salvos no total de {} parceiros de negócio", todosSalvos.size());
	

		

		
	}
}
