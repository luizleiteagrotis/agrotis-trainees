package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.agrotis.trainees.crud.convert.ItemNotaFiscalConversor;
import com.agrotis.trainees.crud.dto.ItemNotaFiscalDto;
import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.ControleEstoqueException;
import com.agrotis.trainees.crud.exception.ItemNotaFiscalExcecao;
import com.agrotis.trainees.crud.repository.ItemNotaFiscalRepository;
import com.agrotis.trainees.crud.repository.ProdutoRepository;

class ItemNotaFiscalServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ProdutoService produtoService;
    @Mock
    private NotaFiscalService notaFiscalService;

    @Mock
    private ControleEstoque controleEstoque;

    @Mock
    private CustoMedioService custoMedioService;
    @Mock
    private ItemNotaFiscalConversor itemNotaFiscalConversor;
    @Mock
    private ItemNotaFiscalRepository itemNotaFiscalRepository;

    @InjectMocks
    private ItemNotaFiscalService itemNotaFiscalService;
    final int ID = 0;
    final NotaFiscal NOTA_FISCAL_ENTRADA = criarNotaFiscalEntrada();
    final NotaFiscal NOTA_FISCAL_SAIDA = criarNotaFiscalSaida();
    final Produto PRODUTO = criarProduto();
    final BigDecimal PRECO_UNITARIO = new BigDecimal(65.90);
    final BigDecimal QUANTIDADE = new BigDecimal(10_000);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    ItemNotaFiscalDto criarItemNotaFiscalDtoEntrada() {
        ItemNotaFiscalDto dto = new ItemNotaFiscalDto(PRODUTO, NOTA_FISCAL_ENTRADA, QUANTIDADE, PRECO_UNITARIO);
        dto.setValorTotal(QUANTIDADE.multiply(PRECO_UNITARIO));
        return dto;
    }

    ItemNotaFiscal criarItemNotaFiscalEntrada() {
        ItemNotaFiscal entidade = new ItemNotaFiscal(NOTA_FISCAL_ENTRADA, PRODUTO, QUANTIDADE, PRECO_UNITARIO);
        entidade.setValorTotal(QUANTIDADE.multiply(PRECO_UNITARIO));
        return entidade;
    }

    ItemNotaFiscalDto criarItemNotaFiscalDtoSaida() {
        ItemNotaFiscalDto dto = new ItemNotaFiscalDto(PRODUTO, NOTA_FISCAL_SAIDA, QUANTIDADE, PRECO_UNITARIO);
        dto.setValorTotal(QUANTIDADE.multiply(PRECO_UNITARIO));
        return dto;
    }

    ItemNotaFiscal criarItemNotaFiscalSaida() {
        ItemNotaFiscal entidade = new ItemNotaFiscal(NOTA_FISCAL_SAIDA, PRODUTO, QUANTIDADE, PRECO_UNITARIO);
        entidade.setValorTotal(QUANTIDADE.multiply(PRECO_UNITARIO));
        return entidade;
    }

    Produto criarProduto() {
        Produto entidade = new Produto();
        entidade.setNome("fertilizante");
        entidade.setDescricao("4-18-8");
        entidade.setFabricante(new ParceiroNegocio());
        entidade.setDataFabricacao(LocalDate.of(2020, 10, 10));
        entidade.setDataValidade(LocalDate.of(2023, 10, 10));
        entidade.setEstoque(BigDecimal.ZERO);
        entidade.setCustoMedio(BigDecimal.ZERO);
        return entidade;
    }

    NotaFiscal criarNotaFiscalEntrada() {
        NotaFiscal entidade = new NotaFiscal("entrada", new ParceiroNegocio(), 101010, LocalDate.of(2021, 10, 10));
        return entidade;
    }

    NotaFiscal criarNotaFiscalSaida() {
        NotaFiscal entidade = new NotaFiscal("saida", new ParceiroNegocio(), 101010, LocalDate.of(2021, 10, 10));
        return entidade;
    }

    @Test
    void verificarSalvarComRetorno() throws ItemNotaFiscalExcecao, ControleEstoqueException {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoEntrada();
        ItemNotaFiscal entidade = criarItemNotaFiscalEntrada();
        when(itemNotaFiscalConversor.converter(dto)).thenReturn(entidade);
        when(notaFiscalService.verificarPorId(ID)).thenReturn(NOTA_FISCAL_ENTRADA);
        when(produtoService.verificarPorId(ID)).thenReturn(PRODUTO);
        when(itemNotaFiscalRepository.findByNotaFiscalAndProduto(NOTA_FISCAL_ENTRADA, PRODUTO)).thenReturn(Collections.emptyList());
        when(controleEstoque.controlarQuantidadeEstoque(entidade)).thenCallRealMethod();
        when(controleEstoque.somarEstoque(entidade.getProduto().getEstoque(), QUANTIDADE)).thenReturn(QUANTIDADE);
        when(notaFiscalService.persistirValorTotal(ID)).thenReturn(new BigDecimal(659_000.00));
        when(itemNotaFiscalConversor.converter(entidade)).thenReturn(dto);
        ItemNotaFiscalDto dtoSalvo = itemNotaFiscalService.salvar(dto);
        assertNotNull(dtoSalvo);
        verify(itemNotaFiscalRepository, times(1)).save(entidade);
        assertEquals(QUANTIDADE, dtoSalvo.getProduto().getEstoque());
    }

    @Test
    void verificarSalvarNotaFiscalTipoSaidaEsperandoCustoMedioZero() throws ItemNotaFiscalExcecao, ControleEstoqueException {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoSaida();
        ItemNotaFiscal entidade = criarItemNotaFiscalSaida();
        dto.getProduto().setEstoque(new BigDecimal(10001));
        entidade.getProduto().setEstoque(new BigDecimal(10001));
        when(itemNotaFiscalConversor.converter(dto)).thenReturn(entidade);
        when(notaFiscalService.verificarPorId(ID)).thenReturn(NOTA_FISCAL_SAIDA);
        when(produtoService.verificarPorId(ID)).thenReturn(PRODUTO);
        when(itemNotaFiscalRepository.findByNotaFiscalAndProduto(NOTA_FISCAL_SAIDA, PRODUTO)).thenReturn(Collections.emptyList());
        when(controleEstoque.controlarQuantidadeEstoque(entidade)).thenReturn(new BigDecimal(1));
        when(notaFiscalService.persistirValorTotal(ID)).thenReturn(new BigDecimal(659_000.00));
        when(itemNotaFiscalConversor.converter(entidade)).thenReturn(dto);
        ItemNotaFiscalDto dtoSalvo = itemNotaFiscalService.salvar(dto);
        assertNotNull(dtoSalvo);
        verify(itemNotaFiscalRepository, times(1)).save(entidade);
        assertEquals(new BigDecimal(1), dtoSalvo.getProduto().getEstoque());
        assertEquals(new BigDecimal(0), dtoSalvo.getProduto().getCustoMedio());
    }

    @Test
    void verificarSalvarComNotaFiscalTipoSaidaComQuantidadeMaiorQueEstoqueEsperandoExcecao()
                    throws ItemNotaFiscalExcecao, ControleEstoqueException {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoSaida();
        ItemNotaFiscal entidade = criarItemNotaFiscalSaida();
        when(itemNotaFiscalConversor.converter(dto)).thenReturn(entidade);
        when(notaFiscalService.verificarPorId(ID)).thenReturn(NOTA_FISCAL_SAIDA);
        when(produtoService.verificarPorId(ID)).thenReturn(PRODUTO);
        when(itemNotaFiscalRepository.findByNotaFiscalAndProduto(NOTA_FISCAL_SAIDA, PRODUTO)).thenReturn(Collections.emptyList());
        when(controleEstoque.controlarQuantidadeEstoque(entidade)).thenCallRealMethod();
        assertThrows(ControleEstoqueException.class, () -> {
            itemNotaFiscalService.salvar(dto);
        });

    }

    @Test
    void verificarSalvarComNotaFiscalNullEsperandoExcecao() throws ItemNotaFiscalExcecao, ControleEstoqueException {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoEntrada();
        ItemNotaFiscal entidade = criarItemNotaFiscalEntrada();
        dto.setNotaFiscal(null);
        entidade.setNotaFiscal(null);
        when(itemNotaFiscalConversor.converter(dto)).thenReturn(entidade);
        when(notaFiscalService.verificarPorId(ID)).thenReturn(NOTA_FISCAL_ENTRADA);
        when(produtoService.verificarPorId(entidade.getProduto().getId())).thenReturn(PRODUTO);
        when(itemNotaFiscalRepository.findByNotaFiscalAndProduto(NOTA_FISCAL_ENTRADA, PRODUTO)).thenReturn(Collections.emptyList());
        when(controleEstoque.controlarQuantidadeEstoque(entidade)).thenCallRealMethod();
        when(itemNotaFiscalConversor.converter(entidade)).thenReturn(dto);
        assertThrows(ItemNotaFiscalExcecao.class, () -> {
            ItemNotaFiscalDto dtoSalvo = itemNotaFiscalService.salvar(dto);
            assertNull(dtoSalvo);
        });
    }

    @Test
    void verificarSalvarComNotaFiscalNaoEncontradoEsperandoExcecao() throws ItemNotaFiscalExcecao, ControleEstoqueException {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoEntrada();
        ItemNotaFiscal entidade = criarItemNotaFiscalEntrada();
        when(itemNotaFiscalConversor.converter(dto)).thenReturn(entidade);
        when(notaFiscalService.verificarPorId(ID)).thenReturn(null);
        when(produtoService.verificarPorId(entidade.getProduto().getId())).thenReturn(PRODUTO);
        when(itemNotaFiscalRepository.findByNotaFiscalAndProduto(NOTA_FISCAL_ENTRADA, PRODUTO)).thenReturn(Collections.emptyList());
        when(controleEstoque.controlarQuantidadeEstoque(entidade)).thenCallRealMethod();
        when(itemNotaFiscalConversor.converter(entidade)).thenReturn(dto);
        assertThrows(ItemNotaFiscalExcecao.class, () -> {
            ItemNotaFiscalDto dtoSalvo = itemNotaFiscalService.salvar(dto);
            assertNull(dtoSalvo);
        });

    }

    @Test
    void verificarSalvarComProdutoNullEsperandoExcecao() throws ItemNotaFiscalExcecao, ControleEstoqueException {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoEntrada();
        ItemNotaFiscal entidade = criarItemNotaFiscalEntrada();
        dto.setProduto(null);
        entidade.setProduto(null);
        when(itemNotaFiscalConversor.converter(dto)).thenReturn(entidade);
        when(notaFiscalService.verificarPorId(ID)).thenReturn(NOTA_FISCAL_ENTRADA);
        when(produtoService.verificarPorId(ID)).thenReturn(PRODUTO);
        when(itemNotaFiscalRepository.findByNotaFiscalAndProduto(NOTA_FISCAL_ENTRADA, PRODUTO)).thenReturn(Collections.emptyList());
        when(controleEstoque.controlarQuantidadeEstoque(entidade)).thenCallRealMethod();
        when(itemNotaFiscalConversor.converter(entidade)).thenReturn(dto);
        assertThrows(ItemNotaFiscalExcecao.class, () -> {
            ItemNotaFiscalDto dtoSalvo = itemNotaFiscalService.salvar(dto);
            assertNull(dtoSalvo);
        });
    }

    @Test
    void verificarSalvarComProdutoNaoExistenteEsperandoExcecao() throws ItemNotaFiscalExcecao, ControleEstoqueException {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoEntrada();
        ItemNotaFiscal entidade = criarItemNotaFiscalEntrada();
        when(itemNotaFiscalConversor.converter(dto)).thenReturn(entidade);
        when(notaFiscalService.verificarPorId(ID)).thenReturn(NOTA_FISCAL_ENTRADA);
        when(produtoService.verificarPorId(ID)).thenReturn(null);
        when(itemNotaFiscalRepository.findByNotaFiscalAndProduto(NOTA_FISCAL_ENTRADA, PRODUTO)).thenReturn(Collections.emptyList());
        when(controleEstoque.controlarQuantidadeEstoque(entidade)).thenCallRealMethod();
        when(itemNotaFiscalConversor.converter(entidade)).thenReturn(dto);
        assertThrows(ItemNotaFiscalExcecao.class, () -> {
            ItemNotaFiscalDto dtoSalvo = itemNotaFiscalService.salvar(dto);
            assertNull(dtoSalvo);
        });
    }

    @Test
    void verificarSalvarComProdutoJaExistenteNestaNotaEsperandoExcecao() throws ItemNotaFiscalExcecao, ControleEstoqueException {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoEntrada();
        ItemNotaFiscal entidade = criarItemNotaFiscalEntrada();
        List<ItemNotaFiscal> itens = new ArrayList<>();
        itens.add(entidade);
        when(itemNotaFiscalConversor.converter(dto)).thenReturn(entidade);
        when(notaFiscalService.verificarPorId(ID)).thenReturn(NOTA_FISCAL_ENTRADA);
        when(produtoService.verificarPorId(ID)).thenReturn(PRODUTO);
        when(itemNotaFiscalRepository.findByNotaFiscalAndProduto(NOTA_FISCAL_ENTRADA, PRODUTO)).thenReturn(itens);
        when(controleEstoque.controlarQuantidadeEstoque(entidade)).thenCallRealMethod();
        when(itemNotaFiscalConversor.converter(entidade)).thenReturn(dto);
        assertThrows(ItemNotaFiscalExcecao.class, () -> {
            ItemNotaFiscalDto dtoSalvo = itemNotaFiscalService.salvar(dto);
            assertNull(dtoSalvo);
        });
    }

    @Test
    void verificarSalvarComPrecoUnitarioNullEsperandoExcecao() throws ItemNotaFiscalExcecao, ControleEstoqueException {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoEntrada();
        ItemNotaFiscal entidade = criarItemNotaFiscalEntrada();
        dto.setPrecoUnitario(null);
        entidade.setPrecoUnitario(null);
        when(itemNotaFiscalConversor.converter(dto)).thenReturn(entidade);
        when(notaFiscalService.verificarPorId(ID)).thenReturn(NOTA_FISCAL_ENTRADA);
        when(produtoService.verificarPorId(ID)).thenReturn(PRODUTO);
        when(itemNotaFiscalRepository.findByNotaFiscalAndProduto(NOTA_FISCAL_ENTRADA, PRODUTO)).thenReturn(Collections.emptyList());
        when(controleEstoque.controlarQuantidadeEstoque(entidade)).thenCallRealMethod();
        when(itemNotaFiscalConversor.converter(entidade)).thenReturn(dto);
        assertThrows(ItemNotaFiscalExcecao.class, () -> {
            ItemNotaFiscalDto dtoSalvo = itemNotaFiscalService.salvar(dto);
            assertNull(dtoSalvo);
        });
    }

    @Test
    void verificarSalvarComPrecoUnitarioNegativoEsperandoExcecao() throws ItemNotaFiscalExcecao, ControleEstoqueException {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoEntrada();
        ItemNotaFiscal entidade = criarItemNotaFiscalEntrada();
        dto.setPrecoUnitario(new BigDecimal(-10));
        entidade.setPrecoUnitario(new BigDecimal(-10));
        when(itemNotaFiscalConversor.converter(dto)).thenReturn(entidade);
        when(notaFiscalService.verificarPorId(ID)).thenReturn(NOTA_FISCAL_ENTRADA);
        when(produtoService.verificarPorId(ID)).thenReturn(PRODUTO);
        when(itemNotaFiscalRepository.findByNotaFiscalAndProduto(NOTA_FISCAL_ENTRADA, PRODUTO)).thenReturn(Collections.emptyList());
        when(controleEstoque.controlarQuantidadeEstoque(entidade)).thenCallRealMethod();
        when(itemNotaFiscalConversor.converter(entidade)).thenReturn(dto);
        assertThrows(ItemNotaFiscalExcecao.class, () -> {
            ItemNotaFiscalDto dtoSalvo = itemNotaFiscalService.salvar(dto);
            assertNull(dtoSalvo);
        });
    }

    @Test
    void verificarSalvarComPrecoUnitarioZeradoEsperandoExcecao() throws ItemNotaFiscalExcecao, ControleEstoqueException {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoEntrada();
        ItemNotaFiscal entidade = criarItemNotaFiscalEntrada();
        dto.setPrecoUnitario(new BigDecimal(0));
        entidade.setPrecoUnitario(new BigDecimal(0));
        when(itemNotaFiscalConversor.converter(dto)).thenReturn(entidade);
        when(notaFiscalService.verificarPorId(ID)).thenReturn(NOTA_FISCAL_ENTRADA);
        when(produtoService.verificarPorId(ID)).thenReturn(PRODUTO);
        when(itemNotaFiscalRepository.findByNotaFiscalAndProduto(NOTA_FISCAL_ENTRADA, PRODUTO)).thenReturn(Collections.emptyList());
        when(controleEstoque.controlarQuantidadeEstoque(entidade)).thenCallRealMethod();
        when(itemNotaFiscalConversor.converter(entidade)).thenReturn(dto);
        assertThrows(ItemNotaFiscalExcecao.class, () -> {
            ItemNotaFiscalDto dtoSalvo = itemNotaFiscalService.salvar(dto);
            assertNull(dtoSalvo);
        });
    }

    @Test
    void verificarSalvarComQuantidadeNullEsperandoExcecao() throws ItemNotaFiscalExcecao, ControleEstoqueException {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoEntrada();
        ItemNotaFiscal entidade = criarItemNotaFiscalEntrada();
        dto.setQuantidade(null);
        entidade.setQuantidade(null);
        when(itemNotaFiscalConversor.converter(dto)).thenReturn(entidade);
        when(notaFiscalService.verificarPorId(ID)).thenReturn(NOTA_FISCAL_ENTRADA);
        when(produtoService.verificarPorId(ID)).thenReturn(PRODUTO);
        when(itemNotaFiscalRepository.findByNotaFiscalAndProduto(NOTA_FISCAL_ENTRADA, PRODUTO)).thenReturn(Collections.emptyList());
        when(controleEstoque.controlarQuantidadeEstoque(entidade)).thenCallRealMethod();
        when(itemNotaFiscalConversor.converter(entidade)).thenReturn(dto);
        assertThrows(ItemNotaFiscalExcecao.class, () -> {
            ItemNotaFiscalDto dtoSalvo = itemNotaFiscalService.salvar(dto);
            assertNull(dtoSalvo);
        });
    }

    @Test
    void verificarSalvarComQuantidadeNegativaEsperandoExcecao() throws ItemNotaFiscalExcecao, ControleEstoqueException {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoEntrada();
        ItemNotaFiscal entidade = criarItemNotaFiscalEntrada();
        dto.setQuantidade(new BigDecimal(-10));
        entidade.setQuantidade(new BigDecimal(-10));
        when(itemNotaFiscalConversor.converter(dto)).thenReturn(entidade);
        when(notaFiscalService.verificarPorId(ID)).thenReturn(NOTA_FISCAL_ENTRADA);
        when(produtoService.verificarPorId(ID)).thenReturn(PRODUTO);
        when(itemNotaFiscalRepository.findByNotaFiscalAndProduto(NOTA_FISCAL_ENTRADA, PRODUTO)).thenReturn(Collections.emptyList());
        when(controleEstoque.controlarQuantidadeEstoque(entidade)).thenCallRealMethod();
        when(itemNotaFiscalConversor.converter(entidade)).thenReturn(dto);
        assertThrows(ItemNotaFiscalExcecao.class, () -> {
            ItemNotaFiscalDto dtoSalvo = itemNotaFiscalService.salvar(dto);
            assertNull(dtoSalvo);
        });
    }

    @Test
    void verificarSalvarComQuantidadeZeradaEsperandoExcecao() throws ItemNotaFiscalExcecao, ControleEstoqueException {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoEntrada();
        ItemNotaFiscal entidade = criarItemNotaFiscalEntrada();
        dto.setQuantidade(new BigDecimal(0));
        entidade.setQuantidade(new BigDecimal(0));
        when(itemNotaFiscalConversor.converter(dto)).thenReturn(entidade);
        when(notaFiscalService.verificarPorId(ID)).thenReturn(NOTA_FISCAL_ENTRADA);
        when(produtoService.verificarPorId(ID)).thenReturn(PRODUTO);
        when(itemNotaFiscalRepository.findByNotaFiscalAndProduto(NOTA_FISCAL_ENTRADA, PRODUTO)).thenReturn(Collections.emptyList());
        when(controleEstoque.controlarQuantidadeEstoque(entidade)).thenCallRealMethod();
        when(itemNotaFiscalConversor.converter(entidade)).thenReturn(dto);
        assertThrows(ItemNotaFiscalExcecao.class, () -> {
            ItemNotaFiscalDto dtoSalvo = itemNotaFiscalService.salvar(dto);
            assertNull(dtoSalvo);
        });
    }

    @Test
    void buscarPorIdEsperandoRetorno() {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoSaida();
        ItemNotaFiscal entidade = criarItemNotaFiscalSaida();
        when(itemNotaFiscalRepository.findById(ID)).thenReturn(Optional.of(entidade));
        when(itemNotaFiscalConversor.converter(entidade)).thenReturn(dto);
        ItemNotaFiscalDto dtoEncontrado = itemNotaFiscalService.buscarPorId(ID);
        assertNotNull(dtoEncontrado);
    }

    @Test
    void buscarPorIdSemRetorno() {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoSaida();
        ItemNotaFiscal entidade = criarItemNotaFiscalSaida();
        when(itemNotaFiscalRepository.findById(ID)).thenReturn(Optional.empty());
        ItemNotaFiscalDto dtoEncontrado = itemNotaFiscalService.buscarPorId(ID);
        assertNull(dtoEncontrado);
    }

    @Test
    void buscarPorIdComIdNull() {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoSaida();
        ItemNotaFiscal entidade = criarItemNotaFiscalSaida();
        when(itemNotaFiscalRepository.findById(null)).thenReturn(Optional.of(entidade));
        when(itemNotaFiscalConversor.converter(entidade)).thenReturn(dto);
        ItemNotaFiscalDto dtoEncontrado = itemNotaFiscalService.buscarPorId(ID);
        assertNull(dtoEncontrado);
    }

    @Test
    void buscarPorQuantidadeComRetorno() {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoSaida();
        ItemNotaFiscal entidade = criarItemNotaFiscalSaida();
        List<ItemNotaFiscalDto> listaDto = new ArrayList<>();
        List<ItemNotaFiscal> listaEntidade = new ArrayList<>();
        listaDto.add(dto);
        listaEntidade.add(entidade);
        when(itemNotaFiscalRepository.findByQuantidade(QUANTIDADE)).thenReturn(listaEntidade);
        when(itemNotaFiscalConversor.converter(listaEntidade)).thenReturn(listaDto);
        List<ItemNotaFiscalDto> listaRetorno = itemNotaFiscalService.buscarPorQuantidade(QUANTIDADE);
        assertEquals(1, listaRetorno.size());
    }

    @Test
    void buscarPorQuantidadeSemRetorno() {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoSaida();
        ItemNotaFiscal entidade = criarItemNotaFiscalSaida();
        List<ItemNotaFiscalDto> listaDto = new ArrayList<>();
        List<ItemNotaFiscal> listaEntidade = new ArrayList<>();
        when(itemNotaFiscalRepository.findByQuantidade(QUANTIDADE)).thenReturn(listaEntidade);
        when(itemNotaFiscalConversor.converter(listaEntidade)).thenReturn(listaDto);
        List<ItemNotaFiscalDto> listaRetorno = itemNotaFiscalService.buscarPorQuantidade(QUANTIDADE);
        assertEquals(0, listaRetorno.size());
    }

    @Test
    void buscarPorQuantidadeComQuantidadeNull() {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoSaida();
        ItemNotaFiscal entidade = criarItemNotaFiscalSaida();
        List<ItemNotaFiscalDto> listaDto = new ArrayList<>();
        List<ItemNotaFiscal> listaEntidade = new ArrayList<>();
        when(itemNotaFiscalRepository.findByQuantidade(null)).thenReturn(listaEntidade);
        when(itemNotaFiscalConversor.converter(listaEntidade)).thenReturn(listaDto);
        List<ItemNotaFiscalDto> listaRetorno = itemNotaFiscalService.buscarPorQuantidade(QUANTIDADE);
        assertEquals(0, listaRetorno.size());
    }

    @Test
    void buscarPorPrecoComRetorno() {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoSaida();
        ItemNotaFiscal entidade = criarItemNotaFiscalSaida();
        List<ItemNotaFiscalDto> listaDto = new ArrayList<>();
        List<ItemNotaFiscal> listaEntidade = new ArrayList<>();
        listaDto.add(dto);
        listaEntidade.add(entidade);
        when(itemNotaFiscalRepository.findByPrecoUnitario(PRECO_UNITARIO)).thenReturn(listaEntidade);
        when(itemNotaFiscalConversor.converter(listaEntidade)).thenReturn(listaDto);
        List<ItemNotaFiscalDto> listaRetorno = itemNotaFiscalService.buscarPorPreco(PRECO_UNITARIO);
        assertEquals(1, listaRetorno.size());
    }

    @Test
    void buscarPorPrecoSemRetorno() {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoSaida();
        ItemNotaFiscal entidade = criarItemNotaFiscalSaida();
        List<ItemNotaFiscalDto> listaDto = new ArrayList<>();
        List<ItemNotaFiscal> listaEntidade = new ArrayList<>();
        listaDto.add(dto);
        listaEntidade.add(entidade);
        when(itemNotaFiscalRepository.findByPrecoUnitario(new BigDecimal(5))).thenReturn(listaEntidade);
        when(itemNotaFiscalConversor.converter(listaEntidade)).thenReturn(listaDto);
        List<ItemNotaFiscalDto> listaRetorno = itemNotaFiscalService.buscarPorPreco(PRECO_UNITARIO);
        assertEquals(0, listaRetorno.size());
    }

    @Test
    void buscarPorPrecoComValorNullDeParametro() {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoSaida();
        ItemNotaFiscal entidade = criarItemNotaFiscalSaida();
        List<ItemNotaFiscalDto> listaDto = new ArrayList<>();
        List<ItemNotaFiscal> listaEntidade = new ArrayList<>();
        listaDto.add(dto);
        listaEntidade.add(entidade);
        when(itemNotaFiscalRepository.findByPrecoUnitario(null)).thenReturn(listaEntidade);
        when(itemNotaFiscalConversor.converter(listaEntidade)).thenReturn(listaDto);
        List<ItemNotaFiscalDto> listaRetorno = itemNotaFiscalService.buscarPorPreco(PRECO_UNITARIO);
        assertEquals(0, listaRetorno.size());
    }

    @Test
    void buscarPorValorTotalComRetorno() {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoSaida();
        ItemNotaFiscal entidade = criarItemNotaFiscalSaida();
        List<ItemNotaFiscalDto> listaDto = new ArrayList<>();
        List<ItemNotaFiscal> listaEntidade = new ArrayList<>();
        listaDto.add(dto);
        listaEntidade.add(entidade);
        when(itemNotaFiscalRepository.findByValorTotal(entidade.getValorTotal())).thenReturn(listaEntidade);
        when(itemNotaFiscalConversor.converter(listaEntidade)).thenReturn(listaDto);
        List<ItemNotaFiscalDto> listaRetorno = itemNotaFiscalService.buscarPorValorTotal(entidade.getValorTotal());
        assertEquals(1, listaRetorno.size());
    }

    @Test
    void buscarPorValorTotalSemRetorno() {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoSaida();
        ItemNotaFiscal entidade = criarItemNotaFiscalSaida();
        List<ItemNotaFiscalDto> listaDto = new ArrayList<>();
        List<ItemNotaFiscal> listaEntidade = new ArrayList<>();
        listaDto.add(dto);
        listaEntidade.add(entidade);
        when(itemNotaFiscalRepository.findByValorTotal(new BigDecimal(1))).thenReturn(listaEntidade);
        when(itemNotaFiscalConversor.converter(listaEntidade)).thenReturn(listaDto);
        List<ItemNotaFiscalDto> listaRetorno = itemNotaFiscalService.buscarPorValorTotal(entidade.getValorTotal());
        assertEquals(0, listaRetorno.size());
    }

    @Test
    void buscarPorValorTotalNullDeParametro() {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoSaida();
        ItemNotaFiscal entidade = criarItemNotaFiscalSaida();
        List<ItemNotaFiscalDto> listaDto = new ArrayList<>();
        List<ItemNotaFiscal> listaEntidade = new ArrayList<>();
        listaDto.add(dto);
        listaEntidade.add(entidade);
        when(itemNotaFiscalRepository.findByValorTotal(null)).thenReturn(listaEntidade);
        when(itemNotaFiscalConversor.converter(listaEntidade)).thenReturn(listaDto);
        List<ItemNotaFiscalDto> listaRetorno = itemNotaFiscalService.buscarPorValorTotal(entidade.getValorTotal());
        assertEquals(0, listaRetorno.size());
    }

    @Test
    void listarTodosComRetornoDeUmObjeto() {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoSaida();
        ItemNotaFiscal entidade = criarItemNotaFiscalSaida();
        List<ItemNotaFiscalDto> listaDto = new ArrayList<>();
        List<ItemNotaFiscal> listaEntidade = new ArrayList<>();
        listaDto.add(dto);
        listaEntidade.add(entidade);
        when(itemNotaFiscalRepository.findAll()).thenReturn(listaEntidade);
        when(itemNotaFiscalConversor.converter(listaEntidade)).thenReturn(listaDto);
        List<ItemNotaFiscalDto> listaRetorno = itemNotaFiscalService.listarTodos();
        assertEquals(1, listaRetorno.size());

    }

    @Test
    void listarTodosSemRetornoDeUmObjeto() {
        List<ItemNotaFiscalDto> listaDto = new ArrayList<>();
        List<ItemNotaFiscal> listaEntidade = new ArrayList<>();
        when(itemNotaFiscalRepository.findAll()).thenReturn(listaEntidade);
        when(itemNotaFiscalConversor.converter(listaEntidade)).thenReturn(listaDto);
        List<ItemNotaFiscalDto> listaRetorno = itemNotaFiscalService.listarTodos();
        assertEquals(0, listaRetorno.size());
    }

    @Test
    void deletarItemDeUmaNotaDeSaida() {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoSaida();
        ItemNotaFiscal entidade = criarItemNotaFiscalSaida();
        dto.getProduto().setEstoque(new BigDecimal(10001));
        entidade.getProduto().setEstoque(new BigDecimal(10001));
        when(itemNotaFiscalRepository.findById(ID)).thenReturn(Optional.of(entidade));
        when(produtoService.verificarPorId(ID)).thenReturn(entidade.getProduto());
        when(controleEstoque.somarEstoque(entidade.getProduto().getEstoque(), QUANTIDADE)).thenReturn(new BigDecimal(20001));
        itemNotaFiscalService.deletarPorId(ID);
        assertEquals(new BigDecimal(20001), entidade.getProduto().getEstoque());
        verify(itemNotaFiscalRepository, times(1)).deleteById(ID);

    }

    @Test
    void deletarItemDeUmaNotaDeEntrada() {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoEntrada();
        ItemNotaFiscal entidade = criarItemNotaFiscalEntrada();
        dto.getProduto().setEstoque(new BigDecimal(10001));
        entidade.getProduto().setEstoque(new BigDecimal(10001));
        when(itemNotaFiscalRepository.findById(ID)).thenReturn(Optional.of(entidade));
        when(produtoService.verificarPorId(ID)).thenReturn(entidade.getProduto());
        when(controleEstoque.diminuirEstoque(entidade.getProduto().getEstoque(), QUANTIDADE)).thenReturn(new BigDecimal(1));
        itemNotaFiscalService.deletarPorId(ID);
        assertEquals(new BigDecimal(1), entidade.getProduto().getEstoque());
        verify(itemNotaFiscalRepository, times(1)).deleteById(ID);

    }

    @Test
    void testarAtualizarTodosOsCampos() throws ItemNotaFiscalExcecao, ControleEstoqueException {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoSaida();
        ItemNotaFiscal entidade = criarItemNotaFiscalSaida();
        ItemNotaFiscal novaEntidade = new ItemNotaFiscal(NOTA_FISCAL_ENTRADA, new Produto(), new BigDecimal(10),
                        new BigDecimal(10));
        when(itemNotaFiscalConversor.converter(dto)).thenReturn(novaEntidade);
        when(itemNotaFiscalRepository.findById(ID)).thenReturn(Optional.of(entidade));
        when(notaFiscalService.verificarPorId(ID)).thenReturn(NOTA_FISCAL_ENTRADA);
        when(produtoService.verificarPorId(ID)).thenReturn(novaEntidade.getProduto());
        when(controleEstoque.atualizarEstoque(entidade, novaEntidade)).thenReturn(entidade);
        itemNotaFiscalService.atualizar(dto, ID);
        assertEquals(novaEntidade.getNotaFiscal(), entidade.getNotaFiscal());
        assertEquals(novaEntidade.getProduto(), entidade.getProduto());
        assertEquals(novaEntidade.getPrecoUnitario(), entidade.getPrecoUnitario());
        assertEquals(novaEntidade.getQuantidade(), entidade.getQuantidade());

        verify(itemNotaFiscalRepository, times(1)).save(any(ItemNotaFiscal.class));
    }

    @Test
    void atualizarApenasNotaFiscalDoItem() throws ItemNotaFiscalExcecao, ControleEstoqueException {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoSaida();
        ItemNotaFiscal entidade = criarItemNotaFiscalSaida();
        ItemNotaFiscal novaEntidade = new ItemNotaFiscal();
        novaEntidade.setNotaFiscal(NOTA_FISCAL_ENTRADA);
        when(itemNotaFiscalConversor.converter(dto)).thenReturn(novaEntidade);
        when(itemNotaFiscalRepository.findById(ID)).thenReturn(Optional.of(entidade));
        when(notaFiscalService.verificarPorId(ID)).thenReturn(NOTA_FISCAL_ENTRADA);
        when(produtoService.verificarPorId(ID)).thenReturn(novaEntidade.getProduto());
        when(controleEstoque.atualizarEstoque(entidade, novaEntidade)).thenReturn(entidade);
        itemNotaFiscalService.atualizar(dto, ID);
        assertEquals(novaEntidade.getNotaFiscal(), entidade.getNotaFiscal());

        verify(itemNotaFiscalRepository, times(1)).save(any(ItemNotaFiscal.class));
    }

    @Test
    void atualizarItemNotaComUmaNotaFiscalInexistente() throws ItemNotaFiscalExcecao, ControleEstoqueException {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoSaida();
        ItemNotaFiscal entidade = criarItemNotaFiscalSaida();
        ItemNotaFiscal novaEntidade = new ItemNotaFiscal();
        novaEntidade.setNotaFiscal(NOTA_FISCAL_ENTRADA);
        when(itemNotaFiscalConversor.converter(dto)).thenReturn(novaEntidade);
        when(itemNotaFiscalRepository.findById(ID)).thenReturn(Optional.of(entidade));
        when(notaFiscalService.verificarPorId(ID)).thenReturn(null);
        when(produtoService.verificarPorId(ID)).thenReturn(novaEntidade.getProduto());
        when(controleEstoque.atualizarEstoque(entidade, novaEntidade)).thenReturn(entidade);
        assertThrows(ItemNotaFiscalExcecao.class, () -> {
            itemNotaFiscalService.atualizar(dto, ID);

        });

        verify(itemNotaFiscalRepository, times(0)).save(any(ItemNotaFiscal.class));
    }

    @Test
    void atualizarApenasProdutoDoItem() throws ItemNotaFiscalExcecao, ControleEstoqueException {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoSaida();
        ItemNotaFiscal entidade = criarItemNotaFiscalSaida();
        ItemNotaFiscal novaEntidade = new ItemNotaFiscal();
        novaEntidade.setProduto(new Produto());
        when(itemNotaFiscalConversor.converter(dto)).thenReturn(novaEntidade);
        when(itemNotaFiscalRepository.findById(ID)).thenReturn(Optional.of(entidade));
        when(notaFiscalService.verificarPorId(ID)).thenReturn(NOTA_FISCAL_SAIDA);
        when(produtoService.verificarPorId(ID)).thenReturn(novaEntidade.getProduto());
        itemNotaFiscalService.atualizar(dto, ID);
        assertEquals(novaEntidade.getProduto(), entidade.getProduto());

        verify(itemNotaFiscalRepository, times(1)).save(any(ItemNotaFiscal.class));
    }

    @Test
    void atualizarProdutoDoItemComUmInvalido() throws ItemNotaFiscalExcecao, ControleEstoqueException {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoSaida();
        ItemNotaFiscal entidade = criarItemNotaFiscalSaida();
        ItemNotaFiscal novaEntidade = new ItemNotaFiscal();
        novaEntidade.setProduto(new Produto());
        when(itemNotaFiscalConversor.converter(dto)).thenReturn(novaEntidade);
        when(itemNotaFiscalRepository.findById(ID)).thenReturn(Optional.of(entidade));
        when(notaFiscalService.verificarPorId(ID)).thenReturn(NOTA_FISCAL_SAIDA);
        when(produtoService.verificarPorId(ID)).thenReturn(null);
        assertThrows(ItemNotaFiscalExcecao.class, () -> {
            itemNotaFiscalService.atualizar(dto, ID);

        });

        verify(itemNotaFiscalRepository, times(0)).save(any(ItemNotaFiscal.class));
    }

    @Test
    void atualizarApenasQuantidade() throws ItemNotaFiscalExcecao, ControleEstoqueException {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoSaida();
        ItemNotaFiscal entidade = criarItemNotaFiscalSaida();
        ItemNotaFiscal novaEntidade = new ItemNotaFiscal();
        novaEntidade.setQuantidade(new BigDecimal(13198));
        when(itemNotaFiscalConversor.converter(dto)).thenReturn(novaEntidade);
        when(itemNotaFiscalRepository.findById(ID)).thenReturn(Optional.of(entidade));
        when(notaFiscalService.verificarPorId(ID)).thenReturn(NOTA_FISCAL_SAIDA);
        when(produtoService.verificarPorId(ID)).thenReturn(novaEntidade.getProduto());
        itemNotaFiscalService.atualizar(dto, ID);
        assertEquals(novaEntidade.getQuantidade(), entidade.getQuantidade());

        verify(itemNotaFiscalRepository, times(1)).save(any(ItemNotaFiscal.class));
    }

    @Test
    void atualizarApenasQuantidadeComUmaQuantidadeNegativaNaoPermitindo() throws ItemNotaFiscalExcecao, ControleEstoqueException {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoSaida();
        ItemNotaFiscal entidade = criarItemNotaFiscalSaida();
        ItemNotaFiscal novaEntidade = new ItemNotaFiscal();
        novaEntidade.setQuantidade(new BigDecimal(-13198));
        when(itemNotaFiscalConversor.converter(dto)).thenReturn(novaEntidade);
        when(itemNotaFiscalRepository.findById(ID)).thenReturn(Optional.of(entidade));
        when(notaFiscalService.verificarPorId(ID)).thenReturn(NOTA_FISCAL_SAIDA);
        when(produtoService.verificarPorId(ID)).thenReturn(novaEntidade.getProduto());
        itemNotaFiscalService.atualizar(dto, ID);
        assertNotEquals(novaEntidade.getQuantidade(), entidade.getQuantidade());

        verify(itemNotaFiscalRepository, times(1)).save(any(ItemNotaFiscal.class));
    }

    @Test
    void atualizarApenasPrecoUnitairoComUmValorNegativoNaoPermitindo() throws ItemNotaFiscalExcecao, ControleEstoqueException {
        ItemNotaFiscalDto dto = criarItemNotaFiscalDtoSaida();
        ItemNotaFiscal entidade = criarItemNotaFiscalSaida();
        ItemNotaFiscal novaEntidade = new ItemNotaFiscal();
        novaEntidade.setPrecoUnitario(new BigDecimal(-13198));
        when(itemNotaFiscalConversor.converter(dto)).thenReturn(novaEntidade);
        when(itemNotaFiscalRepository.findById(ID)).thenReturn(Optional.of(entidade));
        when(notaFiscalService.verificarPorId(ID)).thenReturn(NOTA_FISCAL_SAIDA);
        when(produtoService.verificarPorId(ID)).thenReturn(novaEntidade.getProduto());
        assertThrows(ItemNotaFiscalExcecao.class, () -> {
            itemNotaFiscalService.atualizar(dto, ID);

        });

        verify(itemNotaFiscalRepository, times(0)).save(any(ItemNotaFiscal.class));
    }

    @Test
    void deletarUmRegistroInexistente() {
        when(itemNotaFiscalRepository.findById(ID)).thenReturn(Optional.empty());
        itemNotaFiscalService.deletarPorId(ID);
        verify(itemNotaFiscalRepository, times(0)).deleteById(ID);
    }

    @Test
    void deletarUmRegistroComRetornoNullDoFindById() {
        when(itemNotaFiscalService.verificarPorId(1)).thenReturn(null);
        itemNotaFiscalService.deletarPorId(ID);
        verify(itemNotaFiscalRepository, times(0)).deleteById(ID);
    }

}
