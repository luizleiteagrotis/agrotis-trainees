package com.agrotis.trainees.crud.service.parceiro;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.agrotis.trainees.crud.dto.parceiro.ParceiroAtualizacaoDto;
import com.agrotis.trainees.crud.dto.parceiro.ParceiroCadastroDto;

@ExtendWith(MockitoExtension.class)
class ParceiroNegocioServiceTest {

	@Mock
	private ParceiroNegocioCadastroService cadastroService;
	
	@Mock
	private ParceiroNegocioBuscaService buscaService;
	
	@Mock
	private ParceiroNegocioAtualizacaoService atualizacaoService;
	
	@Mock
	private ParceiroNegocioDelecaoService delecaoService;
	
	@InjectMocks
	private ParceiroNegocioService parceiroNegocioService;
	
	@Test
	public void deveDelegarParaCadastroServiceQuandoCadastrarParceiro() {
		ParceiroCadastroDto cadastroDto = new ParceiroCadastroDto();
		
		parceiroNegocioService.cadastrar(cadastroDto);
		
		verify(cadastroService, times(1)).cadastrar(cadastroDto);
	}
	
	@Test
	public void deveDelegarParaBuscaServiceQuandoBuscarPorIdParceiro() {
		final Long ID_PARCEIRO = 1L;
		
		parceiroNegocioService.buscarPor(ID_PARCEIRO);
		
		verify(buscaService, times(1)).buscarPor(ID_PARCEIRO);
	}
	
	@Test
	public void deveDelegarParaBuscaServiceQuandoListarTodosParceiros() {
		final int INDEX_PAGINA_PAGEABLE = 1;
		final int QUANTIDADE_ELEMENTOS_PAGEABLE = 10;
		Pageable pageable = PageRequest.of(INDEX_PAGINA_PAGEABLE, QUANTIDADE_ELEMENTOS_PAGEABLE);
		
		parceiroNegocioService.listarTodos(pageable);
		
		verify(buscaService, times(1)).listarTodos(pageable);
	}
	
	@Test
	public void deveDelegarParaAtualizacaoServiceQuandoAtualizarParceiro() {
		ParceiroAtualizacaoDto atualizacaoDto = new ParceiroAtualizacaoDto();
		
		parceiroNegocioService.atualizar(atualizacaoDto);
		
		verify(atualizacaoService, times(1)).atualizar(atualizacaoDto);
	}
	
	@Test
	public void deveDelegarParaDelecaoServiceQuandoDeletarPorIdParceiro() {
		final Long ID_PARCEIRO = 1L;
		
		parceiroNegocioService.deletarPor(ID_PARCEIRO);
		
		verify(delecaoService, times(1)).deletarPor(ID_PARCEIRO);
	}
}
