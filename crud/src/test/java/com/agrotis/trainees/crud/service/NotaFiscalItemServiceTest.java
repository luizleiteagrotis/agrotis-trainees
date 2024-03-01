package com.agrotis.trainees.crud.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.dto.NotaFiscalItemDto;
import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.enums.NotaFiscalTipo;
import com.agrotis.trainees.crud.repository.ItemNotaFiscalRepository;
import com.agrotis.trainees.crud.repository.ProdutoRepository;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;

public class NotaFiscalItemServiceTest {

    @Mock
    private ItemNotaFiscalRepository repository;

    @Mock
    private ProdutoRepository produtoRepository;
    
    @Mock
    private ProdutoService produtoService;
    
    @Mock
    private NotaFiscalService notaFiscalService;


    @InjectMocks
    private ItemNotaFiscalService service;
    

    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
      



    
    
  @Test
  void salvarDeveSalvarItemNotaQuandoDtoValido() {
      // Criar o DTO do item da nota
      NotaFiscalItemDto itemNotaDto = criarNotaFiscalItemDto();
      
      // Criar o item da nota e o produto associado
      ItemNotaFiscal itemNotaSalvo = criarItemNotaFiscal();
      Produto produtoSalvo = itemNotaSalvo.getProduto();
      
      // Configurar o comportamento do mock do repositório do produto para retornar o produto salvo
      when(produtoRepository.save(any(Produto.class))).thenReturn(produtoSalvo);

      // Configurar o comportamento do mock do repositório do item da nota para retornar o item da nota salvo
      when(repository.save(any(ItemNotaFiscal.class))).thenReturn(itemNotaSalvo);

      // Chamar o método a ser testado
      NotaFiscalItemDto result = service.salvar(itemNotaDto);

      // Verificar se o resultado não é nulo
      assertNotNull(result);
      
      // Verificar se o ID do item da nota salvo é igual ao ID do resultado
      assertEquals(itemNotaSalvo.getId(), result.getId());
      
      // Verificar se o método save do repositório do item da nota foi invocado
      verify(repository).save(any(ItemNotaFiscal.class));
  }


    

  @Test
  void buscarPorIdDeveRetornarItemNotaDto_QuandoEncontrarItemNota() {
      int id = 1;
      ItemNotaFiscal itemNota = criarItemNotaFiscal();
      when(repository.findById(id)).thenReturn(Optional.of(itemNota));

      NotaFiscalItemDto result = service.buscarPorId(id);

      assertNotNull(result);
      assertEquals(itemNota.getPrecoUnitario(), result.getPrecoUnitario());
  }

  @Test
  void buscarPorIdDeveLancarEntidadeNaoEncontradaException_QuandoNaoEncontrarItemNota() {
      int id = 1;
      when(repository.findById(id)).thenReturn(Optional.empty());

      assertThrows(EntidadeNaoEncontradaException.class, () -> service.buscarPorId(id));
  }

  @Test
  void listarTodosDeveRetornarListaDeItensNotaDto() {
      List<ItemNotaFiscal> itensNota = new ArrayList<>();
      itensNota.add(criarItemNotaFiscal());
      when(repository.findAll()).thenReturn(itensNota);

      List<NotaFiscalItemDto> result = service.listarTodos();
      
      assertNotNull(result);
      assertEquals(itensNota.size(), result.size());
      assertEquals(itensNota.get(0).getPrecoUnitario(), result.get(0).getPrecoUnitario());
  }

  @Test
  void atualizarDeveAtualizarItemNota_QuandoEncontrarItemNota() {
      int id = 1;
      ItemNotaFiscal itemNotaExistente = criarItemNotaFiscal();
      NotaFiscalItemDto itemNotaDto = criarNotaFiscalItemDto();
      when(repository.findById(id)).thenReturn(Optional.of(itemNotaExistente));
      when(repository.save(any(ItemNotaFiscal.class))).thenAnswer(invocation -> invocation.getArgument(0));

      NotaFiscalItemDto result = service.update(id, itemNotaDto);

      assertNotNull(result);
      assertEquals(itemNotaDto.getPrecoUnitario(), result.getPrecoUnitario());
      verify(repository).save(any(ItemNotaFiscal.class));
  }



