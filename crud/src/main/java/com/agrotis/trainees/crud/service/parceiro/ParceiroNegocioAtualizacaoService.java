package com.agrotis.trainees.crud.service.parceiro;

import com.agrotis.trainees.crud.dto.parceiro.ParceiroAtualizacaoDto;
import com.agrotis.trainees.crud.dto.parceiro.ParceiroRetornoDto;

public interface ParceiroNegocioAtualizacaoService {

	ParceiroRetornoDto atualizar(ParceiroAtualizacaoDto atualizacaoDto);
}
