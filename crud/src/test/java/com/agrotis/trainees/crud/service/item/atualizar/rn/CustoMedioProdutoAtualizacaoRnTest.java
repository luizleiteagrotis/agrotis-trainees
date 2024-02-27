package com.agrotis.trainees.crud.service.item.atualizar.rn;

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
class CustoMedioProdutoAtualizacaoRnTest {

	@Mock
	private ProdutoRepository produtoRepository;
	
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
		produtoNovo.setCustoTotal(novoValorTotal("20.00"));
		produtoNovo.setEstoque(estoqueProduto(10));
		
		custoMedioProdutoAtualizacaoRn.operarSobre(itemNovo, itemAntigo);
		
		assertThat(produtoNovo.getCustoMedio(), is(equalTo(custoMedioEsperado("2.00"))));
	}
	
	@Test
	public void deveNaoAtualizarCustoMedioProdutoQUandoCabecalhoTipoSaida() {
		cabecalho.setTipo(TipoNota.SAIDA);
		produtoNovo.setCustoTotal(novoValorTotal("20.00"));
		produtoNovo.setEstoque(estoqueProduto(10));
		produtoNovo.setCustoMedio(custoMedioInicial("5.00"));
		
		custoMedioProdutoAtualizacaoRn.operarSobre(itemNovo, itemAntigo);
		
		assertThat(produtoNovo.getCustoMedio(), is(equalTo(custoMedioEsperado("5.00"))));
	}
	
	@Test
	public void deveSalvarProdutoQuandoRealizarOperacoesComSucesso() {
		cabecalho.setTipo(TipoNota.ENTRADA);
		produtoNovo.setCustoTotal(novoValorTotal("20.00"));
		produtoNovo.setEstoque(estoqueProduto(10));
		
		custoMedioProdutoAtualizacaoRn.operarSobre(itemNovo, itemAntigo);
		
		verify(produtoRepository, times(1)).salvar(produtoNovo);
	}
	
	private Integer estoqueProduto(Integer valor) {
		return valor;
	}
	
	private BigDecimal novoValorTotal(String valor) {
		return new BigDecimal(valor);
	}
	
	private BigDecimal custoMedioInicial(String valor) {
		return new BigDecimal(valor);
	}
	
	private BigDecimal custoMedioEsperado(String valor) {
		return new BigDecimal(valor);
	}
}
