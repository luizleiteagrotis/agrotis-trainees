package com.agrotis.trainees.crud.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
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
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.dto.NotaFiscalItemDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.CrudException;
import com.agrotis.trainees.crud.exception.DescricaoExisteException;
import com.agrotis.trainees.crud.exception.EstoqueZeradoException;
import com.agrotis.trainees.crud.exception.ValorDiferenteException;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;
import com.agrotis.trainees.crud.wrapper.NotaFiscalItemWrapper;
import com.agrotis.trainees.crud.wrapper.NotaFiscalWrapper;

public class NotaFiscalItemServiceTest {

    @Mock
    NotaFiscalItemRepository repository;

    @Mock
    NotaFiscalItemWrapper itemWrapper;

    @Mock
    NotaFiscalWrapper notaWrapper;

    @Mock
    EstoqueService estoqueService;

    @Mock
    ItemService itemService;

    @Mock
    NotaFiscalService notaService;

    @Mock
    ProdutoService produtoService;

    @InjectMocks
    NotaFiscalItemService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Teste de inserção com sucesso")
    void deveInserirComSucesso() throws EstoqueZeradoException, ValorDiferenteException, DescricaoExisteException {
        NotaFiscal nota = new NotaFiscal();
        nota.setId(1);

        Produto produto = new Produto();
        produto.setId(1);

        NotaFiscalItemDto dto = new NotaFiscalItemDto();
        dto.setId(1);
        dto.setIdNota(nota);
        dto.setProduto(produto);

        NotaFiscalItem entidade = new NotaFiscalItem();
        entidade.setId(1);
        entidade.setIdNota(nota);
        entidade.setProduto(produto);

        when(itemWrapper.converterParaEntidade(dto)).thenReturn(entidade);
        when(estoqueService.alterarEstoque(entidade)).thenReturn(entidade);
        when(repository.save(entidade)).thenReturn(entidade);
        when(itemWrapper.converterParaDto(entidade)).thenReturn(dto);

        assertDoesNotThrow(() -> {
            service.inserir(dto);
        });
    }

    @Test
    @DisplayName("Testa erro de estoque zerado")
    void deveRetornarErroEstoqueZerado() throws ValorDiferenteException, DescricaoExisteException, EstoqueZeradoException {
        NotaFiscalItemDto dto = new NotaFiscalItemDto();
        dto.setId(1);

        NotaFiscalItem entidade = new NotaFiscalItem();
        entidade.setId(1);

        when(itemWrapper.converterParaEntidade(dto)).thenReturn(entidade);
        when(estoqueService.alterarEstoque(entidade))
                        .thenThrow(new EstoqueZeradoException("A quantidade em estoque não é suficiente"));

        Exception excecao = assertThrows(EstoqueZeradoException.class, () -> {
            service.inserir(dto);
        });

        assertEquals("A quantidade em estoque não é suficiente", excecao.getMessage());
    }

    @Test
    @DisplayName("Testa erro de valor diferente")
    void deveRetornarErroValorDiferente() throws ValorDiferenteException, DescricaoExisteException, EstoqueZeradoException {
        NotaFiscalItemDto dto = new NotaFiscalItemDto();
        dto.setId(1);

        NotaFiscalItem entidade = new NotaFiscalItem();
        entidade.setId(1);

        when(itemWrapper.converterParaEntidade(dto)).thenReturn(entidade);
        when(estoqueService.alterarEstoque(entidade))
                        .thenThrow(new ValorDiferenteException("Item com preço diferente do original"));

        Exception excecao = assertThrows(ValorDiferenteException.class, () -> {
            service.inserir(dto);
        });

        assertEquals("Item com preço diferente do original", excecao.getMessage());
    }

    @Test
    @DisplayName("Testar atualizar com sucesso")
    void deveAtualizar() throws EstoqueZeradoException, DescricaoExisteException {
        NotaFiscal nota = new NotaFiscal();
        nota.setId(1);
        nota.setValorTotal(new BigDecimal(100));

        NotaFiscalDto notaDto = new NotaFiscalDto();

        Produto produto = new Produto();
        produto.setId(1);

        NotaFiscalItemDto dto = new NotaFiscalItemDto();
        dto.setId(1);
        dto.setIdNota(nota);
        dto.setProduto(produto);

        NotaFiscalItem entidade = new NotaFiscalItem();
        entidade.setId(1);
        entidade.setIdNota(nota);
        entidade.setProduto(produto);

        NotaFiscalItem entidade2 = new NotaFiscalItem();
        entidade2.setId(1);
        entidade2.setIdNota(nota);
        entidade2.setProduto(produto);
        entidade2.setValorTotal(new BigDecimal(15));

        when(itemWrapper.converterParaEntidade(dto)).thenReturn(entidade);
        when(repository.findById(entidade.getId())).thenReturn(Optional.of(entidade2));
        when(itemService.jutsuDeSubstituicao(entidade, entidade2)).thenReturn(entidade);
        when(notaService.buscarPorId(entidade.getIdNota().getId())).thenReturn(nota);
        when(produtoService.buscarPorId(entidade2.getProduto().getId())).thenReturn(produto);
        when(estoqueService.atualizarEstoque(entidade, entidade2)).thenReturn(entidade);
        when(itemService.obterValorTotal(entidade2)).thenReturn(entidade2);
        when(notaWrapper.converterParaDto(nota)).thenReturn(notaDto);
        when(notaService.atualizar(notaDto)).thenReturn(notaDto);
        when(repository.save(entidade2)).thenReturn(entidade2);
        when(itemWrapper.converterParaDto(entidade2)).thenReturn(dto);

        assertDoesNotThrow(() -> {
            service.atualizar(dto);
        });

        verify(repository, times(1)).save(entidade2);

    }

