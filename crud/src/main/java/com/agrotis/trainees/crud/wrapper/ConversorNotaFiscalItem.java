package com.agrotis.trainees.crud.wrapper;

import com.agrotis.trainees.crud.dto.NotaFiscalItemDto;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;

public interface ConversorNotaFiscalItem {

    NotaFiscalItemDto converterParaDto(NotaFiscalItem entidade);

    NotaFiscalItem converterParaEntidade(NotaFiscalItemDto dto);
}
