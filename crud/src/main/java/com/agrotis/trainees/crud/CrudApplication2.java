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
		parceiroNegocio.setNome("VT Engenharia");
		parceiroNegocio.setEndereco("Rua Treze de Maio, 35, Centro - Curitiba, Paraná");
		parceiroNegocio.setInscricaoFiscal("140000863187");
		parceiroNegocio.setTelefone("41999964331");
		ParceiroNegocio parceiroNegocio2 = parceiroNegocioService.salvar(parceiroNegocio);
		LOG.info("id inserido: {}", parceiroNegocio2.getId());

	}
}
