package com.agrotis.trainees.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.Produto;
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

    public void run(String... args) {
        Produto produtoItem = produtoService.buscarPorId(82);
        NotaFiscal notaItem = notaFiscalService.buscarPorId(180);
        ItemNotaFiscal item = new ItemNotaFiscal(notaItem, produtoItem, 10, 20);

        ItemNotaFiscal salvar = itemNotaFiscalService.salvar(item);
        LOG.info("O id {}", salvar.getId());
        // itemNotaFiscalService.deletarPorId(228);
    }
}