    @Test
    @DisplayName("Testa erro de id nulo")
    void deveRetornarErroIdNulo() {
        NotaFiscalItemDto dto = new NotaFiscalItemDto();
        dto.setId(1);

        NotaFiscalItem entidade = new NotaFiscalItem();

        when(itemWrapper.converterParaEntidade(dto)).thenReturn(entidade);

        Exception excecao = assertThrows(CrudException.class, () -> {
            service.atualizar(dto);
        });

        assertEquals("Obrigatório preencher o id do item.", excecao.getMessage());

    }

    @Test
    @DisplayName("Testa erro de estoque zerado no atualizar")
    void deveRetornarErroEstoqueZeradoAtualizar() throws EstoqueZeradoException, DescricaoExisteException {
        NotaFiscal nota = new NotaFiscal();
        nota.setId(1);

        Produto produto = new Produto();
        produto.setId(1);

        NotaFiscalItemDto dto = new NotaFiscalItemDto();
        dto.setId(1);

        NotaFiscalItem entidade = new NotaFiscalItem();
        entidade.setId(1);
        entidade.setIdNota(nota);
        entidade.setProduto(produto);

        NotaFiscalItem entidade2 = new NotaFiscalItem();
        entidade2.setId(1);
        entidade2.setIdNota(nota);
        entidade2.setProduto(produto);

        when(itemWrapper.converterParaEntidade(dto)).thenReturn(entidade);
        when(repository.findById(entidade.getId())).thenReturn(Optional.of(entidade2));
        when(itemService.jutsuDeSubstituicao(entidade, entidade2)).thenReturn(entidade);
        when(notaService.buscarPorId(entidade.getIdNota().getId())).thenReturn(nota);
        when(produtoService.buscarPorId(entidade2.getProduto().getId())).thenReturn(produto);
        when(estoqueService.atualizarEstoque(entidade, entidade2))
                        .thenThrow(new EstoqueZeradoException("A quantidade em estoque não é suficiente"));

        Exception excecao = assertThrows(EstoqueZeradoException.class, () -> {
            service.atualizar(dto);
        });

        assertEquals("A quantidade em estoque não é suficiente", excecao.getMessage());

    }

    @Test
    @DisplayName("Testa Buscar por id, falha")
    void deveRetornarErroAoBuscarPorId() {

        when(repository.findById(1)).thenReturn(Optional.empty());

        Exception excecao = assertThrows(NoSuchElementException.class, () -> {
            service.buscarPorId(1);
        });

        assertEquals("Informações não encontradas para o id 1", excecao.getMessage());
    }

    @Test
    @DisplayName("Testa listar por produto, sucesso")
    void deveListarPorProduto() {
        Produto produto = new Produto();
        produto.setDescricao("Batata");

        NotaFiscalItem item = new NotaFiscalItem();
        item.setId(1);

        List<NotaFiscalItem> itens = new ArrayList();
        itens.add(item);
        itens.add(item);
        itens.add(item);

        when(repository.findByProduto(produto)).thenReturn(itens);

        assertDoesNotThrow(() -> {
            service.buscarPorProduto(produto);
        });

        verify(repository, times(1)).findByProduto(produto);
        assertEquals(3, itens.size());
    }

    @Test
    @DisplayName("Testa listar por nota, sucesso")
    void deveListarPorNota() {
        NotaFiscal nota = new NotaFiscal();
        nota.setId(1);

        NotaFiscalItem item = new NotaFiscalItem();
        NotaFiscalItem item2 = new NotaFiscalItem();

        List<NotaFiscalItem> itens = new ArrayList();
        itens.add(item);
        itens.add(item2);

        when(notaService.buscarPorId(1)).thenReturn(nota);
        when(repository.findByNota(nota)).thenReturn(itens);

        assertDoesNotThrow(() -> {
            service.listarPorNota(1);
        });

        verify(repository, times(1)).findByNota(nota);
        assertEquals(2, itens.size());
    }

    @Test
    @DisplayName("Testa listar todos, sucesso")
    void deveListarTodos() {

        NotaFiscalItem item = new NotaFiscalItem();
        NotaFiscalItem item2 = new NotaFiscalItem();
        NotaFiscalItem item3 = new NotaFiscalItem();

        List<NotaFiscalItem> itens = new ArrayList();
        itens.add(item);
        itens.add(item2);
        itens.add(item3);

        when(repository.findAll()).thenReturn(itens);

        assertDoesNotThrow(() -> {
            service.listarTodos();
        });

        verify(repository, times(1)).findAll();
        assertEquals(3, itens.size());
    }

    @Test
    @DisplayName("Teste para deleção, sucesso")
    void deveDeletarItem() throws DescricaoExisteException, EstoqueZeradoException {
        doNothing().when(estoqueService).deletarEstoque(1);

        assertDoesNotThrow(() -> {
            service.deletarPorId(1);
        });
        verify(estoqueService, times(1)).deletarEstoque(1);

    }
}
