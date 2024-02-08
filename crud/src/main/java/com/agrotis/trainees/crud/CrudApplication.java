package com.agrotis.trainees.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.service.ItemNotaFiscalService;
import com.agrotis.trainees.crud.service.NotaFiscalService;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;
import com.agrotis.trainees.crud.service.ProdutoService;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(CrudApplication.class);

    private final ParceiroNegocioService parceiroNegocioService;
    private final ProdutoService produtoService;
    private final NotaFiscalService notaFiscalService;
    private final ItemNotaFiscalService itemNotaFiscalService;

    public CrudApplication(ParceiroNegocioService parceiroNegocioService, ProdutoService produtoService,
                    NotaFiscalService notaFiscalService, ItemNotaFiscalService itemNotaFiscalService) {

        this.parceiroNegocioService = parceiroNegocioService;
        this.produtoService = produtoService;
        this.notaFiscalService = notaFiscalService;
        this.itemNotaFiscalService = itemNotaFiscalService;
    }

    public static void main(String[] args) {
        LOG.info("Iniciado com sucesso!");
        SpringApplication.run(CrudApplication.class, args);
    }

    @Override
    public void run(String... args) {

        LOG.info("-------------------------------------------------PARCEIRO DE NEGOCIOS---------------------------------------------------------------------------------------");

        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
        parceiroNegocio.setNome("AGROTIS AGRO");
        parceiroNegocio.setInscricaoFiscal("135694442-0001");
        parceiroNegocio.setEndereco("Rua 13 de maio");
        parceiroNegocio.setTelefone("41996483268");

        ParceiroNegocio parceiroNegocio2 = parceiroNegocioService.salvar(parceiroNegocio);
        LOG.info("ID Inserido: {}", parceiroNegocio2.getId());

        ParceiroNegocio porId1 = parceiroNegocioService.buscarPorId(parceiroNegocio2.getId());
        LOG.info("Busca por id. Nome {} id {} ", porId1.getNome(), porId1.getId());

        List<ParceiroNegocio> todosSalvos2 = parceiroNegocioService.listarTodos();
        LOG.info("Salvos no total de {} TIPOS de Parceiros de Negocios", todosSalvos2.size());

        ParceiroNegocio porNome2 = parceiroNegocioService.buscarPorNome(parceiroNegocio.getNome());
        porNome2.setNome("AGROTIS AGROINFORMATICA");

        parceiroNegocioService.salvar(porNome2);
        LOG.info("Busca por Nome. Nome {} id {} ", porNome2.getNome(), porNome2.getId());

        parceiroNegocioService.buscarPorId(parceiroNegocio2.getId());
        parceiroNegocioService.buscarPorNome(parceiroNegocio2.getNome());
        parceiroNegocioService.deletarPorId(parceiroNegocio2.getId());

        LOG.info("------------------------------------------------------PRODUTO---------------------------------------------------------------------------------");

        Produto produto = new Produto();

        produto.setDescricao("Grao Plantado");
        produto.setNome("AGROTIS");
        produto.setFabricante(parceiroNegocio);
        produto.setDataFabricacao(LocalDate.parse("09/05/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        produto.setDataValidade(LocalDate.parse("09/05/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        produto.setEstoque(12);
        produtoService.salvar(produto);

        List<Produto> buscarTodos = produtoService.listarTodos();
        LOG.info("Salvos no total de {} parceiros de negocio.", buscarTodos.size());

        Produto novoProduto = new Produto();
        novoProduto.setDescricao("Trigo");
        novoProduto.setNome("AGROTIS AGRO");
        novoProduto.setFabricante(parceiroNegocio);
        novoProduto.setDataFabricacao(LocalDate.parse("09/05/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        novoProduto.setDataValidade(LocalDate.parse("09/05/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        produto.setEstoque(15);
        produtoService.update(produto.getId(), novoProduto);
        LOG.info("Produto ATUALIZADO {}", produto.getId());

        produtoService.deletarPorId(produto.getId());
        LOG.info("Produto DELETADO {}", produto.getId());

        LOG.info("---------------------------------------------------NOTA FISCAL------------------------------------------------------------------------------------");

        NotaFiscal notaFiscal = new NotaFiscal();

        notaFiscal.setData(LocalDate.parse("09/05/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        notaFiscal.setTipo(NotaFiscalTipo.ENTRADA);
        notaFiscal.setNumero(210798);
        notaFiscal.setParceiroNegocio(parceiroNegocio2);
        notaFiscalService.salvar(notaFiscal);

        LOG.info("Nota Fiscal Salva: {}", notaFiscal.getId());

        NotaFiscal notaFiscalSaida = new NotaFiscal();
        notaFiscalSaida.setData(LocalDate.parse("09/05/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        notaFiscalSaida.setTipo(NotaFiscalTipo.SAIDA);
        notaFiscalSaida.setNumero(6193007);
        notaFiscalSaida.setParceiroNegocio(parceiroNegocio2);
        notaFiscalService.salvar(notaFiscalSaida);
        LOG.info("Nota Fiscal Salva: {}", notaFiscalSaida.getId());

        notaFiscalService.buscarPorId(notaFiscal.getId());
        LOG.info("Em processo de busca da nota por ID {}", notaFiscal.getId());

        notaFiscalService.update(notaFiscalSaida.getId(), notaFiscalSaida);
        LOG.info("Atualizando a Nota Fiscal pelo ID {}", notaFiscalSaida.getId());

        List<NotaFiscal> notasFiscais = notaFiscalService.listarTodos();
        {
            LOG.info("Listas de notas fiscais: ", notasFiscais.size());
        }

        notaFiscalService.deletarPorId(notaFiscalSaida.getId());
        LOG.info("A nota {}", notaFiscalSaida.getId(), "Foi deletada");

        LOG.info("---------------------------------------------NOTA FISCAL ITEM----------------------------------------------------------------------------------------------");

        ItemNotaFiscal itemNota = new ItemNotaFiscal();

        itemNota.setNotaFiscal(notaFiscal);
        itemNota.setProduto(produto);
        itemNota.setPrecoUnitario(19.90);
        itemNota.setQuantidade(5);
        System.out.println(itemNota.getValorTotal());

        itemNotaFiscalService.salvar(itemNota);

        List<ItemNotaFiscal> listItem = itemNotaFiscalService.buscarTodos();
        LOG.info("Buscando Item Nota Fiscal: {} ", listItem.size());

        itemNotaFiscalService.buscarPorId(itemNota.getId());

        ItemNotaFiscal itemUpdate = new ItemNotaFiscal();
        itemNota.setNotaFiscal(notaFiscal);
        itemNota.setProduto(novoProduto);
        itemNota.setPrecoUnitario(19.90);
        itemNota.setQuantidade(5);

        itemNotaFiscalService.update(itemNota.getId(), itemUpdate);

        itemNotaFiscalService.salvar(itemNota);

    }

}
