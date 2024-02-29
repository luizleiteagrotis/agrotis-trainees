package com.agrotis.trainees.crud.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;

import enums.TipoNota;

public class NumeroServiceTest {

    @Mock
    NotaFiscalRepository repository;

    @InjectMocks
    NumeroService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Teste Gerar numero  de entrada com sucesso")
    void deveGerarNumeroNuloEntrada() {
        TipoNota tipo = TipoNota.ENTRADA;

        NotaFiscal nota = new NotaFiscal();
        nota.setId(1);
        nota.setTipo(tipo);

        when(repository.findMaxNumeroByTipo(tipo)).thenReturn(Optional.empty());

        nota.setNumero(1);

        assertDoesNotThrow(() -> {
            service.gerarNumero(nota);
        });

        verify(repository, times(1)).findMaxNumeroByTipo(tipo);

    }

    @Test
    @DisplayName("Teste Gerar numero diferente de nulo com sucesso")
    void deveGerarNumeroEntrada() {
        TipoNota tipo = TipoNota.ENTRADA;

        NotaFiscal nota = new NotaFiscal();
        nota.setId(1);
        nota.setTipo(tipo);

        when(repository.findMaxNumeroByTipo(tipo)).thenReturn(Optional.of(1));

        nota.setNumero(1 + 1);

        assertDoesNotThrow(() -> {
            service.gerarNumero(nota);
        });

        verify(repository, times(1)).findMaxNumeroByTipo(tipo);

    }

    @Test
    @DisplayName("Teste Gerar numero diferente de nulo e saida com sucesso")
    void deveGerarNumeroSaida() {
        TipoNota tipo = TipoNota.SAIDA;

        NotaFiscal nota = new NotaFiscal();
        nota.setId(1);
        nota.setTipo(tipo);

        when(repository.findMaxNumeroByTipo(tipo)).thenReturn(Optional.of(1));

        nota.setNumero(1 + 1);

        assertDoesNotThrow(() -> {
            service.gerarNumero(nota);
        });

        verify(repository, times(1)).findMaxNumeroByTipo(tipo);
        assertEquals(TipoNota.SAIDA, nota.getTipo());

    }
}
