package com.agrotis.trainees.crud.service.item.deletar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.repository.item.ItemNotaRepository;
import com.agrotis.trainees.crud.repository.wrapper.EntityNotFoundException;
import com.agrotis.trainees.crud.service.item.ItemDelecaoService;

@Component
public class ItemDelecaoServiceImpl implements ItemDelecaoService {

	private List<ItemDelecaoRn> delecaoRns;
	private ItemNotaRepository itemRepository;
	
	@Autowired
	public ItemDelecaoServiceImpl(List<ItemDelecaoRn> delecaoRns, ItemNotaRepository itemRepository) {
		this.delecaoRns = delecaoRns;
		this.itemRepository = itemRepository;
	}

	@Override
	@Transactional(readOnly = false)
	public void deletar(Long idItem) {
		ItemNota item; 
		item = itemRepository.buscarPor(idItem);
		
		delecaoRns.forEach((rn) -> {
			rn.operarSobre(item);
		});
		
		itemRepository.deletar(idItem);
	}

}