  @Test
  void deletarPorIdDeveDeletarItemNota_QuandoEncontrarItemNota() {
      int id = 1;
      ItemNotaFiscal itemNota = criarItemNotaFiscal();
      when(repository.findById(id)).thenReturn(Optional.of(itemNota));

      service.deletarPorId(id);

      verify(repository).deleteById(id);
  }
  

  @Test
  void deletarPorIdDeveLancarEntidadeNaoEncontradaException_QuandoNaoEncontrarItemNota() {
      int id = 1;
      when(repository.findById(id)).thenReturn(Optional.empty());

      assertThrows(EntidadeNaoEncontradaException.class, () -> service.deletarPorId(id));
  }
  
  
  
  
  
  @Test
  public void testAdicionarValorTotalCabecalho_ValoresNaoNulos() {
      // Arrange
      BigDecimal valorTotalCabecalho = new BigDecimal("100");
      BigDecimal valorTotalItem = new BigDecimal("50");
      NotaFiscal cabecalhoNota = new NotaFiscal();
      cabecalhoNota.setValorTotal(valorTotalCabecalho);

      ItemNotaFiscal itemNota = new ItemNotaFiscal();
      itemNota.setNotaFiscal(cabecalhoNota);
      itemNota.setValorTotal(valorTotalItem);

      // Act
      service.adicionarValorTotalCabecalho(itemNota);

      // Assert
      assertEquals(valorTotalCabecalho.add(valorTotalItem), cabecalhoNota.getValorTotal());
  }

  @Test
  public void testAdicionarValorTotalCabecalho_CabecalhoNaoNulo() {
      // Arrange
      BigDecimal valorTotalItem = new BigDecimal("50");
      NotaFiscal cabecalhoNota = new NotaFiscal();
      cabecalhoNota.setValorTotal(null);

      ItemNotaFiscal itemNota = new ItemNotaFiscal();
      itemNota.setNotaFiscal(cabecalhoNota);
      itemNota.setValorTotal(valorTotalItem);

      // Act
      service.adicionarValorTotalCabecalho(itemNota);

      // Assert
      assertEquals(valorTotalItem, cabecalhoNota.getValorTotal());
  }

  
  @Test
  public void testAdicionarValorTotalCabecalho_ItemTotalNulo() {
      // Arrange
      BigDecimal valorTotalCabecalho = new BigDecimal("100");
      NotaFiscal cabecalhoNota = new NotaFiscal();
      cabecalhoNota.setValorTotal(valorTotalCabecalho);

      ItemNotaFiscal itemNota = new ItemNotaFiscal();
      itemNota.setNotaFiscal(cabecalhoNota);
      itemNota.setValorTotal(null);

      // Act
      service.adicionarValorTotalCabecalho(itemNota);

      // Assert
      assertEquals(valorTotalCabecalho, cabecalhoNota.getValorTotal());
  }
  
  
  @Test
  void testAddValorTotal_NotaFiscalEItemValido() {
      // Arrange
      ItemNotaFiscal item = new ItemNotaFiscal();
      NotaFiscal notaFiscal = new NotaFiscal();
      BigDecimal valorTotalItem = new BigDecimal("50");
      item.setNotaFiscal(notaFiscal);
      item.setValorTotal(valorTotalItem);

      // Act
      service.addValorTotal(item);

      // Assert
      assertEquals(valorTotalItem, notaFiscal.getValorTotal());
      verify(notaFiscalService, times(1)).salvar(any());
  }

  @Test
  void testAddValorTotal_ItemNulo() {
      // Arrange
      ItemNotaFiscal item = null;

      // Act & Assert
      assertThrows(IllegalArgumentException.class, () -> {
          service.addValorTotal(item);
      });

      verifyNoInteractions(notaFiscalService);
  }

