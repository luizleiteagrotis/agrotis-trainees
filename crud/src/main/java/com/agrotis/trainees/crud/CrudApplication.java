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
import java.util.Scanner;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
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
    private ParceiroNegocio parceiroNegocio2;
    private ParceiroNegocioTipoService parceiroNegocioTipoService;
    private ProdutoTipoService produtoTipoService;
    private NotaFiscalService notaFiscalService;
    private NotaFiscalItemService notaFiscalItemService;

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
        Scanner scanner = new Scanner(System.in);

        while (true) {
            exibirMenu();

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    operacoesParceiroNegocio();
                    break;
                case 2:
                    operacoesProduto();
                    break;
                case 3:
                    operacoesNotaFiscal();
                    break;
                case 4:
                    operacoesNotaFiscalItem();
                    break;
                case 0:
                    LOG.info("Encerrando o programa. Até logo!");
                    System.exit(0);
                default:
                    LOG.warn("Opção inválida. Tente novamente.");
            }
        }
    }

    private void exibirMenu() {
        LOG.info("\n----- MENU -----");
        LOG.info("1. Operações com Parceiro de Negócio");
        LOG.info("2. Operações com Produto");
        LOG.info("3. Operações com Nota Fiscal");
        LOG.info("4. Operações com Nota Fiscal Item");
        LOG.info("0. Sair");
        LOG.info("-----------------\n");
        LOG.info("Escolha uma opção:");
    }

    private void operacoesParceiroNegocio() {
        LOG.info("\n----- Operações com Parceiro de Negócio -----");

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
    }

    private void operacoesProduto() {
        LOG.info("\n----- Operações com Produto -----");
        Produto produto = new Produto();
        parceiroNegocio2 = new ParceiroNegocio();

        parceiroNegocio2.setNome("Agrotis");
        parceiroNegocio2.setInscricaoFiscal("123456789");
        LOG.info("id inserido: {}", parceiroNegocio2.getId());

        parceiroNegocio2 = parceiroNegocioTipoService.salvar(parceiroNegocio2);

        produto.setDataFabricacao(LocalDate.parse("08/01/2025", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        produto.setDataValidade(LocalDate.parse("09/12/2014", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        produto.setDescricao("Milho");
        produto.setFabricante(parceiroNegocio2);
        produto.setEstoque(15);

        produtoTipoService.salvar(produto);
        LOG.info("id inserido: {}", produto.getId());

        Produto produtoPorId = produtoTipoService.buscarPorId(produto.getId());
        LOG.info("Busca por id. Descrição {} id {} ", produtoPorId.getDescricao(), produtoPorId.getId());

        Produto produtoPorDescricao = produtoTipoService.buscarPorDescricao(produto.getDescricao());
        LOG.info("Busca por descrição. Nome {} id {} ", produtoPorDescricao.getDescricao(), produtoPorDescricao.getId());

        List<Produto> todosProdutosSalvos = produtoTipoService.listarTodos();
        LOG.info("Salvos no total de {} tipos de produtos", todosProdutosSalvos.size());

        Produto porDescricao = produtoTipoService.buscarPorDescricao(produto.getDescricao());
        porDescricao.setDescricao("Milho");
        produtoTipoService.salvar(porDescricao);
        LOG.info("Busca por descrição. Descrição {} id {} ", porDescricao.getDescricao(), porDescricao.getId());

        // produtoTipoService.deletarProdutoPorId(produtoPorId.getId());
    }

    private void operacoesNotaFiscal() {
        LOG.info("\n----- Operações com Nota Fiscal -----");
        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setNotaFiscalTipo(NotaFiscalTipo.SAIDA);
        notaFiscal.setParceiroNegocio(parceiroNegocio2);
        notaFiscal.setNumero(48686425);
        notaFiscal.setDataEmissao(LocalDate.now());

        NotaFiscal savedNotaFiscal = notaFiscalService.salvar(notaFiscal);
        LOG.info("id inserido: {}", savedNotaFiscal.getId());

        NotaFiscal notaPorId = notaFiscalService.buscarPorId(notaFiscal.getId());
        LOG.info("Busca por id. Tipo Nota fiscal {} id {} ", notaPorId.getNotaFiscalTipo(), notaPorId.getId());

        notaFiscalService.atualizar(notaFiscal.getId(), notaFiscal);
        LOG.info("Atualizando pelo id {}", notaFiscal.getId());

        List<NotaFiscal> notas = notaFiscalService.listarTodos();
        LOG.info("Tamanho da lista: {}", notas.size());

        // notaFiscalService.deletarPorId(notaFiscal2.getId());
        // LOG.info("Deletando a nota {}", notaFiscal2.getId());
    }

    private void operacoesNotaFiscalItem() {
        LOG.info("\n----- Operações com Nota Fiscal Item -----");

        NotaFiscal notaFiscal = notaFiscalService.buscarPorId(542);
        Produto produto = produtoTipoService.buscarPorId(541);

        NotaFiscalItem notaFiscalItem = new NotaFiscalItem();
        notaFiscalItem.setNotaFiscal(notaFiscal);
        notaFiscalItem.setProduto(produto);
        notaFiscalItem.setQuantidade(5);
        notaFiscalItem.setPreco_unitario(10.0);

        NotaFiscalItem savedNotaFiscalItem = notaFiscalItemService.salvar(notaFiscalItem);
        LOG.info("ID do NotaFiscalItem inserido: {}", savedNotaFiscalItem.getId());

        savedNotaFiscalItem.setQuantidade(8);
        savedNotaFiscalItem.setPreco_unitario(15.0);
        notaFiscalItemService.salvar(savedNotaFiscalItem);
        LOG.info("NotaFiscalItem atualizado com sucesso. ID: {}", savedNotaFiscalItem.getId());

        List<NotaFiscalItem> todosItens = notaFiscalItemService.buscarTodos();
        LOG.info("Total de NotaFiscalItens: {}", todosItens.size());

        // // Deletar Item da Nota Fiscal
        // notaFiscalItemService.deletarPorId(savedNotaFiscalItem.getId());
        // LOG.info("NotaFiscalItem deletado com sucesso. ID: {}",
        // savedNotaFiscalItem.getId());
    }

}
