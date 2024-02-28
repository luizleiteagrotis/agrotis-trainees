package com.agrotis.trainees.crud.service;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.agrotis.trainees.crud.dto.NotaFiscalItemDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;
import com.agrotis.trainees.crud.wrapper.NotaFiscalItemWrapper;
import com.agrotis.trainees.crud.wrapper.NotaFiscalWrapper;

public class NotaFiscalItemServiceTest {

    @Mock
    NotaFiscalItemRepository repository;

    @Mock
    NotaFiscalItemWrapper itemWrapper;

    @Mock
    NotaFiscalWrapper notaWrapper;

    @Mock
    EstoqueService estoqueService;

    @InjectMocks
    NotaFiscalItemService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Teste de inserção com sucesso")
    void deveInserirComSucesso() {
        NotaFiscal nota = new NotaFiscal();
        nota.setId(1);

        Produto produto = new Produto();
        produto.setId(1);

        NotaFiscalItemDto dto = new NotaFiscalItemDto();
        dto.setId(1);
        dto.setIdNota(nota);
        dto.setProduto(produto);

        NotaFiscalItem entidade = new NotaFiscalItem();
        entidade.setId(1);
        entidade.setIdNota(nota);
        entidade.setProduto(produto);

        when(itemWrapper.converterParaEntidade(dto)).thenReturn(entidade);

    }

}
