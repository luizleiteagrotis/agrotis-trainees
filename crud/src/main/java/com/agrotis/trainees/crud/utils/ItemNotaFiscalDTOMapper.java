package com.agrotis.trainees.crud.utils;

import org.springframework.context.annotation.Configuration;

import com.agrotis.trainees.crud.dto.ItemNotaFiscalDto;
import com.agrotis.trainees.crud.entity.ItemNotaFiscal;

@Configuration
public class ItemNotaFiscalDTOMapper {

    public ItemNotaFiscalDto converterParaDto(ItemNotaFiscal entidade) {
        return new ItemNotaFiscalDto(entidade);
    }

    public ItemNotaFiscal convertarParaEntidade(ItemNotaFiscalDto dto) {
        return new ItemNotaFiscal(dto);
    }

}
