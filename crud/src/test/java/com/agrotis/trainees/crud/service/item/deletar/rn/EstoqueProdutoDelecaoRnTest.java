package com.agrotis.trainees.crud.service.item.deletar.rn;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
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
import com.agrotis.trainees.crud.repository.produto.ProdutoRepository;
import com.agrotis.trainees.crud.service.item.deletar.ItemDelecaoRnException;

@ExtendWith(MockitoExtension.class)
class EstoqueProdutoDelecaoRnTest {
	
	@InjectMocks
	private EstoqueProdutoDelecaoRn estoqueProdutoDelecaoRn;
	
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
	public void deveDiminuirEstoqueProdutoQuandoDeletarItemComCabecalhoTipoEntrada() {
		produto.setEstoque(estoqueInicialProduto(30));
		item.setQuantidade(quantidadeItem(20));
		cabecalho.setTipo(TipoNota.ENTRADA);
		
		estoqueProdutoDelecaoRn.operarSobre(item);
		
		assertThat(produto.getEstoque(), is(equalTo(estoqueEsperado(10))));
	}
	
	@Test
	public void deveAumentarEstoqueProdutoQuandoDeletarItemComCabecalhoTipoSaida() {
		produto.setEstoque(estoqueInicialProduto(10));
		item.setQuantidade(quantidadeItem(20));
		cabecalho.setTipo(TipoNota.SAIDA);
		
		estoqueProdutoDelecaoRn.operarSobre(item);
		
		assertThat(produto.getEstoque(), is(equalTo(estoqueEsperado(30))));
	}
	
	@Test
	public void deveLancarExceptionQuandoDeletarItemComCabecalhoTipoEntradaEEstoqueFicarNegativo() {
		produto.setEstoque(estoqueInicialProduto(10));
		item.setQuantidade(quantidadeItem(20));
		cabecalho.setTipo(TipoNota.ENTRADA);
		
		ItemDelecaoRnException exception = assertThrows(ItemDelecaoRnException.class, () -> {
			estoqueProdutoDelecaoRn.operarSobre(item);
		});
		
		String mensagemEsperada = "Atual estoque produto {" + estoqueInicialProduto(10)
									+ "} nao possui valor suficiente para deletar quantidade item {" 
									+ quantidadeItem(20) + "}";
		assertThat(exception.getMessage(), is(equalTo(mensagemEsperada)));
	}
	
	
	private Integer estoqueInicialProduto(Integer valor) {
		return valor;
	}
	
	private Integer quantidadeItem(Integer valor) {
		return valor;
	}
	
	private Integer estoqueEsperado(Integer valor) {
		return valor;
	}
}
