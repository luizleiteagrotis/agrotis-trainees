package com.agrotis.trainees.crud.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;
import com.agrotis.trainees.crud.wrapper.NotaFiscalWrapper;

public class NotaFiscalServiceTest {

    @Mock
    NotaFiscalRepository repository;

    @Mock
    NotaFiscalWrapper wrapper;

    @Mock
    NumeroService numeroService;

    @InjectMocks
    NotaFiscalService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Teste inserir com sucesso")
    void deveInserirComSucesso() {
        ParceiroNegocio parceiro = new ParceiroNegocio();
        parceiro.setId(1);
        parceiro.setInscricaoFiscal("123456789");

        NotaFiscalDto dto = new NotaFiscalDto();
        dto.setId(1);
        dto.setParceiro(parceiro);

        NotaFiscal entidade = new NotaFiscal();
        entidade.setId(1);
        entidade.setParceiro(parceiro);

        when(wrapper.converterParaEntidade(dto)).thenReturn(entidade);
        when(repository.save(entidade)).thenReturn(entidade);
        when(wrapper.converterParaDto(entidade)).thenReturn(dto);

        assertDoesNotThrow(() -> {
            service.inserir(dto);
        });

        verify(repository, times(1)).save(entidade);
    }

}
