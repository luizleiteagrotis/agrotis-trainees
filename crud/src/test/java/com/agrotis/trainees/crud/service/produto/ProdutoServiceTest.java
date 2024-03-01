package com.agrotis.trainees.crud.service.produto;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.agrotis.trainees.crud.dto.produto.ProdutoAtualizacaoDto;
import com.agrotis.trainees.crud.dto.produto.ProdutoCadastroDto;
import com.agrotis.trainees.crud.dto.produto.ProdutoRetornoDto;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

	@Mock
	private ProdutoCadastroService cadastroService;
	
	@Mock
	private ProdutoBuscaService buscaService;
	
	@Mock
	private ProdutoAtualizacaoService atualizacaoService;
	
	@Mock
	private ProdutoDelecaoService delecaoService;
	
	@InjectMocks
	private ProdutoService produtoService;
	
	@Test
	public void deveDelegarParaCadastroServiceQuandoCadastrarProduto() {
		ProdutoRetornoDto retornoDtoEsperado = new ProdutoRetornoDto();
		ProdutoCadastroDto cadastroDto = new ProdutoCadastroDto();
		when(cadastroService.cadastrar(cadastroDto)).thenReturn(retornoDtoEsperado);
		
		ProdutoRetornoDto retornoDto = produtoService.cadastrar(cadastroDto);
		
		verify(cadastroService, times(1)).cadastrar(cadastroDto);
		assertThat(retornoDto, is(sameInstance(retornoDtoEsperado)));
	}
	
	@Test
	public void deveDelegarParaBuscaServiceQuandoBuscarProdutoPorId() {
		ProdutoRetornoDto retornoDtoEsperado = new ProdutoRetornoDto();
		Long idProduto = 1L;
		when(buscaService.buscarPor(idProduto)).thenReturn(retornoDtoEsperado);
		
		ProdutoRetornoDto retornoDto = produtoService.buscarPor(idProduto);
		
		verify(buscaService, times(1)).buscarPor(idProduto);
		assertThat(retornoDto, is(sameInstance(retornoDtoEsperado)));
	}
	
	@Test
	public void deveDelegarParaBuscaServiceQuandoListarTodosProdutos() {
		int indexPagina = 1;
		int quantidadeElementos = 10;
		Pageable pageable = PageRequest.of(indexPagina, quantidadeElementos);
		Page<ProdutoRetornoDto> paginaEsperada = new PageImpl<ProdutoRetornoDto>(new ArrayList<ProdutoRetornoDto>());
		when(buscaService.listarTodos(pageable)).thenReturn(paginaEsperada);
		
		Page<ProdutoRetornoDto> paginaRetorno = produtoService.listarTodos(pageable);
		
		verify(buscaService, times(1)).listarTodos(pageable);
		assertThat(paginaRetorno, is(sameInstance(paginaEsperada)));
	}
	
	@Test
	public void deveDelegarParaAtualizacaoServiceQuandoAtualizarProduto() {
		ProdutoRetornoDto retornoDtoEsperado = new ProdutoRetornoDto();
		ProdutoAtualizacaoDto atualizacaoDto = new ProdutoAtualizacaoDto();
		when(atualizacaoService.atualizar(atualizacaoDto)).thenReturn(retornoDtoEsperado);
		
		ProdutoRetornoDto retornoDto = produtoService.atualizar(atualizacaoDto);
		
		verify(atualizacaoService, times(1)).atualizar(atualizacaoDto);
		assertThat(retornoDto, is(sameInstance(retornoDtoEsperado)));
	}
	
	@Test
	public void deveDelegarParaDelecaoServiceQuandoDeletarProdutoPorId() {
		Long idProduto = 1L;
		
		produtoService.deletarPor(idProduto);
		
		verify(delecaoService, times(1)).deletarPor(idProduto);
	}
}
