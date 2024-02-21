package com.agrotis.trainees.crud.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.agrotis.trainees.crud.exception.CrudException;

public interface CustoMedioService {
    public static BigDecimal calcularCustoMedio(BigDecimal quantidadeTotal, BigDecimal custoTotal) {

        if (quantidadeTotal == null || custoTotal == null) {
            throw new CrudException("O quantidade total e o custo total não podem ser nulos.");
        }

        if (custoTotal.compareTo(BigDecimal.ZERO) < 0) {
            throw new CrudException("Custo total deve ser maior que zero.");
        }

        if (quantidadeTotal.compareTo(BigDecimal.ZERO) <= 0) {
            throw new CrudException("Quantidade total deve ser maior que zero.");
        }

        return custoTotal.divide(quantidadeTotal, 2, RoundingMode.HALF_UP);
    }
}
