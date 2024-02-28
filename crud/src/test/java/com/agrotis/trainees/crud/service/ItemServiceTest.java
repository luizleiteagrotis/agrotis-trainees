package com.agrotis.trainees.crud.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.ValorDiferenteException;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;

public class ItemServiceTest {

    @Mock
    NotaFiscalItemRepository repository;

    @Mock
    NotaFiscalService notaService;

    @Mock
    ProdutoService produtoService;

    @Mock
    ValorNotaFiscalService valorService;

    @InjectMocks
    ItemService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Teste para validar Nota e item existente, sucesso")
    void deveValidarPorNotaEItemExistente() {
        Produto produto = new Produto();
        produto.setId(1);

        NotaFiscal nota = new NotaFiscal();
        nota.setId(1);

        NotaFiscalItem item = new NotaFiscalItem();
        item.setId(1);
        item.setProduto(produto);
        item.setIdNota(nota);
        item.setQuantidade(5);
        item.setPrecoUnitario(new BigDecimal(7.99));

        when(notaService.buscarPorId(item.getIdNota().getId())).thenReturn(nota);
        when(produtoService.buscarPorId(item.getProduto().getId())).thenReturn(produto);
        when(repository.findByProdutoAndIdNota(produto, nota)).thenReturn(item);

        assertDoesNotThrow(() -> {
            service.validarNotaEItem(item);
        });
        assertEquals(10, item.getQuantidade());
    }

    @Test
    @DisplayName("Teste para validar Nota e item,novo item sucesso")
    void deveValidarPorNotaEItemNovo() {
        Produto produto = new Produto();
        produto.setId(1);

        NotaFiscal nota = new NotaFiscal();
        nota.setId(1);

        NotaFiscalItem item = new NotaFiscalItem();
        item.setId(1);
        item.setProduto(produto);
        item.setIdNota(nota);
        item.setQuantidade(5);
        item.setPrecoUnitario(new BigDecimal(7.99));

        when(notaService.buscarPorId(item.getIdNota().getId())).thenReturn(nota);
        when(produtoService.buscarPorId(item.getProduto().getId())).thenReturn(produto);
        when(repository.findByProdutoAndIdNota(produto, nota)).thenReturn(null);

        assertDoesNotThrow(() -> {
            service.validarNotaEItem(item);
        });
        assertEquals(5, item.getQuantidade());

    }

    @Test
    @DisplayName("Teste para preços diferentes, retorna erro")
    void deveRetornarErroValoresDiferentes() {
        Produto produto = new Produto();
        produto.setId(1);

        NotaFiscal nota = new NotaFiscal();
        nota.setId(1);

        NotaFiscalItem item = new NotaFiscalItem();
        item.setId(1);
        item.setProduto(produto);
        item.setIdNota(nota);
        item.setQuantidade(5);
        item.setPrecoUnitario(new BigDecimal(7.99));

        NotaFiscalItem item2 = new NotaFiscalItem();
        item2.setId(1);
        item2.setProduto(produto);
        item2.setIdNota(nota);
        item2.setQuantidade(5);
        item2.setPrecoUnitario(new BigDecimal(8.99));

        when(notaService.buscarPorId(item.getIdNota().getId())).thenReturn(nota);
        when(produtoService.buscarPorId(item.getProduto().getId())).thenReturn(produto);
        when(repository.findByProdutoAndIdNota(produto, nota)).thenReturn(item2);

        Exception excecao = assertThrows(ValorDiferenteException.class, () -> {
            service.validarNotaEItem(item);
        });

        assertEquals("Item com preço diferente do original", excecao.getMessage());
        assertNotEquals(item.getPrecoUnitario(), item2.getPrecoUnitario());
    }

    @Test
    @DisplayName("Teste que trata os valores nulos do item recebido pelos atuais")
    void deveTrocarValoresNulosPelosExistentes() {
        Produto produto = new Produto();
        produto.setId(1);

        NotaFiscal nota = new NotaFiscal();
        nota.setId(1);

        NotaFiscalItem item = new NotaFiscalItem();
        item.setId(1);
        item.setPrecoUnitario(new BigDecimal(8.99));

        NotaFiscalItem item2 = new NotaFiscalItem();
        item2.setId(1);
        item2.setPrecoUnitario(new BigDecimal(8.99));
        item2.setIdNota(nota);
        item2.setProduto(produto);
        item2.setQuantidade(15);
        item2.setValorTotal(new BigDecimal(0));

        assertDoesNotThrow(() -> {
            service.tratarNulos(item, item2);
        });

        assertEquals(produto, item.getProduto());
        assertEquals(nota, item.getIdNota());
        assertEquals(item2.getQuantidade(), item.getQuantidade());
        assertEquals(item2.getValorTotal(), item.getValorTotal());

    }

    @Test
    @DisplayName("Teste que trata os valores zerados do item recebido pelos atuais")
    void deveTrocarValoresZeradosPelosExistentes() {
        Produto produto = new Produto();
        produto.setId(1);

        NotaFiscal nota = new NotaFiscal();
        nota.setId(1);

        NotaFiscalItem item = new NotaFiscalItem();
        item.setId(1);
        item.setPrecoUnitario(new BigDecimal(0));
        item.setQuantidade(0);
        item.setValorTotal(new BigDecimal(0));

        NotaFiscalItem item2 = new NotaFiscalItem();
        item2.setId(1);
        item2.setPrecoUnitario(new BigDecimal(8.99));
        item2.setIdNota(nota);
        item2.setProduto(produto);
        item2.setQuantidade(15);
        item2.setValorTotal(new BigDecimal(25));

        assertDoesNotThrow(() -> {
            service.tratarNulos(item, item2);
        });

        assertEquals(produto, item.getProduto());
        assertEquals(nota, item.getIdNota());
        assertEquals(item2.getPrecoUnitario(), item.getPrecoUnitario());
        assertEquals(item2.getQuantidade(), item.getQuantidade());
        assertEquals(item2.getValorTotal(), item.getValorTotal());

    }

}
