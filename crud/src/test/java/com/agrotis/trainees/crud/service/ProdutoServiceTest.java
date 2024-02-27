package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.DataValidadeInvalidaException;
import com.agrotis.trainees.crud.exception.ProdutoDuplicadoException;
import com.agrotis.trainees.crud.exception.ProdutoNaoEncontradoException;
import com.agrotis.trainees.crud.repository.ProdutoRepository;
import com.agrotis.trainees.crud.utils.ProdutoDTOMapper;

public class ProdutoServiceTest {

    private final Integer ID = 1;
    private final String DESCRICAO = "Descrição do Produto";
    // private final ParceiroNegocio FABRICANTE = new ParceiroNegocio();
    private final int QUANTIDADEESTOQUE = 0;
    private final BigDecimal CUSTOMEDIO = new BigDecimal(0);

    @Mock
    private ProdutoRepository repository;

    @Mock
    private ProdutoDTOMapper mapper;

    @Mock
    private ParceiroNegocio fabricante;

    @InjectMocks
    private ProdutoService service;

    private Produto produto;

    private ProdutoDto produtoDto;

    private Optional<Produto> produtoOpcional;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        inicializarProduto();
    }

 @Test
 public void naoDeveriaSalvarProduto() {
 when(mapper.converterParaEntidade(produtoDto)).thenReturn(produto);
 when(repository.existsByDescricao(anyString())).thenReturn(true);

 Exception excecao = assertThrows(ProdutoDuplicadoException.class, () -> {
 service.salvar(produtoDto);
 });

 assertEquals(ProdutoDuplicadoException.class, excecao.getClass());
 assertEquals("Descrição do produto já existe", excecao.getMessage());
 }

    @Test
    public void naoDeveriaSalvarProdutoDataInvalida() {

        produto.setDataFabricacao(new Date());
        produto.setDataValidade(new Date(1000));

        when(mapper.converterParaEntidade(any(ProdutoDto.class))).thenReturn(produto);
        when(repository.existsByDescricao(anyString())).thenReturn(false);

        Exception excecao = assertThrows(DataValidadeInvalidaException.class, () -> {
            service.salvar(produtoDto);
        });

        assertEquals("A data de validade deve ser após a data de fabricação", excecao.getMessage());
    }

 @Test
 public void deveSalvarProduto() {
 when(mapper.converterParaEntidade(produtoDto)).thenReturn(produto);
 when(mapper.converterParaDto(produto)).thenReturn(produtoDto);
 when(repository.existsByDescricao(anyString())).thenReturn(false);
 when(repository.save(any())).thenReturn(produto);

 ProdutoDto produtoSalvo = service.salvar(produtoDto);

 assertNotNull(produtoSalvo);
 assertEquals(ProdutoDto.class, produtoSalvo.getClass());
 assertEquals(ID, produtoSalvo.getId());
 verify(repository, times(1)).save(produto);
 }

 @Test
 public void deveriaNaoAtualizarProdutoComDescricaoExistente() {
 when(mapper.converterParaEntidade(produtoDto)).thenReturn(produto);
 when(repository.existsByDescricaoAndIdNot(anyString(),
 anyInt())).thenReturn(true);

 Exception excecao = assertThrows(ProdutoDuplicadoException.class, () -> {
 service.atualizar(produtoDto);
 });

 assertEquals(ProdutoDuplicadoException.class, excecao.getClass());
 assertEquals("Descrição do produto já existe", excecao.getMessage());
 }

 @Test
 public void deveAtualizarProduto() {
 when(mapper.converterParaEntidade(produtoDto)).thenReturn(produto);
 when(mapper.converterParaDto(produto)).thenReturn(produtoDto);
 when(repository.existsByDescricaoAndIdNot(any(), any())).thenReturn(false);
 when(repository.save(any(Produto.class))).thenReturn(produto);

 ProdutoDto produtoAtualizado = service.atualizar(produtoDto);

 assertNotNull(produtoAtualizado);
 verify(repository, times(1)).save(produto);
 assertEquals(ProdutoDto.class, produtoAtualizado.getClass());
 assertEquals(ID, produtoAtualizado.getId());
 }

 @Test
 public void deveriaNaoBuscarPorIdInexistente() {
 when(mapper.converterParaDto(produto)).thenReturn(produtoDto);
 when(repository.findById(anyInt())).thenReturn(Optional.empty());

 Exception excecao = assertThrows(ProdutoNaoEncontradoException.class, () -> {
 service.buscarPorId(ID);
 });

 assertEquals(ProdutoNaoEncontradoException.class, excecao.getClass());
 assertEquals("Produto não encontrado para o id " + ID, excecao.getMessage());
 }

 @Test
 public void deveEncontrarProdutoPorIdExistente() {
 when(mapper.converterParaDto(produto)).thenReturn(produtoDto);
 when(repository.findById(anyInt())).thenReturn(produtoOpcional);

 ProdutoDto produdoAchado = service.buscarPorId(produtoDto.getId());

 assertNotNull(produdoAchado);
 assertEquals(ProdutoDto.class, produdoAchado.getClass());
 assertEquals(ID, produdoAchado.getId());
 }

 @Test
 public void deveriaNaoEncontrarProdutoPorDescricaoInexistente() {
 when(mapper.converterParaDto(produto)).thenReturn(produtoDto);
 when(repository.findByDescricao(any())).thenReturn(Optional.empty());

 Exception excecao = assertThrows(ProdutoNaoEncontradoException.class, () -> {
 service.buscarPorDescricao(DESCRICAO);
 });

 assertEquals(ProdutoNaoEncontradoException.class, excecao.getClass());
 assertEquals("Produto não encontrada para o nome " + DESCRICAO,
 excecao.getMessage());
 }

 @Test
 public void deveEscontrarProdutoPorDescricao() {
 when(mapper.converterParaDto(produto)).thenReturn(produtoDto);
 when(repository.findByDescricao(any())).thenReturn(produtoOpcional);

 ProdutoDto produtoAchado = service.buscarPorDescricao(DESCRICAO);

 assertNotNull(produtoAchado);
 verify(repository, times(1)).findByDescricao(DESCRICAO);
 assertEquals(ProdutoDto.class, produtoAchado.getClass());
 assertEquals(DESCRICAO, produtoAchado.getDescricao());
 }

 @Test
 public void listarTodos() {
 when(mapper.converterParaDto(produto)).thenReturn(produtoDto);
 when(repository.findAll()).thenReturn(List.of(produto, produto));

 List<ProdutoDto> produtos = service.listarTodos();

 assertNotNull(produtos);
 assertEquals(2, produtos.size());
 assertEquals(ProdutoDto.class, produtos.get(0).getClass());
 assertEquals(ID, produtos.get(1).getId());
 verify(repository, times(1)).findAll();
 }

 @Test
 public void deveriaNaoDeletarProdutoIdInexistente() {
 when(repository.findById(anyInt())).thenReturn(Optional.empty());

 Exception excecao = assertThrows(ProdutoNaoEncontradoException.class, () -> {
 service.deletarPorId(ID);
 });

 assertEquals(ProdutoNaoEncontradoException.class, excecao.getClass());
 assertEquals("Produto não encontrado para o id " + ID, excecao.getMessage());
 verify(repository, never()).deleteById(anyInt());
 }

 @Test
 public void deveRemoverProdutoPorId() {
 when(mapper.converterParaDto(produto)).thenReturn(produtoDto);
 when(repository.findById(any())).thenReturn(produtoOpcional);
 doNothing().when(repository).deleteById(ID);
 service.deletarPorId(ID);
 verify(repository, times(1)).deleteById(anyInt());
 }

    private void inicializarProduto() {
        produto = new Produto();
        produto.setId(ID);
        produto.setQuantidadeEstoque(QUANTIDADEESTOQUE);
        produto.setDescricao(DESCRICAO);
        produto.setCustoMedio(CUSTOMEDIO);
        produto.setFabricante(fabricante);
        SimpleDateFormat dataFormadata = new SimpleDateFormat("dd-MM-yyyy");
        try {
            produto.setDataFabricacao(dataFormadata.parse("12-12-2004"));
            produto.setDataValidade(dataFormadata.parse("31-12-2025"));

        } catch (ParseException e) {
            throw new RuntimeException("Erro ao inicializar os dados da data");
        }

        produtoDto = new ProdutoDto();
        produtoDto.setId(ID);
        produtoDto.setQuantidadeEstoque(QUANTIDADEESTOQUE);
        produtoDto.setDescricao(DESCRICAO);
        produtoDto.setCustoMedio(CUSTOMEDIO);
        produtoDto.setFabricante(fabricante);
        produtoDto.setDataFabricacao(produto.getDataFabricacao());
        produtoDto.setDataValidade(produto.getDataValidade());

        produtoOpcional = Optional.of(produto);

    }

}
