package com.agrotis.trainees.crud.service.item.deletar;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.mapper.item.ItemMapper;
import com.agrotis.trainees.crud.repository.item.ItemNotaRepository;
import com.agrotis.trainees.crud.service.item.util.SalvadorEmCascata;

@ExtendWith(MockitoExtension.class)
class ItemDelecaoServiceImplTest {

	@Mock
	private ItemMapper itemMapper;
	
	@Mock
	private SalvadorEmCascata salvadorEmCascata;
	
	@Mock
	private ItemNotaRepository itemRepository;
	
	@Mock
	private ItemDelecaoRn itemDelecaoRn1;
	
	@Mock
	private ItemDelecaoRn itemDelecaoRn2;
	
	private List<ItemDelecaoRn> delecaoRns;
	
	private ItemDelecaoServiceImpl itemDelecaoServiceImpl;
	
	private final long ITEM_ID = 1L;
	private ItemNota item;
	
	@BeforeEach
	public void setUp() {
		delecaoRns = new ArrayList<>();
		itemDelecaoServiceImpl = new ItemDelecaoServiceImpl(delecaoRns, itemRepository, salvadorEmCascata);
		item = new ItemNota();
	}
	
	@Test
	public void deveDeletarItemQuandoPassarPorTodasAsRns() {
		when(itemRepository.buscarPor(ITEM_ID)).thenReturn(item);
		delecaoRns.add(itemDelecaoRn1);
		when(itemDelecaoRn1.operarSobre(item)).thenReturn(item);
		
		itemDelecaoServiceImpl.deletar(ITEM_ID);
		
		verify(itemRepository, times(1)).deletar(ITEM_ID);
	}
	
	@Test
	public void deveSalvarItemEmCascataQuandoPassarPorTodasAsRns() {
		when(itemRepository.buscarPor(ITEM_ID)).thenReturn(item);
		
		itemDelecaoServiceImpl.deletar(ITEM_ID);
		
		verify(salvadorEmCascata, times(1)).salvar(item);
	}
	
	@Test
	public void deveExecutarTodosOsRnsQuandoEncontrarUmItemComSucesso() {
		when(itemRepository.buscarPor(ITEM_ID)).thenReturn(item);
		delecaoRns.add(itemDelecaoRn1);
		delecaoRns.add(itemDelecaoRn2);
		
		try {
			itemDelecaoServiceImpl.deletar(ITEM_ID);
		} catch (Exception ignored) {}
		
		verify(itemDelecaoRn1, times(1)).operarSobre(item);
		verify(itemDelecaoRn2, times(1)).operarSobre(item);
	}
	
	@Test
	public void deveNaoDeletarItemQuandoUmaRnFalhar() {
		when(itemRepository.buscarPor(ITEM_ID)).thenReturn(item);
		delecaoRns.add(itemDelecaoRn1);
		when(itemDelecaoRn1.operarSobre(item)).thenThrow(ItemDelecaoRnException.class);
		
		try {
			itemDelecaoServiceImpl.deletar(ITEM_ID);
		} catch (ItemDelecaoRnException ignored) {}
		
		verify(itemRepository, never()).deletar(ITEM_ID);
	}
	
	@Test
	public void deveNaoExecutarOutrosRnsQuandoUmDelesFalhar() {
		when(itemRepository.buscarPor(ITEM_ID)).thenReturn(item);
		delecaoRns.add(itemDelecaoRn1);
		delecaoRns.add(itemDelecaoRn2);
		when(itemDelecaoRn1.operarSobre(item)).thenThrow(ItemDelecaoRnException.class);
		
		try {
			itemDelecaoServiceImpl.deletar(ITEM_ID);
		} catch (ItemDelecaoRnException ignored) {}
		
		verify(itemDelecaoRn2, never()).operarSobre(item);
	}
}
