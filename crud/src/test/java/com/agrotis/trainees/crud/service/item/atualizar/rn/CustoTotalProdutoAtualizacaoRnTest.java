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
class CustoTotalProdutoAtualizacaoRnTest {

	@Mock
	private ProdutoRepository produtoRepository;
	
	@InjectMocks
	private CustoTotalProdutoAtualizacaoRn custoTotalProdutoAtualizacaoRn;
	
	private ItemNota itemNovo;
	private CabecalhoNota cabecalho;
	private Produto produto;
	private ItemNota itemAntigo;

	@BeforeEach
	public void setUp() {
		itemNovo = new ItemNota();
		cabecalho = new CabecalhoNota();
		produto = new Produto();
		itemNovo.setCabecalhoNota(cabecalho);
		itemNovo.setProduto(produto);
		itemAntigo = new ItemNota();
	}
	
	@Test
	public void deveAumentarCustoTotalProdutoQuandoValorTotalItemAumentarECabecalhoTipoEntrada() {
		itemAntigo.setValorTotal(valorTotalAntigo("10.00"));
		itemNovo.setValorTotal(valorTotalNovo("20.00"));
		produto.setCustoTotal(custoTotalInicial("50.00"));
		cabecalho.setTipo(TipoNota.ENTRADA);
		
		custoTotalProdutoAtualizacaoRn.operarSobre(itemNovo, itemAntigo);
		
		assertThat(produto.getCustoTotal(), is(equalTo(custoTotalEsperado("60.00"))));
	}
	
	@Test
	public void deveDiminuirCustoTotalProdutoQuandoValorTotalItemDiminuirECabecalhoTipoEntrada() {
		itemAntigo.setValorTotal(valorTotalAntigo("20.00"));
		itemNovo.setValorTotal(valorTotalNovo("10.00"));
		produto.setCustoTotal(custoTotalInicial("50.00"));
		cabecalho.setTipo(TipoNota.ENTRADA);
		
		custoTotalProdutoAtualizacaoRn.operarSobre(itemNovo, itemAntigo);
		
		assertThat(produto.getCustoTotal(), is(equalTo(custoTotalEsperado("40.00"))));
	}
	
	@Test
	public void deveDiminuirCustoTotalProdutoComBaseNoCustoMedioQuandoQuantidadeItemAumentarECabecalhoTipoSaida() {
		itemAntigo.setQuantidade(quantidadeAntiga(5));
		itemNovo.setQuantidade(quantidadeNova(10));
		produto.setCustoMedio(custoMedio("2.50"));
		produto.setCustoTotal(custoTotalInicial("50.00"));
		cabecalho.setTipo(TipoNota.SAIDA);
		
		custoTotalProdutoAtualizacaoRn.operarSobre(itemNovo, itemAntigo);
		
		assertThat(produto.getCustoTotal(), is(equalTo(custoTotalEsperado("37.50"))));
	}
	
	@Test
	public void deveAumentarCustoTotalProdutoComBaseNoCustoMedioQuandoQuantidadeItemDiminuirECabecalhoTipoSaida() {
		itemAntigo.setQuantidade(quantidadeAntiga(10));
		itemNovo.setQuantidade(quantidadeNova(5));
		produto.setCustoMedio(custoMedio("2.50"));
		produto.setCustoTotal(custoTotalInicial("50.00"));
		cabecalho.setTipo(TipoNota.SAIDA);
		
		custoTotalProdutoAtualizacaoRn.operarSobre(itemNovo, itemAntigo);
		
		assertThat(produto.getCustoTotal(), is(equalTo(custoTotalEsperado("62.50"))));
	}
	
	@Test
	public void deveSalvarProdutoQuandoOperacoesForemBemSucedidas() {
		itemAntigo.setValorTotal(valorTotalAntigo("10.00"));
		itemNovo.setValorTotal(valorTotalNovo("20.00"));
		produto.setCustoTotal(custoTotalInicial("50.00"));
		cabecalho.setTipo(TipoNota.ENTRADA);
		
		custoTotalProdutoAtualizacaoRn.operarSobre(itemNovo, itemAntigo);
		
		verify(produtoRepository, times(1)).salvar(produto);
	}
	
	private Integer quantidadeAntiga(Integer valor) {
		return valor;
	}
	
	private Integer quantidadeNova(Integer valor) {
		return valor;
	}
	
	private BigDecimal custoMedio(String valor) {
		return new BigDecimal(valor);
	}
	
	private BigDecimal valorTotalAntigo(String valor) {
		return new BigDecimal(valor);
	}
	
	private BigDecimal valorTotalNovo(String valor) {
		return new BigDecimal(valor);
	}
	
	private BigDecimal custoTotalInicial(String valor) {
		return new BigDecimal(valor);
	}
	
	private BigDecimal custoTotalEsperado(String valor) {
		return new BigDecimal(valor);
	}
}
