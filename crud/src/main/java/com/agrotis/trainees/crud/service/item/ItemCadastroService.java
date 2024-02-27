package com.agrotis.trainees.crud.service.item;

import com.agrotis.trainees.crud.dto.item.ItemCadastroDto;
import com.agrotis.trainees.crud.dto.item.ItemRetornoDto;

public interface ItemCadastroService {

	ItemRetornoDto cadastrar(ItemCadastroDto cadastroDto);
}
