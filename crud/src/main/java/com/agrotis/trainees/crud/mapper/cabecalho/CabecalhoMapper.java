package com.agrotis.trainees.crud.mapper.cabecalho;

import com.agrotis.trainees.crud.dto.cabecalho.CabecalhoCadastroDto;
import com.agrotis.trainees.crud.dto.cabecalho.CabecalhoRetornoDto;
import com.agrotis.trainees.crud.entity.CabecalhoNota;

public interface CabecalhoMapper {

	CabecalhoNota converterParaEntidade(CabecalhoCadastroDto cadastroDto);
	
	CabecalhoRetornoDto converterParaDto(CabecalhoNota cabecalho);
}
