package com.agrotis.trainees.crudmenu.api.cabecalho;

import org.springframework.stereotype.Component;

import com.agrotis.trainees.crudmenu.api.CrudApiMethodsTemplate;
import com.agrotis.trainees.crudmenu.dto.cabecalho.CabecalhoCadastroDto;
import com.agrotis.trainees.crudmenu.dto.cabecalho.CabecalhoRetornoDto;

@Component
public class CabecalhoApiImpl extends CrudApiMethodsTemplate<CabecalhoCadastroDto, CabecalhoRetornoDto> implements CabecalhoApi {

	@Override
	protected String getUrl() {
		return "http://localhost:8081/crud/api/notas-fiscais/cabecalhos";
	}

	@Override
	protected Class<CabecalhoRetornoDto> getRetornoDtoClass() {
		return CabecalhoRetornoDto.class;
	}
}
