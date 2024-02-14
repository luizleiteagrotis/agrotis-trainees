package com.agrotis.trainees.crudmenu.api.item;

import com.agrotis.trainees.crudmenu.dto.item.ItemCadastroDto;
import com.agrotis.trainees.crudmenu.dto.item.ItemRetornoDto;

public interface ItemApi {

	ItemRetornoDto cadastrar(ItemCadastroDto cadastroDto);
}
