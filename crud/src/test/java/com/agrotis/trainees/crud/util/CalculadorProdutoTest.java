package com.agrotis.trainees.crud.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agrotis.trainees.crud.entity.Produto;

@ExtendWith(MockitoExtension.class)
class CalculadorProdutoTest {

	@InjectMocks
	private CalculadorProduto calculadorProduto;
	
	private Produto produto;

	@BeforeEach
	public void setUp() {
		produto = new Produto();
	}
	
	@Test
	public void deveCalcularCustoMedioQuandoEstoqueMaiorQueZero() {
		produto.setCustoTotal(custoTotal("10.00"));
		produto.setEstoque(20);
		
		produto = calculadorProduto.calcularCustoMedio(produto);
		
		assertThat(produto.getCustoMedio(), is(equalTo(custoMedioEsperado("0.50"))));
	}
	
	@Test
	public void devePossuirDuasCasasDecimaisQuandoRetornarProdutoComCustoMedioAtualizado() {
		produto.setCustoTotal(custoTotal("10.00"));
		produto.setEstoque(20);
		
		produto = calculadorProduto.calcularCustoMedio(produto);
		
		assertThat(quantidadeCasasDecimais(produto.getCustoMedio()), is(equalTo(casasDecimaisEsperadas(2))));
	}
	
	@Test
	public void deveZerarCustoMedioQuandoEstoqueZero() {
		produto.setCustoTotal(custoTotal("10.00"));
		produto.setEstoque(0);
		
		produto = calculadorProduto.calcularCustoMedio(produto);
		
		assertThat(produto.getCustoMedio(), is(equalTo(custoMedioEsperado("0"))));
	}
	
	private int casasDecimaisEsperadas(int valor) {
		return valor;
	}
	
	private int quantidadeCasasDecimais(BigDecimal valor) {
		return valor.scale();
	}
	
	private BigDecimal custoTotal(String valor) {
		return new BigDecimal(valor);
	}
	
	private BigDecimal custoMedioEsperado(String valor) {
		return new BigDecimal(valor);
	}
}
