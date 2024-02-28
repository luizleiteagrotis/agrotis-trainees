package com.agrotis.trainees.crud.service.item.cadastrar.rn;

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
import com.agrotis.trainees.crud.service.item.cadastrar.ItemCadastroRnException;

@ExtendWith(MockitoExtension.class)
class EstoqueProdutoCadastroRnTest {
	
	@InjectMocks
	private EstoqueProdutoCadastroRn estoqueProdutoCadastroRn;	
	
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
	public void deveSomarEstoqueProdutoQuandoReceberUmItemComCabecalhoTipoEntrada() {
		produto.setEstoque(estoqueInicial(10));
		item.setQuantidade(quantidadeItem(20));
		cabecalhoNota.setTipo(TipoNota.ENTRADA);
		
		estoqueProdutoCadastroRn.operarSobre(item);
		
		assertThat(produto.getEstoque(), is(equalTo(estoqueEsperado(30))));
	}
	
	@Test
	public void deveSubtrairEstoqueProdutoQuandoReceberUmItemComCabecalhoTipoSaida() {
		produto.setEstoque(estoqueInicial(40));
		item.setQuantidade(quantidadeItem(10));
		cabecalhoNota.setTipo(TipoNota.SAIDA);
		
		estoqueProdutoCadastroRn.operarSobre(item);
		
		assertThat(produto.getEstoque(), is(equalTo(estoqueEsperado(30))));
	}
	
	@Test
	public void deveLancarExceptionQuandoNaoTemEstoqueSuficienteParaUmItemComCabecalhoTipoSaida() {
		produto.setEstoque(estoqueInicial(0));
		item.setQuantidade(quantidadeItem(10));
		cabecalhoNota.setTipo(TipoNota.SAIDA);
		
		ItemCadastroRnException exception = assertThrows(ItemCadastroRnException.class, () -> {
			estoqueProdutoCadastroRn.operarSobre(item);
		});
		
		String mensagemEsperada = "Estoque insuficiente para emitir item de saida."
				+ " EstoqueAtual=" + estoqueInicial(0)
				+ " TentandoRetirar=" + quantidadeItem(10);
		assertThat(exception.getMessage(), is(equalTo(mensagemEsperada)));
	}
	
	private Integer estoqueInicial(Integer valor) {
		return valor;
	}
	
	private Integer quantidadeItem(Integer valor) {
		return valor;
	}
	
	private Integer estoqueEsperado(Integer valor) {
		return valor;
	}
}
