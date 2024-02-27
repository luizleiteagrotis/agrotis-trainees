package com.agrotis.trainees.crud.service.item.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agrotis.trainees.crud.entity.ItemNota;

@ExtendWith(MockitoExtension.class)
class CalculadorItemTest {
	
	@InjectMocks
	private CalculadorItem calculadorItem;

	private ItemNota item;
	
	@BeforeEach
	public void setUp() {
		item = new ItemNota();
	}
	
	@Test
	public void deveCalcularValorTotalQuandoPrecoUnitarioEQuantidadeEstaoCorretos() {
		item.setPrecoUnitario(precoUnitarioItem("10.00"));
		item.setQuantidade(quantidadeItem(100));
		
		item = calculadorItem.calcularValorTotal(item);
		
		assertThat(item.getValorTotal(), is(equalTo(valorTotalEsperado("1000.00"))));
	}
	
	@Test
	public void deveLancarExceptionQuandoPrecoUnitarioForNegativo() {
		item.setPrecoUnitario(precoUnitarioItem("-10.00"));
		item.setQuantidade(quantidadeItem(100));
		
		CalculadorItemException exception = assertThrows(CalculadorItemException.class, () -> {
			calculadorItem.calcularValorTotal(item);
		});
		
		String mensagemEsperada = "Preco unitario nao pode ser negativo."
									+ " Fornecido: " + precoUnitarioItem("-10.00");
		assertThat(exception.getMessage(), is(equalTo(mensagemEsperada)));
	}
	
	@Test
	public void deveLancarExceptionQuandoQuantidadeForNegativa() {
		item.setPrecoUnitario(precoUnitarioItem("10.00"));
		item.setQuantidade(quantidadeItem(-100));
		
		CalculadorItemException exception = assertThrows(CalculadorItemException.class, () -> {
			calculadorItem.calcularValorTotal(item);
		});
		
		String mensagemEsperada = "Quantidade nao pode ser negativa. "
				+ "Fornecido: " + quantidadeItem(-100);
		assertThat(exception.getMessage(), is(equalTo(mensagemEsperada)));
	}
	
	private BigDecimal precoUnitarioItem(String valor) {
		return new BigDecimal(valor);
	}
	
	private Integer quantidadeItem(Integer valor) {
		return valor;
	}
	
	private BigDecimal valorTotalEsperado(String valor) {
		return new BigDecimal(valor);
	}
}
