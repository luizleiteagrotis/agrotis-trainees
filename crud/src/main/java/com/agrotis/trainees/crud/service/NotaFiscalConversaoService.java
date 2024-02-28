package com.agrotis.trainees.crud.service;

import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;

@Component
public class NotaFiscalConversaoService {

    private ParceiroNegocioConversaoService conversaoParceiro;

    public NotaFiscalDto converterParaDto(NotaFiscal entidade) {
        NotaFiscalDto dto = new NotaFiscalDto();
        dto.setId(entidade.getId());
        dto.setNotaFiscalTipo(entidade.getNotaFiscalTipo());
        dto.setParceiroNegocio(conversaoParceiro.converterParaDto(entidade.getParceiroNegocio()));
        dto.setNumero(entidade.getNumero());
        dto.setData(entidade.getData());
        dto.setItens(entidade.getItens());
        dto.setValorTotal(entidade.getValorTotal());

        return dto;
    }

    public NotaFiscal converterParaEntidade(NotaFiscalDto dto) {
        NotaFiscal entidade = new NotaFiscal();
        entidade.setId(dto.getId());
        entidade.setNotaFiscalTipo(dto.getNotaFiscalTipo());
        entidade.setParceiroNegocio(conversaoParceiro.converterParaEntidade(dto.getParceiroNegocio()));
        entidade.setNumero(dto.getNumero());
        entidade.setData(dto.getData());
        entidade.setItens(dto.getItens());
        entidade.setValorTotal(dto.getValorTotal());

        return entidade;
    }

}
