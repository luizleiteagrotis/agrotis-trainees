package com.agrotis.trainees.crudmenu.api.parceiro;

import org.springframework.stereotype.Component;

import com.agrotis.trainees.crudmenu.api.CrudApiMethodsTemplate;
import com.agrotis.trainees.crudmenu.dto.parceiro.ParceiroCadastroDto;
import com.agrotis.trainees.crudmenu.dto.parceiro.ParceiroRetornoDto;

@Component
public class ParceiroApiImpl extends CrudApiMethodsTemplate<ParceiroCadastroDto, ParceiroRetornoDto> implements ParceiroApi {

	@Override
	protected String getUrl() {
		return "http://localhost:8081/crud/api/parceiros";
	}

	@Override
	protected Class<ParceiroRetornoDto> getRetornoDtoClass() {
		return ParceiroRetornoDto.class;
	}
}
