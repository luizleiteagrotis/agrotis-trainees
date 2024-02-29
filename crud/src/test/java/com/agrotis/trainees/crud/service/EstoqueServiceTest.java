package com.agrotis.trainees.crud.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.DescricaoExisteException;
import com.agrotis.trainees.crud.exception.EstoqueZeradoException;
import com.agrotis.trainees.crud.exception.ValorDiferenteException;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;
import com.agrotis.trainees.crud.wrapper.NotaFiscalWrapper;
import com.agrotis.trainees.crud.wrapper.ProdutoWrapper;

import enums.TipoNota;

public class EstoqueServiceTest {

    @Mock
    NotaFiscalItemRepository repository;

    @Mock
    NotaFiscalService notaService;

    @Mock
    ProdutoService produtoService;

    @Mock
    ProdutoWrapper produtoWrapper;

    @Mock
    ItemService itemService;

    @Mock
    NotaFiscalWrapper notaWrapper;

    @InjectMocks
    EstoqueService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Teste de atualização do estoque nota entrada e produtos iguais")
    void deveAtualizarEstoqueEntradaEIgual() throws DescricaoExisteException {
        TipoNota tipo = TipoNota.ENTRADA;

        Produto produto = new Produto();
        produto.setId(1);
        produto.setEstoque(15);

        ProdutoDto produtoDto = new ProdutoDto();

        NotaFiscal nota = new NotaFiscal();
        nota.setId(1);
        nota.setTipo(tipo);

        NotaFiscalItem novoItem = new NotaFiscalItem();
        novoItem.setId(1);
        novoItem.setQuantidade(5);
        novoItem.setIdNota(nota);
        novoItem.setProduto(produto);

        NotaFiscalItem itemAtual = new NotaFiscalItem();
        itemAtual.setId(1);
        itemAtual.setQuantidade(10);
        itemAtual.setIdNota(nota);
        itemAtual.setProduto(produto);

        BigDecimal valor = new BigDecimal(25);
        BigDecimal custoTotal = new BigDecimal(200);

        when(produtoService.buscarPorId(1)).thenReturn(produto);
        when(notaService.buscarPorId(1)).thenReturn(nota);
        when(produtoWrapper.converterParaDto(produto)).thenReturn(produtoDto);
        when(produtoService.atualizar(produtoDto)).thenReturn(produtoDto);
        when(itemService.obterCustoTotal(any())).thenReturn(custoTotal);

        assertDoesNotThrow(() -> {
            service.atualizarEstoque(novoItem, itemAtual);
        });

        assertEquals(10, produto.getEstoque());
    }

    @Test
    @DisplayName("Teste de atualização do estoque nota entrada e produto diferente")
    void deveAtualizarEstoqueEntradaEDiferente() throws DescricaoExisteException {
        TipoNota tipo = TipoNota.ENTRADA;

        Produto produto = new Produto();
        produto.setId(1);
        produto.setEstoque(15);

        Produto produto2 = new Produto();
        produto2.setId(2);
        produto2.setEstoque(15);

        ProdutoDto produtoDto = new ProdutoDto();

        NotaFiscal nota = new NotaFiscal();
        nota.setId(1);
        nota.setTipo(tipo);

        NotaFiscalItem novoItem = new NotaFiscalItem();
        novoItem.setId(1);
        novoItem.setQuantidade(5);
        novoItem.setIdNota(nota);
        novoItem.setProduto(produto);

        NotaFiscalItem itemAtual = new NotaFiscalItem();
        itemAtual.setId(1);
        itemAtual.setQuantidade(10);
        itemAtual.setIdNota(nota);
        itemAtual.setProduto(produto2);

        BigDecimal custoTotal = new BigDecimal(200);

        when(produtoService.buscarPorId(1)).thenReturn(produto);
        when(produtoService.buscarPorId(2)).thenReturn(produto2);
        when(notaService.buscarPorId(1)).thenReturn(nota);
        when(produtoWrapper.converterParaDto(produto)).thenReturn(produtoDto);
        when(produtoService.atualizar(produtoDto)).thenReturn(produtoDto);
        when(itemService.obterCustoTotal(any())).thenReturn(custoTotal);

        assertDoesNotThrow(() -> {
            service.atualizarEstoque(novoItem, itemAtual);
        });

        verify(produtoService, times(2)).atualizar(produtoDto);
        assertEquals(20, produto.getEstoque());
        assertEquals(5, produto2.getEstoque());
    }

