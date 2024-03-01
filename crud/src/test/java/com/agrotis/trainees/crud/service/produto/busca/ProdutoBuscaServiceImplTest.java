package com.agrotis.trainees.crud.service.produto.busca;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agrotis.trainees.crud.dto.produto.ProdutoRetornoDto;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.mapper.produto.ProdutoMapper;
import com.agrotis.trainees.crud.repository.produto.ProdutoRepository;

@ExtendWith(MockitoExtension.class)
class ProdutoBuscaServiceImplTest {

	@Mock
	private ProdutoRepository produtoRepository;
	
	@Mock
	private ProdutoMapper produtoMapper;
	
	@InjectMocks
	private ProdutoBuscaServiceImpl buscaServiceImpl;
	
	@Test
	public void deveRetornarRetornoDtoQuandoEncontrarProdutoPorId() {
		Long idProduto = 1L;
		Produto produto = new Produto();
		ProdutoRetornoDto retornoDtoEsperado = new ProdutoRetornoDto();
		when(produtoRepository.buscarPor(idProduto)).thenReturn(produto);
		when(produtoMapper.converterParaDto(produto)).thenReturn(retornoDtoEsperado);
		
		ProdutoRetornoDto retornoDto = buscaServiceImpl.buscarPor(idProduto);
		
		assertThat(retornoDto, is(sameInstance(retornoDtoEsperado)));
	}

}
