package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.agrotis.trainees.crud.exception.CrudException;

class CustoMedioServiceTest {

    @Test
    void deveCalcularCustoMedio() {
        BigDecimal custoTotal = new BigDecimal(1_038_500);
        BigDecimal quantidadeTotal = new BigDecimal(15000);
        BigDecimal custoMedioCalculado = CustoMedioService.calcularCustoMedio(quantidadeTotal, custoTotal);
        assertEquals(new BigDecimal(69.23).setScale(2, RoundingMode.HALF_UP), custoMedioCalculado);
    }

    @Test
    void deveTravarQuantidadeTotalZero() {
        BigDecimal custoTotal = new BigDecimal(1_038_500);
        BigDecimal quantidadeTotal = new BigDecimal(0);
        Exception excecao = assertThrows(CrudException.class, () -> {
            CustoMedioService.calcularCustoMedio(quantidadeTotal, custoTotal);
        });
        assertEquals("Quantidade total deve ser maior que zero.", (excecao.getMessage()));
    }

    @Test
    void deveLancarExceptionQuandoCustoTotalForNegativo() {
        BigDecimal custoTotal = new BigDecimal(-1_038_500);
        BigDecimal quantidadeTotal = new BigDecimal(15000);
        Exception excecao = assertThrows(CrudException.class, () -> {
            CustoMedioService.calcularCustoMedio(quantidadeTotal, custoTotal);
        });
        assertEquals("Custo total deve ser maior que zero.", (excecao.getMessage()));

    }

    @Test
    void verificarValoresNulos() {
        BigDecimal custoTotal = null;
        BigDecimal quantidadeTotal = new BigDecimal(15000);
        Exception excecao = assertThrows(CrudException.class, () -> {
            CustoMedioService.calcularCustoMedio(quantidadeTotal, custoTotal);
        });
        assertEquals("O quantidade total e o custo total não podem ser nulos.", (excecao.getMessage()));

    }

    @Test
    void verificarQuantidadeTotalNull() {
        BigDecimal custoTotal = new BigDecimal(15000);
        BigDecimal quantidadeTotal = null;
        Exception excecao = assertThrows(CrudException.class, () -> {
            CustoMedioService.calcularCustoMedio(quantidadeTotal, custoTotal);
        });
        assertEquals("O quantidade total e o custo total não podem ser nulos.", (excecao.getMessage()));

    }

    @Test
    void verificarCustoTotalNull() {
        BigDecimal custoTotal = null;
        BigDecimal quantidadeTotal = null;
        Exception excecao = assertThrows(CrudException.class, () -> {
            CustoMedioService.calcularCustoMedio(quantidadeTotal, custoTotal);
        });
        assertEquals("O quantidade total e o custo total não podem ser nulos.", (excecao.getMessage()));

    }

    @Test
    void calculaCustoMedioValorEsperadoSessentaCincoNoventaCentavos() {
        BigDecimal custoTotal = new BigDecimal(659_000.00);
        BigDecimal quantidadeTotal = new BigDecimal(10000);
        BigDecimal custoMedioCalculado = CustoMedioService.calcularCustoMedio(quantidadeTotal, custoTotal);
        assertEquals(new BigDecimal(65.90).setScale(2, RoundingMode.HALF_UP), custoMedioCalculado);
    }

    @Test
    void calculaCustoMedioValorEsperadoSessentaCincoVinteTresCentavos() {
        BigDecimal custoTotal = new BigDecimal(1038500);
        BigDecimal quantidadeTotal = new BigDecimal(15000);
        BigDecimal custoMedioCalculado = CustoMedioService.calcularCustoMedio(quantidadeTotal, custoTotal);
        assertEquals(new BigDecimal(69.23).setScale(2, RoundingMode.HALF_UP), custoMedioCalculado);
    }

    @Test
    void calcularCustoTotalEsperandoCem() {
        BigDecimal custoTotal = new BigDecimal(10);
        BigDecimal quantidadeTotal = new BigDecimal(10);
        BigDecimal calcularCustoTotal = CustoMedioService.calcularCustoTotal(quantidadeTotal, custoTotal);
        assertEquals(new BigDecimal(100), calcularCustoTotal);
    }

    @Test
    void calcularCustoTotalComCustoTotalNull() {
        BigDecimal custoTotal = null;
        BigDecimal quantidadeTotal = new BigDecimal(10);
        assertThrows(CrudException.class, () -> {
            CustoMedioService.calcularCustoTotal(quantidadeTotal, custoTotal);
        });
    }

    @Test
    void calcularCustoTotalComQuantidadeTotalNull() {
        BigDecimal custoTotal = new BigDecimal(10);
        BigDecimal quantidadeTotal = null;
        assertThrows(CrudException.class, () -> {
            CustoMedioService.calcularCustoTotal(quantidadeTotal, custoTotal);
        });
    }

    @Test
    void adicionarValoresEsperandoVinte() {
        BigDecimal custoTotal = new BigDecimal(10);
        BigDecimal quantidadeTotal = new BigDecimal(10);
        BigDecimal adicionadoCusto = CustoMedioService.adicionarCustoTotal(custoTotal, quantidadeTotal);
        assertEquals(new BigDecimal(20), adicionadoCusto);
    }
}