    @Test
    @DisplayName("Teste de atualização do estoque nota saida e produtos iguais")
    void deveAtualizarEstoqueSaidaEIgual() throws DescricaoExisteException {
        TipoNota tipo = TipoNota.SAIDA;

        Produto produto = new Produto();
        produto.setId(1);
        produto.setEstoque(15);

        ProdutoDto produtoDto = new ProdutoDto();

        NotaFiscal nota = new NotaFiscal();
        nota.setId(1);
        nota.setTipo(tipo);

        NotaFiscalItem novoItem = new NotaFiscalItem();
        novoItem.setId(1);
        novoItem.setQuantidade(5);
        novoItem.setIdNota(nota);
        novoItem.setProduto(produto);

        NotaFiscalItem itemAtual = new NotaFiscalItem();
        itemAtual.setId(1);
        itemAtual.setQuantidade(10);
        itemAtual.setIdNota(nota);
        itemAtual.setProduto(produto);

        when(produtoService.buscarPorId(1)).thenReturn(produto);
        when(notaService.buscarPorId(1)).thenReturn(nota);
        when(produtoWrapper.converterParaDto(produto)).thenReturn(produtoDto);
        when(produtoService.atualizar(produtoDto)).thenReturn(produtoDto);

        assertDoesNotThrow(() -> {
            service.atualizarEstoque(novoItem, itemAtual);
        });

        verify(produtoService, times(1)).atualizar(produtoDto);
        assertEquals(20, produto.getEstoque());
    }

    @Test
    @DisplayName("Teste de atualização do estoque nota saida e produto diferente")
    void deveAtualizarEstoqueSaidaEDiferente() throws DescricaoExisteException {
        TipoNota tipo = TipoNota.SAIDA;

        Produto produto = new Produto();
        produto.setId(1);
        produto.setEstoque(15);

        Produto produto2 = new Produto();
        produto2.setId(2);
        produto2.setEstoque(15);

        ProdutoDto produtoDto = new ProdutoDto();

        NotaFiscal nota = new NotaFiscal();
        nota.setId(1);
        nota.setTipo(tipo);

        NotaFiscalItem novoItem = new NotaFiscalItem();
        novoItem.setId(1);
        novoItem.setQuantidade(5);
        novoItem.setIdNota(nota);
        novoItem.setProduto(produto);

        NotaFiscalItem itemAtual = new NotaFiscalItem();
        itemAtual.setId(1);
        itemAtual.setQuantidade(10);
        itemAtual.setIdNota(nota);
        itemAtual.setProduto(produto2);

        when(produtoService.buscarPorId(1)).thenReturn(produto);
        when(produtoService.buscarPorId(2)).thenReturn(produto2);
        when(notaService.buscarPorId(1)).thenReturn(nota);
        when(produtoWrapper.converterParaDto(produto)).thenReturn(produtoDto);
        when(produtoService.atualizar(produtoDto)).thenReturn(produtoDto);

        assertDoesNotThrow(() -> {
            service.atualizarEstoque(novoItem, itemAtual);
        });

        verify(produtoService, times(1)).atualizar(produtoDto);
        assertEquals(10, produto.getEstoque());
        assertEquals(25, produto2.getEstoque());
    }

