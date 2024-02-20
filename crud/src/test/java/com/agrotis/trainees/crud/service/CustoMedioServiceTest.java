package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.agrotis.trainees.crud.exception.CrudException;

public class CustoMedioServiceTest {

    @Test
    public void deveCalcularCustoMedio() {
        BigDecimal custoTotal = new BigDecimal(1038500);
        BigDecimal quantidadeTotal = new BigDecimal(15000);
        BigDecimal custoMedioCalculado = CustoMedioService.calcularCustoMedio(custoTotal, quantidadeTotal);
        assertEquals(new BigDecimal(69.23).setScale(2, RoundingMode.HALF_UP), custoMedioCalculado);
    }

    @Test
    public void deveTravarQuantidadeTotalZero() {
        BigDecimal custoTotal = new BigDecimal(1038500);
        BigDecimal quantidadeTotal = new BigDecimal(0);
        Exception excecao = assertThrows(CrudException.class, () -> {
            BigDecimal custoMedioCalculado = CustoMedioService.calcularCustoMedio(custoTotal, quantidadeTotal);
        });
        assertEquals("Quantidade Total deve ser maior que zero.", excecao.getMessage());
    }

    @Test
    public void deveTravarQuantidadeTotalNegativa() {
        BigDecimal custoTotal = new BigDecimal(1038500);
        BigDecimal quantidadeTotal = new BigDecimal(-15000);
        Exception excecao = assertThrows(CrudException.class, () -> {
            BigDecimal custoMedioCalculado = CustoMedioService.calcularCustoMedio(custoTotal, quantidadeTotal);
        });
        assertEquals("Quantidade Total deve ser positiva.", excecao.getMessage());
    }

    @Test
    public void deveVerificarNulls() {
        BigDecimal custoTotal = null;
        BigDecimal quantidadeTotal = null;
        Exception excecao = assertThrows(CrudException.class, () -> {
            BigDecimal custoMedioCalculado = CustoMedioService.calcularCustoMedio(custoTotal, quantidadeTotal);
        });
        assertEquals("Não é possível calcular. Numero Nulo.", excecao.getMessage());
    }

    @Test
    public void deveVerificarCustoTotalZero() {
        BigDecimal custoTotal = BigDecimal.ZERO;
        BigDecimal quantidadeTotal = new BigDecimal(15000);

        Exception excecao = assertThrows(CrudException.class, () -> {
            BigDecimal custoMedioCalculado = CustoMedioService.calcularCustoMedio(custoTotal, quantidadeTotal);
        });
        assertEquals("Não é possível calcular o custo médio, pois o custo total não pode ser igual ou menor que 0.",
                        excecao.getMessage());
    }

}