  @Test
  void testAddValorTotal_NuloNotaFiscal() {
      // Arrange
      ItemNotaFiscal item = new ItemNotaFiscal();
      BigDecimal valorTotalItem = new BigDecimal("50");
      item.setNotaFiscal(null);
      item.setValorTotal(valorTotalItem);

      // Act & Assert
      assertThrows(IllegalArgumentException.class, () -> {
          service.addValorTotal(item);
      });

      verifyNoInteractions(notaFiscalService);
  }
  
  
  @Test
  void testCalcularValorTotal_QtdEPrecoNaoNulo() {
      // Arrange
      
      ItemNotaFiscal itemNotaFiscal = new ItemNotaFiscal();
      BigDecimal quantidade = new BigDecimal("5");
      BigDecimal precoUnitario = new BigDecimal("10");
      itemNotaFiscal.setQuantidade(quantidade);
      itemNotaFiscal.setPrecoUnitario(precoUnitario);

      // Act
      service.calcularValorTotal(itemNotaFiscal);

      // Assert
      BigDecimal expectedValorTotal = new BigDecimal("50");
      assertEquals(expectedValorTotal, itemNotaFiscal.getValorTotal());
  }

  @Test
  void testCalcularValorTotal_QuantidadeNulo() {
      // Arrange
      
      ItemNotaFiscal itemNotaFiscal = new ItemNotaFiscal();
      BigDecimal precoUnitario = new BigDecimal("10");
      itemNotaFiscal.setQuantidade(null);
      itemNotaFiscal.setPrecoUnitario(precoUnitario);

      // Act
      service.calcularValorTotal(itemNotaFiscal);

      // Assert
      assertNull(itemNotaFiscal.getValorTotal());
  }

  @Test
  void testCalcularValorTotal_PrecoUnitarioNulo() {
      // Arrange
      
      ItemNotaFiscal itemNotaFiscal = new ItemNotaFiscal();
      BigDecimal quantidade = new BigDecimal("5");
      itemNotaFiscal.setQuantidade(quantidade);
      itemNotaFiscal.setPrecoUnitario(null);

      // Act
      service.calcularValorTotal(itemNotaFiscal);

      // Assert
      assertNull(itemNotaFiscal.getValorTotal());
  }
  
   

  


  private NotaFiscalItemDto criarNotaFiscalItemDto() {
      NotaFiscalItemDto dto = new NotaFiscalItemDto();
      dto.setPrecoUnitario(BigDecimal.valueOf(20));
      dto.setQuantidade(BigDecimal.TEN);
      dto.setValorTotal(BigDecimal.valueOf(200));
      NotaFiscal cabecalhoNota = new NotaFiscal();
      cabecalhoNota.setTipo(NotaFiscalTipo.ENTRADA);
      dto.setNotaFiscal(cabecalhoNota);
      Produto produto = new Produto();
      produto.setDescricao("Produto Teste");
      dto.setProduto(produto);
      return dto;
  }
  

  private ItemNotaFiscal criarItemNotaFiscal() {
      ItemNotaFiscal itemNota = new ItemNotaFiscal();
      itemNota.setPrecoUnitario(BigDecimal.valueOf(20));
      itemNota.setQuantidade(BigDecimal.TEN);
      itemNota.setValorTotal(BigDecimal.valueOf(200));
      NotaFiscal cabecalhoNota = new NotaFiscal();
      cabecalhoNota.setTipo(NotaFiscalTipo.ENTRADA);
      itemNota.setNotaFiscal(cabecalhoNota);
      Produto produto = new Produto();
      produto.setDescricao("Produto Teste");
      produto.setEstoque(new BigDecimal(10));
      produto.setDataFabricacao(LocalDate.of(2024, 2, 21));
      produto.setDataValidade(LocalDate.of(2025, 2, 21));
      itemNota.setProduto(produto);
      return itemNota;
  }
    
 
}

