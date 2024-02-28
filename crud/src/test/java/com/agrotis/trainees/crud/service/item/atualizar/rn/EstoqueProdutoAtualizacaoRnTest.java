package com.agrotis.trainees.crud.service.item.atualizar.rn;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
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
import com.agrotis.trainees.crud.repository.item.ItemNotaRepository;
import com.agrotis.trainees.crud.repository.produto.ProdutoRepository;
import com.agrotis.trainees.crud.service.item.atualizar.ItemAtualizacaoRnException;
import com.agrotis.trainees.crud.service.item.util.CalculadorItem;

@ExtendWith(MockitoExtension.class)
class EstoqueProdutoAtualizacaoRnTest {
	
	@Mock
	private CalculadorItem calculadorItem;
	
	@InjectMocks
	private EstoqueProdutoAtualizacaoRn estoqueProdutoAtualizacaoRn;
	
	private CabecalhoNota cabecalho;
	private Produto produto;
	private ItemNota novoItem;
	private ItemNota antigoItem;
	
	@BeforeEach
	public void setUp() {
		cabecalho = new CabecalhoNota();
		produto = new Produto();
		novoItem = new ItemNota();
		novoItem.setProduto(produto);
		novoItem.setCabecalhoNota(cabecalho);
		antigoItem = new ItemNota();
	}
	
	@Test
	public void deveAumentarEstoqueQuandoAumentarQuantidadeItemECabecalhoTipoEntrada() {
		produto.setEstoque(estoqueInicialProduto(20));
		antigoItem.setQuantidade(quantidadeInicialItem(15));
		novoItem.setQuantidade(quantidadeNovaItem(20));
		cabecalho.setTipo(TipoNota.ENTRADA);
		
		estoqueProdutoAtualizacaoRn.operarSobre(novoItem, antigoItem);
		
		assertThat(produto.getEstoque(), is(equalTo(estoqueEsperado(25))));
	}
	
	@Test
	public void deveDiminuirEstoqueQuandoDiminuirQuantidadeItemECabecalhoTipoEntrada() {
		produto.setEstoque(estoqueInicialProduto(50));
		antigoItem.setQuantidade(quantidadeInicialItem(20));
		novoItem.setQuantidade(quantidadeNovaItem(10));
		cabecalho.setTipo(TipoNota.ENTRADA);
			
		estoqueProdutoAtualizacaoRn.operarSobre(novoItem, antigoItem);
		
		assertThat(produto.getEstoque(), is(equalTo(estoqueEsperado(40))));
	}
	
	@Test
	public void deveDiminuirEstoqueQuandoAumentarQuantidadeItemECabecalhoTipoSaida() {
		produto.setEstoque(estoqueInicialProduto(40));
		antigoItem.setQuantidade(quantidadeInicialItem(10));
		novoItem.setQuantidade(quantidadeNovaItem(20));
		cabecalho.setTipo(TipoNota.SAIDA);
		
		estoqueProdutoAtualizacaoRn.operarSobre(novoItem, antigoItem);
		
		assertThat(produto.getEstoque(), is(equalTo(estoqueEsperado(30))));
	}
	
	@Test
	public void deveAumentarEstoqueQuandoDiminuirQuantidadeItemECabecalhoTipoSaida() {
		produto.setEstoque(estoqueInicialProduto(40));
		antigoItem.setQuantidade(quantidadeInicialItem(20));
		novoItem.setQuantidade(quantidadeNovaItem(10));
		cabecalho.setTipo(TipoNota.SAIDA);
		
		estoqueProdutoAtualizacaoRn.operarSobre(novoItem, antigoItem);
	
		assertThat(produto.getEstoque(), is(equalTo(estoqueEsperado(50))));
	}
	
	@Test
	public void deveLancarExceptionQuandoEstoqueProdutoFicarNegativo() {
		produto.setEstoque(estoqueInicialProduto(10));
		antigoItem.setQuantidade(quantidadeInicialItem(20));
		novoItem.setQuantidade(quantidadeNovaItem(40));
		cabecalho.setTipo(TipoNota.SAIDA);
		
		ItemAtualizacaoRnException exception = assertThrows(ItemAtualizacaoRnException.class, () -> {
			estoqueProdutoAtualizacaoRn.operarSobre(novoItem, antigoItem);
		});
		
		String mensagemEsperada = "Estoque insuficiente para atualizar saida." 
									+ " EstoqueProduto=" + estoqueInicialProduto(10)
									+ " QuantidadeAntigaItem=" + quantidadeInicialItem(20)
									+ " QuantidadeNovaItem=" + quantidadeNovaItem(40);
		assertThat(exception.getMessage(), is(equalTo(mensagemEsperada)));
	}
	
	private Integer estoqueInicialProduto(Integer valor) {
		return valor;
	}
	
	private Integer quantidadeInicialItem(Integer valor) {
		return valor;
	}
	
	private Integer quantidadeNovaItem(Integer valor) {
		return valor;
	}
	
	private Integer estoqueEsperado(Integer valor) {
		return valor;
	}
}
