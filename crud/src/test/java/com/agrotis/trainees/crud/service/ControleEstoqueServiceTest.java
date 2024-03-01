package com.agrotis.trainees.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.ControleEstoqueException;
import com.agrotis.trainees.crud.repository.ProdutoRepository;

public class ControleEstoqueServiceTest {

    @Mock
    ProdutoService produtoService;
    @Mock
    ProdutoRepository produtoRepository;

    @InjectMocks
    ControleEstoque controleEstoque;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void entradaDeUmaNotaFiscalComQuantidadeDe1000() throws ControleEstoqueException {
        ItemNotaFiscal item = new ItemNotaFiscal();
        item.setQuantidade(new BigDecimal(1000));
        Produto produto = new Produto();
        produto.setEstoque(new BigDecimal(0));
        NotaFiscal nota = new NotaFiscal();
        nota.setTipo("entrada");
        item.setProduto(produto);
        item.setNotaFiscal(nota);
        BigDecimal estoqueAtualizado = controleEstoque.controlarQuantidadeEstoque(item);
        assertEquals(new BigDecimal(1000), estoqueAtualizado);

    }

    @Test
    void saidaDeUmaNotaFiscalComQuantidade1000() throws ControleEstoqueException {
        ItemNotaFiscal item = new ItemNotaFiscal();
        item.setQuantidade(new BigDecimal(1000));
        Produto produto = new Produto();
        produto.setEstoque(new BigDecimal(1001));
        NotaFiscal nota = new NotaFiscal();
        nota.setTipo("saida");
        item.setProduto(produto);
        item.setNotaFiscal(nota);
        BigDecimal estoqueAtualizado = controleEstoque.controlarQuantidadeEstoque(item);
        assertEquals(new BigDecimal(1), estoqueAtualizado);
    }

    @Test
    void saidaDeUmaNotaFiscalComAQuantidadeMaiorQueEstoqueEsperandoExcecao() throws ControleEstoqueException {
        ItemNotaFiscal item = new ItemNotaFiscal();
        item.setQuantidade(new BigDecimal(10000));
        Produto produto = new Produto();
        produto.setEstoque(new BigDecimal(1000));
        NotaFiscal nota = new NotaFiscal();
        nota.setTipo("saida");
        item.setProduto(produto);
        item.setNotaFiscal(nota);
        assertThrows(ControleEstoqueException.class, () -> {
            controleEstoque.controlarQuantidadeEstoque(item);
        });
    }

    @Test
    void atualizacaoDaQuantidadeDeUmItemJaCadastradoEmUmaNotaDeEntrada() throws ControleEstoqueException {
        Produto produto = new Produto();
        produto.setEstoque(new BigDecimal(1000));
        NotaFiscal nota = new NotaFiscal();
        nota.setTipo("entrada");
        ItemNotaFiscal itemNotaAtualizar = new ItemNotaFiscal(nota, produto, new BigDecimal(1000), new BigDecimal(50));
        ItemNotaFiscal itemNotaFiscal = new ItemNotaFiscal(nota, produto, new BigDecimal(100), new BigDecimal(50));
        when(produtoService.verificarPorId(anyInt())).thenReturn(produto);
        ItemNotaFiscal itemAtualizado = controleEstoque.atualizarEstoque(itemNotaFiscal, itemNotaAtualizar);
        assertEquals(new BigDecimal(100), itemAtualizado.getProduto().getEstoque());
    }

    @Test
    void atualizacaoDaQuantidadeDeUmItemJaCadastradoEmUmaNotaDeSaida() throws ControleEstoqueException {
        Produto produto = new Produto();
        produto.setEstoque(new BigDecimal(5000));
        NotaFiscal nota = new NotaFiscal();
        nota.setTipo("saida");
        ItemNotaFiscal itemNotaAtualizar = new ItemNotaFiscal(nota, produto, new BigDecimal(1000), new BigDecimal(50));
        ItemNotaFiscal itemNotaFiscal = new ItemNotaFiscal(nota, produto, new BigDecimal(100), new BigDecimal(50));
        when(produtoService.verificarPorId(anyInt())).thenReturn(produto);
        ItemNotaFiscal itemAtualizado = controleEstoque.atualizarEstoque(itemNotaFiscal, itemNotaAtualizar);
        assertEquals(new BigDecimal(5900), itemAtualizado.getProduto().getEstoque());
    }

    @Test
    void atualizacaoDaQuantidadeMaiorQueQuantidadeEstoqueNotaDeSaida() throws ControleEstoqueException {
        Produto produto = new Produto();
        produto.setEstoque(new BigDecimal(1000));
        NotaFiscal nota = new NotaFiscal();
        nota.setTipo("saida");
        ItemNotaFiscal itemNotaAtualizar = new ItemNotaFiscal(nota, produto, new BigDecimal(1000), new BigDecimal(50));
        ItemNotaFiscal itemNotaFiscal = new ItemNotaFiscal(nota, produto, new BigDecimal(10000), new BigDecimal(50));
        when(produtoService.verificarPorId(anyInt())).thenReturn(produto);
        assertThrows(ControleEstoqueException.class, () -> {
            controleEstoque.atualizarEstoque(itemNotaFiscal, itemNotaAtualizar);
        });
    }
}
