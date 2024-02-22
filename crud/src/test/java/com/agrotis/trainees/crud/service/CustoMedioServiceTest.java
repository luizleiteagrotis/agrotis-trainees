package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Test;

import utilidades.CrudException;

class CustoMedioServiceTest {

    @Test
    public void precisoCalcularCustoMedio() throws Exception {
        BigDecimal custoTotal = new BigDecimal(1038500);
        BigDecimal quantidadeTotal = new BigDecimal(15000);
        BigDecimal custoMedioEsperado = new BigDecimal(69.23);
        BigDecimal custoMedioCalculado = CustoMedioService.calcularCustoMedio(custoTotal, quantidadeTotal);
        assertEquals(custoMedioEsperado.setScale(2, RoundingMode.HALF_UP) , custoMedioCalculado.setScale(2, RoundingMode.HALF_UP));
    }
    
    @Test
    public void deveTravarQuantidadeTotalZero() {
        BigDecimal custoTotal = new BigDecimal(1038500);
        BigDecimal quantidadeTotal = new BigDecimal(0);
        Exception excecao = assertThrows(CrudException.class, () -> {
            BigDecimal custoMedioCalculado = CustoMedioService.calcularCustoMedio(custoTotal, quantidadeTotal);
        });
        assertEquals("Quantidade total tem que ser diferente de 0.", excecao.getMessage());
    }
    
    
    

}


