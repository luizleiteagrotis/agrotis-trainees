package com.agrotis.trainees.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.QuantidadeInsuficienteException;
import com.agrotis.trainees.crud.repository.ItemNotaFiscalRepository;
import com.agrotis.trainees.crud.utils.ProdutoDTOMapper;

public class CalcularEstoqueServiceTest {

    @Mock
    private ItemNotaFiscalRepository repository;

    @Mock
    private ItemNotaFiscal item;

    @Mock
    private Produto produto;

    @Mock
    private NotaFiscal notaFiscal;

    @Mock
    private ProdutoService produtoService;

    @Mock
    private ProdutoDTOMapper mapperProduto;

    @Mock
    private NotaFiscalTipo notaFiscalTipo;

    @Mock
    private ItemNotaFiscal itemExistente;

    @InjectMocks
    private CalcularEstoqueService calcularEstoqueService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
     public void naoDeveDiminuirEstoqueNotaSaidaQuantidadeInvalida() {
     when(item.getProduto()).thenReturn(produto);
     when(item.getQuantidade()).thenReturn(10);
     when(item.getNotaFiscal()).thenReturn(notaFiscal);
     when(notaFiscal.getNotaFiscalTipo()).thenReturn(notaFiscalTipo);
     when(notaFiscal.getNotaFiscalTipo().getNome()).thenReturn("SAÍDA");
    
     when(produto.getQuantidadeEstoque()).thenReturn(5);
    
     Exception excecao = assertThrows(QuantidadeInsuficienteException.class,
     () -> {
     calcularEstoqueService.atualizarEstoque(item);
     });
    
     verify(produtoService, never()).atualizar(any());
     assertEquals("Quantidade insuficiente em estoque para a saída do produto. Quantidade em estoque: " + produto.getQuantidadeEstoque() + " " + ". Quantidade item saída: " + 10, excecao.getMessage());
     }

    @Test
     public void deveAtualizarOEstoqueSaida() {
     when(item.getProduto()).thenReturn(produto);
     when(item.getQuantidade()).thenReturn(10);
     when(item.getNotaFiscal()).thenReturn(notaFiscal);
     when(notaFiscal.getNotaFiscalTipo()).thenReturn(notaFiscalTipo);
     when(notaFiscal.getNotaFiscalTipo().getNome()).thenReturn("SAÍDA");
    
     when(produto.getQuantidadeEstoque()).thenReturn(15);
    
     calcularEstoqueService.atualizarEstoque(item);
    
     verify(produto, times(1)).setQuantidadeEstoque(5);
    
     verify(produtoService, times(1)).atualizar(any());
     }

    @Test
     public void deveAtualizarOEstoqueEntrada() {
     when(item.getProduto()).thenReturn(produto);
     when(item.getQuantidade()).thenReturn(10);
     when(item.getNotaFiscal()).thenReturn(notaFiscal);
     when(notaFiscal.getNotaFiscalTipo()).thenReturn(notaFiscalTipo);
     when(notaFiscal.getNotaFiscalTipo().getNome()).thenReturn("ENTRADA");
    
     when(produto.getQuantidadeEstoque()).thenReturn(15);
    
     calcularEstoqueService.atualizarEstoque(item);
    
     verify(produto, times(1)).setQuantidadeEstoque(25);
    
     verify(produtoService, times(1)).atualizar(any());
     }

    @Test
    public void deveAtualizarEstoque_ItemExistenteEntrada() {
        itemExistente.setQuantidade(10);
        when(item.getProduto()).thenReturn(produto);
        when(item.getQuantidade()).thenReturn(15);
        when(item.getNotaFiscal()).thenReturn(notaFiscal);
        when(notaFiscal.getNotaFiscalTipo()).thenReturn(notaFiscalTipo);
        when(notaFiscal.getNotaFiscalTipo().getNome()).thenReturn("ENTRADA");

        when(produto.getQuantidadeEstoque()).thenReturn(10);
        when(itemExistente.getQuantidade()).thenReturn(10);
        when(repository.findByProdutoAndNotaFiscal(item.getProduto(), item.getNotaFiscal())).thenReturn(itemExistente);

        calcularEstoqueService.atualizarEstoque(item);

        verify(produto, times(1)).setQuantidadeEstoque(15);
        verify(produtoService, times(1)).atualizar(any());

    }

    @Test
    public void deveAtualizarEstoque_ItemExistenteSaida() {
        itemExistente.setQuantidade(10);
        when(item.getProduto()).thenReturn(produto);
        when(item.getQuantidade()).thenReturn(15);
        when(item.getNotaFiscal()).thenReturn(notaFiscal);
        when(notaFiscal.getNotaFiscalTipo()).thenReturn(notaFiscalTipo);
        when(notaFiscal.getNotaFiscalTipo().getNome()).thenReturn("SAÍDA");

        when(produto.getQuantidadeEstoque()).thenReturn(10);
        when(itemExistente.getQuantidade()).thenReturn(10);
        when(repository.findByProdutoAndNotaFiscal(item.getProduto(), item.getNotaFiscal())).thenReturn(itemExistente);

        calcularEstoqueService.atualizarEstoque(item);

        verify(produto, times(1)).setQuantidadeEstoque(5);
        verify(produtoService, times(1)).atualizar(any());

    }

}
