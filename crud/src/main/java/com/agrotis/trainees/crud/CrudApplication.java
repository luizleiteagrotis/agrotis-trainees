package com.agrotis.trainees.crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.agrotis.trainees.crud.service.NotaFiscalItemService;
import com.agrotis.trainees.crud.service.NotaFiscalService;
import com.agrotis.trainees.crud.service.NotaFiscalTipoService;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;
import com.agrotis.trainees.crud.service.ProdutoService;

@SpringBootApplication
public class CrudApplication {

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

}
