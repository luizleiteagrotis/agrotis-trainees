package com.agrotis.trainees.crud.helper;

import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.service.NotaFiscalService;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;
import com.agrotis.trainees.crud.service.ProdutoService;

@Component
public class Validador {
    private static ParceiroNegocioService parceiroNegocioService;
    private static NotaFiscalService notaFiscalService;
    private static ProdutoService produtoService;

    Validador(ParceiroNegocioService parceiroNegocioService, NotaFiscalService notaFiscalService, ProdutoService produtoService) {
        super();
        Validador.parceiroNegocioService = parceiroNegocioService;
        Validador.notaFiscalService = notaFiscalService;
        Validador.produtoService = produtoService;
    }

    public static boolean validarParceiro(int id) {
        return parceiroNegocioService.buscarPorId(id) != null;
    }

    public static boolean validarNotaFiscal(int id) {
        return notaFiscalService.buscarPorId(id) != null;
    }

    public static boolean validarProduto(int id) {
        return produtoService.buscarPorId(id) != null;
    }

}
