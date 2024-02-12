package com.agrotis.trainees.crud.utils;

import org.springframework.context.annotation.Configuration;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;

@Configuration
public class ParceiroNegocioDTOMapper {

    public ParceiroNegocioDto converterParaDto(ParceiroNegocio entidade) {
        return new ParceiroNegocioDto(entidade);
    }

    public ParceiroNegocio conveterParaEntidade(ParceiroNegocioDto dto) {
        return new ParceiroNegocio(dto);
    }
}
