package com.agrotis.trainees.crud.service.item.cadastrar.rn;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
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
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.TipoNota;
import com.agrotis.trainees.crud.repository.cabecalho.CabecalhoNotaRepository;
import com.agrotis.trainees.crud.service.item.cadastrar.ItemCadastroRnException;
import com.agrotis.trainees.crud.service.item.util.CalculadorItem;
import com.agrotis.trainees.crud.service.item.util.CalculadorItemException;

@ExtendWith(MockitoExtension.class)
class ValorTotalCadastroRnTest {
	
	@Mock
	private CabecalhoNotaRepository cabecalhoRepository;
	
	@Mock
	private CalculadorItem calculadorItem;
	
	@InjectMocks
	private ValorTotalCadastroRn valorTotalCabecalhoCadastroRn;
	
	private CabecalhoNota cabecalhoNota;
	private Produto produto;
	private ItemNota item;
	
	@BeforeEach
	public void setUp() {
		cabecalhoNota = new CabecalhoNota();
		produto = new Produto();
		item = new ItemNota();
		item.setProduto(produto);
		item.setCabecalhoNota(cabecalhoNota);
	}
	
	@Test
	public void deveSomarValorTotalItemNoCabecalhoQuandoReceberItemComCabecalhoTipoEntrada() {
		cabecalhoNota.setTipo(TipoNota.ENTRADA);
		cabecalhoNota.setValorTotal(valorInicialCabecalho("10.00"));
		item.setValorTotal(valorTotalItem("30.00"));
		when(calculadorItem.calcularValorTotal(item)).thenReturn(item);
		
		valorTotalCabecalhoCadastroRn.operarSobre(item);
		
		assertThat(cabecalhoNota.getValorTotal(), is(equalTo(valorTotalEsperado("40.00"))));
	}
	
	@Test
	public void deveSomarValorTotalItemNoCabecalhoQuandoReceberItemComCabecalhoTipoSaida() {
		cabecalhoNota.setTipo(TipoNota.SAIDA);
		cabecalhoNota.setValorTotal(valorInicialCabecalho("30.00"));
		item.setValorTotal(valorTotalItem("10.00"));
		when(calculadorItem.calcularValorTotal(item)).thenReturn(item);
		
		valorTotalCabecalhoCadastroRn.operarSobre(item);
		
		assertThat(cabecalhoNota.getValorTotal(), is(equalTo(valorTotalEsperado("40.00"))));
		verify(cabecalhoRepository, times(1)).salvar(cabecalhoNota);
	}
	
	@Test
	public void deveSalvarCabecalhoQuandoRealizarOperacoesComSucesso() {
		cabecalhoNota.setTipo(TipoNota.SAIDA);
		cabecalhoNota.setValorTotal(valorInicialCabecalho("30.00"));
		item.setValorTotal(valorTotalItem("10.00"));
		when(calculadorItem.calcularValorTotal(item)).thenReturn(item);
		
		valorTotalCabecalhoCadastroRn.operarSobre(item);
		
		verify(cabecalhoRepository, times(1)).salvar(cabecalhoNota);
	}
	
	@Test
	public void deveNaoSalvarCabecalhoQuandoCalculadorItemDerException() {
		when(calculadorItem.calcularValorTotal(item)).thenThrow(CalculadorItemException.class);
		
		try {
			valorTotalCabecalhoCadastroRn.operarSobre(item);
		} catch (ItemCadastroRnException ignored) {
		}
		
		verify(cabecalhoRepository, never()).salvar(cabecalhoNota);
	}
	
	@Test
	public void deveLancarExceptionQuandoCalculadorItemDerException() {
		when(calculadorItem.calcularValorTotal(item)).thenThrow(CalculadorItemException.class);
		
		assertThrows(ItemCadastroRnException.class, () -> {
			valorTotalCabecalhoCadastroRn.operarSobre(item);
		});
	}
	
	private BigDecimal valorInicialCabecalho(String valor) {
		return new BigDecimal(valor);
	}
	
	private BigDecimal valorTotalItem(String valor) {
		return new BigDecimal(valor);
	}
	
	private BigDecimal valorTotalEsperado(String valor) {
		return new BigDecimal(valor);
	}
}
