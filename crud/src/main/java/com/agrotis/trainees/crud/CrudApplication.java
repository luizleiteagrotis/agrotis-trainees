package com.agrotis.trainees.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
    private final ParceiroNegocioService parceiroNegocioService;
    private final ProdutoService produtoService;
    private final NotaFiscalService notaFiscalService;
    private final NotaFiscalItemService notaFiscalItemService;

    public CrudApplication(ParceiroNegocioService parceiroNegocioService, NotaFiscalTipoService notaFiscalTipoService,
                    ProdutoService produtoService, NotaFiscalService notaFiscalService,
                    NotaFiscalItemService notaFiscalItemService) {
        this.notaFiscalTipoService = notaFiscalTipoService;
        this.parceiroNegocioService = parceiroNegocioService;
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
        notaFiscalTipoService.salvar(porNome);
        LOG.info("Busca por nome. Nome {} id {} ", porNome.getNome(), porNome.getId());

        // notaFiscalTipoService.deletarPorId(porId.getId());
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
        LOG.info("Busca por id. Nome {} id {} inscricaoFiscal {} endereco {} telefone {}", porIdParceiro.getNome(),
                        porIdParceiro.getId(), porIdParceiro.getInscricaoFiscal(), porIdParceiro.getEndereco(),
                        porIdParceiro.getTelefone());

        ParceiroNegocio porNomeParceiro = parceiroNegocioService.buscarPorNome(parceiroNegocio2.getNome());
        LOG.info("Busca por id. Nome {} id {} inscricaoFiscal {} endereco {} telefone {}", porNomeParceiro.getNome(),
                        porNomeParceiro.getId(), porNomeParceiro.getInscricaoFiscal(), porNomeParceiro.getEndereco(),
                        porNomeParceiro.getTelefone());

        ParceiroNegocio porInscricaoFiscalParceiro = parceiroNegocioService
                        .buscarPorInscricaoFiscal(parceiroNegocio2.getInscricaoFiscal());
        LOG.info("Busca por id. Nome {} id {} inscricaoFiscal {} endereco {} telefone {}", porInscricaoFiscalParceiro.getNome(),
                        porInscricaoFiscalParceiro.getId(), porInscricaoFiscalParceiro.getInscricaoFiscal(),
                        porInscricaoFiscalParceiro.getEndereco(), porInscricaoFiscalParceiro.getTelefone());

        List<ParceiroNegocio> todosSalvosParceiro = parceiroNegocioService.listarTodos();
        LOG.info("Salvos no total de {} parceiros de negócio", todosSalvos.size());

        porNomeParceiro = parceiroNegocioService.buscarPorNome(parceiroNegocio.getNome());
        porNomeParceiro.setNome("Agrofertil");
        porNomeParceiro.setEndereco("Rua 98 de janeiro, 98. Campo Mourão - Paraná");
        parceiroNegocioService.salvar(porNomeParceiro);
        LOG.info("Dados alterados com sucesso!", porNomeParceiro.getNome(), porNomeParceiro.getId(),
                        porNomeParceiro.getInscricaoFiscal(), porNomeParceiro.getEndereco(), porIdParceiro.getTelefone());

        boolean parceiroDeletar = false; // o comando funcionou adequadamente,
                                         // por isso, deixei a variável como
                                         // false
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
        LOG.info("Busca por id. Nome {} id {} Descricao {} Parceiro de Negócio {} Fabricante {} Data de Fabricação {} Data de Validade {}",
                        porIdProduto.getNome(), porIdProduto.getId(), porIdProduto.getDescricao(),
                        porIdProduto.getParceiroNegocio(), porIdProduto.getFabricante(), porIdProduto.getDataFabricacao(),
                        porIdProduto.getDataValidade());

        Produto porNomeProduto = produtoService.buscarPorNome(produto2.getNome());
        LOG.info("Busca por nome do Produto. Nome {} id {} Descricao {} Parceiro de Negócio {} Fabricante {} Data de Fabricação {} Data de Validade {}",
                        porNomeProduto.getNome(), porNomeProduto.getId(), porNomeProduto.getDescricao(),
                        porNomeProduto.getParceiroNegocio(), porNomeProduto.getFabricante(), porNomeProduto.getDataFabricacao(),
                        porNomeProduto.getDataValidade());

        Produto porFabricanteProduto = produtoService.buscarPorFabricante(produto2.getFabricante());
        LOG.info("Busca por fabricante do Produto. Nome {} id {} Descricao {} Parceiro de Negócio {} Fabricante {} Data de Fabricação {} Data de Validade {}",
                        porFabricanteProduto.getNome(), porFabricanteProduto.getId(), porFabricanteProduto.getDescricao(),
                        porFabricanteProduto.getParceiroNegocio(), porFabricanteProduto.getFabricante(),
                        porFabricanteProduto.getDataFabricacao(), porFabricanteProduto.getDataValidade());

        Produto porDataFabricacaoProduto = produtoService.buscarPorDataFabricacao(produto2.getDataFabricacao());
        LOG.info("Busca por data de fabricação do Produto. Nome {} id {} Descricao {} Parceiro de Negócio {} Fabricante {} Data de Fabricação {} Data de Validade {}",
                        porDataFabricacaoProduto.getNome(), porDataFabricacaoProduto.getId(),
                        porDataFabricacaoProduto.getDescricao(), porDataFabricacaoProduto.getParceiroNegocio(),
                        porDataFabricacaoProduto.getFabricante(), porDataFabricacaoProduto.getDataFabricacao(),
                        porDataFabricacaoProduto.getDataValidade());

        Produto porDataValidadeProduto = produtoService.buscarPorDataValidade(produto2.getDataValidade());
        LOG.info("Busca por data de validade do Produto. Nome {} id {} Descricao {} Parceiro de Negócio {} Fabricante {} Data de Fabricação {} Data de Validade {}",
                        porDataValidadeProduto.getNome(), porDataValidadeProduto.getId(), porDataValidadeProduto.getDescricao(),
                        porDataValidadeProduto.getParceiroNegocio(), porDataValidadeProduto.getFabricante(),
                        porDataValidadeProduto.getDataFabricacao(), porDataValidadeProduto.getDataValidade());

        Produto porParceiroProduto = produtoService.buscarPorParceiro(produto2.getParceiroNegocio());
        LOG.info("Busca por parceiro de negócio. Nome {} id {} Descricao {} Parceiro de Negócio {} Fabricante {} Data de Fabricação {} Data de Validade {}",
                        porParceiroProduto.getNome(), porParceiroProduto.getId(), porParceiroProduto.getDescricao(),
                        porParceiroProduto.getParceiroNegocio(), porParceiroProduto.getFabricante(),
                        porParceiroProduto.getDataFabricacao(), porParceiroProduto.getDataValidade());

        List<Produto> todosSalvosProduto = produtoService.listarTodos();
        LOG.info("Salvos no total de {} produtos", todosSalvos.size());

        porNomeProduto = produtoService.buscarPorNome(produto.getNome());
        porNomeProduto.setNome("Milho");
        porNomeProduto.setDescricao("Para alimentar animais");
        porNomeProduto.setParceiroNegocio(parceiroNegocio2);
        porNomeProduto.setFabricante("Lierson Damares");
        porNomeProduto.setDataFabricacao(LocalDate.of(2023, 10, 01));
        porNomeProduto.setDataValidade(LocalDate.of(2024, 2, 01));
        produtoService.salvar(porNomeProduto);
        LOG.info("Dados alterados com sucesso!", porNomeProduto.getNome(), porNomeProduto.getDescricao(),
                        porNomeProduto.getParceiroNegocio(), porNomeProduto.getFabricante(), porNomeProduto.getDataFabricacao(),
                        porNomeProduto.getDataValidade());

        boolean produtoDeletar = false; // o comando funcionou adequadamente,
                                        // por isso, deixei a variável como
                                        // false para envio
        if (produtoDeletar == true) {
            produtoService.deletarPorId(produto2.getId());
        }

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setNotaFiscalTipo(notaFiscalTipo2);
        notaFiscal.setParceiroNegocio(parceiroNegocio2);
        notaFiscal.setNumeroNota(51581831);
        notaFiscal.setDataNota(LocalDate.now());
        NotaFiscal notaFiscal2 = notaFiscalService.salvar(notaFiscal);
        LOG.info("id inserido: {}", parceiroNegocio2.getId());

        NotaFiscal porIdNotaFiscal = notaFiscalService.buscarPorId(notaFiscal2.getId());
        LOG.info("Busca por id. id {} Tipo de Nota Fiscal {} Parceiro de Negócio {} Número da Nota fiscal {} Data da Nota Fiscal {}",
                        porIdNotaFiscal.getId(), porIdNotaFiscal.getNotaFiscalTipo(), porIdNotaFiscal.getParceiroNegocio(),
                        porIdNotaFiscal.getNumeroNota(), porIdNotaFiscal.getDataNota());

        NotaFiscal porNotaFiscalTipo = notaFiscalService.buscarPorNotaFiscalTipo(notaFiscalTipo2);
        LOG.info("Busca por tipo de nota fiscal. id {} Tipo de Nota Fiscal {} Parceiro de Negócio {} Número da Nota fiscal {} Data da Nota Fiscal {}",
                        porNotaFiscalTipo.getId(), porNotaFiscalTipo.getNotaFiscalTipo(), porNotaFiscalTipo.getParceiroNegocio(),
                        porNotaFiscalTipo.getNumeroNota(), porNotaFiscalTipo.getDataNota());

        NotaFiscal porParceiroNegocioNota = notaFiscalService.buscarPorParceiroNegocio(parceiroNegocio2);
        LOG.info("Busca por parceiro de negócio. id {} Tipo de Nota Fiscal {} Parceiro de Negócio {} Número da Nota fiscal {} Data da Nota Fiscal {}",
                        porParceiroNegocioNota.getId(), porParceiroNegocioNota.getNotaFiscalTipo(),
                        porParceiroNegocioNota.getParceiroNegocio(), porParceiroNegocioNota.getNumeroNota(),
                        porParceiroNegocioNota.getDataNota());

        NotaFiscal porNumeroNotaFiscal = notaFiscalService.buscarPorNumero(notaFiscal2.getNumeroNota());
        LOG.info("Busca por número da nota fiscal. id {} Tipo de Nota Fiscal {} Parceiro de Negócio {} Número da Nota fiscal {} Data da Nota Fiscal {}",
                        porNumeroNotaFiscal.getId(), porNumeroNotaFiscal.getNotaFiscalTipo(),
                        porNumeroNotaFiscal.getParceiroNegocio(), porNumeroNotaFiscal.getNumeroNota(),
                        porNumeroNotaFiscal.getDataNota());

        NotaFiscal porDataNota = notaFiscalService.buscarPorData(notaFiscal2.getDataNota());
        LOG.info("Busca por data da nota fiscal. id {} Tipo de Nota Fiscal {} Parceiro de Negócio {} Número da Nota fiscal {} Data da Nota Fiscal {}",
                        porDataNota.getId(), porDataNota.getNotaFiscalTipo(), porDataNota.getParceiroNegocio(),
                        porDataNota.getNumeroNota(), porDataNota.getDataNota());

        List<NotaFiscal> todosSalvosNotaFiscal = notaFiscalService.listarTodos();
        LOG.info("Salvos no total de {} notas fiscais", todosSalvos.size());

        porNumeroNotaFiscal = notaFiscalService.buscarPorNumero(notaFiscal.getNumeroNota());
        porNumeroNotaFiscal.setParceiroNegocio(parceiroNegocio2);
        porNumeroNotaFiscal.setNumeroNota(896178687);
        porNumeroNotaFiscal.setDataNota(LocalDate.of(2023, 10, 01));
        notaFiscalService.salvar(porNumeroNotaFiscal);
        LOG.info("Dados alterados com sucesso!", porNumeroNotaFiscal.getNumeroNota(),
                        porNumeroNotaFiscal.getParceiroNegocio().getEndereco(), porNumeroNotaFiscal.getNumeroNota(),
                        porNumeroNotaFiscal.getDataNota());

        boolean notaFiscalDeletar = false; // o comando funcionou adequadamente,
                                           // por isso, deixei a variável como
                                           // false para envio
        if (notaFiscalDeletar == true) {
            notaFiscalService.deletarPorId(notaFiscal2.getId());
        }

        NotaFiscalItem notaFiscalItem = new NotaFiscalItem();
        notaFiscalItem.setNotaFiscal(notaFiscal2);
        notaFiscalItem.setProduto(produto2);
        notaFiscalItem.setQuantidade(2000);
        notaFiscalItem.setPrecoUnitario(20.0);
        NotaFiscalItem notaFiscalItem2 = notaFiscalItemService.salvar(notaFiscalItem);
        LOG.info("id inserido: {}", notaFiscalItem2.getId());
        System.out.println(notaFiscalItem2.getId().toString());

        NotaFiscalItem porIdNotaFiscalItem2 = notaFiscalItemService.buscarPorId(notaFiscalItem2.getId());
        if (porIdNotaFiscalItem2 != null) {
            LOG.info("Busca por id. id {} Nota Fiscal {} Produto {} Quantidade {} Preço Unitário {} Valor Total {}",
                            porIdNotaFiscalItem2.getId(), porIdNotaFiscalItem2.getNotaFiscal(), porIdNotaFiscalItem2.getProduto(),
                            porIdNotaFiscalItem2.getQuantidade(), porIdNotaFiscalItem2.getPrecoUnitario(),
                            porIdNotaFiscalItem2.getValorTotal());
        } else {
            LOG.warn("Nota Fiscal não encontrado para o id {}.", notaFiscalItem2.getId());
        }

        produto.setNome("Milho");
        produto.setDescricao("Grão Plantado");
        produto.setParceiroNegocio(parceiroNegocio2);
        produto.setFabricante("Coamo");
        produto.setDataFabricacao(LocalDate.of(2023, 4, 12));
        produto.setDataValidade(LocalDate.of(2023, 5, 10));
        Produto produto3 = produtoService.salvar(produto);
        LOG.info("id inserido: {}", produto.getId());

        notaFiscalItem.setNotaFiscal(notaFiscal2);
        notaFiscalItem.setProduto(produto3);
        notaFiscalItem.setQuantidade(1000);
        notaFiscalItem.setPrecoUnitario(10.0);
        NotaFiscalItem notaFiscalItem3 = notaFiscalItemService.salvar(notaFiscalItem);
        LOG.info("id inserido: {}", notaFiscalItem3.getId());
        System.out.println(notaFiscalItem3.getId().toString());

        notaFiscalService.adicionarItem(notaFiscal2, notaFiscalItem2);
        notaFiscalService.adicionarItem(notaFiscal2, notaFiscalItem3);

        NotaFiscal porIdNotaFiscal2 = notaFiscalService.buscarPorId(notaFiscal2.getId());
        LOG.info("Busca por id. id {} Tipo de Nota Fiscal {} Parceiro de Negócio {} Número da Nota fiscal {} Data da Nota Fiscal {} Valor Total",
                        porIdNotaFiscal2.getId(), porIdNotaFiscal2.getNotaFiscalTipo(), porIdNotaFiscal2.getParceiroNegocio(),
                        porIdNotaFiscal2.getNumeroNota(), porIdNotaFiscal2.getDataNota(), porIdNotaFiscal2.getValorTotal());

    }

}
