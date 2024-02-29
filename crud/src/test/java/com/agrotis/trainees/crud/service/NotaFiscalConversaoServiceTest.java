package com.agrotis.trainees.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;

@RunWith(MockitoJUnitRunner.class)
public class NotaFiscalConversaoServiceTest {

    @Mock
    private ParceiroNegocioConversaoService conversaoParceiro;

    @Mock
    private NotaFiscalService service;

    @InjectMocks
    private NotaFiscalConversaoService conversao;

    @Test
    @DisplayName("Teste para o método converterParaDto")
    public void testeConverterDto() {
        NotaFiscal entidade = new NotaFiscal();
        entidade.setId(1);
        entidade.setNotaFiscalTipo(NotaFiscalTipo.ENTRADA);
        entidade.setParceiroNegocio(new ParceiroNegocio());
        entidade.setNumero(123456);
        entidade.setData(LocalDate.now());
        entidade.setItens(new ArrayList<>());
        entidade.setValorTotal(BigDecimal.TEN);

        ParceiroNegocioDto parceiroNegocioDto = new ParceiroNegocioDto();
        when(conversaoParceiro.converterParaDto(any(ParceiroNegocio.class))).thenReturn(parceiroNegocioDto);

        NotaFiscalDto dto = conversao.converterParaDto(entidade);

        assertEquals(entidade.getId(), dto.getId());
        assertEquals(entidade.getNotaFiscalTipo(), dto.getNotaFiscalTipo());
        assertEquals(parceiroNegocioDto, dto.getParceiroNegocio());
        assertEquals(entidade.getNumero(), dto.getNumero());
        assertEquals(entidade.getData(), dto.getData());
        assertEquals(entidade.getItens(), dto.getItens());
        assertEquals(entidade.getValorTotal(), dto.getValorTotal());
    }

    @Test
    @DisplayName("Teste para o método converterParaEntidade")
    public void testeConverterEntidade() {
        NotaFiscalDto dto = new NotaFiscalDto();
        dto.setId(1);
        dto.setNotaFiscalTipo(NotaFiscalTipo.ENTRADA);
        dto.setParceiroNegocio(new ParceiroNegocioDto());
        dto.setNumero(123456);
        dto.setData(LocalDate.now());
        dto.setItens(new ArrayList<>());
        dto.setValorTotal(BigDecimal.TEN);

        ParceiroNegocio parceiroEntidade = new ParceiroNegocio();
        when(conversaoParceiro.converterParaEntidade(any(ParceiroNegocioDto.class))).thenReturn(parceiroEntidade);

        NotaFiscal entidade = conversao.converterParaEntidade(dto);

        assertEquals(dto.getId(), dto.getId());
        assertEquals(dto.getNotaFiscalTipo(), dto.getNotaFiscalTipo());
        assertEquals(parceiroEntidade, entidade.getParceiroNegocio());
        assertEquals(dto.getNumero(), dto.getNumero());
        assertEquals(dto.getData(), dto.getData());
        assertEquals(dto.getItens(), dto.getItens());
        assertEquals(dto.getValorTotal(), dto.getValorTotal());
    }
}
