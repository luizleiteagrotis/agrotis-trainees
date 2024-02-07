package com.agrotis.trainees.crud.helper;

import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.service.ItemNotaFiscalService;
import com.agrotis.trainees.crud.service.NotaFiscalService;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;
import com.agrotis.trainees.crud.service.ProdutoService;

@Component
public class Validador {
    private static ParceiroNegocioService parceiroNegocioService;
    private static NotaFiscalService notaFiscalService;
    private static ProdutoService produtoService;
    private static ItemNotaFiscalService itemNotaFiscalService;

    Validador(ParceiroNegocioService parceiroNegocioService, NotaFiscalService notaFiscalService, ProdutoService produtoService,
                    ItemNotaFiscalService itemNotaFiscalService) {
        super();
        Validador.parceiroNegocioService = parceiroNegocioService;
        Validador.notaFiscalService = notaFiscalService;
        Validador.produtoService = produtoService;
        Validador.itemNotaFiscalService = itemNotaFiscalService;
    }

    public static boolean existeParceiroPorId(int id) {
        return parceiroNegocioService.buscarPorId(id) != null;
    }

    public static boolean existeNotaFiscalPorId(int id) {
        return notaFiscalService.buscarPorId(id) != null;
    }

    public static boolean existeProdutoPorId(int id) {
        return produtoService.buscarPorId(id) != null;
    }

    public static boolean existeItemNotaFiscalPorId(int id) {
        return itemNotaFiscalService.buscarPorId(id) != null;
    }

}
