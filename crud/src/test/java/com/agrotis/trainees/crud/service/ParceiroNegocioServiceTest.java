package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;

public class ParceiroNegocioServiceTest {

    private ParceiroNegocioService service;
    private ParceiroNegocioRepository repository;

    @Before
    public void setUp() {
        repository = mock(ParceiroNegocioRepository.class);
        service = new ParceiroNegocioService(repository);
    }

    @Test
    public void testSalvar() {
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setNome("AgroTeste");

        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setId(1);
        entidade.setNome("AgroTestes");

        when(repository.save(any(ParceiroNegocio.class))).thenReturn(entidade);

        ParceiroNegocioDto resultado = service.salvar(dto);

        assertEquals(dto.getNome(), resultado.getNome());
        verify(repository, times(1)).save(any(ParceiroNegocio.class));
    }

}
