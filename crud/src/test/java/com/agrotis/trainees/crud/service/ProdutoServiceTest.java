package com.agrotis.trainees.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agrotis.trainees.crud.dtos.ProdutoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.repository.ProdutoRepository;
import com.agrotis.trainees.crud.service.exceptions.CampoVazioOuNuloException;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;
import com.agrotis.trainees.crud.service.impl.ProdutoServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ParceiroNegocioRepository parceiroNegocioRepository;

    @InjectMocks
    private ProdutoServiceImpl service;

    @Test
    void salvarDeveSalvarProdutoQuandoDtoValido() {
        ProdutoDto produtoDto = criarProdutoDto();
        Produto produtoSalvo = criarProduto();
        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoSalvo);

        ProdutoDto result = service.salvar(produtoDto);

        assertNotNull(result);
        assertEquals(produtoSalvo.getId(), result.getId());
        verify(produtoRepository).save(any(Produto.class));
    }

    @Test
    void salvarDeveLancarCampoVazioOuNuloException_QuandoDtoInvalido() {
        ProdutoDto produtoDto = new ProdutoDto();
        assertThrows(CampoVazioOuNuloException.class, () -> {
            service.salvar(produtoDto);
        });
    }

    @Test
    void buscaPeloIdDeveRetornarProdutoDto_QuandoEncontrarProduto() {
        int id = 1;
        Produto produto = criarProduto();
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produto));

        ProdutoDto result = service.buscaPeloId(id);

        assertNotNull(result);
        assertEquals(produto.getDescricao(), result.getDescricao());
    }

    @Test
    void buscaPeloIdDeveLancarEntidadeNaoEncontradaException_QuandoNaoEncontrarProduto() {
        int id = 1;
        when(produtoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> {
            service.buscaPeloId(id);
        });
    }

    @Test
    void buscarTodosDeveRetornarListaDeProdutosDto() {
        List<Produto> produtos = new ArrayList<>();
        produtos.add(criarProduto());
        when(produtoRepository.findAll()).thenReturn(produtos);

        List<ProdutoDto> result = service.buscarTodos();

        assertNotNull(result);
        assertEquals(produtos.size(), result.size());
        assertEquals(produtos.get(0).getDescricao(), result.get(0).getDescricao());
    }

    @Test
    void deletarPorIdDeveDeletarProduto_QuandoEncontrarProduto() {
        int id = 1;
        Produto produto = criarProduto();
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produto));

        service.deletarPorId(id);

        verify(produtoRepository).deleteById(id);
    }

    @Test
    void deletarPorIdDeveLancarEntidadeNaoEncontradaException_QuandoNaoEncontrarProduto() {
        int id = 1;
        when(produtoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> {
            service.deletarPorId(id);
        });
    }

    @Test
    void atualizarDeveAtualizarProduto_QuandoEncontrarProduto() {
        int id = 1;
        Produto produtoExistente = criarProduto();
        ProdutoDto produtoDto = criarProdutoDto();
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produtoExistente));
        when(produtoRepository.save(any(Produto.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ProdutoDto result = service.atualizar(id, produtoDto);

        assertNotNull(result);
        assertEquals(produtoDto.getDescricao(), result.getDescricao());
        verify(produtoRepository).save(any(Produto.class));
    }

    @Test
    void atualizar_DeveLancarEntidadeNaoEncontradaException_QuandoNaoEncontrarProduto() {
        int id = 1;
        ProdutoDto produtoDto = criarProdutoDto();
        when(produtoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> {
            service.atualizar(id, produtoDto);
        });
    }

    private ProdutoDto criarProdutoDto() {
        ProdutoDto dto = new ProdutoDto();
        dto.setDescricao("Produto Teste");
        dto.setQuantidadeEstoque(new BigDecimal(10));
        dto.setDataFabricacao(LocalDate.of(2024, 2, 21));
        dto.setDataValidade(LocalDate.of(2025, 2, 21));
        ParceiroNegocio fabricante = new ParceiroNegocio();
        fabricante.setNome("Fabricante Teste");
        dto.setFabricante(fabricante);
        return dto;
    }

    private Produto criarProduto() {
        Produto produto = new Produto();
        produto.setDescricao("Produto Teste");
        produto.setQuantidadeEstoque(new BigDecimal(10));
        produto.setDataFabricacao(LocalDate.of(2024, 2, 21));
        produto.setDataValidade(LocalDate.of(2025, 2, 21));
        ParceiroNegocio fabricante = new ParceiroNegocio();
        fabricante.setNome("Fabricante Teste");
        produto.setFabricante(fabricante);
        return produto;
    }

}
