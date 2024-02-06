package com.agrotis.trainees.crud;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.service.NotaFiscalService;
import com.agrotis.trainees.crud.service.NotaFiscalTipoService;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;
import com.agrotis.trainees.crud.service.ProdutoService;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(CrudApplication.class);

    private final ParceiroNegocioService parceiroNegocioService;
    private final ProdutoService produtoService;
    private final NotaFiscalService notaFiscalService;
    private final NotaFiscalTipoService notaFiscalTipoService;

    public CrudApplication(ParceiroNegocioService parceiroNegocioService, ProdutoService produtoService,
                    NotaFiscalService notaFiscalService, NotaFiscalTipoService notaFiscalTipoService) {
        this.parceiroNegocioService = parceiroNegocioService;
        this.produtoService = produtoService;
        this.notaFiscalService = notaFiscalService;
        this.notaFiscalTipoService = notaFiscalTipoService;
    }

    public static void main(String[] args) {
        LOG.info("Iniciado com sucesso!");
        SpringApplication.run(CrudApplication.class, args);
    }

    @Override
    public void run(String... args) {

        // NotaFiscalTipo notaFiscalTipo = new NotaFiscalTipo();
        // notaFiscalTipo.setNome("nomeTeste");
        // NotaFiscalTipo notaFiscalTipo2 =
        // notaFiscalTipoService.salvar(notaFiscalTipo);
        // LOG.info("id inserido: {}", notaFiscalTipo2.getId());
        // NotaFiscalTipo porId =
        // notaFiscalTipoService.buscarPorId(notaFiscalTipo2.getId());
        // LOG.info("Busca por id. Nome {} id {} ", porId.getNome(),
        // porId.getId());
        // List<NotaFiscalTipo> todosSalvos =
        // notaFiscalTipoService.listarTodos();
        // LOG.info("Salvos no total de {} tipos de notas", todosSalvos.size());
        // NotaFiscalTipo porNome =
        // notaFiscalTipoService.buscarPorNome(notaFiscalTipo.getNome());
        // porNome.setNome("nomeAlterado");
        // notaFiscalTipoService.salvar(porNome);
        // LOG.info("Busca por nome. Nome {} id {} ", porNome.getNome(),
        // porNome.getId());
        // notaFiscalTipoService.deletarPorId(porId.getId());
        // notaFiscalTipoService.buscarPorId(notaFiscalTipo2.getId());
        // notaFiscalTipoService.buscarPorNome(notaFiscalTipo.getNome());

        LOG.info("Parceiro NEGOCIO");
        // ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
        // parceiroNegocio.setNome("E");
        // parceiroNegocio.setInscricaoFiscal("47566876962587");
        // parceiroNegocio.setEndereco("Rua Treze de Maio, 35, Centro, Curitiba
        // - Paraná");
        // parceiroNegocio.setTelefone("4140046807");
        // parceiroNegocioService.salvar(parceiroNegocio);
        // LOG.info("id inserido: {}", parceiroNegocio.getId());
        //
        // ParceiroNegocio buscaParceiroID =
        // parceiroNegocioService.buscarPorId(parceiroNegocio.getId());
        // LOG.info("Busca id. Nome {} id {} ", buscaParceiroID.getNome(),
        // buscaParceiroID.getId());
        //
        // ParceiroNegocio buscaParceiroNome =
        // parceiroNegocioService.buscarPorNome(parceiroNegocio.getNome());
        // LOG.info("Busca nome. Nome {} id {} ", buscaParceiroNome.getNome(),
        // buscaParceiroNome.getId());
        //
        // List<ParceiroNegocio> parceirosSalvos =
        // parceiroNegocioService.listarTodos();
        // LOG.info("Salvos no total de {} parceiros de negocio.",
        // parceirosSalvos.size());

        LOG.info("Produto");
        // ParceiroNegocio parceiroNegocio2 = new ParceiroNegocio();
        // parceiroNegocio2.setNome("Armazem Castanha");
        // parceiroNegocio2.setInscricaoFiscal("1076688911257");
        // parceiroNegocio2.setEndereco("Rua Henrique Mehl, 337");
        // parceiroNegocio2.setTelefone("4137692125");
        // parceiroNegocioService.salvar(parceiroNegocio2);
        // LOG.info("id inserido: {}", parceiroNegocio2.getId());
        // Produto produto = new Produto();
        // produto.setDataFabricacao(LocalDate.parse("10/01/2023",
        // DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        // produto.setDataValidade(LocalDate.parse("11/02/2025",
        // DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        // produto.setDescricao("Tomate");
        // produto.setFabricante(parceiroNegocio2);
        // produtoService.salvar(produto);
        // LOG.info("id inserido: {}", produto.getId());
        //
        // List<Produto> buscarTodos = produtoService.buscarTodos();
        // LOG.info("Salvos no total de {} parceiros de negocio.",
        // buscarTodos.size());
        //
        // Produto produtoPorId = produtoService.buscaPeloId(produto.getId());
        // LOG.info("Buscando id: {}", produtoPorId.getId());

        ParceiroNegocio parceiroNegocio3 = new ParceiroNegocio();
        parceiroNegocio3.setNome("Dnamy");
        parceiroNegocio3.setInscricaoFiscal("1066997339847993");
        parceiroNegocio3.setEndereco("Avenida Iguaçu, 9458, Rebouças - Curitiba, Paraná");
        parceiroNegocio3.setTelefone("41998756845");
        parceiroNegocioService.salvar(parceiroNegocio3);
        LOG.info("id inserido: {}", parceiroNegocio3.getId());

        Produto produtoAtualizado = new Produto();
        produtoAtualizado.setDataFabricacao(LocalDate.parse("15/10/2010", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        produtoAtualizado.setDataValidade(LocalDate.parse("11/07/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        produtoAtualizado.setDescricao("Batata");
        produtoAtualizado.setFabricante(parceiroNegocio3);
        produtoService.salvar(produtoAtualizado);

        // Produto produtoAtualizado2 = new Produto();
        // produtoAtualizado2.setDataFabricacao(LocalDate.parse("20/12/2020",
        // DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        // produtoAtualizado2.setDataValidade(LocalDate.parse("30/03/2023",
        // DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        // produtoAtualizado2.setDescricao("Bergamota");
        // produtoAtualizado2.setFabricante(parceiroNegocio3);
        // produtoService.salvar(produtoAtualizado2);

        // produtoService.atualizar(produtoPorId.getId(), produtoAtualizado);
        // LOG.info("Atualizando o produto {}", produtoPorId.getId());
        //
        // produtoService.deletarPorId(produtoPorId.getId());
        // LOG.info("Deletando o produto {}", produtoPorId.getId());

        LOG.info("Inicio cabeçalho nota fiscal");
        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setDataNf(LocalDate.parse("09/10/1999", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        notaFiscal.setNotaFiscalTipo("SAIDA");
        notaFiscal.setNumeroDaNota(101042);
        notaFiscal.setParceiroNegocio(parceiroNegocio3);
        notaFiscalService.salvar(notaFiscal);
        LOG.info("Salvando nota fiscal: {}", notaFiscal.getId());

        List<NotaFiscal> buscarnfTodos = notaFiscalService.buscarTodos();
        LOG.info("Salvos no total de {} parceiros de negocio.", buscarnfTodos.size());

        NotaFiscal nfPorId = notaFiscalService.buscaPeloId(notaFiscal.getId());
        LOG.info("Buscando id: {}", nfPorId.getId());

        NotaFiscal notaFiscal1 = new NotaFiscal();
        notaFiscal1.setDataNf(LocalDate.parse("12/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        notaFiscal1.setNotaFiscalTipo("ENTRADA");
        notaFiscal1.setNumeroDaNota(159753);
        notaFiscal1.setParceiroNegocio(parceiroNegocio3);
        notaFiscalService.salvar(notaFiscal1);
        LOG.info("Salvando nota fiscal: {}", notaFiscal1.getId());

    }
}