package com.agrotis.trainees.crud;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.TipoNota;
import com.agrotis.trainees.crud.service.CabecalhoNotaService;
import com.agrotis.trainees.crud.service.ItemNotaService;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;
import com.agrotis.trainees.crud.service.ProdutoService;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(CrudApplication.class);

    private final ParceiroNegocioService parceiroNegocioService;
    private final ProdutoService produtoService;
    private final CabecalhoNotaService cabecalhoNotaService;
    private final ItemNotaService itemNotaService;

    public CrudApplication(ParceiroNegocioService parceiroNegocioService, ProdutoService produtoService,
                    CabecalhoNotaService notaFiscalService, ItemNotaService itemNotaService) {
        this.parceiroNegocioService = parceiroNegocioService;
        this.produtoService = produtoService;
        this.cabecalhoNotaService = notaFiscalService;
        this.itemNotaService = itemNotaService;
    }

    public static void main(String[] args) {
        LOG.info("Iniciado com sucesso!");
        SpringApplication.run(CrudApplication.class, args);
    }

    @Override
    public void run(String... args) {

        LOG.info("----------------Parceiro de Negocios------------------");

        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
        parceiroNegocio.setNome("Copersucar");
        parceiroNegocio.setInscricaoFiscal("22.338.624/0002-37");
        parceiroNegocio.setEndereco("Rua dos Sonhadores, 67");
        parceiroNegocio.setTelefone("41988556544");
        parceiroNegocioService.salvar(parceiroNegocio);
        LOG.info("id inserido: {}", parceiroNegocio.getId());

        ParceiroNegocio buscaParceiroID = parceiroNegocioService.buscarPorId(parceiroNegocio.getId());
        LOG.info("Busca por id. Nome {} id {} ", buscaParceiroID.getNome(), buscaParceiroID.getId());

        ParceiroNegocio buscaParceiroNome = parceiroNegocioService.buscarPorNome(parceiroNegocio.getNome());
        LOG.info("Busca por nome. Nome {} id {} ", buscaParceiroNome.getNome(), buscaParceiroNome.getId());

        List<ParceiroNegocio> parceirosSalvos = parceiroNegocioService.listarTodos();
        LOG.info("Salvos no total de {} parceiros de negocio.", parceirosSalvos.size());

        LOG.info("----------------PRODUTO------------------");

        ParceiroNegocio parceiroNegocio2 = new ParceiroNegocio();
        parceiroNegocio2.setNome("Copacol");
        parceiroNegocio2.setInscricaoFiscal("22.338.624/0002-37");
        parceiroNegocio2.setEndereco("Rua dos Sonhadores, 67");
        parceiroNegocio2.setTelefone("41988556544");
        parceiroNegocioService.salvar(parceiroNegocio2);
        LOG.info("id inserido: {}", parceiroNegocio2.getId());

        Produto produto = new Produto();
        produto.setDataFabricacao(LocalDate.parse("12/12/2015", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        produto.setDataValidade(LocalDate.parse("12/12/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        produto.setDescricao("Fertilizante Quebra Nózes");
        produto.setFabricante(parceiroNegocio2);
        produto.setQuantidadeEstoque(0);
        produtoService.salvar(produto);
        LOG.info("id inserido: {}", produto.getId());

        List<Produto> buscarTodos = produtoService.buscarTodos();
        LOG.info("Salvos no total de {} parceiros de negocio.", buscarTodos.size());

        Produto produtoPorId = produtoService.buscaPeloId(produto.getId());
        LOG.info("Buscando pelo id: {}", produtoPorId.getId());

        ParceiroNegocio parceiroNegocio3 = new ParceiroNegocio();
        parceiroNegocio3.setNome("Coamo");
        parceiroNegocio3.setInscricaoFiscal("22.338.624/0002-37");
        parceiroNegocio3.setEndereco("Rua dos Sonhadores, 67");
        parceiroNegocio3.setTelefone("41988556544");
        parceiroNegocioService.salvar(parceiroNegocio3);
        LOG.info("id inserido: {}", parceiroNegocio3.getId());

        Produto produtoAtualizado = new Produto();
        produtoAtualizado.setDataFabricacao(LocalDate.parse("12/12/2015", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        produtoAtualizado.setDataValidade(LocalDate.parse("12/12/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        produtoAtualizado.setDescricao("Purificador de Algodão");
        produtoAtualizado.setFabricante(parceiroNegocio3);
        produtoAtualizado.setQuantidadeEstoque(0);
        produtoService.salvar(produtoAtualizado);

        Produto produtoAtualizado2 = new Produto();
        produtoAtualizado2.setDataFabricacao(LocalDate.parse("12/12/2015", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        produtoAtualizado2.setDataValidade(LocalDate.parse("12/12/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        produtoAtualizado2.setDescricao("Semente de Soja");
        produtoAtualizado2.setFabricante(parceiroNegocio3);
        produtoAtualizado2.setQuantidadeEstoque(0);
        produtoService.salvar(produtoAtualizado2);

        produtoService.atualizar(produtoPorId.getId(), produtoAtualizado);
        LOG.info("Atualizando o produto {}", produtoPorId.getId());

        // produtoService.deletarPorId(produtoPorId.getId());
        // LOG.info("Deletando o produto {}", produtoPorId.getId() );

        LOG.info("--------------------------------------Nota Fiscal---------------------------------------");

        CabecalhoNota notaFiscal = new CabecalhoNota();
        notaFiscal.setData(LocalDate.parse("12/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        notaFiscal.setNotaFiscalTipo(TipoNota.ENTRADA);
        notaFiscal.setNumeroDaNota(159753);
        notaFiscal.setParceiroNegocio(parceiroNegocio3);
        cabecalhoNotaService.salvar(notaFiscal);
        LOG.info("Salvando nota fiscal: {}", notaFiscal.getId());

        CabecalhoNota notaFiscal3 = new CabecalhoNota();
        notaFiscal3.setData(LocalDate.parse("15/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        notaFiscal3.setNotaFiscalTipo(TipoNota.ENTRADA);
        notaFiscal3.setNumeroDaNota(178954);
        notaFiscal3.setParceiroNegocio(parceiroNegocio3);
        cabecalhoNotaService.salvar(notaFiscal3);
        LOG.info("Salvando nota fiscal: {}", notaFiscal3.getId());

        CabecalhoNota notaFiscal2 = new CabecalhoNota();
        notaFiscal2.setData(LocalDate.parse("05/01/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        notaFiscal2.setNotaFiscalTipo(TipoNota.SAIDA);
        notaFiscal2.setNumeroDaNota(159755);
        notaFiscal2.setParceiroNegocio(parceiroNegocio3);
        cabecalhoNotaService.salvar(notaFiscal2);
        LOG.info("Salvando nota fiscal: {}", notaFiscal2.getId());

        cabecalhoNotaService.buscarPorId(notaFiscal.getId());
        LOG.info("Buscando nota pelo id {}", notaFiscal.getId());

        notaFiscal2.setNumeroDaNota(777777);

        cabecalhoNotaService.atualizar(notaFiscal2.getId(), notaFiscal2);
        LOG.info("Atualizando pelo id {}", notaFiscal2.getId());

        List<CabecalhoNota> notas = cabecalhoNotaService.listarTodos();
        LOG.info("Tamanho da lista: ", notas.size());

        // notaFiscalService.deletarPorId(notaFiscal2.getId());
        // LOG.info("Deletando a nota {}", notaFiscal2.getId() );

        LOG.info("---------------- Nota Fiscal Item ------------------");


        ParceiroNegocio parceiroNegocioComNotaFiscal = new ParceiroNegocio();
        parceiroNegocioComNotaFiscal.setNome("Sementes Sorria");
        parceiroNegocioComNotaFiscal.setTelefone("45951214144");
        parceiroNegocioComNotaFiscal.setInscricaoFiscal("22.338.624/0002-37");
        parceiroNegocioComNotaFiscal.setEndereco("Rua dos Agropecuários, 158");
        parceiroNegocioService.salvar(parceiroNegocioComNotaFiscal);
        
        CabecalhoNota cabecalhoNota = new CabecalhoNota();
        cabecalhoNota.setNotaFiscalTipo(TipoNota.ENTRADA);
        cabecalhoNota.setData(LocalDate.parse("12/12/2015", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        cabecalhoNota.setParceiroNegocio(parceiroNegocioComNotaFiscal);
        cabecalhoNota.setNumeroDaNota(741852);
        cabecalhoNotaService.salvar(cabecalhoNota);
        
        Produto produtoDaNotaCompleta = new Produto();
        produtoDaNotaCompleta.setFabricante(parceiroNegocioComNotaFiscal);
        produtoDaNotaCompleta.setDescricao("Pacote de Semente");
        produtoDaNotaCompleta.setDataValidade(LocalDate.parse("05/02/2030", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        produtoDaNotaCompleta.setDataFabricacao(LocalDate.parse("05/02/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        produtoDaNotaCompleta.setQuantidadeEstoque(0);
        produtoService.salvar(produtoDaNotaCompleta);
        
        ItemNota notaFiscalItem = new ItemNota();
        notaFiscalItem.setNotaFiscal(cabecalhoNota);
        notaFiscalItem.setProduto(produtoDaNotaCompleta);
        notaFiscalItem.setQuantidade(150);
        notaFiscalItem.setPrecoUnitario(19.90);
        
        itemNotaService.calcularValorTotal(notaFiscalItem);
        itemNotaService.salvar(notaFiscalItem);
        
        
        LOG.info("---------------- Nota Fiscal Item ------------------");

        ParceiroNegocio parceiroNegocioComNotaFiscal2 = new ParceiroNegocio();
        parceiroNegocioComNotaFiscal2.setNome("Castano");
        parceiroNegocioComNotaFiscal2.setTelefone("45951214144");
        parceiroNegocioComNotaFiscal2.setInscricaoFiscal("22.338.624/0002-37");
        parceiroNegocioComNotaFiscal2.setEndereco("Rua dos Caetanos, 158");
        parceiroNegocioService.salvar(parceiroNegocioComNotaFiscal2);
        
        CabecalhoNota cabecalhoNota2 = new CabecalhoNota();
        cabecalhoNota2.setNotaFiscalTipo(TipoNota.SAIDA);
        cabecalhoNota2.setData(LocalDate.parse("12/12/2015", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        cabecalhoNota2.setParceiroNegocio(parceiroNegocioComNotaFiscal);
        cabecalhoNota2.setNumeroDaNota(952632);
        cabecalhoNotaService.salvar(cabecalhoNota2);
        
        Produto produtoDaNotaCompleta2 = new Produto();
        produtoDaNotaCompleta2.setFabricante(parceiroNegocioComNotaFiscal);
        produtoDaNotaCompleta2.setDescricao("Feijão Gandu");
        produtoDaNotaCompleta2.setDataValidade(LocalDate.parse("05/02/2030", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        produtoDaNotaCompleta2.setDataFabricacao(LocalDate.parse("05/02/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        produtoDaNotaCompleta2.setQuantidadeEstoque(0);
        produtoService.salvar(produtoDaNotaCompleta2);
        
        ItemNota notaFiscalItem2 = new ItemNota();
        notaFiscalItem2.setNotaFiscal(cabecalhoNota);
        notaFiscalItem2.setProduto(produtoDaNotaCompleta2);
        notaFiscalItem2.setQuantidade(100);
        notaFiscalItem2.setPrecoUnitario(49.90);     
        itemNotaService.calcularValorTotal(notaFiscalItem);
        itemNotaService.salvar(notaFiscalItem);
        

    }
}
