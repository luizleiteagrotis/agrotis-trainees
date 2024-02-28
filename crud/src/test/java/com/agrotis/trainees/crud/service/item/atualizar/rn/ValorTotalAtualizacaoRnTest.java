package com.agrotis.trainees.crud.service.item.atualizar.rn;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.repository.cabecalho.CabecalhoNotaRepository;
import com.agrotis.trainees.crud.service.item.atualizar.ItemAtualizacaoRnException;
import com.agrotis.trainees.crud.service.item.util.CalculadorItem;
import com.agrotis.trainees.crud.service.item.util.CalculadorItemException;

@ExtendWith(MockitoExtension.class)
class ValorTotalAtualizacaoRnTest {
	
	@Mock
	private CalculadorItem calculadorItem;
		
	@InjectMocks
	private ValorTotalAtualizacaoRn valorTotalAtualizacaoRn;
	
	private ItemNota antigoItem;
	private ItemNota novoItem;
	private CabecalhoNota cabecalho;
	
	@BeforeEach
	public void setUp() {
		novoItem = new ItemNota();
		cabecalho = new CabecalhoNota();
		novoItem.setCabecalhoNota(cabecalho);
		antigoItem = new ItemNota();
	}
	
	@Test
	public void deveDiminuirValorTotalCabecalhoQuandoDiminuirValorTotalDeItem() {
		antigoItem.setValorTotal(antigoValorItem("20.00"));
		novoItem.setValorTotal(novoValorItem("10.00"));
		cabecalho.setValorTotal(atualValorCabecalho("50.00"));
		when(calculadorItem.calcularValorTotal(novoItem)).thenReturn(novoItem);
		
		assertDoesNotThrow(() -> { 
			valorTotalAtualizacaoRn.operarSobre(novoItem, antigoItem);
		});
		
		assertThat(cabecalho.getValorTotal(), is(equalTo(valorEsperadoCabecalho("40.00"))));
	}
	
	@Test
	public void deveAumentarValorTotalCabecalhoQuandoAumentrarValorTotalDeItem() {
		antigoItem.setValorTotal(antigoValorItem("10.00"));
		novoItem.setValorTotal(novoValorItem("20.00"));
		cabecalho.setValorTotal(atualValorCabecalho("50.00"));
		when(calculadorItem.calcularValorTotal(novoItem)).thenReturn(novoItem);
		
		valorTotalAtualizacaoRn.operarSobre(novoItem, antigoItem);
		
		assertThat(cabecalho.getValorTotal(), is(equalTo(valorEsperadoCabecalho("60.00"))));
	}
	
	@Test
	public void deveLancarItemAtualizacaoRnExceptionQuandoCalculadorItemLancarException() {
		when(calculadorItem.calcularValorTotal(novoItem)).thenThrow(CalculadorItemException.class);
		
		ItemAtualizacaoRnException exception = assertThrows(ItemAtualizacaoRnException.class, () -> {
			valorTotalAtualizacaoRn.operarSobre(novoItem, antigoItem);
		});
	}
	
	private BigDecimal novoValorItem(String valor) {
		return new BigDecimal(valor);
	}
	
	private BigDecimal antigoValorItem(String valor) {
		return new BigDecimal(valor);
	}
	
	private BigDecimal atualValorCabecalho(String valor) {
		return new BigDecimal(valor);
	}
	
	private BigDecimal valorEsperadoCabecalho(String valor) {
		return new BigDecimal(valor);
	}
} 
