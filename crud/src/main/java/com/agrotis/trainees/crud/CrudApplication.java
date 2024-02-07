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
import com.agrotis.trainees.crud.entity.enums.TipoNota;
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

        parceiroNegocio.setEndereco("Nova Rua, 123");
        parceiroNegocioService.atualizar(parceiroNegocio.getId(), parceiroNegocio);

        parceiroNegocioService.buscarPorNome(parceiroNegocio.getNome());

        LOG.info("----------------PRODUTO------------------");

        Produto produto = new Produto();
        produto.setDataFabricacao(LocalDate.parse("12/12/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        produto.setDataValidade(LocalDate.parse("12/12/2025", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        produto.setDescricao("Fertilizante Quebra Nózes");
        produto.setFabricante(parceiroNegocio);
        produto.setQuantidadeEstoque(15);
        produtoService.salvar(produto);
        LOG.info("id inserido: {}", produto.getId());
        
         List<Produto> buscarTodos = produtoService.buscarTodos();
         LOG.info("Salvos no total de {} parceiros de negocio.",
         buscarTodos.size());
         
         Produto produtoPorId = produtoService.buscaPeloId(produto.getId());
         LOG.info("Buscando pelo id: {}", produtoPorId.getId());
         
         produto.setDescricao("Nova Descriçao");
         produtoService.atualizar(produto.getId(), produto);

         LOG.info("----------------CABEÇALHO------------------");
         
         CabecalhoNota cabecalhoNotaFiscal = new CabecalhoNota();
         cabecalhoNotaFiscal.setData(LocalDate.parse("09/05/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
         cabecalhoNotaFiscal.setNumeroDaNota(154445);
         cabecalhoNotaFiscal.setNotaFiscalTipo(TipoNota.ENTRADA);
         cabecalhoNotaFiscal.setParceiroNegocio(parceiroNegocio);
         cabecalhoNotaService.salvar(cabecalhoNotaFiscal);
         
         ItemNota itensNotaFiscal = new ItemNota();
         itensNotaFiscal.setCabecalhoNota(cabecalhoNotaFiscal);
         itensNotaFiscal.setProduto(produto);
         itensNotaFiscal.setQuantidade(150);
         itensNotaFiscal.setPrecoUnitario(149.90);
         itemNotaService.salvar(itensNotaFiscal);
         
         
        

    }
}
