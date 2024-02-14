package com.agrotis.trainees.crud.mapper.parceiro;

import com.agrotis.trainees.crud.dto.parceiro.ParceiroCadastroDto;
import com.agrotis.trainees.crud.dto.parceiro.ParceiroRetornoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;

public interface ParceiroMapper {
	
	ParceiroNegocio converterParaEntidade(ParceiroCadastroDto cadastroDto);
		
	ParceiroRetornoDto converterParaDto(ParceiroNegocio parceiro);
}