    @Test
    @DisplayName("Teste nota entrada produtos iguais, estoque zerado")
    void deveRetornarErroEstoqueZeradoNotaEntradaProdutosIguais() throws DescricaoExisteException {
        TipoNota tipo = TipoNota.ENTRADA;

        Produto produto = new Produto();
        produto.setId(1);
        produto.setEstoque(15);

        ProdutoDto produtoDto = new ProdutoDto();

        NotaFiscal nota = new NotaFiscal();
        nota.setId(1);
        nota.setTipo(tipo);

        NotaFiscalItem novoItem = new NotaFiscalItem();
        novoItem.setId(1);
        novoItem.setQuantidade(5);
        novoItem.setIdNota(nota);
        novoItem.setProduto(produto);

        NotaFiscalItem itemAtual = new NotaFiscalItem();
        itemAtual.setId(1);
        itemAtual.setQuantidade(30);
        itemAtual.setIdNota(nota);
        itemAtual.setProduto(produto);

        when(produtoService.buscarPorId(1)).thenReturn(produto);
        when(notaService.buscarPorId(1)).thenReturn(nota);
        when(produtoWrapper.converterParaDto(produto)).thenReturn(produtoDto);
        when(produtoService.atualizar(produtoDto)).thenReturn(produtoDto);

        Exception excecao = assertThrows(EstoqueZeradoException.class, () -> {
            service.atualizarEstoque(novoItem, itemAtual);
        });

        assertEquals("Valor em estoque do produto indisponível", excecao.getMessage());
    }

    @Test
    @DisplayName("Teste nota entrada produtos diferentes, estoque zerado")
    void deveRetornarErroEstoqueZeradoNotaEntradaProdutosDiferentes() throws DescricaoExisteException {
        TipoNota tipo = TipoNota.ENTRADA;

        Produto produto = new Produto();
        produto.setId(1);
        produto.setEstoque(15);

        Produto produto2 = new Produto();
        produto2.setId(2);
        produto2.setEstoque(15);

        ProdutoDto produtoDto = new ProdutoDto();

        NotaFiscal nota = new NotaFiscal();
        nota.setId(1);
        nota.setTipo(tipo);

        NotaFiscalItem novoItem = new NotaFiscalItem();
        novoItem.setId(1);
        novoItem.setQuantidade(5);
        novoItem.setIdNota(nota);
        novoItem.setProduto(produto);

        NotaFiscalItem itemAtual = new NotaFiscalItem();
        itemAtual.setId(1);
        itemAtual.setQuantidade(30);
        itemAtual.setIdNota(nota);
        itemAtual.setProduto(produto2);

        when(produtoService.buscarPorId(1)).thenReturn(produto);
        when(produtoService.buscarPorId(2)).thenReturn(produto2);
        when(notaService.buscarPorId(1)).thenReturn(nota);
        when(produtoWrapper.converterParaDto(produto)).thenReturn(produtoDto);
        when(produtoService.atualizar(produtoDto)).thenReturn(produtoDto);

        Exception excecao = assertThrows(EstoqueZeradoException.class, () -> {
            service.atualizarEstoque(novoItem, itemAtual);
        });

        assertEquals("Valor em estoque do produto indisponível", excecao.getMessage());
    }

