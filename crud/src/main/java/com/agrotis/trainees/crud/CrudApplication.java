package com.agrotis.trainees.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.agrotis.trainees.crud.entity.CabecalhoNotaFiscal;
import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.service.CabecalhoNotaFiscalService;
import com.agrotis.trainees.crud.service.ItemNotaFiscalService;
import com.agrotis.trainees.crud.service.NotaFiscalTipoService;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;
import com.agrotis.trainees.crud.service.ProdutoService;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(CrudApplication.class);

    private final NotaFiscalTipoService notaFiscalTipoService;
    private final ParceiroNegocioService parceiroNegocioService;
    private final ProdutoService produtoService;
    private final CabecalhoNotaFiscalService cabecalhoNotaFiscalService;
    private final ItemNotaFiscalService itemNotaFiscalService;

    public CrudApplication(NotaFiscalTipoService notaFiscalTipoService, ParceiroNegocioService parceiroNegocioService,
                    ProdutoService produtoService, CabecalhoNotaFiscalService cabecalhoNotaFiscalService, ItemNotaFiscalService itemNotaFiscalService) {
        this.notaFiscalTipoService = notaFiscalTipoService;
        this.parceiroNegocioService = parceiroNegocioService;
        this.produtoService = produtoService;
        this.cabecalhoNotaFiscalService = cabecalhoNotaFiscalService;
        this.itemNotaFiscalService = itemNotaFiscalService;
    }

    public static void main(String[] args) {
        LOG.info("Iniciado com sucesso!");
        SpringApplication.run(CrudApplication.class, args);
    }

    @Override
    public void run(String... args) {

        Scanner scan = new Scanner(System.in);
        List<ParceiroNegocio> parceirosSalvos = parceiroNegocioService.listarTodos();

        List<Produto> produtosSalvos = produtoService.listarTodos();

        System.out.println(
                        "Insira um número para mexer com o CRUD do tipo nota fiscal , parceiro de negócio, produto, nota fiscal ou item nota fiscal: 1 - 2 - 3 - 4 - 5");

        int opcao = scan.nextInt();

        if (opcao == 1) {
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
        }
        if (opcao == 2) {

            // ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
            //
            // parceiroNegocio.setNome("AGROTIS");
            // parceiroNegocio.setInscricaoFiscal("111222333");
            // parceiroNegocio.setTelefone("41 9 7777 9999");
            // parceiroNegocio.setEndereco("Rua Teste 1, Curitiba");
            //
            // ParceiroNegocio parceiroNegocio2 =
            // parceiroNegocioService.salvar(parceiroNegocio);
            // LOG.info("id inserido: {}", parceiroNegocio2.getId());

            ParceiroNegocio parceiroBuscaID = parceiroNegocioService.buscarPorId(parceirosSalvos.get(0).getId());
            // parceiroNegocioService.deletarPorId(parceiroBuscaID.getId());
            LOG.info("Parceiro {} encontrado por id.", parceiroBuscaID);
            ParceiroNegocio parceiroBuscaIF = parceiroNegocioService
                            .buscarPorInscricaoFiscal(parceirosSalvos.get(1).getInscricaoFiscal());
            LOG.info("Parceiro {} encontrado por inscrição fiscal.", parceiroBuscaIF);
            ParceiroNegocio parceiroBuscaNome = parceiroNegocioService.buscarPorNome(parceirosSalvos.get(2).getNome());
            LOG.info("Parceiro {} encontrado por nome.", parceiroBuscaNome);

            // parceiroBuscaIF.setEndereco("Rua São João 93, Curitiba");
            // parceiroNegocioService.atualizar(parceiroBuscaIF.getId());
            // LOG.info("Registro do parceiro {} atualizado.", parceiroBuscaIF);
        }
        if (opcao == 3) {

            // LocalDate agora = LocalDate.now();
            // LocalDate ontem = LocalDate.of(agora.getYear(), agora.getMonth(),
            // agora.getDayOfMonth() - 1);
            // LocalDate dataValidade = LocalDate.of(ontem.getYear() + 1,
            // ontem.getMonth().getValue() + 2, ontem.getDayOfMonth() + 3);
            // Produto produto = new Produto();
            //
            // produto.setDescricao("Grão - Soja");
            // produto.setFabricante(parceirosSalvos.get(3));
            // produto.setDataFabricacao(Date.valueOf(agora));
            // produto.setDataValidade(Date.valueOf(dataValidade));
            //
            // Produto produto2 = produtoService.salvar(produto);
            // LOG.info("id inserido: {}", produto2.getId());
            //
            //
            // Produto produtoBuscaID =
            // produtoService.buscarPorId(produtosSalvos.get(1).getId());
            // LOG.info("Produto {} encontrado por id.",
            // produtoBuscaID.getId());
            // Produto produtoBuscaDescricao =
            // produtoService.buscarPorDescricao(produtosSalvos.get(1).getDescricao());
            // LOG.info("Produto {} encontrado por descrição.",
            // produtoBuscaDescricao);
            // Produto produtoBuscaDescricaoInexistente =
            // produtoService.buscarPorDescricao("aaaaa");
            //
            // produtoBuscaID.setDescricao("Outros Insumos - BIG BAG");
            // produtoBuscaID.setFabricante(parceirosSalvos.get(3));
            // produtoService.atualizar(produtoBuscaID.getId());
            // LOG.info("Registro do produto {} atualizado.", produtoBuscaID);

            // produtoService.deletarPorId(produtoBuscaID.getId());
            // LOG.info("Registro do parceiro {} deletado.", produtoBuscaID);
        }
        if (opcao == 4) {

            LocalDate agora = LocalDate.now();
            LocalDate dataEmissao = LocalDate.of(agora.getYear(), agora.getMonth(), agora.getDayOfMonth());

//            CabecalhoNotaFiscal cabecalhoNota = new CabecalhoNotaFiscal();
//            cabecalhoNota.setTipoNota("ENTRADA");
//            cabecalhoNota.setNumero(1);
//            cabecalhoNota.setDataEmissao(Date.valueOf(dataEmissao));
//            cabecalhoNota.setParceiro(parceirosSalvos.get(0));
//
//            CabecalhoNotaFiscal cabecalhoNota2 = cabecalhoNotaFiscalService.salvar(cabecalhoNota);
//            LOG.info("id inserido: {}", cabecalhoNota2.getId());
            
            List<CabecalhoNotaFiscal> notasFiscaisSalvas = cabecalhoNotaFiscalService.listarTodos();
            
            for (CabecalhoNotaFiscal cabecalhoNota : notasFiscaisSalvas) {
                System.out.println(cabecalhoNota);
            }
            
            CabecalhoNotaFiscal notaFiscalBuscaID = cabecalhoNotaFiscalService.buscarPorId(notasFiscaisSalvas.get(0).getId());
            LOG.info("Nota Fiscal {} encontrado por id.", notaFiscalBuscaID.getId());
            
            CabecalhoNotaFiscal notaFiscalBuscaNumero = cabecalhoNotaFiscalService.buscarPorNumero(notasFiscaisSalvas.get(1).getId());
            LOG.info("Nota Fiscal {} encontrado por numero.", notaFiscalBuscaNumero.getNumero());
            
            List<CabecalhoNotaFiscal> listNotasFiscaisBuscaTipo = cabecalhoNotaFiscalService.buscarPorTipo(notasFiscaisSalvas.get(2).getTipoNota());
            LOG.info("{} notas fiscais encontradas para o tipo {}.",listNotasFiscaisBuscaTipo.size(),notasFiscaisSalvas.get(2).getTipoNota() );
            
            List<CabecalhoNotaFiscal> listNotasBuscaParceiro = cabecalhoNotaFiscalService.buscarPorParceiro(notasFiscaisSalvas.get(3).getParceiro());
            LOG.info("{} notas fiscais encontradas para o parceiro {}.",listNotasBuscaParceiro.size(),notasFiscaisSalvas.get(3).getParceiro());
            
            notaFiscalBuscaID.setTipoNota("SAIDA");
            notaFiscalBuscaID.setNumero(2);
            
            notaFiscalBuscaID = cabecalhoNotaFiscalService.atualizar(notaFiscalBuscaID.getId());
            LOG.info("Registro da nota fiscal {} atualizado.", notaFiscalBuscaID);
            
            cabecalhoNotaFiscalService.deletarPorId(notaFiscalBuscaID.getId());
            LOG.info("Registro da nota fiscal {} removido.", notaFiscalBuscaID);
        }
        if (opcao == 5) {
        	
        	ItemNotaFiscal itemNota = new ItemNotaFiscal();
        	itemNota.setPrecoUnitario(BigDecimal.valueOf(2.5));
        	itemNota.setProduto(produtosSalvos.get(0));
        	itemNota.setQuantidade(50);
        	itemNota.calculaValorTotal();
        	
        	List<ItemNotaFiscal> itensNotaSalvos = itemNotaFiscalService.listarTodos();
        	
        	ItemNotaFiscal itemNotaBuscaID = itemNotaFiscalService.buscarPorId(itensNotaSalvos.get(0).getId());
        	LOG.info("Item Nota Fiscal {} encontrado por id.", itemNotaBuscaID.getId());
        	
        	ItemNotaFiscal itemNotaBuscaProduto = itemNotaFiscalService.buscarPorProduto(produtosSalvos.get(1));
        	LOG.info("Item Nota Fiscal {} encontrado por produto.", itemNotaBuscaProduto.getId());
        	
        	itemNotaBuscaID.setPrecoUnitario(BigDecimal.valueOf(3.5));
        	itemNotaFiscalService.atualizar(itemNotaBuscaID.getId());
        	LOG.info("Registro do item nota fiscal {} atualizado.", itemNotaBuscaID);
        	
        	itemNotaFiscalService.deletarPorId(itemNotaBuscaID.getId());
            LOG.info("Registro do item nota fiscal {} removido.", itemNotaBuscaID);
        }
    }
}
