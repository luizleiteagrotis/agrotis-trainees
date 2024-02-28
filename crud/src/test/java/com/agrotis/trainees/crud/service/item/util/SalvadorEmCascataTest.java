package com.agrotis.trainees.crud.service.item.util;

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
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.cabecalho.CabecalhoNotaRepository;
import com.agrotis.trainees.crud.repository.item.ItemNotaRepository;
import com.agrotis.trainees.crud.repository.parceiro.ParceiroRepository;
import com.agrotis.trainees.crud.repository.produto.ProdutoRepository;

@ExtendWith(MockitoExtension.class)
class SalvadorEmCascataTest {

	@Mock
	private ParceiroRepository parceiroRepository;
	
	@Mock
	private ProdutoRepository produtoRepository;
	
	@Mock
	private CabecalhoNotaRepository cabecalhoNotaRepository;
	
	@Mock
	private ItemNotaRepository itemNotaRepository;
	
	@InjectMocks
	private SalvadorEmCascata salvadorEmCascata;
	
	private ParceiroNegocio fabricante;
	private Produto produto;
	private CabecalhoNota cabecalho;
	private ParceiroNegocio parceiroNegocio;
	private ItemNota itemNota;
	
	@BeforeEach
	public void setUp() {
		fabricante = new ParceiroNegocio();
		produto = new Produto();
		cabecalho = new CabecalhoNota();
		parceiroNegocio = new ParceiroNegocio();
		itemNota = new ItemNota();
		produto.setFabricante(fabricante);
		cabecalho.setParceiro(parceiroNegocio);
		itemNota.setProduto(produto);
		itemNota.setCabecalhoNota(cabecalho);
	}
	
	@Test
	public void deveSalvarItemEmCascataQuandoSalvarItem() {
		salvadorEmCascata.salvar(itemNota);
		
		verify(parceiroRepository, times(1)).salvar(fabricante);
		verify(produtoRepository, times(1)).salvar(produto);
		verify(cabecalhoNotaRepository, times(1)).salvar(cabecalho);
		verify(parceiroRepository, times(1)).salvar(parceiroNegocio);
		verify(itemNotaRepository, times(1)).salvar(itemNota);
	} 
}
