package com.agrotis.trainees.crud.wrapper;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;

public interface ConversorNotaFiscal {

    NotaFiscalDto converterParaDto(NotaFiscal entidade);

    NotaFiscal converterParaEntidade(NotaFiscalDto dto);
}
