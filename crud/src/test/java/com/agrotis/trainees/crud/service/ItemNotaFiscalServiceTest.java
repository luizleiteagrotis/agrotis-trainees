package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.agrotis.trainees.crud.dto.ItemNotaFiscalDto;
import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.ItemDuplicadoException;
import com.agrotis.trainees.crud.exception.ItemNaoEncontrado;
import com.agrotis.trainees.crud.repository.ItemNotaFiscalRepository;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;
import com.agrotis.trainees.crud.repository.ProdutoRepository;
import com.agrotis.trainees.crud.utils.ItemNotaFiscalDTOMapper;
import com.agrotis.trainees.crud.utils.NotaFiscalDTOMapper;
import com.agrotis.trainees.crud.utils.ProdutoDTOMapper;

public class ItemNotaFiscalServiceTest {

    private final Integer ID = 1;
    private final int QUANTIDADE = 500;
    private final BigDecimal VALORUNITARIO = new BigDecimal(70);

    public static final Produto PRODUTO_FIXO;

    static {
        PRODUTO_FIXO = new Produto();
        PRODUTO_FIXO.setId(746);
        PRODUTO_FIXO.setDescricao("Fertilizante 4-14-8");
        PRODUTO_FIXO.setDataFabricacao(new Date());
        PRODUTO_FIXO.setDataValidade(new Date());
        PRODUTO_FIXO.setFabricante(new ParceiroNegocio());
    }

    public static final NotaFiscal NOTAFISCAL;

    static {
        NOTAFISCAL = new NotaFiscal();
        NOTAFISCAL.setId(2);
        NOTAFISCAL.setData(LocalDate.now());
        NOTAFISCAL.setNumero("12345");
        NOTAFISCAL.setParceiroNegocio(new ParceiroNegocio());
        NOTAFISCAL.setNotaFiscalTipo(new NotaFiscalTipo());
    }

    private final String ENTRADA = "ENTRADA";
    private final String SAIDA = "SAÍDA";

    @Mock
    private ItemNotaFiscalRepository repository;
    @Mock
    private ItemNotaFiscalDTOMapper mapper;

    @Mock
    private ProdutoRepository produtoRepository;
    @Mock
    private ProdutoService produtoService;
    @Mock
    private ProdutoDTOMapper mapperProduto;
    @Mock
    private ProdutoDto produtoDto;
    @Mock
    private Produto produto;

    @Mock
    private NotaFiscal notaFiscal;
    @Mock
    private NotaFiscalDto notaFiscalDto;
    @Mock
    private NotaFiscalRepository notaFiscalRepository;
    @Mock
    private NotaFiscalService notaFiscalService;
    @Mock
    private NotaFiscalDTOMapper mapperNotaFiscal;
    @Mock
    private CalcularListaItemService calcularListaItemService;
    @Mock
    private CalcularEstoqueService calcularEstoqueService;
    @Mock
    private NotaFiscalTipo notaFiscalTipo;
    @InjectMocks
    private ItemNotaFiscalService service;

    private ItemNotaFiscal itemNotaFiscal;

    private ItemNotaFiscalDto itemNotaFiscalDto;

