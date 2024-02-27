package com.agrotis.trainees.crud.service.item.cadastrar.rn;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.item.ItemNotaRepository;
import com.agrotis.trainees.crud.service.item.cadastrar.ItemCadastroRnException;

@ExtendWith(MockitoExtension.class)
class ProdutoNoCabecalhoCadastroRnTest {

	@Mock
	private ItemNotaRepository itemRepository;
	
	@InjectMocks
	private ProdutoNoCabecalhoCadastroRn produtoNoCabecalhoCadastroRn;	
	
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
	public void deveLancarExceptionQuandoItemComProdutoJaExisteNoCabecalho() {
		when(itemRepository.existeInstanciaCom(produto, cabecalhoNota)).thenReturn(true);
		
		ItemCadastroRnException exception = assertThrows(ItemCadastroRnException.class, () -> {
			produtoNoCabecalhoCadastroRn.operarSobre(item);
		});
		
		String mensagemEsperada = "Ja existe item com idProduto: " + produto.getId() 
									+ " e idCabecalho: " + cabecalhoNota.getId();
		assertThat(exception.getMessage(), is(equalTo(mensagemEsperada)));
	}
	
	@Test
	public void naoDeveLancarExceptionQuandoItemComProdutoNaoExisteNoCabecalho() {
		when(itemRepository.existeInstanciaCom(produto, cabecalhoNota)).thenReturn(false);
		
		assertDoesNotThrow(() -> {
			produtoNoCabecalhoCadastroRn.operarSobre(item);
		});
	}
}
