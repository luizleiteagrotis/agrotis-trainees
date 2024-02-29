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

//  @Test
//  void atualizar_DeveLancarEntidadeNaoEncontradaException_QuandoNaoEncontrarItemNota() {
//      int id = 1;
//      NotaFiscalItemDto itemNotaDto = criarNotaFiscalItemDto();
//      when(repository.findById(id)).thenReturn(Optional.empty());
//
//      assertThrows(EntidadeNaoEncontradaException.class, () -> service.atualizarItemNota(id, itemNotaDto));
//  }

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

