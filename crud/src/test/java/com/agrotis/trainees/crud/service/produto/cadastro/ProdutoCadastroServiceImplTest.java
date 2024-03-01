package com.agrotis.trainees.crud.service.produto.cadastro;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agrotis.trainees.crud.dto.produto.ProdutoCadastroDto;
import com.agrotis.trainees.crud.dto.produto.ProdutoRetornoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.mapper.produto.ProdutoMapper;
import com.agrotis.trainees.crud.repository.produto.ProdutoRepository;

@ExtendWith(MockitoExtension.class)
class ProdutoCadastroServiceImplTest {

	@Mock
	private ProdutoMapper produtoMapper;
	
	@Mock
	private ProdutoRepository produtoRepository;
	
	@InjectMocks	
	private ProdutoCadastroServiceImpl cadastroServiceImpl;
	
	private final String NOME_PRODUTO = "Banana";
	private final String DESCRICAO_PRODUTO = "A melhor banana do mundo";
	private final String NOME_FABRICANTE = "Fabrica de bananas";
	private ParceiroNegocio fabricanteProduto;
	private Produto produto;
	private ProdutoCadastroDto cadastroDto;
	private ProdutoRetornoDto retornoDto;
	
	@BeforeEach
	public void setUp() {
		fabricanteProduto = new ParceiroNegocio();
		produto = new Produto();
		cadastroDto = new ProdutoCadastroDto();
		retornoDto = new ProdutoRetornoDto();
		produto.setNome(NOME_PRODUTO);
		produto.setDescricao(DESCRICAO_PRODUTO);
		produto.setFabricante(fabricanteProduto);
		fabricanteProduto.setNome(NOME_FABRICANTE);
		when(produtoMapper.converterParaEntidade(cadastroDto)).thenReturn(produto);
	}
	
	@Test
	public void deveLancarExceptionQuandoExisteOutroProdutoComOMesmoNomeDescricaoEFabricante() {
		when(produtoRepository.existeInstanciaCom(NOME_PRODUTO, DESCRICAO_PRODUTO, fabricanteProduto)).thenReturn(true);
		
		Exception exception = assertThrows(ProdutoCadastroRnException.class, () -> {
			cadastroServiceImpl.cadastrar(cadastroDto);
		});
		String mensagemEsperada = "Ja existe produto com nome {" + NOME_PRODUTO + "} e"
									+ " descricao {" + DESCRICAO_PRODUTO + "} do"
									+ " fabricante {" + NOME_FABRICANTE + "}";
		assertThat(exception.getMessage(), is(equalTo(mensagemEsperada)));
	}
	
	@Test
	public void deveNaoCadastrarProdutoQuandoExisteOutroProdutoComOMesmoNomeDescricaoEFabricante() {
		when(produtoRepository.existeInstanciaCom(NOME_PRODUTO, DESCRICAO_PRODUTO, fabricanteProduto)).thenReturn(true);
		
		try {
			cadastroServiceImpl.cadastrar(cadastroDto);			
		} catch (ProdutoCadastroRnException ignored) {}
		
		verify(produtoRepository, never()).salvar(produto);
	}
	
	@Test
	public void deveSalvarProdutoQuandoNaoExisteOutroProdutoComOMesmoNomeDescricaoEFabricante() {
		when(produtoRepository.existeInstanciaCom(NOME_PRODUTO, DESCRICAO_PRODUTO, fabricanteProduto)).thenReturn(false);
		
		cadastroServiceImpl.cadastrar(cadastroDto);	
		
		verify(produtoRepository, times(1)).salvar(produto);
	}
}
