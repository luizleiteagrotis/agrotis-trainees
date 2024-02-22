package com.agrotis.trainees.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;

import dto.ParceiroNegocioDto;

@ExtendWith(MockitoExtension.class)
public class ParceirNegocioTest {

    private final String NOME = "Agrotis";
    private final String INSCRICAO_FISCAL = "400289-22";
    private final String ENDERECO = "Avenidade Grega";
    private final String TELEFONE = "41998567895";

    @Mock
    private ParceiroNegocioRepository repository;

    @InjectMocks
    private ParceiroNegocioService service;

    @Test
    public void inserirParceiroNegocio() {
        ParceiroNegocio parceiroNegocio = criarParceiroNegocio();
        when(repository.save(any(ParceiroNegocio.class))).thenReturn(parceiroNegocio);

        ParceiroNegocioDto dto = criarParceiroNegocioDto();
        ParceiroNegocioDto result = service.salvar(dto);

        assertNotNull(result);
        assertEquals(parceiroNegocio.getId(), result.getId());
        verify(repository).save(any(ParceiroNegocio.class));
    }

    @Test
    public void quandoCriadoRetornaSucesso() {
        when(repository.save(any(ParceiroNegocio.class))).thenReturn(criarParceiroNegocio());

    }
    

    private ParceiroNegocio criarParceiroNegocio() {
        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setNome(NOME);
        entidade.setEndereco(ENDERECO);
        entidade.setInscricaoFiscal(INSCRICAO_FISCAL);
        entidade.setTelefone(TELEFONE);
        return entidade;
    }

    private ParceiroNegocioDto criarParceiroNegocioDto() {
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setNome(NOME);
        dto.setEndereco(ENDERECO);
        dto.setInscricao(INSCRICAO_FISCAL);
        dto.setTelefone(TELEFONE);
        return dto;
    }
}
