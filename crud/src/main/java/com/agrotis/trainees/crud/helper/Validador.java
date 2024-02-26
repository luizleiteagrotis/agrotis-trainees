package com.agrotis.trainees.crud.helper;

import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.service.ItemNotaFiscalService;
import com.agrotis.trainees.crud.service.NotaFiscalService;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;
import com.agrotis.trainees.crud.service.ProdutoService;

@Component
public class Validador {
    private ParceiroNegocioService parceiroNegocioService;
    private NotaFiscalService notaFiscalService;
    private ProdutoService produtoService;
    private ItemNotaFiscalService itemNotaFiscalService;

    Validador(ParceiroNegocioService parceiroNegocioService, NotaFiscalService notaFiscalService, ProdutoService produtoService,
                    ItemNotaFiscalService itemNotaFiscalService) {
        super();
        this.parceiroNegocioService = parceiroNegocioService;
        this.notaFiscalService = notaFiscalService;
        this.produtoService = produtoService;
        this.itemNotaFiscalService = itemNotaFiscalService;
    }

    public boolean existeParceiroPorId(int id) {
        return parceiroNegocioService.buscarPorId(id) != null;
    }

    public boolean existeNotaFiscalPorId(int id) {
        return notaFiscalService.buscarPorId(id) != null;
    }

    public boolean existeProdutoPorId(int id) {
        return produtoService.buscarPorId(id) != null;
    }

    public boolean existeItemNotaFiscalPorId(int id) {
        return itemNotaFiscalService.buscarPorId(id) != null;
    }

}
