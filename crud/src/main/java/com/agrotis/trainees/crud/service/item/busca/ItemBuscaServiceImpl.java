package com.agrotis.trainees.crud.service.item.busca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.dto.item.ItemRetornoDto;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.mapper.item.ItemMapper;
import com.agrotis.trainees.crud.repository.item.ItemNotaRepository;
import com.agrotis.trainees.crud.repository.wrapper.EntityNotFoundException;
import com.agrotis.trainees.crud.service.item.ItemBuscaService;

@Component
public class ItemBuscaServiceImpl implements ItemBuscaService {
	
	private ItemNotaRepository itemRepository;
	private ItemMapper itemMapper;
	
	@Autowired
	public ItemBuscaServiceImpl(ItemNotaRepository itemRepository, ItemMapper itemMapper) {
		this.itemRepository = itemRepository;
		this.itemMapper = itemMapper;
	}

	@Override
	public ItemRetornoDto buscarPor(Long idItem) {
		ItemNota item;
		item = itemRepository.buscarPor(idItem);		
		return itemMapper.converterParaDto(item);
	}

	@Override
	public Page<ItemRetornoDto> listarTodos(Pageable pageable) {
		return itemRepository.buscarTodos(pageable).map((item) -> itemMapper.converterParaDto(item));
	}
}
