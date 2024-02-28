package com.agrotis.trainees.crud.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;

public class ValorNotaFiscalServiceTest {

    @Mock
    NotaFiscalRepository repository;

    @Mock
    NotaFiscalService notaService;

    @InjectMocks
    ValorNotaFiscalService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Teste Atualizar valor nota")
    void deveAtualizarValorDaNota() {
        NotaFiscal nota = new NotaFiscal();
        nota.setId(1);
        nota.setValorTotal(new BigDecimal(15.5));

        NotaFiscalItem item = new NotaFiscalItem();
        item.setId(1);
        item.setIdNota(nota);
        item.setPrecoUnitario(new BigDecimal(5.99));
        item.setQuantidade(7);

        BigDecimal resultado = nota.getValorTotal().add(item.getPrecoUnitario().multiply(new BigDecimal(item.getQuantidade())));
        when(notaService.buscarPorId(item.getIdNota().getId())).thenReturn(nota);
        when(repository.save(nota)).thenReturn(nota);

        assertDoesNotThrow(() -> {
            service.atualizarValorTotalNota(item);
        });

        assertEquals(resultado, nota.getValorTotal());
    }
}
