package com.agrotis.trainees.crud.service;

import java.math.BigDecimal;

import com.agrotis.trainees.crud.entity.NotaFiscal;

public class AtualizarValorTotalNotaFiscalService {

    private NotaFiscalService service;

    public void atualizarValorTotalNotaFiscal(NotaFiscal notaFiscal, BigDecimal valorTotalItem) {
        if (notaFiscal == null) {
            throw new IllegalArgumentException("Nota fiscal não pode ser nula");
        } else {
            BigDecimal valorTotalAtual = notaFiscal.getValorTotal() != null ? notaFiscal.getValorTotal() : BigDecimal.ZERO;
            BigDecimal novoValorTotal = valorTotalAtual.add(valorTotalItem);
            notaFiscal.setValorTotal(novoValorTotal);
            service.salvar(notaFiscal);
        }
    }

}
