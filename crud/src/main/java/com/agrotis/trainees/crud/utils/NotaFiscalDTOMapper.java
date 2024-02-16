package com.agrotis.trainees.crud.utils;

import org.springframework.context.annotation.Configuration;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;

@Configuration
public class NotaFiscalDTOMapper {

    public NotaFiscalDto converterParaDto(NotaFiscal entidade) {
        return new NotaFiscalDto(entidade);
    }

    public NotaFiscal converterParaEntidade(NotaFiscalDto dto) {
        return new NotaFiscal(dto);
    }

}
