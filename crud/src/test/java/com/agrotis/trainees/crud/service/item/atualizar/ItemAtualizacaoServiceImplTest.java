package com.agrotis.trainees.crud.service.item.atualizar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agrotis.trainees.crud.dto.item.ItemAtualizacaoDto;
import com.agrotis.trainees.crud.dto.item.ItemRetornoDto;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.mapper.item.ItemMapper;
import com.agrotis.trainees.crud.repository.item.ItemNotaRepository;
import com.agrotis.trainees.crud.service.item.util.CalculadorItem;
import com.agrotis.trainees.crud.util.ItemNotaFactory;

@ExtendWith(MockitoExtension.class)
class ItemAtualizacaoServiceImplTest {

	@Mock
	private CalculadorItem calculadorItem;
	
	@Mock
	private ItemMapper itemMapper;
	
	@Mock
	private ItemNotaFactory itemNotaFactory;
	
	@Mock
	private ItemNotaRepository itemRepository;
	
	@Mock
	private ItemAtualizacaoRn itemAtualizacaoRn1;

	@Mock
	private ItemAtualizacaoRn itemAtualizacaoRn2;
	
	private ItemAtualizacaoServiceImpl itemAtualizacaoServiceImpl;
	
	private final Long ID_ITEM = 1L;
	private List<ItemAtualizacaoRn> atualizacaoRns;
	private ItemAtualizacaoDto itemAtualizacaoDto;
	private ItemNota itemNovo;
	private ItemNota itemAntigo;
	private ItemRetornoDto itemRetornoDto;
	
	@BeforeEach
	public void setUp() {
		atualizacaoRns = new ArrayList<>();
		itemAtualizacaoServiceImpl = new ItemAtualizacaoServiceImpl(itemMapper, itemNotaFactory, itemRepository, atualizacaoRns);
		itemAtualizacaoDto = new ItemAtualizacaoDto();
		itemNovo = new ItemNota();
		itemAntigo = new ItemNota();
		itemRetornoDto = new ItemRetornoDto();
		itemAtualizacaoDto.setId(ID_ITEM);
		itemRetornoDto.setId(ID_ITEM);
	}
	
	@Test
	public void deveNaoExecutarOutrosRnsQuandoUmDelesFalhar() {
		atualizacaoRns.add(itemAtualizacaoRn1);
		atualizacaoRns.add(itemAtualizacaoRn2);
		when(itemMapper.converterParaEntidade(itemAtualizacaoDto)).thenReturn(itemNovo);
		when(itemNotaFactory.criarClone(ID_ITEM)).thenReturn(itemAntigo);
		when(itemAtualizacaoRn1.operarSobre(itemNovo, itemAntigo)).thenThrow(ItemAtualizacaoRnException.class);
		
		try {
			itemAtualizacaoServiceImpl.atualizar(itemAtualizacaoDto);
		} catch (Exception ignored) {}
		
		verify(itemAtualizacaoRn2, never()).operarSobre(itemNovo, itemAntigo);
	}
	
	@Test
	public void deveNaoSalvarItemQuandoUmaRnFalhar() {
		atualizacaoRns.add(itemAtualizacaoRn1);
		when(itemMapper.converterParaEntidade(itemAtualizacaoDto)).thenReturn(itemNovo);
		when(itemNotaFactory.criarClone(ID_ITEM)).thenReturn(itemAntigo);
		when(itemAtualizacaoRn1.operarSobre(itemNovo, itemAntigo)).thenThrow(ItemAtualizacaoRnException.class);
		
		try {
			itemAtualizacaoServiceImpl.atualizar(itemAtualizacaoDto);
		} catch (Exception ignored) {}
		
		verify(itemRepository, never()).salvar(itemNovo);
	}
	
	@Test
	public void deveSalvarItemQuandoOperacoesForemBemSucedidas() {
		atualizacaoRns.add(itemAtualizacaoRn1);
		when(itemMapper.converterParaEntidade(itemAtualizacaoDto)).thenReturn(itemNovo);
		when(itemNotaFactory.criarClone(ID_ITEM)).thenReturn(itemAntigo);
		when(itemAtualizacaoRn1.operarSobre(itemNovo, itemAntigo)).thenReturn(itemNovo);
		
		itemAtualizacaoServiceImpl.atualizar(itemAtualizacaoDto);
		
		verify(itemRepository, times(1)).salvar(itemNovo);
	}
	
	@Test
	public void devePassarItensDiferentesQuandoSaoPassadosComoAgumentosDeAtualizacaoRn() {
		atualizacaoRns.add(itemAtualizacaoRn1);
		when(itemMapper.converterParaEntidade(itemAtualizacaoDto)).thenReturn(itemNovo);
		when(itemNotaFactory.criarClone(ID_ITEM)).thenReturn(itemAntigo);
		ArgumentCaptor<ItemNota> itemAntigoCaptor = ArgumentCaptor.forClass(ItemNota.class);
		ArgumentCaptor<ItemNota> itemNovoCaptor = ArgumentCaptor.forClass(ItemNota.class);
		when(itemAtualizacaoRn1.operarSobre(itemNovoCaptor.capture(), itemAntigoCaptor.capture())).thenReturn(itemNovo);
		
		itemAtualizacaoServiceImpl.atualizar(itemAtualizacaoDto);
		
		ItemNota itemNovo = itemNovoCaptor.getValue();
		ItemNota itemAntigo = itemAntigoCaptor.getValue();
		assertThat(itemNovo, is(not(itemAntigo)));
	}
	
	@Test
	public void deveRetornarItemRetornoDtoQuandoOperacoesForemBemSucedidas() {
		when(itemMapper.converterParaEntidade(itemAtualizacaoDto)).thenReturn(itemNovo);
		when(itemMapper.converterParaDto(itemNovo)).thenReturn(itemRetornoDto);
		
		ItemRetornoDto retornoDto = itemAtualizacaoServiceImpl.atualizar(itemAtualizacaoDto);
		
		assertThat(retornoDto, is(itemRetornoDto));
	}
	
	@Test
	public void deveExecutarTodasAsRnsQuandoCadastrarItemComSucesso() {
		atualizacaoRns.add(itemAtualizacaoRn1);
		atualizacaoRns.add(itemAtualizacaoRn2);
		when(itemMapper.converterParaEntidade(itemAtualizacaoDto)).thenReturn(itemNovo);
		when(itemNotaFactory.criarClone(ID_ITEM)).thenReturn(itemAntigo);
		when(itemAtualizacaoRn1.operarSobre(itemNovo, itemAntigo)).thenReturn(itemNovo);
		when(itemAtualizacaoRn2.operarSobre(itemNovo, itemAntigo)).thenReturn(itemNovo);
		
		itemAtualizacaoServiceImpl.atualizar(itemAtualizacaoDto);
		
		verify(itemAtualizacaoRn1, times(1)).operarSobre(itemNovo, itemAntigo);
		verify(itemAtualizacaoRn2, times(1)).operarSobre(itemNovo, itemAntigo);
	}
}
