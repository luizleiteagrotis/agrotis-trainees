package com.agrotis.trainees.crud.service;

import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;

@Component
public class ParceiroNegocioConversaoService {

    public ParceiroNegocioDto converterParaDto(ParceiroNegocio entidade) {
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setId(entidade.getId());
        dto.setNome(entidade.getNome());
        dto.setInscricaoFiscal(entidade.getInscricaoFiscal());
        dto.setEndereco(entidade.getEndereco());
        dto.setTelefone(entidade.getTelefone());

        return dto;
    }

    public ParceiroNegocio converterParaEntidade(ParceiroNegocioDto dto) {
        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setId(dto.getId());
        entidade.setNome(dto.getNome());
        entidade.setInscricaoFiscal(dto.getInscricaoFiscal());
        entidade.setEndereco(dto.getEndereco());
        entidade.setTelefone(dto.getTelefone());

        return entidade;
    }

}
