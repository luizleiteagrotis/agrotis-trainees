package com.agrotis.trainees.crudmenu.api.item;

import org.springframework.stereotype.Component;

import com.agrotis.trainees.crudmenu.api.CrudApiMethodsTemplate;
import com.agrotis.trainees.crudmenu.dto.item.ItemCadastroDto;
import com.agrotis.trainees.crudmenu.dto.item.ItemRetornoDto;

@Component
public class ItemApiImpl extends CrudApiMethodsTemplate<ItemCadastroDto, ItemRetornoDto> implements ItemApi {

	@Override
	protected String getUrl() {
		return "http://localhost:8081/crud/api/notas-fiscais/itens";
	}

	@Override
	protected Class<ItemRetornoDto> getRetornoDtoClass() {
		return ItemRetornoDto.class;
	}
}
