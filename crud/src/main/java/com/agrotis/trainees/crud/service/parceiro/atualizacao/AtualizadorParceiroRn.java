package com.agrotis.trainees.crud.service.parceiro.atualizacao;

import com.agrotis.trainees.crud.dto.parceiro.ParceiroAtualizacaoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;

public interface AtualizadorParceiroRn {
	
	ParceiroNegocio operarSobre(ParceiroAtualizacaoDto atualizacaoDto);
}
