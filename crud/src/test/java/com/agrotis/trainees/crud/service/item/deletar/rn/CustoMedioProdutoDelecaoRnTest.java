package com.agrotis.trainees.crud.service.item.deletar.rn;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
import com.agrotis.trainees.crud.util.CalculadorProduto;

@ExtendWith(MockitoExtension.class)
class CustoMedioProdutoDelecaoRnTest {
	
	@Mock
	private CalculadorProduto calculadorProduto;
	
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
		cabecalho.setTipo(TipoNota.ENTRADA);
		
		custoMedioProdutoDelecaoRn.operarSobre(item);
		
		verify(calculadorProduto, times(1)).calcularCustoMedio(produto);
	}
	
	@Test
	public void deveNaoRecalcularCustoMedioQuandoCabecalhoTipoSaida() {
		cabecalho.setTipo(TipoNota.SAIDA);
		
		custoMedioProdutoDelecaoRn.operarSobre(item);
		
		verify(calculadorProduto, never()).calcularCustoMedio(produto);
	}
}
