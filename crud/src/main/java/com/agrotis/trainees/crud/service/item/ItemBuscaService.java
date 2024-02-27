package com.agrotis.trainees.crud.service.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.agrotis.trainees.crud.dto.item.ItemRetornoDto;

public interface ItemBuscaService {

	ItemRetornoDto buscarPor(Long idItem);
	
	Page<ItemRetornoDto> listarTodos(Pageable pageable);
}
