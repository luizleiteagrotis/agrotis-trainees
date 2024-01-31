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
		
		/*--------------------------
		Inicio CREATE*/
		ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
		parceiroNegocio.setNome("SR Photo");
		parceiroNegocio.setEndereco("Rua Arlindo Nogueira, 166, Centro - Curitiba, Paraná");
		parceiroNegocio.setInscricaoFiscal("11789600040");
		parceiroNegocio.setTelefone("4840046315");
		ParceiroNegocio parceiroNegocio2 = parceiroNegocioService.salvar(parceiroNegocio);
		LOG.info("id inserido: {}", parceiroNegocio2.getId());
		
		
		/*--------------------------
		Inicio READ*/
		ParceiroNegocio porId =  parceiroNegocioService.buscarPorId(parceiroNegocio2.getId());
		porId.setNome("VR Engenharia");
		LOG.info("Busca por id. Nome {} id {} ", porId.getNome(), porId.getId());
				
		List<ParceiroNegocio> todosSalvos = parceiroNegocioService.listarTodos();
		LOG.info("Salvos no total de {} parceiros", todosSalvos.size());
		
		
		/*--------------------------
		Inicio UPDATE*/
		parceiroNegocioService.buscarPorNome(parceiroNegocio.getNome());
		
		//Insira o nome da empresa que voce quer alterar entre parenteses (depois da função "buscarPorNome");
		ParceiroNegocio porNome = parceiroNegocioService.buscarPorNome(parceiroNegocio.getNome());
		//Insira o novo nome;
		porNome.setNome("Posto Paris");
		parceiroNegocioService.salvar(porNome);
		LOG.info("Nome alterado: novo nome {}", porNome.getNome() );
	
		
		/*-------------------
		Inicio DELETE
		Insira o ID do parceiro que você quer deletar na linha abaixo*/
		parceiroNegocioService.deletarPorId(4);
		//Insira o nome do parceiro que você quer deletar na linha abaixo;
		porId.setNome("VR Engenharia");
	}
}
