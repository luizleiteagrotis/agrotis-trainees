package com.agrotis.trainees.crud.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.agrotis.trainees.crud.exception.CrudException;

public interface CustoMedioService {

    public static BigDecimal calcularCustoMedio(BigDecimal custoTotal, BigDecimal quantidadeTotal) {
        if (quantidadeTotal == null || custoTotal == null) {
            throw new CrudException("Verifique se existe um valor nulo.");
        }
        if (quantidadeTotal.compareTo(BigDecimal.ZERO) < 0 || custoTotal.compareTo(BigDecimal.ZERO) < 0) {
            throw new CrudException("O valor precisa ser maior que zero.");
        }
        return custoTotal.divide(quantidadeTotal, 2, RoundingMode.HALF_UP);
    }

}