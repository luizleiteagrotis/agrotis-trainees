package com.agrotis.trainees.crud;

import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.service.NotaFiscalTipoService;
import com.agrotis.trainees.crud.service.ParceiroNegocioTipoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(CrudApplication.class);

    @Autowired
    private ParceiroNegocioTipoService parceiroNegocioService;
    
	
    public static void main(String[] args) {
        SpringApplication.run(CrudApplication.class, args);
    }

	    @Override
	    public void run(String... args) { {
	        // Operações com a entidade ParceiroNegocio
	
	        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
	        parceiroNegocio.setNome("Agrotis ");
	        parceiroNegocio.setInscricaoFiscal("82.413.816/0001-01");
	        parceiroNegocio.setEndereco("Rua 13 de maio");
	        parceiroNegocio.setTelefone("4135238200");
	
	        // Create
	        ParceiroNegocio parceiroNegocioSalvo = parceiroNegocioService.salvar(parceiroNegocio);
	
	        if (parceiroNegocioSalvo != null) {
	            LOG.info("Parceiro de Negócio criado com sucesso. ID: {}", parceiroNegocioSalvo.getId());
	        } else {
	            LOG.error("Falha ao criar o Parceiro de Negócio.");
	        }
	        ParceiroNegocio parceiroPorId =  parceiroNegocioService.buscarPorId(parceiroNegocio.getId());
			LOG.info("Busca por id. Nome {} id {} ", parceiroPorId.getNome(), parceiroPorId.getId());

			ParceiroNegocio parceiroPorNome =  parceiroNegocioService.buscarPorNome(parceiroNegocio.getNome());
			LOG.info("Busca por nome. Nome {} id {} ", parceiroPorNome.getNome(), parceiroPorNome.getId());

			List<ParceiroNegocio> todosParceirosSalvos = parceiroNegocioService.listarTodos();
			LOG.info("Salvos no total de {} tipos de Parceiros", todosParceirosSalvos.size());
	    }
	   
	    
	}
}