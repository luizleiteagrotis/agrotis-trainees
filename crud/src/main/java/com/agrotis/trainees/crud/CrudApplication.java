package com.agrotis.trainees.crud;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.service.CabecalhoNotaService;
import com.agrotis.trainees.crud.service.ItemNotaService;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;
import com.agrotis.trainees.crud.service.ProdutoService;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;
import com.agrotis.trainees.crud.service.exceptions.CampoVazioOuNuloException;

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
        try {

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
            produto.setDataFabricacao(LocalDate.parse("09/05/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            produto.setDataValidade(LocalDate.parse("09/05/2025", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            produto.setDescricao("Sementes Sorria");
            produto.setQuantidadeEstoque(0);

            produtoService.salvar(produto);
            
        } catch (CampoVazioOuNuloException e) {
            LOG.error("Erro ao salvar o produto: " + e.getMessage());
        } catch (EntidadeNaoEncontradaException e) {
            LOG.error("Entidade não encontrada: " + e.getMessage());
        } 


    }
}
