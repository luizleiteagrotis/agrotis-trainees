package com.agrotis.trainees.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Test;

import com.agrotis.trainees.crud.service.exceptions.CrudException;
import com.agrotis.trainees.crud.service.impl.CustoMedioServiceImpl;

public class CustoMedioServiceTest {
    
    @Test
    public void precisoCalcularCustoMedio() {
        BigDecimal custoTotal = new BigDecimal(1038500);
        BigDecimal quantidadeTotal = new BigDecimal(15000);
        BigDecimal custoMedioEsperado = new BigDecimal(69.23);
        BigDecimal custoMedioCalculado = CustoMedioServiceImpl.calcularCustoMedio(custoTotal, quantidadeTotal);
        assertEquals(custoMedioEsperado.setScale(2, RoundingMode.HALF_UP) , custoMedioCalculado.setScale(2, RoundingMode.HALF_UP));
    }
    
    @Test
    public void deveTravarQuantidadeTotalZero() {
        BigDecimal custoTotal = new BigDecimal(1038500);
        BigDecimal quantidadeTotal = new BigDecimal(0);
        Exception excecao = assertThrows(CrudException.class, () -> {
            BigDecimal custoMedioCalculado = CustoMedioServiceImpl.calcularCustoMedio(custoTotal, quantidadeTotal);
        });
        assertEquals("Quantidade Total deve ser maior que zero.", excecao.getMessage());
    }
    
    
    

}
