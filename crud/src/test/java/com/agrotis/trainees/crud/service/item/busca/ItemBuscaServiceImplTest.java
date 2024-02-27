package com.agrotis.trainees.crud.service.item.busca;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agrotis.trainees.crud.dto.item.ItemRetornoDto;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.mapper.item.ItemMapper;
import com.agrotis.trainees.crud.repository.item.ItemNotaRepository;
import com.agrotis.trainees.crud.repository.wrapper.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
class ItemBuscaServiceImplTest {

	@Mock
	private ItemNotaRepository itemRepository;
	
	@Mock
	private ItemMapper itemMapper;
	
	@InjectMocks
	private ItemBuscaServiceImpl itemBuscaServiceImpl;
	
	private final Long ITEM_ID = 1L;
	private ItemNota item;
	private ItemRetornoDto itemRetornoDto;
	
	@BeforeEach
	public void setUp() {
		item = new ItemNota();
		itemRetornoDto = new ItemRetornoDto();
	}
	
	@Test
	public void deveRetornarItemRetornoDtoQuandoEncontrarItem() {
		when(itemRepository.buscarPor(ITEM_ID)).thenReturn(item);
		when(itemMapper.converterParaDto(item)).thenReturn(itemRetornoDto);
		
		ItemRetornoDto retorno = itemBuscaServiceImpl.buscarPor(ITEM_ID);
		
		assertThat(retorno, is(itemRetornoDto));
	}
}
