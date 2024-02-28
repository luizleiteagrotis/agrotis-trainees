package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.agrotis.trainees.crud.exception.CrudException;

public class CustoMedioServiceTest {

    @Test
    public void deveCalcularCustoMedioEntrada() {
        BigDecimal custoTotal = new BigDecimal(659000);
        BigDecimal quantidadeTotal = new BigDecimal(10000);
        BigDecimal custoMedioCalculado = CustoMedioService.calcularCustoMedio(custoTotal, quantidadeTotal);
        assertEquals(new BigDecimal(65.90).setScale(2, RoundingMode.HALF_UP), custoMedioCalculado);
    }

    @Test
    public void deveTravarQuantidadeTotalZero() {
        BigDecimal custoTotal = new BigDecimal(1038500);
        BigDecimal quantidadeTotal = new BigDecimal(0);
        Exception excecao = assertThrows(CrudException.class, () -> {
            CustoMedioService.calcularCustoMedio(custoTotal, quantidadeTotal);
        });
        assertEquals("Quantidade Total do item deve ser maior que zero.", excecao.getMessage());
    }

}
