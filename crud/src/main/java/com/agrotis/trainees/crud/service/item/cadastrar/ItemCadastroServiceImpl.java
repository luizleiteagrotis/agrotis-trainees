package com.agrotis.trainees.crud.service.item.cadastrar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.agrotis.trainees.crud.dto.item.ItemCadastroDto;
import com.agrotis.trainees.crud.dto.item.ItemRetornoDto;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.mapper.item.ItemMapper;
import com.agrotis.trainees.crud.repository.item.ItemNotaRepository;
import com.agrotis.trainees.crud.service.item.ItemCadastroService;
import com.agrotis.trainees.crud.service.item.util.CalculadorItem;
import com.agrotis.trainees.crud.service.item.util.CalculadorItemException;

@Component
public class ItemCadastroServiceImpl implements ItemCadastroService {

	private List<ItemCadastroRn> cadastroRns;
	private ItemNotaRepository itemRepository;
	private ItemMapper itemMapper;
	
	@Autowired
	public ItemCadastroServiceImpl(List<ItemCadastroRn> cadastroRns, ItemMapper itemMapper, ItemNotaRepository itemRepository) {
		this.cadastroRns = cadastroRns;
		this.itemMapper = itemMapper;
		this.itemRepository = itemRepository;
	}

	@Override
	@Transactional(readOnly = false)
	public ItemRetornoDto cadastrar(ItemCadastroDto cadastroDto) {
		ItemNota item = itemMapper.converterParaEntidade(cadastroDto);
		executarRns(item);
		itemRepository.salvar(item);
		return itemMapper.converterParaDto(item);
	}
	
	private void executarRns(ItemNota item) {
		cadastroRns.forEach((rn) -> rn.operarSobre(item));
	}
}
