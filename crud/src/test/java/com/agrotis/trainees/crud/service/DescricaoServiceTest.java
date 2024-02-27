package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.DescricaoExisteException;
import com.agrotis.trainees.crud.repository.ProdutoRepository;

public class DescricaoServiceTest {

    @Mock
    ProdutoRepository repository;

    @InjectMocks
    DescricaoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Teste que verifica a descrição e o id")
    void deveEncontrarParaDescricaoEId() {

        Produto entidade = new Produto();
        entidade.setId(1);
        entidade.setDescricao("Batata");
        entidade.setEstoque(5);

        when(repository.existsByDescricaoAndIdNot(entidade.getDescricao(), 2)).thenReturn(false);

        assertDoesNotThrow(() -> {
            service.verificarDescricaoEId(entidade);
        });

        assertFalse(repository.existsByDescricaoAndIdNot(entidade.getDescricao(), 2));
    }

    @Test
    @DisplayName("Teste que verifica descrição e id falhou")
    void deveFalharAoEncontrarDescricaoEId() {
        Produto entidade = new Produto();
        entidade.setId(1);
        entidade.setDescricao("Batata");

        when(repository.existsByDescricaoAndIdNot(entidade.getDescricao(), entidade.getId())).thenReturn(true);

        Exception excecao = assertThrows(DescricaoExisteException.class, () -> {
            service.verificarDescricaoEId(entidade);
        });

        assertEquals("A descrição já existe", excecao.getMessage());
    }

    @Test
    @DisplayName("Teste que verifica descrição")
    void deveVerificarDescricao() {
        Produto entidade = new Produto();
        entidade.setDescricao("Banana");

        when(repository.existsByDescricao(entidade.getDescricao())).thenReturn(false);

        assertDoesNotThrow(() -> {
            service.verificarDescricao(entidade);
        });

        assertFalse(repository.existsByDescricao(entidade.getDescricao()));
    }

    @Test
    @DisplayName("Teste que verifica descrição falhou")
    void deveFalharAoEncontrarDescricao() {
        Produto entidade = new Produto();
        entidade.setDescricao("Batata");

        when(repository.existsByDescricao(entidade.getDescricao())).thenReturn(true);

        Exception excecao = assertThrows(DescricaoExisteException.class, () -> {
            service.verificarDescricao(entidade);
        });

        assertEquals("A descrição ja existe", excecao.getMessage());
    }
}
