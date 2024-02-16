package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.agrotis.trainees.crud.dto.NotaFiscalTipoDto;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.exception.CrudException;
import com.agrotis.trainees.crud.repository.NotaFiscalTipoRepository;

public class NotaFiscalTipoServiceTest {

    private final Integer ID_ENTRADA = 1;
    private final Integer ID_SAIDA = 2;
    private final Integer ID_INEXISTENTE = 9000;
    private final String ENTRADA = "Entrada";
    private final String SAIDA = "Saída";
    private final String INEXISTENTE = "XXXX";

    @Mock
    private NotaFiscalTipoRepository repository;

    @InjectMocks
    private NotaFiscalTipoService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void inserirTipoEntrada() {
        NotaFiscalTipo notaFiscalTipo = new NotaFiscalTipo();
        notaFiscalTipo.setNome(ENTRADA);
        when(repository.save(any(NotaFiscalTipo.class))).thenReturn(notaFiscalTipo);

        NotaFiscalTipoDto notaFiscalTipoDto = new NotaFiscalTipoDto();
        notaFiscalTipoDto.setNome(notaFiscalTipo.getNome());
        NotaFiscalTipoDto result = service.inserir(notaFiscalTipoDto);

        assertNotNull(result);
        verify(repository, times(1)).save(any(NotaFiscalTipo.class));
    }

    @Test
    public void inserirTipoSaida() {
        NotaFiscalTipo notaFiscalTipo = new NotaFiscalTipo();
        notaFiscalTipo.setNome(SAIDA);
        when(repository.save(any(NotaFiscalTipo.class))).thenReturn(notaFiscalTipo);

        NotaFiscalTipoDto notaFiscalTipoDto = new NotaFiscalTipoDto();
        notaFiscalTipoDto.setNome(notaFiscalTipo.getNome());
        NotaFiscalTipoDto result = service.inserir(notaFiscalTipoDto);

        assertNotNull(result);
        verify(repository, times(1)).save(any(NotaFiscalTipo.class));
    }

    @Test
    public void inserirDeveObrigarNome() {
        NotaFiscalTipoDto notaFiscalTipoDto = new NotaFiscalTipoDto();

        Exception excecao = assertThrows(CrudException.class, () -> {
            service.inserir(notaFiscalTipoDto);
        });
        assertEquals("Obrigatório preencher o nome do tipo de nota fiscal.", excecao.getMessage());
    }

    @Test
    public void atualizarTipoEntrada() {
        NotaFiscalTipo notaFiscalTipo = new NotaFiscalTipo();
        notaFiscalTipo.setId(ID_ENTRADA);
        notaFiscalTipo.setNome(ENTRADA);
        when(repository.save(any(NotaFiscalTipo.class))).thenReturn(notaFiscalTipo);
        NotaFiscalTipoDto retorno = service.atualizar(notaFiscalTipo);
        assertNotNull(retorno);
        verify(repository, times(1)).save(any(NotaFiscalTipo.class));
    }

    @Test
    public void atualizarDeveObrigarId() {
        NotaFiscalTipo notaFiscalTipo = new NotaFiscalTipo();
        notaFiscalTipo.setNome(ENTRADA);
        when(repository.save(any(NotaFiscalTipo.class))).thenReturn(notaFiscalTipo);
        Exception excecao = assertThrows(CrudException.class, () -> {
            service.atualizar(notaFiscalTipo);
        });
        assertEquals("Obrigatório preencher o id do tipo de nota fiscal.", excecao.getMessage());
    }

    @Test
    public void atualizarDeveObrigarNome() {
        NotaFiscalTipo notaFiscalTipo = new NotaFiscalTipo();
        notaFiscalTipo.setId(ID_SAIDA);
        when(repository.save(any(NotaFiscalTipo.class))).thenReturn(notaFiscalTipo);
        Exception excecao = assertThrows(CrudException.class, () -> {
            service.atualizar(notaFiscalTipo);
        });
        assertEquals("Obrigatório preencher o nome do tipo de nota fiscal.", excecao.getMessage());
    }

    @Test
    public void buscarPorId() {

    }

    @Test
    public void buscarPorIdInexistente() {
        when(repository.findById(ID_INEXISTENTE)).thenReturn(Optional.empty());

        Exception excecao = assertThrows(CrudException.class, () -> {
            service.buscarPorId(ID_INEXISTENTE);
        });
        assertEquals(String.format("Nota não encontrada para id {}.", ID_INEXISTENTE), excecao.getMessage());
    }

    @Test
    public void buscarPorNomeExistente() {

    }

    @Test
    public void buscarPorNomeInexistente() {
        when(repository.findByNome(INEXISTENTE)).thenReturn(Optional.empty());

        Exception excecao = assertThrows(CrudException.class, () -> {
            service.buscarPorNome(INEXISTENTE);
        });
        assertEquals(String.format("Nota não encontrada para o nome {}.", INEXISTENTE), excecao.getMessage());
    }

    @Test
    public void deletarPorId() {
        int id = 1;
        doNothing().when(repository).deleteById(id);
        service.deletarPorId(id);
        verify(repository, times(1)).deleteById(id);
    }

    @Test
    public void listarTodos() {
        List<NotaFiscalTipo> lista = Arrays.asList(new NotaFiscalTipo(), new NotaFiscalTipo());
        when(repository.findAll()).thenReturn(lista);

        List<NotaFiscalTipo> result = service.listarTodos();
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }
}
