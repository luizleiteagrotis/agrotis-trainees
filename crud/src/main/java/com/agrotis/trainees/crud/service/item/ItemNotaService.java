package com.agrotis.trainees.crud.service.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.dto.item.ItemAtualizacaoDto;
import com.agrotis.trainees.crud.dto.item.ItemCadastroDto;
import com.agrotis.trainees.crud.dto.item.ItemRetornoDto;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.mapper.item.ItemMapper;
import com.agrotis.trainees.crud.repository.item.ItemNotaRepository;
import com.agrotis.trainees.crud.service.item.atualizar.AtualizadorItemService;
import com.agrotis.trainees.crud.service.item.cadastrar.CadastradorItemService;
import com.agrotis.trainees.crud.service.item.deletar.DeletadorItemService;

@Service
public class ItemNotaService {
	
	private final ItemMapper MAPPER;
	private final CadastradorItemService CADASTRADOR_ITEM;
	private final AtualizadorItemService ATUALIZADOR_ITEM;
	private final DeletadorItemService DELETADOR_ITEM;
	private final ItemNotaRepository ITEM_REPOSITORY;
	
	@Autowired
	public ItemNotaService(
			ItemMapper mapper,
			ItemNotaRepository itemRepository, 
			CadastradorItemService cadastrarItemService,
			DeletadorItemService deletadorItemService,
			AtualizadorItemService atualizarItemService) {
		MAPPER = mapper;
		ITEM_REPOSITORY = itemRepository;
		CADASTRADOR_ITEM = cadastrarItemService;
		DELETADOR_ITEM = deletadorItemService;
		ATUALIZADOR_ITEM = atualizarItemService;
	}
	
	public ItemRetornoDto salvar(ItemCadastroDto cadastroDto) {
		ItemNota item = MAPPER.converterParaEntidade(cadastroDto);
		return CADASTRADOR_ITEM.cadastrar(cadastroDto);
	}
	 
	public ItemRetornoDto buscar(Long id) {
		ItemNota item = ITEM_REPOSITORY.buscarPor(id);
		return MAPPER.converterParaDto(item);
	}
		
	public Page<ItemRetornoDto> buscarTodos(Pageable pageable) {
		return ITEM_REPOSITORY.buscarTodos(pageable).map((item) -> MAPPER.converterParaDto(item));
	}
	
	public ItemRetornoDto atualizar(ItemAtualizacaoDto atualizacaoDto) {
		return ATUALIZADOR_ITEM.atualizar(atualizacaoDto);
	}
	
	public void deletar(Long id) {
		DELETADOR_ITEM.deletar(id);
	}
}
