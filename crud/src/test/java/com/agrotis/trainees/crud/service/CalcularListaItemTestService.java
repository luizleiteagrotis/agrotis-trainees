package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;

public class CalcularListaItemTestService {

    @Mock
    private NotaFiscalRepository repository;

    @Mock
    private ItemNotaFiscal item;

    @InjectMocks
    private CalcularListaItemService calcularListaItemService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deveAdicionarItem_NotaFiscalSemItens() {
        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setItens(null);

        when(item.getNotaFiscal()).thenReturn(notaFiscal);
        when(item.getValorTotal()).thenReturn(BigDecimal.TEN.multiply(BigDecimal.valueOf(1)));
        calcularListaItemService.adicionarItem(item);

        List<ItemNotaFiscal> itens = notaFiscal.getItens();

        assertEquals(1, itens.size());
        assertEquals(item, itens.get(0));

        assertEquals(item.getValorTotal(), notaFiscal.getValorTotal());
    }

    @Test
    public void deveAdicionarItem_NotaFiscalComItens() {
        NotaFiscal notaFiscal = new NotaFiscal();
        List<ItemNotaFiscal> itens = new ArrayList<>();
        ItemNotaFiscal item1 = new ItemNotaFiscal();
        item1.setId(1);
        item1.setValorTotal(BigDecimal.TEN.multiply(BigDecimal.valueOf(1)));
        item.setValorTotal(BigDecimal.TEN.multiply(BigDecimal.valueOf(1)));
        itens.add(item1);
        notaFiscal.setItens(itens);
        when(item.getNotaFiscal()).thenReturn(notaFiscal);
        when(item.getValorTotal()).thenReturn(BigDecimal.TEN.multiply(BigDecimal.valueOf(1)));

        calcularListaItemService.adicionarItem(item);

        assertEquals(2, itens.size());
        assertEquals(item, itens.get(1));

        assertEquals(item.getValorTotal().add(item1.getValorTotal()), notaFiscal.getValorTotal());
    }

    @Test
    public void deveAdicionarItem_ItemJaNaLista() {
        NotaFiscal notaFiscal = new NotaFiscal();
        List<ItemNotaFiscal> itens = new ArrayList<>();
        itens.add(item);
        notaFiscal.setItens(itens);

        when(item.getNotaFiscal()).thenReturn(notaFiscal);
        when(item.getValorTotal()).thenReturn(BigDecimal.TEN.multiply(BigDecimal.valueOf(1)));

        calcularListaItemService.adicionarItem(item);

        assertEquals(1, itens.size());
        assertEquals(item, itens.get(0));

        assertEquals(item.getValorTotal(), notaFiscal.getValorTotal());
    }

    @Test
    public void removerItem() {
        List<ItemNotaFiscal> itens = new ArrayList<>();
        item.setValorTotal(BigDecimal.TEN.multiply(BigDecimal.valueOf(1)));
        itens.add(item);

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setItens(itens);

        calcularListaItemService.removerItem(item, notaFiscal);

        assertTrue(notaFiscal.getItens().isEmpty());

        assertEquals(BigDecimal.ZERO, notaFiscal.getValorTotal());
    }

    @Test
    public void removerItem_ItemPresenteListaNaoVazia() {
        ItemNotaFiscal item1 = new ItemNotaFiscal();
        item1.setId(1);
        ItemNotaFiscal item2 = new ItemNotaFiscal();
        item2.setId(2);

        item2.setValorTotal(BigDecimal.TEN.multiply(BigDecimal.valueOf(1)));

        List<ItemNotaFiscal> itens = new ArrayList<>();
        itens.add(item1);
        itens.add(item2);

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setItens(itens);

        calcularListaItemService.removerItem(item1, notaFiscal);

        List<Integer> ids = notaFiscal.getItens().stream().map(ItemNotaFiscal::getId).collect(Collectors.toList());
        assertFalse(ids.contains(1));
        assertTrue(ids.contains(2));

        assertEquals(item2.getValorTotal(), notaFiscal.getValorTotal());
    }

}
