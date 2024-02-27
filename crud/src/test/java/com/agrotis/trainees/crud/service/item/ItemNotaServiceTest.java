package com.agrotis.trainees.crud.service.item;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.agrotis.trainees.crud.dto.item.ItemAtualizacaoDto;
import com.agrotis.trainees.crud.dto.item.ItemCadastroDto;
import com.agrotis.trainees.crud.dto.item.ItemRetornoDto;

@ExtendWith(MockitoExtension.class)
class ItemNotaServiceTest {

	@Spy
	private ItemCadastroService cadastroService;
	
	@Spy
	private ItemBuscaService buscaService;
	
	@Spy
	private ItemAtualizacaoService atualizacaoService;
	
	@Spy
	private ItemDelecaoService delecaoService;
	
	@InjectMocks
	private ItemNotaService itemNotaService;
	
	private final Long ID_ITEM = 1L;
	private final Integer INDEX_PAGINA_PAGEABLE = 0;
	private final Integer QUANTIDADE_ELEMENTOS_PAGEABLE = 10;
	
	@Test
	public void deveDelegarParaCadastroServiceQuandoCadastrarUmItem() {
		ItemCadastroDto itemCadastroDto = new ItemCadastroDto();
		
		itemNotaService.cadastrar(itemCadastroDto);
		
		verify(cadastroService, times(1)).cadastrar(itemCadastroDto);
	}
	
	
	@Test
	public void deveDelegarParaBuscaServiceQuandoBuscarPorIdUmItem() {		
		itemNotaService.buscarPor(ID_ITEM);
		
		verify(buscaService, times(1)).buscarPor(ID_ITEM);
	}
	
	@Test
	public void deveDelegarParaBuscaServiceQuandoListarTodosItens() {
		Pageable pageable = criarPageableTeste();
		
		itemNotaService.listarTodos(pageable);
		
		verify(buscaService, times(1)).listarTodos(pageable);
	}
	
	@Test
	public void deveDelegarParaAtualizacaoServiceQuandoAtualizarUmItem() {
		ItemAtualizacaoDto itemAtualizacaoDto = new ItemAtualizacaoDto();
		
		itemNotaService.atualizar(itemAtualizacaoDto);
		
		verify(atualizacaoService, times(1)).atualizar(itemAtualizacaoDto);
	}
	
	@Test
	public void deveDelegarParaDelecaoServiceQuandoDeletarUmItem( ) {
		itemNotaService.deletar(ID_ITEM);
		
		verify(delecaoService, times(1)).deletar(ID_ITEM);
	}
	
	private Pageable criarPageableTeste() {
		Pageable pageable = PageRequest.of(INDEX_PAGINA_PAGEABLE, QUANTIDADE_ELEMENTOS_PAGEABLE);
		return pageable;
	}
}
