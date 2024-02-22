package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.DataValidadeInvalidaException;
import com.agrotis.trainees.crud.exception.ProdutoDuplicadoException;
import com.agrotis.trainees.crud.exception.ProdutoNaoEncontradoException;
import com.agrotis.trainees.crud.repository.ProdutoRepository;
import com.agrotis.trainees.crud.utils.ProdutoDTOMapper;

public class ProdutoServiceTest {

    private final Integer ID = 1000;
    private final String DESCRICAO = "Adubo";

    @Mock
    private ProdutoRepository repository;

    @Mock
    private ProdutoDTOMapper mapper;

    @InjectMocks
    private ProdutoService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private Produto criarProduto() {
        Produto produto = Mockito.mock(Produto.class);
        return produto;
    }

    @Test
    public void naoDeveriaSalvarProduto() {
        ProdutoDto dto = new ProdutoDto();
        Produto produto = new Produto();
        Date date = new Date(2020, 02, 02);
        produto.setDataFabricacao(date);
        Date validade = new Date(2026, 02, 02);
        produto.setDataFabricacao(date);
        produto.setDataValidade(validade);
        produto.setDescricao("Banana");
        dto.setDataFabricacao(produto.getDataFabricacao());
        dto.setDataValidade(produto.getDataValidade());
        dto.setDescricao(produto.getDescricao());
        when(mapper.converterParaEntidade(dto)).thenReturn(produto);
        when(repository.existsByDescricao(produto.getDescricao())).thenReturn(true);

        Exception excecao = assertThrows(ProdutoDuplicadoException.class, () -> {
            service.salvar(dto);
        });

        assertEquals("Descrição do produto já existe", excecao.getMessage());
    }

    @Test
    public void naoDeveriaSalvarProdutoDataInvalida() {
        ProdutoDto dto = new ProdutoDto();
        Produto produto = new Produto();
        Date date = new Date(2026, 02, 02);
        produto.setDataFabricacao(date);
        Date validade = new Date(202, 02, 02);
        produto.setDataFabricacao(date);
        produto.setDataValidade(validade);
        produto.setDescricao("Banana");
        dto.setDataFabricacao(produto.getDataFabricacao());
        dto.setDataValidade(produto.getDataValidade());
        dto.setDescricao(produto.getDescricao());
        when(mapper.converterParaEntidade(dto)).thenReturn(produto);
        when(repository.existsByDescricao(produto.getDescricao())).thenReturn(false);

        Exception excecao = assertThrows(DataValidadeInvalidaException.class, () -> {
            service.salvar(dto);
        });

        assertEquals("A data de validade deve ser após a data de fabricação", excecao.getMessage());
    }

    @Test
    public void deveSalvarProduto() {
        ProdutoDto dto = new ProdutoDto();
        Produto produto = new Produto();
        Date date = new Date(2020, 02, 02);
        produto.setDataFabricacao(date);
        Date validade = new Date(2026, 02, 02);
        produto.setDataFabricacao(date);
        produto.setDataValidade(validade);
        produto.setDescricao("Banana");
        dto.setDataFabricacao(produto.getDataFabricacao());
        dto.setDataValidade(produto.getDataValidade());
        dto.setDescricao(produto.getDescricao());
        when(mapper.converterParaEntidade(dto)).thenReturn(produto);
        when(repository.existsByDescricao(produto.getDescricao())).thenReturn(false);
        when(repository.save(produto)).thenReturn(produto);

        when(service.salvar(dto)).thenReturn(dto);
        verify(repository, times(1)).save(produto);
        ProdutoDto resultado = service.salvar(dto);
        assertEquals(produto.getDescricao(), resultado.getDescricao());
    }

