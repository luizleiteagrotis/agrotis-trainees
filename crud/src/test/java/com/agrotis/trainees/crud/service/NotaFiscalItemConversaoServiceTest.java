package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.dto.NotaFiscalItemDto;
import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.Produto;

@RunWith(MockitoJUnitRunner.class)
public class NotaFiscalItemConversaoServiceTest {

    @Mock
    private NotaFiscal nota;

    @Mock
    private NotaFiscalConversaoService conversaoNota;

    @Mock
    private ProdutoConversaoService conversaoProduto;

    @Mock
    private Produto produto;

    @InjectMocks
    private NotaFiscalItemConversaoService conversao;

    @Before
    public void setUp() {
        // Configurar comportamento dos mocks, se necessário
    }

    @Test
    @DisplayName("Teste para o método converterParaDto")
    public void testeConverterDto() {
        NotaFiscalItem entidade = new NotaFiscalItem();
        entidade.setId(1);
        entidade.setQuantidade(10);
        entidade.setPrecoUnitario(BigDecimal.TEN);
        entidade.setValorTotal(BigDecimal.valueOf(100));

        when(conversaoNota.converterParaDto(nota)).thenReturn(new NotaFiscalDto());
        when(conversaoProduto.converterParaDto(produto)).thenReturn(new ProdutoDto());

        entidade.setNotaFiscal(nota);
        entidade.setProduto(produto);

        NotaFiscalItemDto dto = conversao.converterParaDto(entidade);

        assertEquals(entidade.getId(), dto.getId());
        assertEquals(entidade.getQuantidade(), dto.getQuantidade());
        assertEquals(entidade.getPrecoUnitario(), dto.getPrecoUnitario());
        assertEquals(entidade.getValorTotal(), dto.getValorTotal());
    }

    @Test
    public void testConverterParaEntidade() {
        NotaFiscalItemDto dto = new NotaFiscalItemDto();
        dto.setId(1);
        dto.setQuantidade(10);
        dto.setPrecoUnitario(BigDecimal.TEN);
        dto.setValorTotal(BigDecimal.valueOf(100));

        when(conversaoNota.converterParaEntidade(dto.getNotaFiscal())).thenReturn(nota);
        when(conversaoProduto.converterParaEntidade(dto.getProduto())).thenReturn(produto);

        NotaFiscalItem entidade = conversao.converterParaEntidade(dto);

        assertEquals(dto.getId(), entidade.getId());
        assertEquals(dto.getQuantidade(), entidade.getQuantidade());
        assertEquals(dto.getPrecoUnitario(), entidade.getPrecoUnitario());
        assertEquals(dto.getValorTotal(), entidade.getValorTotal());
    }

}
