package com.agrotis.trainees.crud.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.agrotis.trainees.crud.exception.CrudException;

public interface CustoMedioService {

    public static BigDecimal calcularCustoMedio(BigDecimal custoTotal, BigDecimal quantidadeTotal) {

        if (quantidadeTotal == null || custoTotal == null) {
            throw new CrudException("Não é possível calcular. Numero Nulo.");
        }

        if (custoTotal.compareTo(quantidadeTotal) <= 0) {
            throw new CrudException("Não é possível calcular o custo médio, pois o custo total não pode ser igual ou menor que 0.");
        }

        if (quantidadeTotal.equals(BigDecimal.ZERO)) {
            throw new CrudException("Quantidade Total deve ser maior que zero.");
        }

        if (quantidadeTotal.compareTo(BigDecimal.ZERO) <= 0) {
            throw new CrudException("Quantidade Total deve ser positiva.");
        }

        return custoTotal.divide(quantidadeTotal, 2, RoundingMode.HALF_UP);
    }

}
