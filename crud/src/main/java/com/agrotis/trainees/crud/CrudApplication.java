package com.agrotis.trainees.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.NotaFiscalDuplicadaException;
import com.agrotis.trainees.crud.service.ItemNotaFiscalService;
import com.agrotis.trainees.crud.service.NotaFiscalService;
import com.agrotis.trainees.crud.service.NotaFiscalTipoService;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;
import com.agrotis.trainees.crud.service.ProdutoService;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(CrudApplication.class);

    private final NotaFiscalTipoService notaFiscalTipoService;

    private final ParceiroNegocioService parceiroNegocioService;

    private final ProdutoService produtoService;

    private final NotaFiscalService notaFiscalService;

    private final ItemNotaFiscalService itemNotaFiscalService;

    public CrudApplication(NotaFiscalTipoService notaFiscalTipoService, ParceiroNegocioService parceiroNegocioService,
                    ProdutoService produtoService, NotaFiscalService notaFiscalService,
                    ItemNotaFiscalService itemNotaFiscalService) {
        this.notaFiscalTipoService = notaFiscalTipoService;
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
    public void run(String... args) throws ParseException {

        Scanner scan = new Scanner(System.in);
        System.out.println(
                        "Digite o número correspondente para mexer com o CRUD do tipo nota fiscal , parceiro de negócio, produto, nota fiscal ou item nota fiscal: 1 2 3 4 5");
        int escolha = scan.nextInt();

        if (escolha == 1) {
            NotaFiscalTipo notaFiscalTipo = new NotaFiscalTipo();

            notaFiscalTipo.setNome("Saída");
            NotaFiscalTipo notaFiscalTipo2 = notaFiscalTipoService.salvar(notaFiscalTipo);
            LOG.info("id inserido: {}", notaFiscalTipo2.getId());

            NotaFiscalTipo porId = notaFiscalTipoService.buscarPorId(notaFiscalTipo2.getId());
            LOG.info("Busca por id. Nome {} id {} ", porId.getNome(), porId.getId());

            List<NotaFiscalTipo> todosSalvos = notaFiscalTipoService.listarTodos();
            LOG.info("Salvos no total de {} tipos de notas", todosSalvos.size());

            // NotaFiscalTipo porNome =
            // notaFiscalTipoService.buscarPorNome(notaFiscalTipo.getNome());
            // porNome.setNome("nomeAlterado");
            // notaFiscalTipoService.salvar(porNome);
            // LOG.info("Busca por nome. Nome {} id {} ", porNome.getNome(),
            // porNome.getId());

            // notaFiscalTipoService.deletarPorId(porId.getId());
            notaFiscalTipoService.buscarPorId(notaFiscalTipo2.getId());
            notaFiscalTipoService.buscarPorNome(notaFiscalTipo.getNome());

        }

        if (escolha == 2) {
            // ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
            //
            // parceiroNegocio.setNome("Fazendas do Rio Grnade");
            //
            // parceiroNegocio.setInscricaoFiscal("92939495");
            // parceiroNegocio.setEndereco("Rio Grande, rua Grãos, 15");
            // parceiroNegocio.setTelefone("11 9 5454 5454");
            //
            // try {
            // ParceiroNegocio parceiroNegocio2 =
            // parceiroNegocioService.salvar(parceiroNegocio);
            // LOG.info("id inserido: {}", parceiroNegocio2.getId());
            // } catch (Exception e) {
            // System.out.println("Tratamento de exceção: " + e.getMessage());
            // }

            // ParceiroNegocio parceiroPorId =
            // parceiroNegocioService.buscarPorId(1);
            // LOG.info("Busca por id. Nome {} Inscricao Fiscal {} Endereco {}
            // Telefone {} id {}", parceiroPorId.getNome(),
            // parceiroPorId.getInscricaoFiscal(), parceiroPorId.getEndereco(),
            // parceiroPorId.getTelefone(),
            // parceiroPorId.getId());

            // ParceiroNegocio parceiroPorNome =
            // parceiroNegocioService.buscarPorNome(parceiroNegocio2.getNome());
            // LOG.info("Busca por nome. Nome {} id {}",
            // parceiroPorNome.getNome(), parceiroPorNome.getId());
            //
            // List<ParceiroNegocio> todosParceiros =
            // parceiroNegocioService.listarTodos();
            // LOG.info("Salvos no total de {} tipos de parceiros",
            // todosParceiros.size());

            ParceiroNegocio parceiroPorNome2 = parceiroNegocioService.buscarPorNome("Parceiro3");
            parceiroPorNome2.setEndereco("Rua das merces");
            parceiroPorNome2.setNome("Parceiro4");
            parceiroPorNome2.setInscricaoFiscal("776655");
            try {
                parceiroNegocioService.atualizar(parceiroPorNome2);
                LOG.info("Nome, endereco e telefone atualizado com sucesso, novo nome: {}, novo endereco: {}, novo telefone: {} ",
                                parceiroPorNome2.getNome(), parceiroPorNome2.getEndereco(), parceiroPorNome2.getTelefone());
            } catch (Exception e) {
                System.out.println("Tratamento de exceção: " + e.getMessage());
            }

            // parceiroNegocioService.deletarPorId(parceiroPorId.getId());
        }

        if (escolha == 3) {

            // Produto produto = new Produto();
            //
            // ParceiroNegocio fabricante =
            // parceiroNegocioService.buscarPorId(272);
            // produto.setDescricao("Arroz");
            // produto.setFabricante(fabricante);
            //
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            //
            // Date fabricacaoDate = dateFormat.parse("31-12-2024");
            // produto.setDataFabricacao(fabricacaoDate);
            //
            // Date validaDate = dateFormat.parse("08-08-2025");
            // produto.setDataValidade(validaDate);
            //
            // try {
            // Produto produto2 = produtoService.salvar(produto);
            // } catch (Exception e) {
            // System.out.println("Tratamento de exceção: " + e.getMessage());
            // }

            // Produto produtoPorId = produtoService.buscarPorId(134);
            // LOG.info("Busca por id. Descrição {} Data Fabricação {} Data
            // Validade {} Nome da empresa parceira {} quantidade em estoque
            // {}",
            // produtoPorId.getDescricao(), produtoPorId.getDataFabricacao(),
            // produtoPorId.getDataValidade(),
            // produtoPorId.getFabricante(),
            // produtoPorId.getQuantidade_estoque());
            //
            // produtoPorId.setQuantidade_estoque(500);
            // produtoService.salvar(produtoPorId);
            // Produto produtoPorDescricao =
            // produtoService.buscarPorDescricao(produto2.getDescricao());
            // LOG.info("Busca por descricao. Descrição {} Data Fabricação {}
            // Data Validade {} Nome da empresa parceira {}",
            // produtoPorDescricao.getDescricao(),
            // produtoPorDescricao.getDataFabricacao(),
            // produtoPorDescricao.getDataValidade(),
            // produtoPorDescricao.getParceiroNegocio());
            //
            // List<Produto> todosProdutos = produtoService.listarTodos();
            // LOG.info("Salvos no total de {} tipos de produtos",
            // todosProdutos.size());
            //
            Produto produtoPorDescricao2 = produtoService.buscarPorDescricao("Arroz");
            Date novaValidadeDate = dateFormat.parse("03-07-2060");
            produtoPorDescricao2.setDataValidade(novaValidadeDate);
            produtoPorDescricao2.setDescricao("Abacaxi verde");
            try {
                produtoService.atualizar(produtoPorDescricao2);
                LOG.info("Nova descrição {}, nova data validade {} e novo parceiro {}: ", produtoPorDescricao2.getDescricao(),
                                produtoPorDescricao2.getDataValidade(), produtoPorDescricao2.getFabricante());
            } catch (Exception e) {
                System.out.println("Tratamento de exceção: " + e.getMessage());
            }

            //// produtoService.deletarPorId(produtoPorId.getId());
        }

        if (escolha == 4) {

            // NotaFiscal notaFiscal = new NotaFiscal();
            //
            // ParceiroNegocio parceiro =
            // parceiroNegocioService.buscarPorId(113);
            //
            // notaFiscal.setNumero("51");
            // NotaFiscalTipo notaTipo = notaFiscalTipoService.buscarPorId(165);
            // notaFiscal.setParceiroNegocio(parceiro);
            // notaFiscal.setNotaFiscalTipo(notaTipo);
            //
            // try {
            // NotaFiscal notaFiscal2 = notaFiscalService.salvar(notaFiscal);
            // LOG.info("id inserido: {}", notaFiscal2.getId());
            // } catch (NotaFiscalDuplicadaException e) {
            // System.out.println("Tratamento de exceção " + e.getMessage());
            // }
            //
            NotaFiscal notaFiscalPorId = notaFiscalService.buscarPorId(178);
            // LOG.info("Busca por id. Numero {} Data {} Nome da empresa
            // parceira {} e tipo {}", notaFiscalPorId.getNumero(),
            // notaFiscalPorId.getData(), notaFiscalPorId.getParceiroNegocio(),
            // notaFiscalPorId.getNotaFiscalTipo());

            // List<NotaFiscal> todasNotasFiscais =
            // notaFiscalService.buscarPorNumero("20");
            // LOG.info("Salvos no total de {} tipos de notas fiscais",
            // todasNotasFiscais.size());
            //
            // List<NotaFiscal> todasNotasFiscais2 =
            // notaFiscalService.listarTodos();
            // LOG.info("Salvos no total de {} tipos de notas fiscais",
            // todasNotasFiscais2.size());
            //
            // NotaFiscalTipo novaNotaTipo =
            // notaFiscalTipoService.buscarPorId(162);
            notaFiscalPorId.setParceiroNegocio(parceiroNegocioService.buscarPorId(274));
            try {
                notaFiscalService.salvar(notaFiscalPorId);
                LOG.info("Novo numero {} e nova data {}", notaFiscalPorId.getNumero(), notaFiscalPorId.getData());
            } catch (NotaFiscalDuplicadaException e) {
                System.out.println("Tratamento de exceção: " + e.getMessage());
            }

            // notaFiscalService.deletarPorId(171);

        }

        if (escolha == 5) {
            ItemNotaFiscal itemNotaFiscal = new ItemNotaFiscal();

            Produto produto = produtoService.buscarPorId(183);
            itemNotaFiscal.setProduto(produto);

            NotaFiscal notaFiscal = notaFiscalService.buscarPorId(276);
            itemNotaFiscal.setNotaFiscal(notaFiscal);
            notaFiscalService.atualizarValorTotal(notaFiscal);
            itemNotaFiscal.setValorUnitario(BigDecimal.valueOf(2000));
            itemNotaFiscal.setQuantidade(30);

            try {
                ItemNotaFiscal itemNotaFiscal2 = itemNotaFiscalService.salvar(itemNotaFiscal);
                LOG.info("id inserido {}.", itemNotaFiscal2.getId());
                notaFiscalService.adicionarItem(itemNotaFiscal2, notaFiscal);
            } catch (Exception e) {
                System.out.println("Tratamento de exceção: " + e.getMessage());
            }

            // // notaFiscalService.removerItem(itemNotaFiscal2, notaFiscal);

            ItemNotaFiscal itemPorId = itemNotaFiscalService.buscarPorId(326);
            LOG.info("Busca por id. Quantidade {} valor unitário {} valortotal {}", itemPorId.getQuantidade(),
                            itemPorId.getValorUnitario(), itemPorId.getValorTotal());

            // List<ItemNotaFiscal> itemPorProduto =
            // itemNotaFiscalService.buscarPorProduto(produto);
            // LOG.info("Salvos no total de {} itens notas fiscal",
            // itemPorProduto.size());
            //
            // List<ItemNotaFiscal> todosItemsFiscals =
            // itemNotaFiscalService.listarTodos();
            // LOG.info("Salvos no total de {} itens notas fiscal",
            // todosItemsFiscals.size());

            itemPorId.setQuantidade(100);
            // try {
            // ItemNotaFiscal novoItemNotaFiscal =
            // itemNotaFiscalService.atualizar(itemPorId);
            // notaFiscalService.atualizarValorTotal(novoItemNotaFiscal.getNotaFiscal());
            // } catch (Exception e) {
            // System.out.println("Tratamento de exceção: " + e.getMessage());
            // }

            // NotaFiscal notaFiscal2 =
            // notaFiscalService.buscarPorId(notaFiscal.getId());
            //
            // notaFiscalService.atualizarValorTotal(notaFiscal2);

            // itemNotaFiscalService.deletarPorId(265);
            // NotaFiscal notaFiscal3 =
            // notaFiscalService.buscarPorId(notaFiscal.getId());
            // notaFiscalService.atualizarValorTotal(notaFiscal3);
        }

        if (escolha == 6) {
            NotaFiscalTipo tipoNota = notaFiscalTipoService.buscarPorId(165);
            ParceiroNegocio fabricante = parceiroNegocioService.buscarPorId(274);

            // Produto produto = new Produto();
            // produto.setDescricao("Aveia");
            // produto.setFabricante(fabricante);
            // produto.setQuantidade_estoque(10);
            //
            // SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            //
            // Date dataFabricado = dateFormat.parse("02-09-2010");
            // Date dataValidade = dateFormat.parse("10-03-2011");
            // produto.setDataFabricacao(dataFabricado);
            // produto.setDataValidade(dataValidade);
            //
            // Produto produto2 = produtoService.salvar(produto);

            ItemNotaFiscal itemNotaFiscal = new ItemNotaFiscal();

            itemNotaFiscal.setValorUnitario(BigDecimal.valueOf(1170));
            itemNotaFiscal.setQuantidade(300);
            itemNotaFiscal.setProduto(produtoService.buscarPorId(296));

            // ItemNotaFiscal itemNotaFiscal2 = new ItemNotaFiscal();
            //
            // itemNotaFiscal2.setValor_unitario(BigDecimal.valueOf(200));
            // itemNotaFiscal2.setQuantidade(5);
            // itemNotaFiscal2.setProduto(produtoService.buscarPorId(295));

            // NotaFiscal notaFiscal = new NotaFiscal();
            //
            // notaFiscal.setNumero("34");
            // notaFiscal.setParceiroNegocio(fabricante);
            // notaFiscal.setNotaFiscalTipo(tipoNota);
            //
            // NotaFiscal notaFiscal2 = notaFiscalService.salvar(notaFiscal);

            NotaFiscal notaFiscal2 = notaFiscalService.buscarPorId(276);

            // itemNotaFiscal2.setNotaFiscal(notaFiscal2);
            itemNotaFiscal.setNotaFiscal(notaFiscal2);

            try {
                ItemNotaFiscal itemNotaFiscal3 = itemNotaFiscalService.salvar(itemNotaFiscal);
                notaFiscalService.adicionarItem(itemNotaFiscal3, notaFiscal2);
                System.out.println(notaFiscal2.getValorTotal());
            } catch (Exception e) {
                System.out.println("Tratamento de exceção: " + e.getMessage());
            }

            // try {
            // ItemNotaFiscal itemNotaFiscal4 =
            // itemNotaFiscalService.salvar(itemNotaFiscal2);
            // notaFiscalService.adicionarItem(itemNotaFiscal4, notaFiscal2);
            // System.out.println(notaFiscal2.getValor_total());
            // } catch (QuantidadeInsuficienteException e) {
            // System.out.println("Tratamento de exceção: " + e.getMessage());
            // }

        }

        if (escolha == 7) {
            notaFiscalService.atualizarValorTotal(notaFiscalService.buscarPorId(276));
        }
    }
}
