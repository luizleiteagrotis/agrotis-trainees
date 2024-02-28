package com.agrotis.trainees.crud.service.item.cadastrar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
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

import com.agrotis.trainees.crud.dto.item.ItemCadastroDto;
import com.agrotis.trainees.crud.dto.item.ItemRetornoDto;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.mapper.item.ItemMapper;
import com.agrotis.trainees.crud.repository.item.ItemNotaRepository;
import com.agrotis.trainees.crud.service.item.util.SalvadorEmCascata;

@ExtendWith(MockitoExtension.class)
class ItemCadastroServiceImplTest {
	
	@Mock
	private ItemMapper itemMapper;
	
	@Mock
	private ItemCadastroRn itemCadastroRn1;
	
	@Mock
	private ItemCadastroRn itemCadastroRn2;
	
	@Mock
	private SalvadorEmCascata salvadorEmCascata;
	
	private ItemCadastroServiceImpl itemCadastroServiceImpl;

	List<ItemCadastroRn> cadastroRns;
	private ItemCadastroDto itemCadastroDto;
	private ItemNota item;
	private ItemRetornoDto itemRetornoDto;
	
	@BeforeEach
	public void setUp() {
		cadastroRns = new ArrayList<>();
		itemCadastroServiceImpl = new ItemCadastroServiceImpl(cadastroRns, itemMapper, salvadorEmCascata);
		itemCadastroDto = new ItemCadastroDto();
		item = new ItemNota();
		itemRetornoDto = new ItemRetornoDto();
	}
	
	
	@Test
	public void deveRetornarItemRetornoDtoQuandoCadastrarItem() {
		cadastroRns.add(itemCadastroRn1);
		when(itemMapper.converterParaEntidade(itemCadastroDto)).thenReturn(item);
		when(itemCadastroRn1.operarSobre(item)).thenReturn(item);
		when(itemMapper.converterParaDto(item)).thenReturn(itemRetornoDto);
		
		ItemRetornoDto retorno = itemCadastroServiceImpl.cadastrar(itemCadastroDto);
		
		assertThat(retorno, is(itemRetornoDto));
	}
	
	@Test
	public void devePassarPorTodosOsRnsQuandoForCadastrarItemComSucesso() {
		cadastroRns.add(itemCadastroRn1);
		cadastroRns.add(itemCadastroRn2);
		when(itemMapper.converterParaEntidade(itemCadastroDto)).thenReturn(item);
		when(itemCadastroRn1.operarSobre(item)).thenReturn(item);
		when(itemCadastroRn2.operarSobre(item)).thenReturn(item);
		
		itemCadastroServiceImpl.cadastrar(itemCadastroDto);
		
		verify(itemCadastroRn1, times(1)).operarSobre(item);
		verify(itemCadastroRn2, times(1)).operarSobre(item);
	}
	
	@Test
	public void deveSalvarItemEmCascataQuandoExecutarTodasAsRns() {
		cadastroRns.add(itemCadastroRn1);
		when(itemMapper.converterParaEntidade(itemCadastroDto)).thenReturn(item);
		when(itemCadastroRn1.operarSobre(item)).thenReturn(item);
		
		itemCadastroServiceImpl.cadastrar(itemCadastroDto);
		
		verify(salvadorEmCascata, times(1)).salvar(item);
	}
	
	@Test
	public void deveNaoSalvarItemEmCascataQuandoUmaRnFalhar() {
		cadastroRns.add(itemCadastroRn1);
		when(itemMapper.converterParaEntidade(itemCadastroDto)).thenReturn(item);
		when(itemCadastroRn1.operarSobre(item)).thenThrow(ItemCadastroRnException.class);
		
		try {
			itemCadastroServiceImpl.cadastrar(itemCadastroDto);
		} catch (ItemCadastroRnException ignored) {}
		
		verify(salvadorEmCascata, never()).salvar(item);
	}
	
	@Test
	public void deveNaoPassarPelasOutrasRnsQuandoUmaFalhar() {
		cadastroRns.add(itemCadastroRn1);
		cadastroRns.add(itemCadastroRn2);
		when(itemMapper.converterParaEntidade(itemCadastroDto)).thenReturn(item);
		when(itemCadastroRn1.operarSobre(item)).thenThrow(ItemCadastroRnException.class);
		
		assertThrows(ItemCadastroRnException.class, () -> {
			itemCadastroServiceImpl.cadastrar(itemCadastroDto);
		});
		
		verify(itemCadastroRn2, never()).operarSobre(item);
	}
}
