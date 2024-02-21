package com.agrotis.trainees.crud.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.agrotis.trainees.crud.repository.ProdutoRepository;

public class ItemNotaFiscalServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private NotaFiscalService notaFiscalService;

    @Mock
    private ControleEstoque controleEstoque;

    @Mock
    private CustoMedioService CustoMedioService;

    @InjectMocks
    private ItemNotaFiscalService itemNotaFiscalService;

    @Test
    public void testSalvarItemNotaFiscal_ComTipoEntrada_AtualizaCustoMedio() {

    }
}
