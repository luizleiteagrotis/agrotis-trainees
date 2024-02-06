package com.agrotis.trainees.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.service.NotaFiscalItemService;
import com.agrotis.trainees.crud.service.NotaFiscalService;
import com.agrotis.trainees.crud.service.ParceiroNegocioTipoService;
import com.agrotis.trainees.crud.service.ProdutoTipoService;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(CrudApplication.class);

    private final ParceiroNegocioTipoService parceiroNegocioTipoService;
    private final ProdutoTipoService produtoTipoService;
    private final NotaFiscalService notaFiscalService;
    private final NotaFiscalItemService notaFiscalItemService;

    @Autowired
    public CrudApplication(ParceiroNegocioTipoService parceiroNegocioTipoService, ProdutoTipoService produtoTipoService,
                    NotaFiscalService notaFiscalService, NotaFiscalItemService notaFiscalItemService) {
        this.parceiroNegocioTipoService = parceiroNegocioTipoService;
        this.produtoTipoService = produtoTipoService;
        this.notaFiscalService = notaFiscalService;
        this.notaFiscalItemService = notaFiscalItemService;
    }

    public static void main(String[] args) {
        LOG.info("Iniciado com sucesso!");
        SpringApplication.run(CrudApplication.class, args);

    }

    @Override
    public void run(String... args) {

        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
        parceiroNegocio.setNome("Agrotis Informatica ");
        parceiroNegocio.setInscricaoFiscal("82.413.816/0001-01");
        parceiroNegocio.setEndereco("Rua 13 de maio");
        parceiroNegocio.setTelefone("4135238200");

        ParceiroNegocio parceiroNegocioSalvo = parceiroNegocioTipoService.salvar(parceiroNegocio);

        ParceiroNegocio parceiroPorId = parceiroNegocioTipoService.buscarPorId(parceiroNegocio.getId());
        LOG.info("Busca por id. Nome {} id {} ", parceiroPorId.getNome(), parceiroPorId.getId());

        ParceiroNegocio parceiroNegocioPorNome = parceiroNegocioTipoService.buscarPorNome(parceiroNegocio.getNome());
        LOG.info("Busca por nome. Nome {} id {} ", parceiroNegocioPorNome.getNome(), parceiroNegocioPorNome.getId());

        List<ParceiroNegocio> todosParceirosSalvos = parceiroNegocioTipoService.listarTodos();
        LOG.info("Salvos no total de {} tipos de Parceiros", todosParceirosSalvos.size());

        ParceiroNegocio parceiroPorNome = parceiroNegocioTipoService.buscarPorNome(parceiroNegocio.getNome());
        parceiroPorNome.setNome("Agrotis informática");
        parceiroNegocioTipoService.salvar(parceiroPorNome);
        LOG.info("Busca por nome. Nome {} id {} ", parceiroPorNome.getNome(), parceiroPorNome.getId());

        // parceiroNegocioTipoService.deletarPorId(parceiroPorId.getId());

        // Produto
        Produto produto = new Produto();
        ParceiroNegocio parceiroNegocio2 = new ParceiroNegocio();
        parceiroNegocio2.setNome("Agrotis");
        parceiroNegocio2.setInscricaoFiscal("82.413.816/0001-01");
        parceiroNegocio2.setEndereco("Rua 13 de maio");
        parceiroNegocio2.setTelefone("4135238200");

        parceiroNegocioTipoService.salvar(parceiroNegocio2);
        LOG.info("id inserido: {}", parceiroNegocio2.getId());

        produto.setDataFabricacao(LocalDate.parse("08/01/2025", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        produto.setDataValidade(LocalDate.parse("09/12/2014", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        produto.setDescricao("Algodão");
        produto.setFabricante(parceiroNegocio2);

        produtoTipoService.salvar(produto);
        LOG.info("id inserido: {}", produto.getId());

        Produto produtoPorId = produtoTipoService.buscarPorId(produto.getId());
        LOG.info("Busca por id. Descrição {} id {} ", produtoPorId.getDescricao(), produtoPorId.getId());

        Produto produtoPorDescricao = produtoTipoService.buscarPorDescricao(produto.getDescricao());
        LOG.info("Busca por descrição. Nome {} id {} ", produtoPorDescricao.getDescricao(), produtoPorDescricao.getId());
        //

        List<Produto> todosProdutosSalvos = produtoTipoService.listarTodos();
        LOG.info("Salvos no total de {} tipos de produtos", todosProdutosSalvos.size());

        Produto porDescricao = produtoTipoService.buscarPorDescricao(produto.getDescricao());
        porDescricao.setDescricao("Milho");
        produtoTipoService.salvar(porDescricao);
        LOG.info("Busca por descrição. Descrição {} id {} ", porDescricao.getDescricao(), porDescricao.getId());

        // produtoTipoService.deletarProdutoPorId(produtoPorId.getId());

        // NotaFiscal

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setNotaFiscalTipo(NotaFiscalTipo.ENTRADA);
        notaFiscal.setParceiroNegocio(parceiroNegocio2);
        notaFiscal.setNumero(48686425);
        notaFiscal.setDataEmissao(LocalDate.now());
        //
        NotaFiscal savedNotaFiscal = notaFiscalService.salvar(notaFiscal);
        LOG.info("id inserido: {}", savedNotaFiscal.getId());

        NotaFiscal notaPorId = notaFiscalService.buscarPorId(notaFiscal.getId());
        LOG.info("Busca por id. Tipo Nota fiscal {} id {} ", notaPorId.getNotaFiscalTipo(), notaPorId.getId());

        notaFiscalService.atualizar(notaFiscal.getId(), notaFiscal);
        LOG.info("Atualizando pelo id {}", notaFiscal.getId());

        List<NotaFiscal> notas = notaFiscalService.listarTodos();
        LOG.info("Tamanho da lista: ", notas.size());

        // notaFiscalService.deletarPorId(notaFiscal2.getId());
        // LOG.info("Deletando a nota {}", notaFiscal2.getId() ); }
        //
        // // NotaFiscalItem
        // NotaFiscalItem notaFiscalItem = new NotaFiscalItem();
        // NotaFiscal notaFiscal3 = new NotaFiscal();
        // notaFiscal3.setNotaFiscalTipo(NotaFiscalTipo.ENTRADA);
        // notaFiscal3.setParceiroNegocio(parceiroNegocio2);
        // notaFiscal3.setNumero(48686425);
        // notaFiscal3.setDataEmissao(LocalDate.now());
        // notaFiscalItem.setNotaFiscal(notaFiscal3);

    }
}