    @Test
    @DisplayName("Teste nota saida produtos iguais, estoque zerado")
    void deveRetornarErroEstoqueZeradoNotaSaidaProdutosIguais() throws DescricaoExisteException {
        TipoNota tipo = TipoNota.SAIDA;

        Produto produto = new Produto();
        produto.setId(1);
        produto.setEstoque(15);

        ProdutoDto produtoDto = new ProdutoDto();

        NotaFiscal nota = new NotaFiscal();
        nota.setId(1);
        nota.setTipo(tipo);

        NotaFiscalItem novoItem = new NotaFiscalItem();
        novoItem.setId(1);
        novoItem.setQuantidade(25);
        novoItem.setIdNota(nota);
        novoItem.setProduto(produto);

        NotaFiscalItem itemAtual = new NotaFiscalItem();
        itemAtual.setId(1);
        itemAtual.setQuantidade(5);
        itemAtual.setIdNota(nota);
        itemAtual.setProduto(produto);

        when(produtoService.buscarPorId(1)).thenReturn(produto);
        when(notaService.buscarPorId(1)).thenReturn(nota);
        when(produtoWrapper.converterParaDto(produto)).thenReturn(produtoDto);
        when(produtoService.atualizar(produtoDto)).thenReturn(produtoDto);

        Exception excecao = assertThrows(EstoqueZeradoException.class, () -> {
            service.atualizarEstoque(novoItem, itemAtual);
        });

        assertEquals("Valor em estoque do produto indisponível", excecao.getMessage());
    }

    @Test
    @DisplayName("Teste nota saida produtos diferentes, estoque zerado")
    void deveRetornarErroEstoqueZeradoNotaSaidaProdutosDiferentes() throws DescricaoExisteException {
        TipoNota tipo = TipoNota.SAIDA;

        Produto produto = new Produto();
        produto.setId(1);
        produto.setEstoque(15);

        Produto produto2 = new Produto();
        produto2.setId(2);
        produto2.setEstoque(15);

        ProdutoDto produtoDto = new ProdutoDto();

        NotaFiscal nota = new NotaFiscal();
        nota.setId(1);
        nota.setTipo(tipo);

        NotaFiscalItem novoItem = new NotaFiscalItem();
        novoItem.setId(1);
        novoItem.setQuantidade(30);
        novoItem.setIdNota(nota);
        novoItem.setProduto(produto);

        NotaFiscalItem itemAtual = new NotaFiscalItem();
        itemAtual.setId(1);
        itemAtual.setQuantidade(5);
        itemAtual.setIdNota(nota);
        itemAtual.setProduto(produto2);

        when(produtoService.buscarPorId(1)).thenReturn(produto);
        when(produtoService.buscarPorId(2)).thenReturn(produto2);
        when(notaService.buscarPorId(1)).thenReturn(nota);
        when(produtoWrapper.converterParaDto(produto)).thenReturn(produtoDto);
        when(produtoService.atualizar(produtoDto)).thenReturn(produtoDto);

        Exception excecao = assertThrows(EstoqueZeradoException.class, () -> {
            service.atualizarEstoque(novoItem, itemAtual);
        });

        assertEquals("Valor em estoque do produto indisponível", excecao.getMessage());
    }

    @Test
    @DisplayName("Teste para alteração estoque(inserir), nota de entrada")
    void deveAlterarEstoqueEntrada() throws ValorDiferenteException, DescricaoExisteException {
        TipoNota tipo = TipoNota.ENTRADA;

        Produto produto = new Produto();
        produto.setId(1);
        produto.setEstoque(15);

        ProdutoDto produtoDto = new ProdutoDto();

        NotaFiscal nota = new NotaFiscal();
        nota.setId(1);
        nota.setTipo(tipo);

        NotaFiscalItem novoItem = new NotaFiscalItem();
        novoItem.setId(1);
        novoItem.setQuantidade(30);
        novoItem.setIdNota(nota);
        novoItem.setProduto(produto);

        BigDecimal custoTotal = new BigDecimal(200);

        when(produtoService.buscarPorId(1)).thenReturn(produto);
        when(notaService.buscarPorId(1)).thenReturn(nota);
        when(itemService.validarNotaEItem(novoItem)).thenReturn(novoItem);
        when(produtoWrapper.converterParaDto(produto)).thenReturn(produtoDto);
        when(produtoService.atualizar(produtoDto)).thenReturn(produtoDto);
        when(itemService.obterCustoTotal(any())).thenReturn(custoTotal);

        assertDoesNotThrow(() -> {
            service.alterarEstoque(novoItem);
        });

        assertEquals(45, produto.getEstoque());
    }

