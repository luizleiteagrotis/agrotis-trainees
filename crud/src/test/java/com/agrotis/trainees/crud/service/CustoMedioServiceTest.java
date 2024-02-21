package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.agrotis.trainees.crud.exception.CrudException;

public class CustoMedioServiceTest {

    private BigDecimal custoTotal;
    private BigDecimal estoque;

    @Before
    public void setUp() {
        custoTotal = new BigDecimal(1038500);
        estoque = new BigDecimal(15000);
    }

    @Test
    public void deveCalcularCustoMedio() {
        BigDecimal custoMedioCalculado = CustoMedioService.calcularCustoMedio(custoTotal, estoque);
        assertEquals(new BigDecimal(69.23).setScale(2, RoundingMode.HALF_UP), custoMedioCalculado);
    }

    @Test
    @DisplayName("Deve lançar exceção de valor menor que zero")
    public void custoTotalZerado() {
        custoTotal = new BigDecimal(-20);
        assertThrows(CrudException.class, () -> {
            BigDecimal custoMedioCalculado = CustoMedioService.calcularCustoMedio(custoTotal, estoque);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção de valor menor que zero")
    public void estoqueZerado() {
        estoque = new BigDecimal(-30);
        assertThrows(CrudException.class, () -> {
            BigDecimal custoMedioCalculado = CustoMedioService.calcularCustoMedio(custoTotal, estoque);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção com valor nulo")
    public void custoTotalNulo() {
        custoTotal = null;
        assertThrows(CrudException.class, () -> {
            BigDecimal custoMedioCalculado = CustoMedioService.calcularCustoMedio(custoTotal, estoque);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção com valor nulo")
    public void estoqueNulo() {
        estoque = null;
        assertThrows(CrudException.class, () -> {
            BigDecimal custoMedioCalculado = CustoMedioService.calcularCustoMedio(custoTotal, estoque);
        });
    }
}
