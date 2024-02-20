package com.agrotis.trainees.crud.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.agrotis.trainees.crud.exception.CrudException;

public interface CustoMedioService {

    public static BigDecimal calcular(BigDecimal custoTotal, BigDecimal quantidadeTotal) {
        if (quantidadeTotal == null || custoTotal == null) {
            throw new CrudException("null");
        }
        if (quantidadeTotal.compareTo(BigDecimal.ZERO) <= 0) {
            throw new CrudException("Quantidade Total deve ser maior que zero");
        }
        if (custoTotal.compareTo(BigDecimal.ZERO) <= 0) {
            throw new CrudException("Custo Total deve ser maior que zero");
        }

        return custoTotal.divide(quantidadeTotal, 2, RoundingMode.HALF_UP);

    }
}
