package com.agrotis.trainees.crud.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import utilidades.CrudException;

public class CustoMedioService {

    public static BigDecimal calcularCustoMedio(BigDecimal custoTotal, BigDecimal quantidadeTotal) throws Exception {
        if (quantidadeTotal.equals(BigDecimal.ZERO)) {
            throw new CrudException("Quantidade total tem que ser diferente de 0.");
        }
        return custoTotal.divide(quantidadeTotal, 2, RoundingMode.HALF_UP);
    }
}