    @Test
    public void deveriaNaoAtualizarProdutoComDescricaoExistente() {
        Produto produto = new Produto();
        produto.setDescricao("Adubo");
        produto.setId(1000);

        when(repository.existsByDescricaoAndIdNot(produto.getDescricao(), produto.getId())).thenReturn(true);

        ProdutoDto dto = new ProdutoDto();
        dto.setDescricao(produto.getDescricao());
        dto.setId(produto.getId());

        when(mapper.converterParaEntidade(dto)).thenReturn(produto);
        when(repository.save(any(Produto.class))).thenReturn(produto);

        Exception excecao = assertThrows(ProdutoDuplicadoException.class, () -> {
            service.atualizar(dto);
        });

        assertEquals("Descrição do produto já existe", excecao.getMessage());
    }

    @Test
    public void deveAtualizarProduto() {
        Produto produto = new Produto();

        when(repository.existsByDescricaoAndIdNot(any(), any())).thenReturn(false);

        ProdutoDto dto = new ProdutoDto();

        when(mapper.converterParaEntidade(dto)).thenReturn(produto);
        when(repository.save(any(Produto.class))).thenReturn(produto);

        when(service.atualizar(dto)).thenReturn(dto);
        verify(repository, times(1)).save(produto);
        ProdutoDto resultado = service.atualizar(dto);
        assertEquals(dto, resultado);
    }

    @Test
    public void deveriaNaoBuscarProdutoPorIdInexistente() {
        when(repository.findById(ID)).thenReturn(Optional.empty());
        
        Exception excecao = assertThrows(ProdutoNaoEncontradoException.class, () -> {
            service.buscarPorId(ID);
        });
        
        assertEquals("Produto não encontrado para o id " + ID, excecao.getMessage());
    }

    @Test
    public void deveEncontrarProdutoPorId() {
        Produto produto = new Produto();
        produto.setId(1000);
        ProdutoDto produtoDto = new ProdutoDto();
        when(mapper.converterParaDto(produto)).thenReturn(produtoDto);
        when(repository.findById(anyInt())).thenReturn(Optional.of(produto));
        produtoDto.setId(produto.getId());

        when(service.buscarPorId(produtoDto.getId())).thenReturn(produtoDto);
        verify(repository, times(1)).findById(anyInt());
        ProdutoDto produdoAchado = service.buscarPorId(produtoDto.getId());
        assertEquals(produto.getId(), produdoAchado.getId());
    }

    @Test
    public void deveriaNaoEncontrarProdutoPorDescricaoInexistente() {
        when(repository.findByDescricao(DESCRICAO)).thenReturn(Optional.empty());
        
        Exception excecao = assertThrows(ProdutoNaoEncontradoException.class, () -> {
            service.buscarPorDescricao(DESCRICAO);
        });
            
            assertEquals("Produto não encontrada para o nome " + DESCRICAO, excecao.getMessage());
    }

    @Test
    public void deveEscontrarProdutoPorDescricao() {
        Produto produto = new Produto();
        produto.setDescricao(DESCRICAO);
        ProdutoDto produtoDto = new ProdutoDto();

        when(mapper.converterParaDto(produto)).thenReturn(produtoDto);
        when(repository.findByDescricao(DESCRICAO)).thenReturn(Optional.of(produto));
        produtoDto.setDescricao(produto.getDescricao());

        when(service.buscarPorDescricao(DESCRICAO)).thenReturn(produtoDto);
        verify(repository, times(1)).findByDescricao(DESCRICAO);
        ProdutoDto produtoAchado = service.buscarPorDescricao(DESCRICAO);
        assertEquals(produto.getDescricao(), produtoAchado.getDescricao());
    }

    @Test
    public void deveRemoverProdutoPorId() {
        service.deletarPorId(ID);
        verify(repository, times(1)).deleteById(ID);
    }

    @Test
    public void listarTodos() {
        List<Produto> produtos = Arrays.asList(new Produto(), new Produto());
        when(repository.findAll()).thenReturn(produtos);

        List<ProdutoDto> produtoDtos = service.listarTodos();
        assertNotNull(produtoDtos);
        assertEquals(2, produtoDtos.size());
        verify(repository, times(1)).findAll();
    }

}
