package com.agrotis.trainees.crud;

import com.agrotis.trainees.crud.service.ParceiroNegocioService;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class CrudApplication2 implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory
			.getLogger(CrudApplication2.class);

	private final ParceiroNegocioService parceiroNegocioService;

	public CrudApplication2(ParceiroNegocioService parceiroNegocioService) {
		this.parceiroNegocioService = parceiroNegocioService;
	}

	public static void main(String[] args) {
		LOG.info("Iniciado com sucesso!");
		SpringApplication.run(CrudApplication2.class, args);
	}

	@Override
	public void run(String... args){
		//Create
		ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
		parceiroNegocio.setNome("Intercell");
		parceiroNegocio.setEndereco("Rua Brigadeiro Franco, 3699, Rebouças - Curitiba, Paraná");
		parceiroNegocio.setInscricaoFiscal("00980003465");
		parceiroNegocio.setTelefone("4133698439");
		ParceiroNegocio parceiroNegocio2 = parceiroNegocioService.salvar(parceiroNegocio);
		LOG.info("id inserido: {}", parceiroNegocio2.getId());
		
		ParceiroNegocio porId =  parceiroNegocioService.buscarPorId(parceiroNegocio2.getId());
		LOG.info("Busca por id. Nome {} id {} ", porId.getNome(), porId.getId());
				
		List<ParceiroNegocio> todosSalvos = parceiroNegocioService.listarTodos();
		LOG.info("Salvos no total de {} parceiros", todosSalvos.size());

	}
}
