package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ProdutoTipoRepository;

public class ProdutoTipoServiceTest {
    @Mock
    private ProdutoTipoRepository produtoTipoRepository;

    @Mock
    private ParceiroNegocioTipoService parceiroNegocioService;

    @InjectMocks
    private ProdutoTipoService produtoTipoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deveRetornarNullAoAtualizarProdutoInexistente() {
        ProdutoDto produtoDto = new ProdutoDto();
        produtoDto.setId(999);

        when(produtoTipoRepository.findById(anyInt())).thenReturn(Optional.empty());

        ProdutoDto result = produtoTipoService.atualizar(produtoDto);

        assertNull(result);
        verify(produtoTipoRepository).findById(999);
    }

    @Test
    public void deveBuscarProdutoPorId() {
        ProdutoDto produtoDto = new ProdutoDto();
        produtoDto.setId(1);

        Produto produtoMock = new Produto();
        produtoMock.setId(1);

        when(produtoTipoRepository.findById(anyInt())).thenReturn(Optional.of(produtoMock));

        ProdutoDto result = produtoTipoService.atualizar(produtoDto);

        assertNotNull(result);
        assertEquals(1, result.getId());

        verify(produtoTipoRepository).findById(anyInt());
    }

    @Test
    public void deveInserirProduto() {
        // Arrange
        ProdutoDto produtoDto = new ProdutoDto();

        Produto produtoMock = new Produto();
        produtoMock.setId(1);

        when(produtoTipoRepository.save(any(Produto.class))).thenReturn(produtoMock);

        ProdutoDto result = produtoTipoService.inserir(produtoDto);

        assertNotNull(result);
        assertEquals(1, result.getId());

        verify(produtoTipoRepository).save(any(Produto.class));
    }

    @Test
    public void deveListarTodosOsProdutos() {
        List<Produto> produtosEntidade = new ArrayList<>();
        produtosEntidade.add(criarProdutoEntidade());
        when(produtoTipoRepository.findAll()).thenReturn(produtosEntidade);
        when(produtoTipoRepository.findAll()).thenReturn(listaProdutos);

        List<ProdutoDto> produtosDtoRetornados = produtoTipoService.listarTodos();

        assertNotNull(produtosDtoRetornados);
        assertEquals(1, produtosDtoRetornados.size());
    }

    @Test
    public void deveDeletarProdutoPorId() {
        Integer idProduto = 1;

        when(produtoTipoRepository.findById(Mockito.eq(idProduto))).thenReturn(Optional.of(criarProdutoEntidade()));

        produtoTipoService.deletarPorId(idProduto);

        Mockito.verify(produtoTipoRepository, Mockito.times(1)).deleteById(Mockito.eq(idProduto));
    }

    @Test
    public void deveAtualizarProduto() {
        Integer idProduto = 1;
        ProdutoDto produtoDtoAtualizado = criarProdutoDto();
        produtoDtoAtualizado.setId(idProduto);

        Produto produtoSalvo = criarProdutoEntidade();
        when(produtoTipoRepository.findById(Mockito.eq(idProduto))).thenReturn(Optional.of(criarProdutoEntidade()));
        when(produtoTipoRepository.save(Mockito.any(Produto.class))).thenReturn(produtoSalvo);
        ProdutoDto produtoDtoRetornado = produtoTipoService.atualizar(produtoDtoAtualizado);

        assertNotNull(produtoDtoRetornado);
        assertEquals(idProduto, produtoDtoRetornado.getId());
    }

    private ProdutoDto criarProdutoDto() {
        ProdutoDto produtoDto = new ProdutoDto();
        produtoDto.setId(1);
        produtoDto.setDescricao("Produto Teste");
        produtoDto.setEstoque(BigDecimal.valueOf(100));
        produtoDto.setFabricante(criarParceiroNegocioDto());
        produtoDto.setDataFabricacao(LocalDate.of(2022, 1, 1));
        produtoDto.setDataValidade(LocalDate.of(2023, 1, 1));
        return produtoDto;
    }

    private Produto criarProdutoEntidade() {
        Produto produto = new Produto();
        produto.setId(1);
        produto.setDescricao("Produto Teste");
        produto.setEstoque(new BigDecimal(10));
        produto.setFabricante(new ParceiroNegocio());
        produto.setDataFabricacao(LocalDate.of(2022, 1, 1));
        produto.setDataValidade(LocalDate.of(2023, 1, 1));
        return produto;
    }

    private ParceiroNegocioDto criarParceiroNegocioDto() {
        ParceiroNegocioDto parceiroDto = new ParceiroNegocioDto();
        parceiroDto.setId(1);
        parceiroDto.setNome("Fabricante Teste");
        return parceiroDto;
    }

    private ParceiroNegocio criarParceiroNegocioEntidade() {
        ParceiroNegocio parceiro = new ParceiroNegocio();
        parceiro.setId(1);
        parceiro.setNome("Fabricante Teste");
        return parceiro;
    }
}
