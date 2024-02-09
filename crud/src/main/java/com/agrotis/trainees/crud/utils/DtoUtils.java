package com.agrotis.trainees.crud.utils;

import com.agrotis.trainees.crud.dtos.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;

public class DtoUtils {
    
    public static ParceiroNegocio converteParaEntidade(ParceiroNegocioDto dto) {
        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setNome(dto.getNome());
        entidade.setTelefone(dto.getTelefone());
        entidade.setInscricaoFiscal(dto.getInscricaoFiscal());
        entidade.setEndereco(dto.getEndereco());
        return entidade;
    }
    
    public static ParceiroNegocioDto converteParaDto(ParceiroNegocio entidade) {
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setId(entidade.getId());
        dto.setNome(entidade.getNome());
        dto.setTelefone(entidade.getTelefone());
        dto.setInscricaoFiscal(entidade.getInscricaoFiscal());
        dto.setEndereco(entidade.getEndereco());
        return dto;
    }

}
