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

import com.agrotis.trainees.crud.dtos.ItemNotaDto;
import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.enums.TipoNota;
import com.agrotis.trainees.crud.repository.CabecalhoNotaRepository;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;
import com.agrotis.trainees.crud.repository.ProdutoRepository;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;
import com.agrotis.trainees.crud.service.impl.ItemNotaServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ItemNotaServiceImplTest {

    @Mock
    private NotaFiscalItemRepository repository;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private CabecalhoNotaRepository cabecalhoNotaRepository;

    @InjectMocks
    private ItemNotaServiceImpl service;

//    @Test
//    void salvarDeveSalvarItemNotaQuandoDtoValido() {
//        // Criar o DTO do item da nota
//        ItemNotaDto itemNotaDto = criarItemNotaDto();
//        
//        // Criar o item da nota e o produto associado
//        ItemNota itemNotaSalvo = criarItemNota();
//        Produto produtoSalvo = itemNotaSalvo.getProduto();
//        
//        // Configurar o comportamento do mock do repositório do produto para retornar o produto salvo
//        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoSalvo);
//
//        // Configurar o comportamento do mock do repositório do item da nota para retornar o item da nota salvo
//        when(repository.save(any(ItemNota.class))).thenReturn(itemNotaSalvo);
//
//        // Chamar o método a ser testado
//        ItemNotaDto result = service.salvar(itemNotaDto);
//
//        // Verificar se o resultado não é nulo
//        assertNotNull(result);
//        
//        // Verificar se o ID do item da nota salvo é igual ao ID do resultado
//        assertEquals(itemNotaSalvo.getId(), result.getId());
//        
//        // Verificar se o método save do repositório do item da nota foi invocado
//        verify(repository).save(any(ItemNota.class));
//    }





    @Test
    void buscarPorIdDeveRetornarItemNotaDto_QuandoEncontrarItemNota() {
        int id = 1;
        ItemNota itemNota = criarItemNota();
        when(repository.findById(id)).thenReturn(Optional.of(itemNota));

        ItemNotaDto result = service.buscarPorId(id);

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
        List<ItemNota> itensNota = new ArrayList<>();
        itensNota.add(criarItemNota());
        when(repository.findAll()).thenReturn(itensNota);

        List<ItemNotaDto> result = service.listarTodos();
        
        assertNotNull(result);
        assertEquals(itensNota.size(), result.size());
        assertEquals(itensNota.get(0).getPrecoUnitario(), result.get(0).getPrecoUnitario());
    }

    @Test
    void atualizarDeveAtualizarItemNota_QuandoEncontrarItemNota() {
        int id = 1;
        ItemNota itemNotaExistente = criarItemNota();
        ItemNotaDto itemNotaDto = criarItemNotaDto();
        when(repository.findById(id)).thenReturn(Optional.of(itemNotaExistente));
        when(repository.save(any(ItemNota.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ItemNotaDto result = service.atualizar(id, itemNotaDto);

        assertNotNull(result);
        assertEquals(itemNotaDto.getPrecoUnitario(), result.getPrecoUnitario());
        verify(repository).save(any(ItemNota.class));
    }

    @Test
    void atualizar_DeveLancarEntidadeNaoEncontradaException_QuandoNaoEncontrarItemNota() {
        int id = 1;
        ItemNotaDto itemNotaDto = criarItemNotaDto();
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> service.atualizar(id, itemNotaDto));
    }

    @Test
    void deletarPorIdDeveDeletarItemNota_QuandoEncontrarItemNota() {
        int id = 1;
        ItemNota itemNota = criarItemNota();
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

    private ItemNotaDto criarItemNotaDto() {
        ItemNotaDto dto = new ItemNotaDto();
        dto.setPrecoUnitario(BigDecimal.valueOf(20));
        dto.setQuantidade(BigDecimal.TEN);
        dto.setValorTotal(BigDecimal.valueOf(200));
        CabecalhoNota cabecalhoNota = new CabecalhoNota();
        cabecalhoNota.setNotaFiscalTipo(TipoNota.ENTRADA);
        dto.setCabecalhoNota(cabecalhoNota);
        Produto produto = new Produto();
        produto.setDescricao("Produto Teste");
        dto.setProduto(produto);
        return dto;
    }

    private ItemNota criarItemNota() {
        ItemNota itemNota = new ItemNota();
        itemNota.setPrecoUnitario(BigDecimal.valueOf(20));
        itemNota.setQuantidade(BigDecimal.TEN);
        itemNota.setValorTotal(BigDecimal.valueOf(200));
        CabecalhoNota cabecalhoNota = new CabecalhoNota();
        cabecalhoNota.setNotaFiscalTipo(TipoNota.ENTRADA);
        itemNota.setCabecalhoNota(cabecalhoNota);
        Produto produto = new Produto();
        produto.setDescricao("Produto Teste");
        produto.setQuantidadeEstoque(new BigDecimal(10));
        produto.setDataFabricacao(LocalDate.of(2024, 2, 21));
        produto.setDataValidade(LocalDate.of(2025, 2, 21));
        itemNota.setProduto(produto);
        return itemNota;
    }
}
