package com.agrotis.trainees.crud.mapper.item;

import com.agrotis.trainees.crud.dto.item.ItemCadastroDto;
import com.agrotis.trainees.crud.dto.item.ItemRetornoDto;
import com.agrotis.trainees.crud.entity.ItemNota;

public interface ItemMapper {

	ItemNota converterParaEntidade(ItemCadastroDto cadastroDto);
	
	ItemRetornoDto converterParaDto(ItemNota item);
}
