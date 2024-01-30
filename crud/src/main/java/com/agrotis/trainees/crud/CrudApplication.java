package com.agrotis.trainees.crud;

import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocios;
import com.agrotis.trainees.crud.repository.ParceiroNegociosRepository;
import com.agrotis.trainees.crud.service.NotaFiscalTipoService;
import com.agrotis.trainees.crud.service.ParceiroNegociosService;

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
	private final ParceiroNegociosService parceiroNegociosService;
	
	public CrudApplication(NotaFiscalTipoService notaFiscalTipoService,
				ParceiroNegociosService parceiroNegociosService) {
		
		this.notaFiscalTipoService = notaFiscalTipoService;
		this.parceiroNegociosService = parceiroNegociosService;
	}

	public static void main(String[] args) {
		LOG.info("Iniciado com sucesso!");
		SpringApplication.run(CrudApplication.class, args);
	}

	@Override
	public void run(String... args){
		int p = 1;
		if(p != 1) {
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
		} else {
			ParceiroNegocios parceiroNegocios = new ParceiroNegocios();
			parceiroNegocios.setNome("Lucas Bispo");
			parceiroNegocios.setInscricaoFiscal("14.854.784-8");
			parceiroNegocios.setEndereco("Rua Eduardo Carlos Pereira, 4125");
			parceiroNegocios.setTelefone("41991052437");			
			ParceiroNegocios parceiroNegocios2 = parceiroNegociosService.salvar(parceiroNegocios);
			LOG.info("id inserido: {}", parceiroNegocios2.getId());
		}
		


	}
}
