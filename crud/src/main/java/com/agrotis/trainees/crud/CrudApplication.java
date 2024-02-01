package com.agrotis.trainees.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.helper.TipoNotaFiscal;
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

    public CrudApplication(NotaFiscalTipoService notaFiscalTipoService, ParceiroNegocioService parceiroNegocioService,
                    ProdutoService produtoService, NotaFiscalService notaFiscalService) {
        this.notaFiscalTipoService = notaFiscalTipoService;
        this.parceiroNegocioService = parceiroNegocioService;
        this.produtoService = produtoService;
        this.notaFiscalService = notaFiscalService;
    }

    public static void main(String[] args) {
        LOG.info("Iniciado com sucesso!");
        SpringApplication.run(CrudApplication.class, args);
    }

    @Override
    public void run(String... args) {

        // atualizar.getNome(), atualizar.getInscricaoFiscal());
        // NotaFiscalTipo notaFiscalTipo = new NotaFiscalTipo();
        // notaFiscalTipo.setNome("nomeTeste");
        // NotaFiscalTipo notaFiscalTipo2 =
        // notaFiscalTipoService.salvar(notaFiscalTipo);
        // LOG.info("id inserido: {}", notaFiscalTipo2.getId());
        // NotaFiscalTipo porIdNF =
        // notaFiscalTipoService.buscarPorId(notaFiscalTipo2.getId());
        // LOG.info("Busca por id. Nome {} id {} ", porIdNF.getNome(),
        // porIdNF.getId());
        // List<NotaFiscalTipo> todosSalvos =
        // notaFiscalTipoService.listarTodos();
        // LOG.info("Salvos no total de {} tipos de notas", todosSalvos.size());
        // NotaFiscalTipo porNome =
        // notaFiscalTipoService.buscarPorNome(notaFiscalTipo.getNome());
        // porNome.setNome("nomeAlterado");
        // notaFiscalTipoService.salvar(porNome);
        // LOG.info("Busca por nome. Nome {} id {} ", porNome.getNome(),
        // porNome.getId());
        //
        // //notaFiscalTipoService.deletarPorId(porId.getId());
        // notaFiscalTipoService.buscarPorId(notaFiscalTipo2.getId());
        // notaFiscalTipoService.buscarPorNome(notaFiscalTipo.getNome());

        //////////////////////////////////////////////////////////

        // try {
        // ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
        // parceiroNegocio.setNome("Hadassa e Tatiane Esportes Ltda");
        // parceiroNegocio.setInscricaoFiscal("10199709302155");
        // parceiroNegocio.setEndereco("Moradias Bom Jesus");
        // parceiroNegocio.setTelefone("41992477204");
        // // Create
        // ParceiroNegocio parceiroNegocio2 =
        // parceiroNegocioService.salvar(parceiroNegocio);
        // LOG.info("id inserio: {}", parceiroNegocio2.getId());
        //
        // // select
        // ParceiroNegocio porId =
        // parceiroNegocioService.buscarPorId(parceiroNegocio2.getId());
        // LOG.info("Buscar por id. Nome {} id {}", porId.getNome(),
        // porId.getId());
        // ParceiroNegocio porInscricaoFiscal = parceiroNegocioService
        // .buscarPorInscricaoFiscal(parceiroNegocio2.getInscricaoFiscal());
        // LOG.info("Buscar por if. Nome {} inscricao fiscal {}",
        // porId.getNome(), porInscricaoFiscal.getInscricaoFiscal());
        // List<ParceiroNegocio> listarTodos =
        // parceiroNegocioService.listarTodos();
        // for (ParceiroNegocio parceiro : listarTodos) {
        // LOG.info("Buscar por todos. Nome {} inscricao fiscal {}",
        // parceiro.getNome(), parceiro.getInscricaoFiscal());
        // }
        //
        // // update
        // ParceiroNegocio atualizar =
        // parceiroNegocioService.atualizar("19199704001134", "Hadassa e Tatiane
        // Esportes Ltda",
        // "19199704001134", "Jose Lins do Rego", "41992477204");
        // LOG.info("Atualizado. Nome {} inscricao fiscal {}",
        // atualizar.getNome(), atualizar.getInscricaoFiscal());
        //
        // // delete
        // parceiroNegocioService.deletar(54);
        //
        // } catch (IllegalArgumentException exp) {
        // System.out.println("Houve um erro ao tentar fazer alguma operação no
        // banco");
        // }

        //////////////////////////////

        // ParceiroNegocio parceiroProduto =
        // parceiroNegocioService.buscarPorId(40);
        // Produto produto = new Produto("Manga", Date.valueOf("2024-01-01"),
        // Date.valueOf("2024-06-20"), parceiroProduto);
        //
        // // create
        // Produto salvarProduto = produtoService.salvar(produto);
        // LOG.info("id inserio: {}", salvarProduto.getId());
        //
        // // select por ID
        // Produto procurarProdutoPorID = produtoService.buscarPorId(89);
        // LOG.info("o ID trazido foi {}", procurarProdutoPorID.getId());
        //
        // // select por Data de Fabricacao
        // List<Produto> procurarDataFabricacao =
        // produtoService.buscarPorDataFabricacao(Date.valueOf("2024-01-01"));
        // for (Produto produtos : procurarDataFabricacao) {
        // LOG.info("o nome trazido foi {}", produtos.getNome());
        // }
        //
        // // select por Data de Validade
        // List<Produto> procurarDatavalidade =
        // produtoService.buscarPorDataValidade(Date.valueOf("2024-06-20"));
        // for (Produto produtos : procurarDatavalidade) {
        // LOG.info("A data trazida foi {}", produtos.getDataValidade());
        // }
        //
        // // select por nome
        //
        // List<Produto> procurarNome = produtoService.buscarPorNome("Mimosa");
        // for (Produto nProdutos : procurarNome) {
        // LOG.info("O nome trazido foi {} e o id {} ", nProdutos.getNome(),
        // nProdutos.getId());
        // }
        //
        // // select por Fornecedor
        //
        // List<Produto> procurarFornecedor =
        // produtoService.buscarPorFornecedor(parceiroProduto);
        // for (Produto fProdutos : procurarFornecedor) {
        // LOG.info("O parceiro trazido foi {} e o id {} ",
        // fProdutos.getFabricante().getNome(),
        // fProdutos.getFabricante().getId());
        // }
        //
        // // select todos
        // List<Produto> listarTodos = produtoService.listarTodos();
        // for (Produto lProdutos : listarTodos) {
        // LOG.info("O id foi {}", lProdutos.getId());
        // }
        //
        // // update
        // Produto produtoAtualizado = new Produto("Café",
        // Date.valueOf("2023-03-01"), Date.valueOf("2024-01-01"),
        // parceiroProduto);
        // produtoAtualizado.setDescricao("Produto de alta qualidade");
        // Produto atualizar = produtoService.atualizar(produtoAtualizado, 105);
        // LOG.info("Produto atualizado {} ", atualizar);
        //
        // // delete
        // produtoService.excluir(111);

        //////////////////////////////////////////////////////////////

        String tipoEntrada = TipoNotaFiscal.ENTRADA.getDescricao();
        String tipoSaida = TipoNotaFiscal.SAIDA.getDescricao();

        ParceiroNegocio parceiroNota = parceiroNegocioService.buscarPorId(40);
        NotaFiscal nota = new NotaFiscal();
        nota.setParceiroNegocio(parceiroNota);
        nota.setNumero(1);
        nota.setTipo(tipoEntrada);
        nota.setData(Date.valueOf("2023-10-10"));

        NotaFiscal salvar = notaFiscalService.salvar(nota);
        LOG.info("salvo id {}", salvar);

    }
}
