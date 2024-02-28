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
class CustoTotalProdutoDelecaoRnTest {
	
	@InjectMocks
	private CustoTotalProdutoDelecaoRn custoTotalProdutoDelecaoRn;

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
	public void deveDiminuirCustoTotalProdutoQuandoCabecalhoTipoEntrada() {
		item.setValorTotal(valorTotalItem("10.00"));
		produto.setCustoTotal(custoTotalInicial("30.00"));
		cabecalho.setTipo(TipoNota.ENTRADA);
		
		custoTotalProdutoDelecaoRn.operarSobre(item);
		
		assertThat(produto.getCustoTotal(), is(equalTo(custoTotalEsperado("20.00"))));
	}
	
	@Test
	public void deveAumentarCustoTotalProdutoComBaseNoCustoMedioQuandoCabecalhoTipoSaida() {
		produto.setCustoTotal(custoTotalInicial("30.00"));
		item.setQuantidade(quantidadeItem(10));
		produto.setCustoMedio(custoMedioProduto("2.50"));
		cabecalho.setTipo(TipoNota.SAIDA);
		
		custoTotalProdutoDelecaoRn.operarSobre(item);
		
		assertThat(produto.getCustoTotal(), is(equalTo(custoTotalEsperado("5.00"))));
	}
	
	private BigDecimal valorTotalItem(String valor) {
		return new BigDecimal(valor);
	}
	
	private BigDecimal custoTotalInicial(String valor) {
		return new BigDecimal(valor);
	}
	
	private BigDecimal custoMedioProduto(String valor) {
		return new BigDecimal(valor);
	}
	
	private Integer quantidadeItem(Integer valor) {
		return valor;
	}
	
	private BigDecimal custoTotalEsperado(String valor) {
		return new BigDecimal(valor);
	}
}
