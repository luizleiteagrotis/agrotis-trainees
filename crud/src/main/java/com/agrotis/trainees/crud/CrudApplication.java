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
import com.agrotis.trainees.crud.service.ParceiroNegocioService;
import com.agrotis.trainees.crud.service.ProdutoService;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(CrudApplication.class);

    private final ParceiroNegocioService parceiroNegocioService;
    private final ProdutoService produtoService;
    private final NotaFiscalService notaFiscalService;
    private final NotaFiscalItemService notaFiscalItemService;

    public CrudApplication(ParceiroNegocioService parceiroNegocioService, ProdutoService produtoService,
                    NotaFiscalService notaFiscalService, NotaFiscalItemService notaFiscalItemService) {
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

        ParceiroNegocio parceiroNegocio = new ParceiroNegocio("Cocamar", "01.005.009/0001-01", "Rua Maringá, 195 - Maringá (PR)",
                        "(44) 3567-0119");
        parceiroNegocio = parceiroNegocioService.salvar(parceiroNegocio);
        LOG.info("id inserido: {}", parceiroNegocio.getId());

        parceiroNegocio = new ParceiroNegocio("Agrofertil", "02.006.012/0001-12", "Rua Curitiba, 1830 - Curitiba (PR)",
                        "(41) 3692-1009");
        parceiroNegocio = parceiroNegocioService.salvar(parceiroNegocio);
        LOG.info("id inserido: {}", parceiroNegocio.getId());

        parceiroNegocio = new ParceiroNegocio("Coamo", "03.010.020/0001-02", "Rua Campo Mourão, 10 - Campo Mourão (PR)",
                        "(44) 3522-0223");
        parceiroNegocio = parceiroNegocioService.salvar(parceiroNegocio);
        LOG.info("id inserido: {}", parceiroNegocio.getId());

        ParceiroNegocio porIdParceiro = parceiroNegocioService.buscarPorId(parceiroNegocio.getId());
        LOG.info("Busca por id. Nome {} id {} inscricaoFiscal {} endereco {} telefone {}", porIdParceiro.getNome(),
                        porIdParceiro.getId(), porIdParceiro.getInscricaoFiscal(), porIdParceiro.getEndereco(),
                        porIdParceiro.getTelefone());

        ParceiroNegocio porNomeParceiro = parceiroNegocioService.buscarPorNome(parceiroNegocio.getNome());
        LOG.info("Busca por id. Nome {} id {} inscricaoFiscal {} endereco {} telefone {}", porNomeParceiro.getNome(),
                        porNomeParceiro.getId(), porNomeParceiro.getInscricaoFiscal(), porNomeParceiro.getEndereco(),
                        porNomeParceiro.getTelefone());

        ParceiroNegocio porInscricaoFiscalParceiro = parceiroNegocioService
                        .buscarPorInscricaoFiscal(parceiroNegocio.getInscricaoFiscal());
        LOG.info("Busca por id. Nome {} id {} inscricaoFiscal {} endereco {} telefone {}", porInscricaoFiscalParceiro.getNome(),
                        porInscricaoFiscalParceiro.getId(), porInscricaoFiscalParceiro.getInscricaoFiscal(),
                        porInscricaoFiscalParceiro.getEndereco(), porInscricaoFiscalParceiro.getTelefone());

        List<ParceiroNegocio> todosSalvosParceiro = parceiroNegocioService.listarTodos();
        LOG.info("Salvos no total de {} parceiros de negócio", todosSalvosParceiro.size());

        // Ficou comentado para que não sejam perdidos os dados cadastrados
        // inicialmente
        // porNomeParceiro =
        // parceiroNegocioService.buscarPorNome(parceiroNegocio.getNome());
        // porNomeParceiro.setNome("Agrofertil");
        // porNomeParceiro.setEndereco("Rua 98 de janeiro, 98. Campo Mourão -
        // Paraná");
        // parceiroNegocioService.salvar(porNomeParceiro);
        // LOG.info("Dados alterados com sucesso!", porNomeParceiro.getNome(),
        // porNomeParceiro.getId(),
        // porNomeParceiro.getInscricaoFiscal(), porNomeParceiro.getEndereco(),
        // porIdParceiro.getTelefone());

        // o comando funcionou adequadamente, por isso, deixei a variável
        // comentada
        // parceiroNegocioService.deletarPorId(21);

        Produto produto = new Produto();
        produto.setNome("Soja");
        produto.setDescricao("Grão Plantado");
        produto.setParceiroNegocio(parceiroNegocio);
        produto.setFabricante("Coamo");
        produto.setDataFabricacao(LocalDate.of(2024, 1, 1));
        produto.setDataValidade(LocalDate.of(2024, 5, 8));
        produto = produtoService.salvar(produto);
        LOG.info("id inserido: {}", produto.getId());

        Produto porIdProduto = produtoService.buscarPorId(produto.getId());
        LOG.info("Busca por id. Nome {} id {} Descricao {} Parceiro de Negócio {} Fabricante {} Data de Fabricação {} Data de Validade {}",
                        porIdProduto.getNome(), porIdProduto.getId(), porIdProduto.getDescricao(),
                        porIdProduto.getParceiroNegocio(), porIdProduto.getFabricante(), porIdProduto.getDataFabricacao(),
                        porIdProduto.getDataValidade());

        Produto porNomeProduto = produtoService.buscarPorNome(produto.getNome());
        LOG.info("Busca por nome do Produto. Nome {} id {} Descricao {} Parceiro de Negócio {} Fabricante {} Data de Fabricação {} Data de Validade {}",
                        porNomeProduto.getNome(), porNomeProduto.getId(), porNomeProduto.getDescricao(),
                        porNomeProduto.getParceiroNegocio(), porNomeProduto.getFabricante(), porNomeProduto.getDataFabricacao(),
                        porNomeProduto.getDataValidade());

        Produto porFabricanteProduto = produtoService.buscarPorFabricante(produto.getFabricante());
        LOG.info("Busca por fabricante do Produto. Nome {} id {} Descricao {} Parceiro de Negócio {} Fabricante {} Data de Fabricação {} Data de Validade {}",
                        porFabricanteProduto.getNome(), porFabricanteProduto.getId(), porFabricanteProduto.getDescricao(),
                        porFabricanteProduto.getParceiroNegocio(), porFabricanteProduto.getFabricante(),
                        porFabricanteProduto.getDataFabricacao(), porFabricanteProduto.getDataValidade());

        Produto porDataFabricacaoProduto = produtoService.buscarPorDataFabricacao(produto.getDataFabricacao());
        LOG.info("Busca por data de fabricação do Produto. Nome {} id {} Descricao {} Parceiro de Negócio {} Fabricante {} Data de Fabricação {} Data de Validade {}",
                        porDataFabricacaoProduto.getNome(), porDataFabricacaoProduto.getId(),
                        porDataFabricacaoProduto.getDescricao(), porDataFabricacaoProduto.getParceiroNegocio(),
                        porDataFabricacaoProduto.getFabricante(), porDataFabricacaoProduto.getDataFabricacao(),
                        porDataFabricacaoProduto.getDataValidade());

        Produto porDataValidadeProduto = produtoService.buscarPorDataValidade(produto.getDataValidade());
        LOG.info("Busca por data de validade do Produto. Nome {} id {} Descricao {} Parceiro de Negócio {} Fabricante {} Data de Fabricação {} Data de Validade {}",
                        porDataValidadeProduto.getNome(), porDataValidadeProduto.getId(), porDataValidadeProduto.getDescricao(),
                        porDataValidadeProduto.getParceiroNegocio(), porDataValidadeProduto.getFabricante(),
                        porDataValidadeProduto.getDataFabricacao(), porDataValidadeProduto.getDataValidade());

        Produto porParceiroProduto = produtoService.buscarPorParceiro(produto.getParceiroNegocio());
        LOG.info("Busca por parceiro de negócio. Nome {} id {} Descricao {} Parceiro de Negócio {} Fabricante {} Data de Fabricação {} Data de Validade {}",
                        porParceiroProduto.getNome(), porParceiroProduto.getId(), porParceiroProduto.getDescricao(),
                        porParceiroProduto.getParceiroNegocio(), porParceiroProduto.getFabricante(),
                        porParceiroProduto.getDataFabricacao(), porParceiroProduto.getDataValidade());

        List<Produto> todosSalvosProduto = produtoService.listarTodos();
        LOG.info("Salvos no total de {} produtos", todosSalvosProduto.size());

        // Ficou comentado para que não sejam perdidos os dados cadastrados
        // inicialmente
        // porNomeProduto = produtoService.buscarPorNome(produto.getNome());
        // porNomeProduto.setNome("Milho");
        // porNomeProduto.setDescricao("Para alimentar animais");
        // porNomeProduto.setParceiroNegocio(parceiroNegocio);
        // porNomeProduto.setFabricante("Lierson Damares");
        // porNomeProduto.setDataFabricacao(LocalDate.of(2023, 10, 01));
        // porNomeProduto.setDataValidade(LocalDate.of(2024, 1, 01));
        // produtoService.salvar(porNomeProduto);
        // LOG.info("Dados alterados com sucesso!", porNomeProduto.getNome(),
        // porNomeProduto.getDescricao(),
        // porNomeProduto.getParceiroNegocio(), porNomeProduto.getFabricante(),
        // porNomeProduto.getDataFabricacao(),
        // porNomeProduto.getDataValidade());

        // o comando funcionou adequadamente, por isso, deixei a variável
        // comentada
        // produtoService.deletarPorId(produto.getId());

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setNotaFiscalTipo(NotaFiscalTipo.ENTRADA);
        notaFiscal.setParceiroNegocio(parceiroNegocio);
        notaFiscal.setNumeroNota(51581831);
        notaFiscal.setDataNota(LocalDate.now());
        notaFiscal = notaFiscalService.salvar(notaFiscal);
        LOG.info("id inserido: {}", parceiroNegocio.getId());

        NotaFiscal porIdNotaFiscal = notaFiscalService.buscarPorId(notaFiscal.getId());
        LOG.info("Busca por id. id {} Tipo de Nota Fiscal {} Parceiro de Negócio {} Número da Nota fiscal {} Data da Nota Fiscal {}",
                        porIdNotaFiscal.getId(), porIdNotaFiscal.getNotaFiscalTipo(), porIdNotaFiscal.getParceiroNegocio(),
                        porIdNotaFiscal.getNumeroNota(), porIdNotaFiscal.getDataNota());

        NotaFiscal porNotaFiscalTipo = notaFiscalService.buscarPorNotaFiscalTipo(NotaFiscalTipo.ENTRADA);
        LOG.info("Busca por tipo de nota fiscal. id {} Tipo de Nota Fiscal {} Parceiro de Negócio {} Número da Nota fiscal {} Data da Nota Fiscal{}",
                        porNotaFiscalTipo.getId(), porNotaFiscalTipo.getNotaFiscalTipo(), porNotaFiscalTipo.getParceiroNegocio(),
                        porNotaFiscalTipo.getNumeroNota(), porNotaFiscalTipo.getDataNota());

        NotaFiscal porParceiroNegocioNota = notaFiscalService.buscarPorParceiroNegocio(parceiroNegocio);
        LOG.info("Busca por parceiro de negócio. id {} Tipo de Nota Fiscal {} Parceiro de Negócio {} Número da Nota fiscal {} Data da Nota Fiscal {}",
                        porParceiroNegocioNota.getId(), porParceiroNegocioNota.getNotaFiscalTipo(),
                        porParceiroNegocioNota.getParceiroNegocio(), porParceiroNegocioNota.getNumeroNota(),
                        porParceiroNegocioNota.getDataNota());

        NotaFiscal porNumeroNotaFiscal = notaFiscalService.buscarPorNumero(notaFiscal.getNumeroNota());
        LOG.info("Busca por número da nota fiscal. id {} Tipo de Nota Fiscal {} Parceiro de Negócio {} Número da Nota fiscal {} Data da Nota Fiscal {}",
                        porNumeroNotaFiscal.getId(), porNumeroNotaFiscal.getNotaFiscalTipo(),
                        porNumeroNotaFiscal.getParceiroNegocio(), porNumeroNotaFiscal.getNumeroNota(),
                        porNumeroNotaFiscal.getDataNota());

        NotaFiscal porDataNota = notaFiscalService.buscarPorData(notaFiscal.getDataNota());
        LOG.info("Busca por data da nota fiscal. id {} Tipo de Nota Fiscal {} Parceiro de Negócio {} Número da Nota fiscal {} Data da Nota Fiscal {}",
                        porDataNota.getId(), porDataNota.getNotaFiscalTipo(), porDataNota.getParceiroNegocio(),
                        porDataNota.getNumeroNota(), porDataNota.getDataNota());

        List<NotaFiscal> todosSalvosNotaFiscal = notaFiscalService.listarTodos();
        LOG.info("Salvos no total de {} notas fiscais", todosSalvosNotaFiscal.size());

        // Ficou comentado para que não sejam perdidos os dados cadastrados
        // inicialmente
        // porNumeroNotaFiscal =
        // notaFiscalService.buscarPorNumero(notaFiscal.getNumeroNota());
        // porNumeroNotaFiscal.setParceiroNegocio(parceiroNegocio);
        // porNumeroNotaFiscal.setNumeroNota(896178687);
        // porNumeroNotaFiscal.setDataNota(LocalDate.of(2023, 10, 01));
        // notaFiscalService.salvar(porNumeroNotaFiscal);
        // LOG.info("Dados alterados com sucesso!",
        // porNumeroNotaFiscal.getNumeroNota(),
        // porNumeroNotaFiscal.getParceiroNegocio().getEndereco(),
        // porNumeroNotaFiscal.getNumeroNota(),
        // porNumeroNotaFiscal.getDataNota());

        // o comando funcionou adequadamente, por isso, deixei a variável
        // comentada
        // notaFiscalService.deletarPorId(notaFiscal.getId());

        NotaFiscalItem notaFiscalItem = new NotaFiscalItem();
        notaFiscalItem.setNotaFiscal(notaFiscal);
        notaFiscalItem.setProduto(produto);
        notaFiscalItem.setQuantidade(5000);
        notaFiscalItem.setPrecoUnitario(50.0);
        notaFiscalItemService.controlarEstoque(notaFiscalItem);
        notaFiscalItem = notaFiscalItemService.salvar(notaFiscalItem);
        LOG.info("id inserido: {}", notaFiscalItem.getId());
        System.out.println(notaFiscalItem.getId().toString());
        notaFiscalService.adicionarItem(notaFiscalItem);

        NotaFiscalItem porIdNotaFiscalItem = notaFiscalItemService.buscarPorId(notaFiscalItem.getId());
        if (porIdNotaFiscalItem != null) {
            LOG.info("Busca por id. id {} Nota Fiscal {} Produto {} Quantidade {} Preço Unitário {} Valor Total {}",
                            porIdNotaFiscalItem.getId(), porIdNotaFiscalItem.getNotaFiscal(), porIdNotaFiscalItem.getProduto(),
                            porIdNotaFiscalItem.getQuantidade(), porIdNotaFiscalItem.getPrecoUnitario(),
                            porIdNotaFiscalItem.getValorTotal());
        } else {
            LOG.warn("Nota Fiscal não encontrado para o id {}.", notaFiscalItem.getId());
        }

        produto = new Produto();
        produto.setNome("Milho");
        produto.setDescricao("Grão para alimentar animais");
        produto.setParceiroNegocio(parceiroNegocio);
        produto.setFabricante("Cocamar");
        produto.setDataFabricacao(LocalDate.of(2023, 4, 12));
        produto.setDataValidade(LocalDate.of(2023, 5, 10));
        produto = produtoService.salvar(produto);
        LOG.info("id inserido: {}", produto.getId());

        notaFiscalItem = new NotaFiscalItem();
        notaFiscalItem.setNotaFiscal(notaFiscal);
        notaFiscalItem.setProduto(produto);
        notaFiscalItem.setQuantidade(1000);
        notaFiscalItem.setPrecoUnitario(10.0);
        notaFiscalItemService.controlarEstoque(notaFiscalItem);
        notaFiscalItem = notaFiscalItemService.salvar(notaFiscalItem);
        LOG.info("id inserido: {}", notaFiscalItem.getId());
        System.out.println(notaFiscalItem.getId().toString());
        notaFiscalService.adicionarItem(notaFiscalItem);

        NotaFiscal porIdNotaFiscal2 = notaFiscalService.buscarPorId(notaFiscal.getId());
        LOG.info("Busca por id. id {} Tipo de Nota Fiscal {} Parceiro de Negócio {} Número da Nota fiscal {} Data da Nota Fiscal {} Valor Total",
                        porIdNotaFiscal2.getId(), porIdNotaFiscal2.getNotaFiscalTipo(), porIdNotaFiscal2.getParceiroNegocio(),
                        porIdNotaFiscal2.getNumeroNota(), porIdNotaFiscal2.getDataNota(), porIdNotaFiscal2.getValorTotal());

        // o comando funcionou adequadamente, por isso, deixei a variável
        // comentada
        // notaFiscalItemService.deletarPorId(notaFiscalItem3.getId());

        produto = new Produto();
        produto.setNome("Aveia");
        produto.setDescricao("Cereal Plantado entre safras");
        produto.setParceiroNegocio(parceiroNegocioService.buscarPorNome("Cocamar"));
        produto.setFabricante("Coamo");
        produto.setDataFabricacao(LocalDate.of(2024, 1, 1));
        produto.setDataValidade(LocalDate.of(2024, 5, 8));
        produto = produtoService.salvar(produto);
        LOG.info("id inserido: {}", produto.getId());

        notaFiscalItem = new NotaFiscalItem();
        notaFiscalItem.setNotaFiscal(notaFiscal);
        notaFiscalItem.setProduto(produto);
        notaFiscalItem.setQuantidade(2000);
        notaFiscalItem.setPrecoUnitario(20.0);
        notaFiscalItemService.controlarEstoque(notaFiscalItem);
        notaFiscalItem = notaFiscalItemService.salvar(notaFiscalItem);

        // adicionando uma saída de estoque teste
        notaFiscal.setNotaFiscalTipo(NotaFiscalTipo.SAIDA);
        notaFiscalItem.setQuantidade(1000);
        notaFiscalItemService.controlarEstoque(notaFiscalItem);
        notaFiscalService.atualizarItem(notaFiscalItem);
        notaFiscalItem = notaFiscalItemService.salvar(notaFiscalItem);
        LOG.info("id inserido: {}", notaFiscalItem.getId());
        System.out.println(notaFiscalItem.getId().toString());
        notaFiscalService.adicionarItem(notaFiscalItem);

        // adicionando uma entrada de estoque teste
        notaFiscal.setNotaFiscalTipo(NotaFiscalTipo.ENTRADA);
        notaFiscalItem.setQuantidade(1000);
        notaFiscalItemService.controlarEstoque(notaFiscalItem);
        notaFiscalService.atualizarItem(notaFiscalItem);
        notaFiscalItem = notaFiscalItemService.salvar(notaFiscalItem);
        LOG.info("id inserido: {}", notaFiscalItem.getId());
        System.out.println(notaFiscalItem.getId().toString());
        notaFiscalService.adicionarItem(notaFiscalItem);

    }

}