    @Test
    @DisplayName("Teste para alteração estoque(inserir), nota de saida")
    void deveAlterarEstoqueSaida() throws ValorDiferenteException, DescricaoExisteException {
        TipoNota tipo = TipoNota.SAIDA;

        Produto produto = new Produto();
        produto.setId(1);
        produto.setEstoque(15);

        ProdutoDto produtoDto = new ProdutoDto();

        NotaFiscal nota = new NotaFiscal();
        nota.setId(1);
        nota.setTipo(tipo);

        NotaFiscalItem novoItem = new NotaFiscalItem();
        novoItem.setId(1);
        novoItem.setQuantidade(5);
        novoItem.setIdNota(nota);
        novoItem.setProduto(produto);

        when(produtoService.buscarPorId(1)).thenReturn(produto);
        when(notaService.buscarPorId(1)).thenReturn(nota);
        when(itemService.validarNotaEItem(novoItem)).thenReturn(novoItem);
        when(produtoWrapper.converterParaDto(produto)).thenReturn(produtoDto);
        when(produtoService.atualizar(produtoDto)).thenReturn(produtoDto);

        assertDoesNotThrow(() -> {
            service.alterarEstoque(novoItem);
        });

        verify(produtoService, times(1)).atualizar(produtoDto);
        assertEquals(10, produto.getEstoque());
    }

    @Test
    @DisplayName("Teste para estoque zerado(inserir), nota de saida")
    void deveRetornarEstoqueZeradoNotaSaida() throws ValorDiferenteException, DescricaoExisteException {
        TipoNota tipo = TipoNota.SAIDA;

        Produto produto = new Produto();
        produto.setId(1);
        produto.setEstoque(15);

        ProdutoDto produtoDto = new ProdutoDto();

        NotaFiscal nota = new NotaFiscal();
        nota.setId(1);
        nota.setTipo(tipo);

        NotaFiscalItem novoItem = new NotaFiscalItem();
        novoItem.setId(1);
        novoItem.setQuantidade(20);
        novoItem.setIdNota(nota);
        novoItem.setProduto(produto);

        when(produtoService.buscarPorId(1)).thenReturn(produto);
        when(notaService.buscarPorId(1)).thenReturn(nota);

        Exception excecao = assertThrows(EstoqueZeradoException.class, () -> {
            service.alterarEstoque(novoItem);
        });

        assertEquals("A quantidade em estoque não é suficiente", excecao.getMessage());
    }

    @Test
    @DisplayName("Teste de redução do estoque para nota de entrada deletada")
    void deveReduzirEstoquePorDelecaoDeItem() throws DescricaoExisteException {
        TipoNota tipo = TipoNota.ENTRADA;

        Produto produto = new Produto();
        produto.setId(1);
        produto.setEstoque(25);

        ProdutoDto produtoDto = new ProdutoDto();

        NotaFiscal nota = new NotaFiscal();
        nota.setId(1);
        nota.setTipo(tipo);
        nota.setValorTotal(new BigDecimal(350));

        NotaFiscalDto notaDto = new NotaFiscalDto();

        NotaFiscalItem item = new NotaFiscalItem();
        item.setId(1);
        item.setQuantidade(20);
        item.setIdNota(nota);
        item.setProduto(produto);
        item.setValorTotal(new BigDecimal(125));

        when(repository.findById(1)).thenReturn(Optional.of(item));
        when(notaService.buscarPorId(1)).thenReturn(nota);
        when(produtoService.buscarPorId(1)).thenReturn(produto);
        when(produtoWrapper.converterParaDto(produto)).thenReturn(produtoDto);
        when(produtoService.atualizar(produtoDto)).thenReturn(produtoDto);
        when(notaWrapper.converterParaDto(nota)).thenReturn(notaDto);
        when(notaService.atualizar(notaDto)).thenReturn(notaDto);

        assertDoesNotThrow(() -> {
            service.deletarEstoque(1);
        });

        verify(repository, times(1)).deleteById(1);
        assertEquals(new BigDecimal(225), nota.getValorTotal());
        assertEquals(5, produto.getEstoque());
    }