    private Optional<ItemNotaFiscal> itemNotaFiscalOpcional;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        inicializarItem();
    }

    @Test
    public void deveriaNaoSalvarQuandoJaExisteItemNaNota() {
        when(repository.existsByProdutoAndNotaFiscal(any(), any())).thenReturn(true);


        Exception excecao = assertThrows(ItemDuplicadoException.class, () -> {
            service.salvar(itemNotaFiscalDto);
        });
        
        assertEquals(ItemDuplicadoException.class, excecao.getClass());
        assertEquals("O item já existe na nota", excecao.getMessage());
    }

    @Test
    public void deveSalvarItemNaNotaSaida() {
        itemNotaFiscal.getNotaFiscal().getNotaFiscalTipo().setNome(SAIDA);
        when(repository.existsByProdutoAndNotaFiscal(any(), any())).thenReturn(false);
        when(repository.save(any())).thenReturn(itemNotaFiscal);

        ItemNotaFiscalDto itemSalvo = service.salvar(itemNotaFiscalDto);

        assertNotNull(itemSalvo);
        assertEquals(ItemNotaFiscalDto.class, itemSalvo.getClass());
        assertEquals(ID, itemSalvo.getId());
        assertEquals(SAIDA, itemSalvo.getNotaFiscal().getNotaFiscalTipo().getNome());
        verify(mapper, times(1)).convertarParaEntidade(itemNotaFiscalDto);
        verify(mapperProduto, times(1)).converterParaEntidade(produtoDto);
        verify(mapperNotaFiscal, times(1)).converterParaEntidade(notaFiscalDto);
        verify(repository, times(1)).save(itemNotaFiscal);
        verify(calcularListaItemService, times(1)).adicionarItem(itemNotaFiscal);
        verify(calcularEstoqueService, times(1)).atualizarEstoque(itemNotaFiscal);
    }

    @Test
     public void deveSalvarItemNaNotaEntrada() {
     when(repository.existsByProdutoAndNotaFiscal(any(), any())).thenReturn(false);    
     when(repository.save(any())).thenReturn(itemNotaFiscal);
    
     ItemNotaFiscalDto itemSalvo = service.salvar(itemNotaFiscalDto);
    
     assertNotNull(itemSalvo);
     assertEquals(ItemNotaFiscalDto.class, itemSalvo.getClass());
     assertEquals(ID, itemSalvo.getId());
     assertEquals(ENTRADA, itemSalvo.getNotaFiscal().getNotaFiscalTipo().getNome());
     verify(mapper, times(1)).convertarParaEntidade(itemNotaFiscalDto);
     verify(mapperProduto, times(1)).converterParaEntidade(produtoDto);
     verify(mapperNotaFiscal, times(1)).converterParaEntidade(notaFiscalDto);
     verify(repository, times(1)).save(itemNotaFiscal);
     verify(calcularListaItemService, times(1)).adicionarItem(itemNotaFiscal);
     verify(calcularEstoqueService, times(1)).atualizarEstoque(itemNotaFiscal);
     }

    @Test
     public void naoDeveriaAtualizarItemExistente() {
     when(repository.existsByProdutoAndNotaFiscalAndIdNot(any(), any(),
     any())).thenReturn(true);    
    
     Exception excecao = assertThrows(ItemDuplicadoException.class, () -> {
     service.atualizar(itemNotaFiscalDto);
     });
    
     assertEquals("Já existe um item de nota fiscal com o mesmo produto e nota fiscal", excecao.getMessage());
     
    }

    @Test
     public void deveAtualizarItem() {
     when(repository.existsByProdutoAndNotaFiscalAndIdNot(any(),
     any(), anyInt())).thenReturn(false);
     when(repository.save(any())).thenReturn(itemNotaFiscal);
    
     ItemNotaFiscalDto itemAtualizado = service.atualizar(itemNotaFiscalDto);
     
     assertNotNull(itemAtualizado);
     assertEquals(ItemNotaFiscalDto.class, itemAtualizado.getClass());
     assertEquals(ID, itemAtualizado.getId());
     verify(mapper, times(1)).convertarParaEntidade(itemNotaFiscalDto);
     verify(mapperProduto, times(1)).converterParaEntidade(produtoDto);
     verify(mapperNotaFiscal, times(1)).converterParaEntidade(notaFiscalDto);
     verify(calcularEstoqueService, times(1)).atualizarEstoque(itemNotaFiscal);
     verify(repository, times(1)).save(itemNotaFiscal);
     verify(calcularListaItemService,     times(1)).atualizarValorTotal(notaFiscal);    
     }

    @Test
     public void
     deveAumentarCustoMedioAoInserirItemDeValorUnitarioMaiorQueCustoMedio() {
     when(repository.existsByProdutoAndNotaFiscal(any(), any())).thenReturn(false);
     when(repository.save(any())).thenReturn(itemNotaFiscal);
    
     ItemNotaFiscalDto itemSalvo = service.salvar(itemNotaFiscalDto);
    
     assertTrue(itemSalvo.getProduto().getCustoMedio().equals(BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_UP)));
     }

    @Test
    public void deveReduzirCustoMedioAoInserirItemDeValorUnitarioMenorQueCustoMedio() {
        itemNotaFiscal.setQuantidade(10);
        itemNotaFiscal.setValorUnitario(BigDecimal.valueOf(1));
        produto.setQuantidadeEstoque(10);
        produto.setCustoMedio(BigDecimal.valueOf(2));
        produto.setDescricao("Banana");
        itemNotaFiscal.setProduto(produto);
        when(repository.existsByProdutoAndNotaFiscal(any(), any())).thenReturn(false);

        itemNotaFiscalDto.setQuantidade(itemNotaFiscal.getQuantidade());
        itemNotaFiscalDto.setValorUnitario(itemNotaFiscal.getValorUnitario());
        itemNotaFiscalDto.setProduto(itemNotaFiscal.getProduto());
        when(repository.save(any())).thenReturn(itemNotaFiscal);

        ItemNotaFiscalDto itemSalvo = service.salvar(itemNotaFiscalDto);

        assertTrue(itemSalvo.getProduto().getCustoMedio().equals(BigDecimal.valueOf(1.50).setScale(2, RoundingMode.HALF_UP)));
    }

    @Test
    public void naoDeveriaBuscarPorIdInexistente() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        
        Exception excecao = assertThrows(ItemNaoEncontrado.class, () -> {
            service.buscarPorId(ID);
        });
            
        assertEquals(ItemNaoEncontrado.class, excecao.getClass());
        assertEquals("Item de nota fiscal não encontrado para o id " + ID, excecao.getMessage());    }

    @Test
    public void deveBuscarPorId() {
        when(repository.findById(anyInt())).thenReturn(itemNotaFiscalOpcional);

        ItemNotaFiscalDto itemAchado = service.buscarPorId(ID);

        assertNotNull(itemAchado);
        assertEquals(ItemNotaFiscalDto.class, itemAchado.getClass());
        assertEquals(ID, itemAchado.getId());
    }

    @Test
    public void naoDeveriaBuscarPorProdutoInexistente() {
        when(repository.findAllByProduto(any())).thenReturn(new ArrayList<>());

        Exception excecao = assertThrows(ItemNaoEncontrado.class, () -> {
            service.buscarPorProduto(produto.getId());
        });
        
        assertEquals(ItemNaoEncontrado.class, excecao.getClass());
        assertEquals("Item de nota fiscal não encontrada para o produto de nome: " + produto.getDescricao() + " e id: "
                        + produto.getId(), excecao.getMessage());
    }

    @Test
    public void deveBuscarPorProduto() {
        when(repository.findAllByProduto(produto)).thenReturn(List.of(itemNotaFiscal, itemNotaFiscal));
        
        List<ItemNotaFiscalDto> itens = service.buscarPorProduto(produto.getId());
        
        assertNotNull(itens);
        assertEquals(2, itens.size());
        verify(repository, times(1)).findAllByProduto(any());
    }

    @Test
    public void listarTodos() {
        when(repository.findAll()).thenReturn(List.of(itemNotaFiscal, itemNotaFiscal));

        List<ItemNotaFiscalDto> itensEncontrados = service.listarTodos();
        
        assertNotNull(itensEncontrados);
        assertEquals(2, itensEncontrados.size());
        assertEquals(ID, itensEncontrados.get(0).getId());
        assertEquals(ItemNotaFiscalDto.class, itensEncontrados.get(0).getClass());
        verify(repository, times(1)).findAll();
    }

    @Test
     public void deveriaNaoDeletarItemIdInexistente() {
     when(repository.findById(anyInt())).thenReturn(Optional.empty());
    
     Exception excecao = assertThrows(ItemNaoEncontrado.class, () -> {
     service.deletarPorId(ID);
     });
    
     assertEquals(ItemNaoEncontrado.class, excecao.getClass());
     assertEquals("Item de nota fiscal não encontrado para o id " + ID,
     excecao.getMessage());
     }

    @Test
    public void deveRemoverItemSaida() {
        itemNotaFiscal.getNotaFiscal().getNotaFiscalTipo().setNome("SAÍDA");
        when(repository.findById(anyInt())).thenReturn(itemNotaFiscalOpcional);
        doNothing().when(repository).deleteById(anyInt());
        service.deletarPorId(ID);
        verify(repository, times(1)).deleteById(ID);
    }

    @Test
    public void deveRemoverUnicoItemEntrada() {
        itemNotaFiscal.getProduto().setQuantidadeEstoque(500);
        when(repository.findById(anyInt())).thenReturn(itemNotaFiscalOpcional);
        doNothing().when(repository).deleteById(anyInt());
        service.deletarPorId(ID);
        verify(repository, times(1)).deleteById(ID);
    }

    @Test
    public void deveRemoverItemEntrada() {
        itemNotaFiscal.getProduto().setQuantidadeEstoque(600);
        itemNotaFiscal.getProduto().setCustoMedio(BigDecimal.TEN);
        when(repository.findById(anyInt())).thenReturn(itemNotaFiscalOpcional);
        doNothing().when(repository).deleteById(anyInt());
        service.deletarPorId(ID);
        verify(repository, times(1)).deleteById(ID);
    }

    private void inicializarItem() {
        produto = new Produto();
        produto.setDescricao(PRODUTO_FIXO.getDescricao());
        produto.setId(PRODUTO_FIXO.getId());

        notaFiscalTipo = new NotaFiscalTipo();
        notaFiscalTipo.setNome(ENTRADA);

        notaFiscal = new NotaFiscal();
        notaFiscal.setNotaFiscalTipo(notaFiscalTipo);
        notaFiscal.setId(NOTAFISCAL.getId());

        itemNotaFiscal = new ItemNotaFiscal();
        itemNotaFiscal.setId(ID);
        itemNotaFiscal.setQuantidade(QUANTIDADE);
        itemNotaFiscal.setValorUnitario(VALORUNITARIO);
        itemNotaFiscal.setValorTotal(VALORUNITARIO.multiply(BigDecimal.valueOf(QUANTIDADE)));
        itemNotaFiscal.setProduto(produto);
        itemNotaFiscal.setNotaFiscal(notaFiscal);

        itemNotaFiscalDto = new ItemNotaFiscalDto();
        itemNotaFiscalDto.setId(ID);
        itemNotaFiscalDto.setQuantidade(QUANTIDADE);
        itemNotaFiscalDto.setValorUnitario(VALORUNITARIO);
        itemNotaFiscalDto.setValorTotal(VALORUNITARIO.multiply(BigDecimal.valueOf(QUANTIDADE)));
        itemNotaFiscalDto.setProduto(produto);
        itemNotaFiscalDto.setNotaFiscal(notaFiscal);

        itemNotaFiscalOpcional = Optional.of(itemNotaFiscal);

        when(mapper.convertarParaEntidade(itemNotaFiscalDto)).thenReturn(itemNotaFiscal);
        when(mapper.converterParaDto(itemNotaFiscal)).thenReturn(itemNotaFiscalDto);

        when(produtoService.buscarPorId(anyInt())).thenReturn(produtoDto);
        when(mapperProduto.converterParaEntidade(produtoDto)).thenReturn(produto);

        when(notaFiscalService.buscarPorId(anyInt())).thenReturn(notaFiscalDto);
        when(mapperNotaFiscal.converterParaEntidade(notaFiscalDto)).thenReturn(notaFiscal);

    }

}
