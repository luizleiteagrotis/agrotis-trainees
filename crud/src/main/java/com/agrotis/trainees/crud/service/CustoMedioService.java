package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.agrotis.trainees.crud.exception.CrudException;

public interface CustoMedioService {
    static final Logger LOG = LoggerFactory.getLogger(CustoMedioService.class);

    public static BigDecimal calcularCustoMedio(BigDecimal quantidadeTotal, BigDecimal custoTotal) {
        try {
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
        } catch (CrudException ce) {
            LOG.error(ce.getMessage());
            throw ce;
        }
    }

    public static BigDecimal calcularCustoTotal(BigDecimal custoMedio, BigDecimal estoque) {
        if (custoMedio == null || estoque == null) {
            throw new CrudException("O quantidade total e o custo total não podem ser nulos.");
        }
        return custoMedio.multiply(estoque);
    }

    public static BigDecimal adicionarCustoTotal(BigDecimal valorTotal, BigDecimal custoTotal) {
        return valorTotal.add(custoTotal);
    }
}
