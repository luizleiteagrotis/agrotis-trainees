package com.agrotis.trainees.crud.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.agrotis.trainees.crud.exception.InscricaoExisteException;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;

public class InscricaoServiceTest {

    @Mock
    ParceiroNegocioRepository repository;

    @InjectMocks
    InscricaoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // Teste para verificarInscricao

    @Test
    @DisplayName("Inscrição nao existe, Passou")
    void deveVerificarInscricaoNaoExiste() {

        when(repository.existsByInscricaoFiscal("398.252.499-02")).thenReturn(false);
        assertDoesNotThrow(() -> {
            service.verificarInscricao("398.252.499-02");
        });
    }

    @Test
    @DisplayName("Inscrição existe, deve lançar erro")
    void deveVerificarInscricaoExiste() {

        when(repository.existsByInscricaoFiscal("398.252.499-02")).thenReturn(true);
        Exception excecao = assertThrows(InscricaoExisteException.class, () -> {
            service.verificarInscricao("398.252.499-02");
        });

        assertEquals("A inscrição fiscal já existe", excecao.getMessage());
    }

    @Test
    @DisplayName("Campos foram substituidos")
    void deveVerificarSubstituicao() {

        String inscricaoFiscalPontuada = "12.345.678/0001-90";
        String inscricaoFiscalLimpa = service.normalizarInscricaoFiscal(inscricaoFiscalPontuada);

        assertEquals("12345678000190", inscricaoFiscalLimpa);

    }
}
