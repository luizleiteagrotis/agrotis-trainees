package com.agrotis.trainees.crud;

import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.service.NotaFiscalTipoService;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;
import com.agrotis.trainees.crud.service.ProdutoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory
			.getLogger(CrudApplication.class);

	private final NotaFiscalTipoService notaFiscalTipoService;
	private final ParceiroNegocioService parceiroNegocioService;
	private final ProdutoService produtoService;

	public CrudApplication(ParceiroNegocioService parceiroNegocioService, NotaFiscalTipoService notaFiscalTipoService, ProdutoService produtoService) {
		this.notaFiscalTipoService = notaFiscalTipoService;
		this.parceiroNegocioService = parceiroNegocioService;
		this.produtoService = produtoService;
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
		parceiroNegocio.setNome("C.Vale");		
		parceiroNegocio.setInscricaoFiscal("32.761.087/0001-31");
		parceiroNegocio.setEndereco("Rua São Paulo. Paulo - São Paulo");
		parceiroNegocio.setTelefone("(11)3248-6139");
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
	

		porNomeParceiro = parceiroNegocioService.buscarPorNome(parceiroNegocio.getNome());
		porNomeParceiro.setNome("Agrofertil");
		porNomeParceiro.setEndereco("Rua 98 de janeiro, 98. Campo Mourão - Paraná");
		parceiroNegocioService.salvar(porNomeParceiro);
		LOG.info("Dados alterados com sucesso!", porNomeParceiro.getNome(), porNomeParceiro.getId(), porNomeParceiro.getInscricaoFiscal(), porNomeParceiro.getEndereco(), porIdParceiro.getTelefone());

		
		boolean parceiroDeletar = false; // o comando funcionou adequadamente, por isso, deixei a variável como false 
		if (parceiroDeletar == true) {
			parceiroNegocioService.deletarPorId(21);
		}
		
		Produto produto = new Produto();
		produto.setNome("Soja");		
		produto.setDescricao("Grão Plantado");
		produto.setParceiroNegocio(parceiroNegocio2);
		produto.setFabricante("Coamo");
		produto.setDataFabricacao(LocalDate.of(2024, 1, 8));
		produto.setDataValidade(LocalDate.of(2024, 5, 8));
		Produto produto2 = produtoService.salvar(produto);
		LOG.info("id inserido: {}", produto.getId());
		
		Produto porIdProduto = produtoService.buscarPorId(produto2.getId());
		LOG.info("Busca por id. Nome {} id {} Descricao {} Parceiro de Negócio {} Fabricante {} Data de Fabricação {} Data de Validade {}", porIdProduto.getNome(), porIdProduto.getId(), porIdProduto.getDescricao(), porIdProduto.getParceiroNegocio(), porIdProduto.getFabricante(), porIdProduto.getDataFabricacao(), porIdProduto.getDataValidade()); 
		
		Produto porNomeProduto = produtoService.buscarPorNome(produto2.getNome());
		LOG.info("Busca por nome do Produto. Nome {} id {} Descricao {} Parceiro de Negócio {} Fabricante {} Data de Fabricação {} Data de Validade {}", porNomeProduto.getNome(), porNomeProduto.getId(), porNomeProduto.getDescricao(), porNomeProduto.getParceiroNegocio(), porNomeProduto.getFabricante(), porNomeProduto.getDataFabricacao(), porNomeProduto.getDataValidade());
		
		Produto porFabricanteProduto = produtoService.buscarPorFabricante(produto2.getFabricante());
		LOG.info("Busca por fabricante do Produto. Nome {} id {} Descricao {} Parceiro de Negócio {} Fabricante {} Data de Fabricação {} Data de Validade {}", porFabricanteProduto.getNome(), porFabricanteProduto.getId(), porFabricanteProduto.getDescricao(), porFabricanteProduto.getParceiroNegocio(), porFabricanteProduto.getFabricante(), porFabricanteProduto.getDataFabricacao(), porFabricanteProduto.getDataValidade());
		
		Produto porDataFabricacaoProduto = produtoService.buscarPorDataFabricacao(produto2.getDataFabricacao());
		LOG.info("Busca por data de fabricação do Produto. Nome {} id {} Descricao {} Parceiro de Negócio {} Fabricante {} Data de Fabricação {} Data de Validade {}", porDataFabricacaoProduto.getNome(), porDataFabricacaoProduto.getId(), porDataFabricacaoProduto.getDescricao(), porDataFabricacaoProduto.getParceiroNegocio(), porDataFabricacaoProduto.getFabricante(), porDataFabricacaoProduto.getDataFabricacao(), porDataFabricacaoProduto.getDataValidade());
		
		Produto porDataValidadeProduto = produtoService.buscarPorDataValidade(produto2.getDataValidade());
		LOG.info("Busca por data de validade do Produto. Nome {} id {} Descricao {} Parceiro de Negócio {} Fabricante {} Data de Fabricação {} Data de Validade {}", porDataValidadeProduto.getNome(), porDataValidadeProduto.getId(), porDataValidadeProduto.getDescricao(), porDataValidadeProduto.getParceiroNegocio(), porDataValidadeProduto.getFabricante(), porDataValidadeProduto.getDataFabricacao(), porDataValidadeProduto.getDataValidade());
		
		Produto porParceiroProduto = produtoService.buscarPorParceiro(produto2.getParceiroNegocio());
		LOG.info("Busca por parceiro de negócio. Nome {} id {} Descricao {} Parceiro de Negócio {} Fabricante {} Data de Fabricação {} Data de Validade {}", porParceiroProduto.getNome(), porParceiroProduto.getId(), porParceiroProduto.getDescricao(), porParceiroProduto.getParceiroNegocio(), porParceiroProduto.getFabricante(), porParceiroProduto.getDataFabricacao(), porParceiroProduto.getDataValidade());
		
		List<Produto> todosSalvosProduto = produtoService.listarTodos();
		LOG.info("Salvos no total de {} produtos", todosSalvos.size());
	
		
		
	}
}
