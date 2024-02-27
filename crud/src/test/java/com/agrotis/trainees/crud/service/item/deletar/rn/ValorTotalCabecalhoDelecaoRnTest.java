package com.agrotis.trainees.crud.service.item.deletar.rn;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
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
import com.agrotis.trainees.crud.repository.cabecalho.CabecalhoNotaRepository;
import com.agrotis.trainees.crud.service.item.deletar.ItemDelecaoRnException;

@ExtendWith(MockitoExtension.class)
class ValorTotalCabecalhoDelecaoRnTest {

	@Mock
	private CabecalhoNotaRepository cabecalhoRepository;
	
	@InjectMocks
	private ValorTotalCabecalhoDelecaoRn valorTotalCabecalhoDelecaoRn;

	private CabecalhoNota cabecalho;
	private Produto produto;
	private ItemNota item;
	
	@BeforeEach
	public void setUp() {
		cabecalho = new CabecalhoNota();
		produto = new Produto();
		item = new ItemNota();
		item.setProduto(produto);
		item.setCabecalhoNota(cabecalho);
	}
	
	@Test
	public void deveDiminuirValorTotalCabecalhoQuandoCabecalhoTipoEntrada() {
		cabecalho.setValorTotal(valorInicialCabecalho("30.00"));
		item.setValorTotal(valorItem("20.00"));
		cabecalho.setTipo(TipoNota.ENTRADA);
		
		valorTotalCabecalhoDelecaoRn.operarSobre(item);
		
		assertThat(cabecalho.getValorTotal(), is(equalTo(valorEsperado("10.00"))));
	}
	
	@Test
	public void deveDiminuirValorTotalCabecalhoQuandoCabecalhoTipoSaida() {
		cabecalho.setValorTotal(valorInicialCabecalho("30.00"));
		item.setValorTotal(valorItem("20.00"));
		cabecalho.setTipo(TipoNota.SAIDA);
		
		valorTotalCabecalhoDelecaoRn.operarSobre(item);
		
		assertThat(cabecalho.getValorTotal(), is(equalTo(valorEsperado("10.00"))));
	}
	
	@Test
	public void deveSalvarCabecalhoQuandoOperacoesForemBemSucedidas() {
		cabecalho.setValorTotal(valorInicialCabecalho("30.00"));
		item.setValorTotal(valorItem("20.00"));
		cabecalho.setTipo(TipoNota.SAIDA);
		
		valorTotalCabecalhoDelecaoRn.operarSobre(item);
		
		verify(cabecalhoRepository, times(1)).salvar(cabecalho);
	}
	
	@Test
	public void deveNaoSalvarCabecalhoQuandoNovoValorTotalCabecalhoFicarNegativo() {
		cabecalho.setValorTotal(valorInicialCabecalho("10.00"));
		item.setValorTotal(valorItem("20.00"));
		
		try {
			valorTotalCabecalhoDelecaoRn.operarSobre(item);
		} catch (ItemDelecaoRnException ignored) {}
		
		verify(cabecalhoRepository, never()).salvar(cabecalho);
	}
	
	@Test
	public void deveLancarExceptionQuandoNovoValorTotalCabecalhoFicarNegativo() {
		cabecalho.setValorTotal(valorInicialCabecalho("10.00"));
		item.setValorTotal(valorItem("20.00"));
		
		ItemDelecaoRnException exception = assertThrows(ItemDelecaoRnException.class, () -> {
			valorTotalCabecalhoDelecaoRn.operarSobre(item);
		});
		
		String mensagemEsperada = "Valor total cabecalho resultante nao pode ficar negativo."
									+ " TentandoRetirar=" + valorItem("20.00")
									+ " deValorTotalCabecalho=" + valorInicialCabecalho("10.00");
		assertThat(exception.getMessage(), is(equalTo(mensagemEsperada)));
	}
	
	private BigDecimal valorInicialCabecalho(String valor) {
		return new BigDecimal(valor);
	}
	
	private BigDecimal valorItem(String valor) {
		return new BigDecimal(valor);
	}
	
	private BigDecimal valorEsperado(String valor) {
		return new BigDecimal(valor);
	}
}
