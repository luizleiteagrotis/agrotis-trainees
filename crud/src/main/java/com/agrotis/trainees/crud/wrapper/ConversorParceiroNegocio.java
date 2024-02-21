package com.agrotis.trainees.crud.wrapper;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;

public interface ConversorParceiroNegocio {

    ParceiroNegocioDto converterParaDto(ParceiroNegocio entidade);

    ParceiroNegocio converterParaEntidade(ParceiroNegocioDto dto);
}
