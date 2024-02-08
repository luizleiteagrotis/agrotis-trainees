package com.agrotis.trainees.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.service.NotaFiscalItemService;
import com.agrotis.trainees.crud.service.NotaFiscalService;
import com.agrotis.trainees.crud.service.NotaFiscalTipoService;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;
import com.agrotis.trainees.crud.service.ProdutoService;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(CrudApplication.class);

    private final NotaFiscalTipoService notaFiscalTipoService;
    private final ParceiroNegocioService parceiroNegociosService;
    private final ProdutoService produtoService;
    private final NotaFiscalService notaFiscalService;
    private final NotaFiscalItemService notaFiscalItemService;

    public CrudApplication(NotaFiscalTipoService notaFiscalTipoService, ParceiroNegocioService parceiroNegociosService,
                    ProdutoService produtoService, NotaFiscalService notaFiscalService,
                    NotaFiscalItemService notaFiscalItemService) {

        this.notaFiscalTipoService = notaFiscalTipoService;
        this.parceiroNegociosService = parceiroNegociosService;
        this.produtoService = produtoService;
        this.notaFiscalService = notaFiscalService;
        this.notaFiscalItemService = notaFiscalItemService;
    }

    public static void main(String[] args) {
        LOG.info("Iniciado com sucesso!");
        SpringApplication.run(CrudApplication.class, args);
    }

    @Override
    public void run(String... args) {
        int atividadeAtual = 8;
        if (atividadeAtual < 1) {
            NotaFiscalTipo notaFiscalTipo = new NotaFiscalTipo();
            notaFiscalTipo.setNome("nomeTeste");
            NotaFiscalTipo notaFiscalTipo2 = notaFiscalTipoService.salvar(notaFiscalTipo);
            LOG.info("id inserido: {}", notaFiscalTipo2.getId());

            NotaFiscalTipo porId = notaFiscalTipoService.buscarPorId(notaFiscalTipo2.getId());
            LOG.info("Busca por id. Nome {} id {} ", porId.getNome(), porId.getId());

            List<NotaFiscalTipo> todosSalvos = notaFiscalTipoService.listarTodos();
            LOG.info("Salvos no total de {} tipos de notas", todosSalvos.size());

            NotaFiscalTipo porNome = notaFiscalTipoService.buscarPorNome(notaFiscalTipo.getNome());
            porNome.setNome("nomeAlterado");
            notaFiscalTipoService.salvar(porId);
            // LOG.info("Busca por nome. Nome {} id {} ", porNome.getNome(),
            // porNome.getId());

            // notaFiscalTipoService.deletarPorId(porId.getId());
            notaFiscalTipoService.buscarPorId(notaFiscalTipo2.getId());
            notaFiscalTipoService.buscarPorNome(notaFiscalTipo.getNome());
        } else if (atividadeAtual < 4) {
            ParceiroNegocio parceiroNegocios = new ParceiroNegocio();
            parceiroNegocios.setNome("Fabio Santos");
            parceiroNegocios.setInscricaoFiscal("854.965.92");
            parceiroNegocios.setEndereco("Disney, 2");
            parceiroNegocios.setTelefone("587489632");
            ParceiroNegocio parceiroNegocios2 = parceiroNegociosService.salvar(parceiroNegocios);
            LOG.info("id inserido: {}", parceiroNegocios2.getId());

            ParceiroNegocio porId = parceiroNegociosService.buscarPorId(parceiroNegocios2.getId());
            LOG.info("Busca por id. Nome {}, Inscrição Fiscal {}, Endereço {}, Telefone ", porId.getNome(),
                            porId.getInscricaoFiscal(), porId.getEndereco(), porId.getTelefone());

            ParceiroNegocio porInscricao = parceiroNegociosService.buscarPorInscricaoFiscal(parceiroNegocios2.getInscricaoFiscal());
            LOG.info("Busca por inscrição fiscal. Nome {}, Endereço {}, Telefone {} ", porInscricao.getNome(),
                            porInscricao.getEndereco(), porInscricao.getTelefone());

            List<ParceiroNegocio> porNome = parceiroNegociosService.buscarPorNome(parceiroNegocios2.getNome());
            if (!porNome.isEmpty()) {
                for (ParceiroNegocio parceiro : porNome) {
                    LOG.info("Nome: {}, Inscrição Fiscal {}, Endereço {}, Telefone {}", parceiro.getNome(),
                                    parceiro.getInscricaoFiscal(), parceiro.getEndereco(), parceiro.getTelefone());
                }
            } else {
                LOG.info("Nenhum Resultado encontrado para o nome : {}", parceiroNegocios2.getNome());
            }

            List<ParceiroNegocio> todosSalvos = parceiroNegociosService.listarTodos();
            LOG.info("Salvos no total de {} tipos de notas", todosSalvos.size());

            // porNome =
            // parceiroNegociosService.buscarPorNome(parceiroNegocios.getNome());
            // porNome.setNome("Sabrina");
            // porNome.setEndereco("globo");
            // porNome.setTelefone("258749632");
            // parceiroNegociosService.salvar(porNome);
            LOG.info("Dados alterados com sucesso");

            if (atividadeAtual == 3) {
                // porId = parceiroNegociosService.buscarPorId(12);
                // parceiroNegociosService.deletarPorId(porId.getId());
                // LOG.info("O usuario {} foi deletado", porId.getNome());
            }

        } else if (atividadeAtual < 6) {

            Produto produto = new Produto();
            ParceiroNegocio fabricante = parceiroNegociosService.buscarPorId(4);

            produto.setDescricao("Limão");
            produto.setIdParceiro(fabricante);
            produto.setDataFabricacao(Date.valueOf("2021-01-01"));
            produto.setDataValidade(Date.valueOf("2024-05-05"));
            produto.setEstoque(0);
            Produto produto2 = produtoService.salvar(produto);
            LOG.info("id inserido: {}", produto2.getId());

            Produto porId = produtoService.buscarPorId(produto2.getId());
            LOG.info("Busca por Id. Descrição {}, Fabricante {}", porId.getDescricao(), porId.getIdParceiro().getNome());

            Produto porDescricao = produtoService.buscarPorDescricao(produto2.getDescricao());
            LOG.info("Busca por Descrição {}, Data de Fabricação {}, Data de Validade {}", porDescricao.getDescricao(),
                            porDescricao.getDataFabricacao(), porDescricao.getDataValidade());

            List<Produto> todosSalvos = produtoService.listarTodos();
            LOG.info("Salvos no total de {} tipos de notas", todosSalvos.size());

            porId = produtoService.buscarPorId(17);
            porId.setDescricao("Uva niagara");
            porId.setDataFabricacao(Date.valueOf("2023-02-07"));
            porId.setDataValidade(Date.valueOf("2024-07-08"));
            produtoService.salvar(porId);
            LOG.info("Dados alterados com sucesso");

            if (atividadeAtual == 5) {
                porId = produtoService.buscarPorId(22);
                produtoService.deletarPorId(porId.getId());

            }
        } else if (atividadeAtual < 8) {
            NotaFiscal notaCabecalho = new NotaFiscal();
            NotaFiscalTipo tipo = notaFiscalTipoService.buscarPorId(2);
            ParceiroNegocio parceiro = parceiroNegociosService.buscarPorId(4);

            notaCabecalho.setTipo(tipo);
            notaCabecalho.setParceiro(parceiro);
            notaCabecalho.setDataEmissao(LocalDate.of(2023, 04, 07));
            notaFiscalService.gerarNumero(notaCabecalho);
            NotaFiscal notaCabecalho2 = notaFiscalService.salvar(notaCabecalho);

            NotaFiscal porId = notaFiscalService.buscarPorId(notaCabecalho2.getId());
            LOG.info("Busca por Id. Tipo de nota: {} ; Parceiro: {} ; Numero da nota: {}; Data de Emissão: {}",
                            porId.getTipo().getNome(), porId.getParceiro().getNome(), porId.getNumero(), porId.getDataEmissao());

            NotaFiscal porNumeroETipo = notaFiscalService.buscarPorTipoeNumero(notaCabecalho2.getTipo(),
                            notaCabecalho2.getNumero());
            LOG.info("Busca por numero da nota e tipo. Tipo de nota: {} ; Parceiro: {} ; Numero da nota: {}; Data de Emissão: {}",
                            porNumeroETipo.getTipo().getNome(), porNumeroETipo.getParceiro().getNome(), porNumeroETipo.getNumero(),
                            porNumeroETipo.getDataEmissao());

            List<NotaFiscal> todosSalvos = notaFiscalService.listarTodos();
            LOG.info("Salvos no total de {} tipos de notas", todosSalvos.size());

            porId = notaFiscalService.buscarPorId(41);
            porId.setParceiro(parceiroNegociosService.buscarPorId(4));
            porId.setDataEmissao(LocalDate.of(2021, 12, 30));
            notaFiscalService.salvar(porId);
            LOG.info("Dados alterados com sucesso");

            if (atividadeAtual == 7) {
                porId = notaFiscalService.buscarPorId(56);
                notaFiscalService.deletarPorId(porId.getId());

            }

        } else if (atividadeAtual == 8) {
            NotaFiscal nota = notaFiscalService.buscarPorId(73);
            NotaFiscalItem item = new NotaFiscalItem();
            Produto produto = produtoService.buscarPorId(24);

            item.setIdNota(nota);
            item.setProduto(produto);
            item.setQuantidade(30);
            item.setPrecoUnitario(4.99);
            NotaFiscalItem item2 = notaFiscalItemService.salvar(item);

            // NotaFiscal nota2 = notaFiscalService.salvar(nota);
            // NotaFiscalItem porId =
            // notaFiscalItemService.buscarPorId(item2.getId());
            // LOG.info("Busca por Id. Produto {} ; Preço {} ; Quantidade {}
            // ;Preço Total {}", porId.getProduto().getDescricao(),
            // porId.getPrecoUnitario(), porId.getQuantidade(),
            // porId.getValorTotal());
            //
            // NotaFiscalItem porProduto =
            // notaFiscalItemService.buscarPorProduto(item2.getProduto());
            // LOG.info("Buscar pelo Produto {}. Preço {} ; Quantidade ;
            // PreçoTotal {}", porProduto.getProduto().getDescricao(),
            // porProduto.getPrecoUnitario(), porProduto.getQuantidade(),
            // porProduto.getValorTotal());
            //
            // List<NotaFiscalItem> todosSalvos =
            // notaFiscalItemService.listarTodos();
            // LOG.info("Salvos no total de {} itens", todosSalvos.size());
            //
            // porId = notaFiscalItemService.buscarPorId(60);
            // porId.setProduto(produtoService.buscarPorId(21));
            // porId.setQuantidade(7);
            // porId.setPrecoUnitario(21.05);
            // porId.setValorTotal(porId.getQuantidade() *
            // porId.getPrecoUnitario());
            // notaFiscalItemService.salvar(porId);
            // LOG.info("Dados alterados com sucesso");
            //
            // valorFinal =
            // notaFiscalItemService.obterOValorTotalDaNota(porId.getIdNota());
            // LOG.info("Valor Total da Nota: {}", valorFinal);

        } else if (atividadeAtual == 9) {
            NotaFiscalItem porId = notaFiscalItemService.buscarPorId(64);
            notaFiscalItemService.deletarPorId(porId.getId());
        }

    }

}
