package com.agrotis.trainees.crud.mapper.parceiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.dto.parceiro.ParceiroCadastroDto;
import com.agrotis.trainees.crud.dto.parceiro.ParceiroRetornoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ParceiroMapperImpl implements ParceiroMapper {
	
	private ObjectMapper mapper;
	
	@Autowired
	public ParceiroMapperImpl(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public ParceiroNegocio converterParaEntidade(ParceiroCadastroDto cadastroDto) {
		return mapper.convertValue(cadastroDto, ParceiroNegocio.class);
	}

	@Override
	public ParceiroRetornoDto converterParaDto(ParceiroNegocio parceiro) {
		return mapper.convertValue(parceiro, ParceiroRetornoDto.class);
	}
}
