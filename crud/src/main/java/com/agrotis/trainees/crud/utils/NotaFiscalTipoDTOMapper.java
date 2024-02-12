package com.agrotis.trainees.crud.utils;

import org.springframework.context.annotation.Configuration;

import com.agrotis.trainees.crud.dto.NotaFiscalTipoDto;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;

@Configuration
public class NotaFiscalTipoDTOMapper {

    public NotaFiscalTipoDto converterParaDto(NotaFiscalTipo entidade) {
        return new NotaFiscalTipoDto(entidade);
    }

    public NotaFiscalTipo converterParaEntidade(NotaFiscalTipoDto dto) {
        return new NotaFiscalTipo(dto);
    }

}
