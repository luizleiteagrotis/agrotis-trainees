package com.agrotis.trainees.crud.service.produto.atualizacao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agrotis.trainees.crud.dto.produto.ProdutoAtualizacaoDto;
import com.agrotis.trainees.crud.dto.produto.ProdutoCadastroDto;
import com.agrotis.trainees.crud.dto.produto.ProdutoRetornoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.mapper.produto.ProdutoMapper;
import com.agrotis.trainees.crud.repository.produto.ProdutoRepository;
import com.agrotis.trainees.crud.service.produto.atualizacao.ProdutoAtualizacaoRnException;
import com.agrotis.trainees.crud.service.produto.atualizacao.ProdutoAtualizacaoServiceImpl;
import com.agrotis.trainees.crud.service.produto.cadastro.ProdutoCadastroRnException;
import com.agrotis.trainees.crud.service.produto.cadastro.ProdutoCadastroServiceImpl;

@ExtendWith(MockitoExtension.class)
class ProdutoAtualizacaoServiceImplTest {

	@Mock
	private ProdutoMapper produtoMapper;
	
	@Mock
	private ProdutoRepository produtoRepository;
	
	@InjectMocks	
	private ProdutoAtualizacaoServiceImpl atualizacaoServiceImpl;
	
	private final String NOME_PRODUTO = "Banana";
	private final String DESCRICAO_PRODUTO = "A melhor banana do mundo";
	private final String NOME_FABRICANTE = "Fabrica de bananas";
	private final Long NAO_ID_PRODUTO = 1L;
	private ParceiroNegocio fabricanteProduto;
	private Produto produto;
	private ProdutoAtualizacaoDto atualizacaoDto;
	private ProdutoRetornoDto retornoDto;
	
	@BeforeEach
	public void setUp() {
		fabricanteProduto = new ParceiroNegocio();
		produto = new Produto();
		atualizacaoDto = new ProdutoAtualizacaoDto();
		retornoDto = new ProdutoRetornoDto();
		atualizacaoDto.setId(NAO_ID_PRODUTO);
		atualizacaoDto.setNome(NOME_PRODUTO);
		atualizacaoDto.setDescricao(DESCRICAO_PRODUTO);
		produto.setFabricante(fabricanteProduto);
		fabricanteProduto.setNome(NOME_FABRICANTE);
		when(produtoRepository.buscarPor(NAO_ID_PRODUTO)).thenReturn(produto);
	}
	
	@Test
	public void deveLancarExceptionQuandoExisteOutroProdutoComOMesmoNomeDescricaoEFabricante() {
		when(produtoRepository.existeInstanciaCom(NOME_PRODUTO, 
												  DESCRICAO_PRODUTO, 
												  fabricanteProduto, 
												  NAO_ID_PRODUTO)).thenReturn(true);
		
		Exception exception = assertThrows(ProdutoAtualizacaoRnException.class, () -> {
			atualizacaoServiceImpl.atualizar(atualizacaoDto);
		});
		
		String mensagemEsperada = "Ja existe produto com nome {" + NOME_PRODUTO + "} e"
									+ " descricao {" + DESCRICAO_PRODUTO + "} do"
									+ " fabricante {" + NOME_FABRICANTE + "}";
		assertThat(exception.getMessage(), is(equalTo(mensagemEsperada)));
	}
	
	@Test
	public void deveNaoCadastrarProdutoQuandoExisteOutroProdutoComOMesmoNomeDescricaoEFabricante() {
		when(produtoRepository.existeInstanciaCom(NOME_PRODUTO, 
												  DESCRICAO_PRODUTO, 
												  fabricanteProduto, 
												  NAO_ID_PRODUTO)).thenReturn(true);
		
		try {
			atualizacaoServiceImpl.atualizar(atualizacaoDto);
		} catch (ProdutoAtualizacaoRnException ignored) {}
		
		verify(produtoRepository, never()).salvar(produto);
	}
	
	@Test
	public void deveSalvarProdutoQuandoNaoExisteOutroProdutoComOMesmoNomeDescricaoEFabricante() {
		when(produtoRepository.buscarPor(NAO_ID_PRODUTO)).thenReturn(produto);
		when(produtoMapper.converterParaEntidade(atualizacaoDto)).thenReturn(produto);
		when(produtoRepository.existeInstanciaCom(NOME_PRODUTO, 
												  DESCRICAO_PRODUTO, 
												  fabricanteProduto, 
												  NAO_ID_PRODUTO)).thenReturn(false);
		
		atualizacaoServiceImpl.atualizar(atualizacaoDto);
		
		verify(produtoRepository, times(1)).salvar(produto);
	}
}
