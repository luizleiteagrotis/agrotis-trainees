package com.agrotis.trainees.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.agrotis.trainees.crud.exception.CrudException;

public class CustoMedioServiceTest {

    @Test
    public void deveCalcularCustoMedio() {
        BigDecimal custoTotal = new BigDecimal(1038500);
        BigDecimal quantidadeTotal = new BigDecimal(15000);
        BigDecimal custoMedioEsperado = new BigDecimal(69.23);
        BigDecimal custoMedioCalculado = CustoMedioService.calcular(custoTotal, quantidadeTotal);

        assertEquals(custoMedioEsperado.setScale(2, RoundingMode.HALF_UP), custoMedioCalculado.setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void deveVerificarNulos() {
        BigDecimal custoTotal = null;
        BigDecimal quantidadeTotal = null;
        Exception excecao = assertThrows(CrudException.class, () -> {
            BigDecimal custoMedioCalculado = CustoMedioService.calcular(custoTotal, quantidadeTotal);
        });
        assertEquals("null", excecao.getMessage());
    }

    @Test
    public void deveVerificarQuantidadeZeroOuMenor() {
        BigDecimal custoTotal = new BigDecimal(1038500);
        BigDecimal quantidadeTotal = new BigDecimal(0);
        Exception excecao = assertThrows(CrudException.class, () -> {
            BigDecimal custoMedioCalculado = CustoMedioService.calcular(custoTotal, quantidadeTotal);
        });
        assertEquals("Quantidade Total deve ser maior que zero", excecao.getMessage());
    }

    @Test
    public void deveVerificarCustoZeroOuMenor() {
        BigDecimal custoTotal = new BigDecimal(0);
        BigDecimal quantidadeTotal = new BigDecimal(15000);
        Exception excecao = assertThrows(CrudException.class, () -> {
            BigDecimal custoMedioCalculado = CustoMedioService.calcular(custoTotal, quantidadeTotal);
        });
        assertEquals("Custo Total deve ser maior que zero", excecao.getMessage());
    }
}