    @Test
    @DisplayName("Teste de aumento do estoque para nota de saida deletada")
    void deveAumentarEstoquePorDelecaoDeItem() throws DescricaoExisteException {
        TipoNota tipo = TipoNota.SAIDA;

        Produto produto = new Produto();
        produto.setId(1);
        produto.setEstoque(25);

        ProdutoDto produtoDto = new ProdutoDto();

        NotaFiscal nota = new NotaFiscal();
        nota.setId(1);
        nota.setTipo(tipo);
        nota.setValorTotal(new BigDecimal(350));

        NotaFiscalDto notaDto = new NotaFiscalDto();

        NotaFiscalItem item = new NotaFiscalItem();
        item.setId(1);
        item.setQuantidade(20);
        item.setIdNota(nota);
        item.setProduto(produto);
        item.setValorTotal(new BigDecimal(125));

        when(repository.findById(1)).thenReturn(Optional.of(item));
        when(notaService.buscarPorId(1)).thenReturn(nota);
        when(produtoService.buscarPorId(1)).thenReturn(produto);
        when(produtoWrapper.converterParaDto(produto)).thenReturn(produtoDto);
        when(produtoService.atualizar(produtoDto)).thenReturn(produtoDto);
        when(notaWrapper.converterParaDto(nota)).thenReturn(notaDto);
        when(notaService.atualizar(notaDto)).thenReturn(notaDto);

        assertDoesNotThrow(() -> {
            service.deletarEstoque(1);
        });

        verify(repository, times(1)).deleteById(1);
        assertEquals(new BigDecimal(475), nota.getValorTotal());
        assertEquals(45, produto.getEstoque());
    }

    @Test
    @DisplayName("Teste com erro estoque zerado para deleção de nota de entrada")
    void deveRetornarErroEstoqueZeradoParaDeleteEntrada() {
        TipoNota tipo = TipoNota.ENTRADA;

        Produto produto = new Produto();
        produto.setId(1);
        produto.setEstoque(25);

        NotaFiscal nota = new NotaFiscal();
        nota.setId(1);
        nota.setTipo(tipo);
        nota.setValorTotal(new BigDecimal(350));

        NotaFiscalItem item = new NotaFiscalItem();
        item.setId(1);
        item.setQuantidade(40);
        item.setIdNota(nota);
        item.setProduto(produto);
        item.setValorTotal(new BigDecimal(125));

        when(repository.findById(1)).thenReturn(Optional.of(item));
        when(notaService.buscarPorId(1)).thenReturn(nota);
        when(produtoService.buscarPorId(1)).thenReturn(produto);

        Exception excecao = assertThrows(EstoqueZeradoException.class, () -> {
            service.deletarEstoque(1);
        });

        assertEquals("A quantidade em estoque não é suficiente", excecao.getMessage());
    }

    @Test
    @DisplayName("Teste calcular custo medio")
    void deveCalcularCustoMedio() {

        Produto produto = new Produto();
        produto.setId(1);
        produto.setEstoque(100);

        NotaFiscalItem item = new NotaFiscalItem();
        item.setId(1);
        item.setProduto(produto);

        BigDecimal qtdTotal = new BigDecimal(100);
        BigDecimal custoTotal = new BigDecimal(500);

        BigDecimal custoM = CustoMedioService.calcular(custoTotal, qtdTotal);

        when(produtoService.buscarPorId(item.getProduto().getId())).thenReturn(produto);
        when(itemService.obterCustoTotal(item)).thenReturn(custoTotal);

        assertDoesNotThrow(() -> {
            service.calcularCustoMedio(item);
        });

    }
}
