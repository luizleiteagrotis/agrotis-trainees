package com.agrotis.trainees.crud;

import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocios;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ParceiroNegociosRepository;
import com.agrotis.trainees.crud.service.NotaFiscalTipoService;
import com.agrotis.trainees.crud.service.ParceiroNegociosService;
import com.agrotis.trainees.crud.service.ProdutoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
import java.util.List;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory
			.getLogger(CrudApplication.class);

	private final NotaFiscalTipoService notaFiscalTipoService;
	private final ParceiroNegociosService parceiroNegociosService;
	private final ProdutoService produtoService;
	
	public CrudApplication(
			NotaFiscalTipoService notaFiscalTipoService,
			ParceiroNegociosService parceiroNegociosService,
			ProdutoService produtoService
				) {
		
		this.notaFiscalTipoService = notaFiscalTipoService;
		this.parceiroNegociosService = parceiroNegociosService;
		this.produtoService = produtoService;
	}

	public static void main(String[] args) {
		LOG.info("Iniciado com sucesso!");
		SpringApplication.run(CrudApplication.class, args);
	}

	@Override
	public void run(String... args){
		int p = 4;
		if(p < 1) {
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
		} else if(p < 4){
			ParceiroNegocios parceiroNegocios = new ParceiroNegocios();
			parceiroNegocios.setNome("Sabrina Sato");
			parceiroNegocios.setInscricaoFiscal("854.569.84");
			parceiroNegocios.setEndereco("Disney, 2");
			parceiroNegocios.setTelefone("587489632");			
			ParceiroNegocios parceiroNegocios2 = parceiroNegociosService.salvar(parceiroNegocios);
			LOG.info("id inserido: {}", parceiroNegocios2.getId());
			
			ParceiroNegocios porId = parceiroNegociosService.buscarPorId(parceiroNegocios2.getId());
			LOG.info("Busca por id. Nome {} id {} ", porId.getNome(), porId.getId());			
					
			ParceiroNegocios porInscricao = parceiroNegociosService.buscarPorInscricaoFiscal(parceiroNegocios2.getInscricaoFiscal());
			LOG.info("Busca por inscrição fiscal. Nome {} inscrição {} ", porInscricao.getNome(), porInscricao.getInscricaoFiscal());
			
			ParceiroNegocios porNome = parceiroNegociosService.buscarPorNome(parceiroNegocios2.getNome());
			LOG.info("Busca por nome. Nome {} inscricao {} ", porNome.getNome(), porNome.getInscricaoFiscal());
			
			List<ParceiroNegocios> todosSalvos = parceiroNegociosService.listarTodos();
			LOG.info("Salvos no total de {} tipos de notas", todosSalvos.size());
			
			porNome = parceiroNegociosService.buscarPorNome(parceiroNegocios.getNome());
			porNome.setNome("Sabrina");
			porNome.setEndereco("globo");
			porNome.setTelefone("258749632");
			parceiroNegociosService.salvar(porNome);
			LOG.info("Dados alterados com sucesso");
			
			if(p == 3) {
				porId = parceiroNegociosService.buscarPorId(12);
				parceiroNegociosService.deletarPorId(porId.getId());
				LOG.info("O usuario {} foi deletado", porId.getNome());
			}
			
		} else {
			
			
			Produto produto = new Produto();
			ParceiroNegocios fabricante = parceiroNegociosService.buscarPorId(4); //Deve associar o fabricante a Lucas Bispo
			produto.setDescricao("Açai");
			produto.setFabricante(fabricante);
			produto.setDataFabricacao(Date.valueOf("2021-01-01"));
			produto.setDataValidade(Date.valueOf("2024-05-05"));
			Produto produto2 = produtoService.salvar(produto);
			LOG.info("id inserido: {}", produto2.getId());
			
			Produto porId = produtoService.buscarPorId(produto2.getId());
			LOG.info("Busca por Id. Descrição {}, Fabricante {}", porId.getDescricao(), porId.getFabricante().getNome());
			
			Produto porDescricao = produtoService.buscarPorDescricao(produto2.getDescricao());
			LOG.info("Busca por Descrição {}, Data de Fabricação {}, Data de Validade {}", porDescricao.getDescricao(), 
						porDescricao.getDataFabricacao(), porDescricao.getDataValidade());
			
			List<Produto> todosSalvos = produtoService.listarTodos();
			LOG.info("Salvos no total de {} tipos de notas", todosSalvos.size());
		}
		


	}
}
