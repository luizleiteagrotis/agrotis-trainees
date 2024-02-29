package com.agrotis.trainees.crud.service;

import java.math.BigDecimal;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;

public class AdicionarOuAtualizarItemNotaFiscalService {

    private AtualizarValorTotalNotaFiscalService atualizarNota;
    private ControlarEstoqueService controlarEstoque;
    private CalcularValorTotalItemService calcularValorTotalItem;

    public void atualizarItem(NotaFiscalItem item) {
        if (item != null && item.getNotaFiscal() != null && item.getProduto() != null) {
            BigDecimal valorTotalItem = calcularValorTotalItem.calcularValorTotalItem(item);
            NotaFiscal notaFiscal = item.getNotaFiscal();
            if (notaFiscal != null) {
                atualizarNota.atualizarValorTotalNotaFiscal(notaFiscal, valorTotalItem);
                controlarEstoque.controlarEstoque(item);
            } else {
                throw new IllegalArgumentException("Nota fiscal não pode ser nula");
            }
        } else {
            throw new IllegalArgumentException("O item da nota fiscal, nota fiscal e produto devem ser fornecidos");
        }
    }

}
