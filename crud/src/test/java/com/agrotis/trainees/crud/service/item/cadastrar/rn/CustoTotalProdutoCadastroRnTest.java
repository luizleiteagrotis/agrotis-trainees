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
class CustoTotalProdutoCadastroRnTest {
	
	@Mock
	private ProdutoRepository produtoRepository;
	
	@InjectMocks
	private CustoTotalProdutoCadastroRn custoTotalProdutoCadastroRn;
	
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
	public void deveAumentarCustoTotalProdutoQuandoItemComCabecalhoTipoEntrada() {
		produto.setCustoTotal(custoTotalInicial("00.00"));
		item.setValorTotal(valorTotalItem("10.00"));
		cabecalho.setTipo(TipoNota.ENTRADA);
		
		custoTotalProdutoCadastroRn.operarSobre(item);
		
		assertThat(produto.getCustoTotal(), is(equalTo(custoTotalEsperado("10.00"))));
	}
	
	@Test
	public void deveDiminuirCustoTotalProdutoQuandoItemComCabecalhoTipoSaida() {
		produto.setCustoTotal(custoTotalInicial("30.00"));
		item.setValorTotal(valorTotalItem("20.00"));
		cabecalho.setTipo(TipoNota.SAIDA);
		
		custoTotalProdutoCadastroRn.operarSobre(item);
		
		assertThat(produto.getCustoTotal(), is(equalTo(custoTotalEsperado("10.00"))));
	}
	
	@Test
	public void deveSalvarProdutoQuandoExecutarOperacoesComSucessoDeItemComCabecalhoTipoEntrada() {
		produto.setCustoTotal(custoTotalInicial("00.00"));
		item.setValorTotal(valorTotalItem("10.00"));
		cabecalho.setTipo(TipoNota.ENTRADA);
		
		custoTotalProdutoCadastroRn.operarSobre(item);
		
		verify(produtoRepository, times(1)).salvar(produto);
	}
	
	@Test
	public void deveSalvarProdutoQuandoExecutarOperacoesComSucessoDeItemComCabecalhoTipoSaida() {
		produto.setCustoTotal(custoTotalInicial("30.00"));
		item.setValorTotal(valorTotalItem("20.00"));
		cabecalho.setTipo(TipoNota.SAIDA);
		
		custoTotalProdutoCadastroRn.operarSobre(item);
		
		verify(produtoRepository, times(1)).salvar(produto);
	}
	
	private BigDecimal valorTotalItem(String valor) {
		return new BigDecimal(valor);
	}
	
	private BigDecimal custoTotalInicial(String valor) {
		return new BigDecimal(valor);
	}
	
	private BigDecimal custoTotalEsperado(String valor) {
		return new BigDecimal(valor);
	}
}
