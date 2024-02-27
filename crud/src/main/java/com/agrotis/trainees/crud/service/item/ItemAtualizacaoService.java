package com.agrotis.trainees.crud.service.item;

import com.agrotis.trainees.crud.dto.item.ItemAtualizacaoDto;
import com.agrotis.trainees.crud.dto.item.ItemRetornoDto;

public interface ItemAtualizacaoService {

	ItemRetornoDto atualizar(ItemAtualizacaoDto atualizacaoDto);
}
