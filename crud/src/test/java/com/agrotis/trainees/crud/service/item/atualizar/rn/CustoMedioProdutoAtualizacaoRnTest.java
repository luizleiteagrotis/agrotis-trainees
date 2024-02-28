package com.agrotis.trainees.crud.service.item.atualizar.rn;

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
class CustoMedioProdutoAtualizacaoRnTest {
	
	@Mock
	private CalculadorProduto calculadorProduto;
	
	@InjectMocks
	private CustoMedioProdutoAtualizacaoRn custoMedioProdutoAtualizacaoRn;
	
	private ItemNota itemNovo;
	private CabecalhoNota cabecalho;
	private Produto produtoNovo;
	private ItemNota itemAntigo;
	private Produto produtoAntigo;

	@BeforeEach
	public void setUp() {
		itemNovo = new ItemNota();
		cabecalho = new CabecalhoNota();
		produtoNovo = new Produto();
		itemNovo.setCabecalhoNota(cabecalho);
		itemNovo.setProduto(produtoNovo);
		itemAntigo = new ItemNota();
		produtoAntigo = new Produto();
		itemAntigo.setProduto(produtoAntigo);
	}
	
	@Test
	public void deveAtualizarCustoMedioProdutoQuandoCabecalhoTipoEntrada() {
		cabecalho.setTipo(TipoNota.ENTRADA);
		
		custoMedioProdutoAtualizacaoRn.operarSobre(itemNovo, itemAntigo);
		
		verify(calculadorProduto, times(1)).calcularCustoMedio(produtoNovo);
	}
	
	@Test
	public void deveNaoAtualizarCustoMedioProdutoQuandoCabecalhoTipoSaida() {
		cabecalho.setTipo(TipoNota.SAIDA);
		
		custoMedioProdutoAtualizacaoRn.operarSobre(itemNovo, itemAntigo);
		
		verify(calculadorProduto, never()).calcularCustoMedio(produtoNovo);
	}
}
