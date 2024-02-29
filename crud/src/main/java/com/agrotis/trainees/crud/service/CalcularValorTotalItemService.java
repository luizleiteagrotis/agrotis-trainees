package com.agrotis.trainees.crud.service;

import java.math.BigDecimal;

import com.agrotis.trainees.crud.entity.NotaFiscalItem;

public interface CalcularValorTotalItemService {

    public default BigDecimal calcularValorTotalItem(NotaFiscalItem item) {
        BigDecimal quantidade = BigDecimal.valueOf(item.getQuantidade());
        BigDecimal precoUnitario = item.getPrecoUnitario();
        return quantidade.multiply(precoUnitario);
    }

}
