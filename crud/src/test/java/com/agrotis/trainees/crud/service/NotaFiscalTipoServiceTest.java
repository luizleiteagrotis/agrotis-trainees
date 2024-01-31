package com.agrotis.trainees.crud.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.repository.NotaFiscalTipoRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class NotaFiscalTipoServiceTest {
	
	private final Integer ID_UM = 1;
	private final Integer ID_DOIS = 2;
	private final String ENTRADA = "Entrada";
	private final String SAIDA = "Saída";
	private final String INEXISTENTE = "XXX";

    @Mock
    private NotaFiscalTipoRepository repository;

    @InjectMocks
    private NotaFiscalTipoService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void salvarTipoEntrada() {
        NotaFiscalTipo notaFiscalTipo = new NotaFiscalTipo();
        notaFiscalTipo.setNome(ENTRADA);
        when(repository.save(any(NotaFiscalTipo.class))).thenReturn(notaFiscalTipo);

        NotaFiscalTipo result = service.salvar(notaFiscalTipo);
        assertNotNull(result);
        verify(repository, times(1)).save(any(NotaFiscalTipo.class));
    }

    @Test
    public void salvarTipoSaida() {
        NotaFiscalTipo notaFiscalTipo = new NotaFiscalTipo();
        notaFiscalTipo.setNome(SAIDA);
        when(repository.save(any(NotaFiscalTipo.class))).thenReturn(notaFiscalTipo);

        NotaFiscalTipo result = service.salvar(notaFiscalTipo);
        assertNotNull(result);
        verify(repository, times(1)).save(any(NotaFiscalTipo.class));
    }

    @Test
    public void salvarSemSucesso() {
        NotaFiscalTipo notaFiscalTipo = new NotaFiscalTipo();
        when(repository.save(any(NotaFiscalTipo.class))).thenReturn(notaFiscalTipo);

        NotaFiscalTipo result = service.salvar(notaFiscalTipo);
        assertNull(result);
        verify(repository, times(0)).save(any(NotaFiscalTipo.class));
    }

    @Test
    public void buscarPorId() {
        NotaFiscalTipo notaFiscalTipo = new NotaFiscalTipo();
        notaFiscalTipo.setId(ID_UM);
        Optional<NotaFiscalTipo> notaFiscalTipoOptional = Optional.of(notaFiscalTipo);
        when(repository.findById(ID_UM)).thenReturn(notaFiscalTipoOptional);

        NotaFiscalTipo result = service.buscarPorId(ID_UM);
        assertNotNull(result);
        verify(repository, times(1)).findById(ID_UM);
    }

    @Test
    public void buscarPorIdInexistente() {
        when(repository.findById(ID_DOIS)).thenReturn(Optional.empty());

        NotaFiscalTipo result = service.buscarPorId(ID_DOIS);
        assertNull(result);
        verify(repository, times(1)).findById(ID_DOIS);
    }

    @Test
    public void buscarPorNomeExistente() {
        NotaFiscalTipo notaFiscalTipo = new NotaFiscalTipo();
        notaFiscalTipo.setId(ID_UM);
        notaFiscalTipo.setNome(ENTRADA);
        Optional<NotaFiscalTipo> notaFiscalTipoOptional = Optional.of(notaFiscalTipo);
        when(repository.findByNome(ENTRADA)).thenReturn(notaFiscalTipoOptional);

        NotaFiscalTipo result = service.buscarPorNome(ENTRADA);
        assertNotNull(result);
        verify(repository, times(1)).findByNome(ENTRADA);
    }

    @Test
    public void buscarPorNomeInexistente() {
        when(repository.findByNome(INEXISTENTE)).thenReturn(Optional.empty());

        NotaFiscalTipo result = service.buscarPorNome(INEXISTENTE);
        assertNull(result);
        verify(repository, times(1)).findByNome(INEXISTENTE);
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
