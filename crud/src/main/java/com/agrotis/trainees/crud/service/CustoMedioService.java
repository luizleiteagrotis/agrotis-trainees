package com.agrotis.trainees.crud.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.agrotis.trainees.crud.service.exceptions.CrudException;

public interface CustoMedioService {

    public static BigDecimal calcularCustoMedio(BigDecimal custoTotal, BigDecimal quantidadeTotal) {
        if (quantidadeTotal.equals(BigDecimal.ZERO)) {
            throw new CrudException("Quantidade Total deve ser maior que zero.");
        }
        return custoTotal.divide(quantidadeTotal, 2, RoundingMode.HALF_UP);
    }

}
