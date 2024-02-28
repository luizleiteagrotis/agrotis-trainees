package com.agrotis.trainees.crud.service.item.deletar.rn;

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
class CustoMedioProdutoDelecaoRnTest {
	
	@Mock
	private ProdutoRepository produtoRepository;
	
	@InjectMocks
	private CustoMedioProdutoDelecaoRn custoMedioProdutoDelecaoRn;
	
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
	public void deveRecalcularCustoMedioQuandoCabecalhoTipoEntrada() {
		produto.setCustoTotal(custoTotal("25.00"));
		produto.setEstoque(10);
		cabecalho.setTipo(TipoNota.ENTRADA);
		
		custoMedioProdutoDelecaoRn.operarSobre(item);
		
		assertThat(produto.getCustoMedio(), is(equalTo(custoMedioEsperado("2.50"))));
	}
	
	@Test
	public void deveNaoRecalcularCustoMedioQuandoCabecalhoTipoSaida() {
		produto.setCustoTotal(custoTotal("25.00"));
		produto.setEstoque(10);
		produto.setCustoMedio(custoMedioAtual("0.00"));
		cabecalho.setTipo(TipoNota.SAIDA);
		
		custoMedioProdutoDelecaoRn.operarSobre(item);
		
		assertThat(produto.getCustoMedio(), is(equalTo(custoMedioEsperado("0.00"))));
	}
	
	@Test
	public void deveSalvarProdutoQuandoOperacoesBemSucedidas() {
		produto.setCustoTotal(custoTotal("25.00"));
		produto.setEstoque(10);
		cabecalho.setTipo(TipoNota.ENTRADA);
		
		custoMedioProdutoDelecaoRn.operarSobre(item);
		
		verify(produtoRepository, times(1)).salvar(produto);
	}
	
	private BigDecimal custoTotal(String valor) {
		return new BigDecimal(valor);
	}
	
	private BigDecimal custoMedioAtual(String valor) {
		return new BigDecimal(valor);
	}
	
	private BigDecimal custoMedioEsperado(String valor) {
		return new BigDecimal(valor);
	}
}
