package com.agrotis.trainees.crud.service.item.cadastrar.rn;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.TipoNota;
import com.agrotis.trainees.crud.repository.produto.ProdutoRepository;

@ExtendWith(MockitoExtension.class)
class CustoMedioProdutoCadastroRnTest {

	@Mock
	private ProdutoRepository produtoRepository;
	
	@InjectMocks
	private CustoMedioProdutoCadastroRn custoMedioProdutoCadastroRn;
	
	private ItemNota item;
	private CabecalhoNota cabecalho;
	private Produto produto;

	@BeforeEach
	public void setUp() {
		item = new ItemNota();
		cabecalho = new CabecalhoNota();
		produto = new Produto();
		item.setCabecalhoNota(cabecalho);
		item.setProduto(produto);
	}
	
	@Test
	public void deveCalcularCustoMedioQuandoCabecalhoTipoEntrada() {
		produto.setCustoTotal(custoTotalProduto("10.00"));
		produto.setEstoque(estoqueProduto(20));
		cabecalho.setTipo(TipoNota.ENTRADA);
		
		custoMedioProdutoCadastroRn.operarSobre(item);
		
		assertThat(produto.getCustoMedio(), is(equalTo(custoMedioEsperado("00.50"))));
	}
	
	@Test
	public void deveNaoCalcularCustoMedioQuandoCabecalhoTipoSaida() {
		produto.setCustoTotal(custoTotalProduto("10.00"));
		produto.setEstoque(estoqueProduto(20));
		produto.setCustoMedio(custoMedioInicial("5.00"));
		cabecalho.setTipo(TipoNota.SAIDA);
		
		custoMedioProdutoCadastroRn.operarSobre(item);
		
		assertThat(produto.getCustoMedio(), is(equalTo(custoMedioEsperado("5.00"))));
	}
	
	@Test
	public void deveSalvarProdutoQuandoFazerOperacoesComSucessoECabecalhoTipoEntrada() {
		produto.setCustoTotal(custoTotalProduto("10.00"));
		produto.setEstoque(estoqueProduto(20));
		cabecalho.setTipo(TipoNota.ENTRADA);
		
		custoMedioProdutoCadastroRn.operarSobre(item);
		
		verify(produtoRepository, times(1)).salvar(produto);
	}
		
	private Integer estoqueProduto(Integer valor) {
		return valor;
	}
	
	private BigDecimal custoMedioInicial(String valor) {
		return new BigDecimal(valor);
	}
	
	private BigDecimal custoTotalProduto(String valor) {
		return new BigDecimal(valor);
	}
	
	private BigDecimal custoMedioEsperado(String valor) {
		return new BigDecimal(valor);
	}
}
