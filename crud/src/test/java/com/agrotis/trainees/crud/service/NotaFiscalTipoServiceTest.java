package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import com.agrotis.trainees.crud.dto.NotaFiscalTipoDto;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.exception.CrudException;
import com.agrotis.trainees.crud.exception.NotaFiscalTipoNaoEncontrada;
import com.agrotis.trainees.crud.repository.NotaFiscalTipoRepository;
import com.agrotis.trainees.crud.utils.NotaFiscalTipoDTOMapper;

public class NotaFiscalTipoServiceTest {

    private final Integer ID = 1;
    private final String ENTRADA = "Entrada";
    private final String SAIDA = "Saída";

    @Mock
    private NotaFiscalTipoRepository repository;

    @Mock
    private NotaFiscalTipoDTOMapper mapper;

    @InjectMocks
    private NotaFiscalTipoService service;

    private NotaFiscalTipo notaFiscalTipo;

    private NotaFiscalTipoDto notaFiscalTipoDto;

    private Optional<NotaFiscalTipo> notaFiscalTipoOpcional;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        inicializarNotaFiscalTipo();
    }

    @Test
    public void inserirTipoEntrada() {
        when(repository.save(any())).thenReturn(notaFiscalTipo);        

        NotaFiscalTipoDto notaFiscalTipoSalva = service.inserir(notaFiscalTipoDto);
        
        assertNotNull(notaFiscalTipoSalva);
        assertEquals(NotaFiscalTipoDto.class, notaFiscalTipoSalva.getClass());
        assertEquals(ENTRADA, notaFiscalTipoSalva.getNome());
    }

    @Test
    public void inserirTipoSaida() {
        notaFiscalTipoDto.setNome(SAIDA);
        when(repository.save(any())).thenReturn(notaFiscalTipo);

        NotaFiscalTipoDto notaFiscalTipoSalva = service.inserir(notaFiscalTipoDto);

        assertNotNull(notaFiscalTipoSalva);
        assertEquals(NotaFiscalTipoDto.class, notaFiscalTipoSalva.getClass());
        assertEquals(SAIDA, notaFiscalTipoSalva.getNome());
    }

    @Test
    public void inserirDeveObrigarNome() {
        notaFiscalTipoDto.setNome(null);
        when(repository.save(any())).thenReturn(notaFiscalTipo);

        Exception excecao = assertThrows(CrudException.class, () -> {
            service.inserir(notaFiscalTipoDto);
        });

        assertEquals("Obrigatório preencher o nome do tipo de nota fiscal.", excecao.getMessage());
    }

    @Test
    public void atualizarTipoEntrada() {
        when(repository.save(any())).thenReturn(notaFiscalTipo);
        
        NotaFiscalTipoDto notaFiscalTipoAtualizada = service.atualizar(notaFiscalTipoDto);

        assertNotNull(notaFiscalTipo);
        when(repository.save(notaFiscalTipo)).thenReturn(notaFiscalTipo);
        assertEquals(ENTRADA, notaFiscalTipoAtualizada.getNome());
    }

    @Test
    public void atualizarDeveObrigarId() {
        notaFiscalTipo.setId(null);
        when(repository.save(any(NotaFiscalTipo.class))).thenReturn(notaFiscalTipo);

        Exception excecao = assertThrows(CrudException.class, () -> {
            service.atualizar(notaFiscalTipoDto);
        });

        assertEquals("Obrigatório preencher o id do tipo de nota fiscal.", excecao.getMessage());
    }

    @Test
    public void atualizarDeveObrigarNome() {
        notaFiscalTipo.setNome(null);
        when(repository.save(any(NotaFiscalTipo.class))).thenReturn(notaFiscalTipo);

        Exception excecao = assertThrows(CrudException.class, () -> {
            service.atualizar(notaFiscalTipoDto);
        });

        assertEquals("Obrigatório preencher o nome do tipo de nota fiscal.", excecao.getMessage());
    }

    @Test
    public void buscarPorId() {
         when(repository.findById(anyInt())).thenReturn(notaFiscalTipoOpcional);

         NotaFiscalTipoDto itemEncontrado = service.buscarPorId(ID);
         
         assertNotNull(itemEncontrado);
         assertEquals(NotaFiscalTipoDto.class, itemEncontrado.getClass());
         assertEquals(ID, itemEncontrado.getId());
    }

    @Test
    public void buscarPorIdInexistente() {
         when(repository.findById(anyInt())).thenReturn(Optional.empty());

        Exception excecao = assertThrows(NotaFiscalTipoNaoEncontrada.class, () -> {
             service.buscarPorId(ID);
        });
        
        assertEquals(NotaFiscalTipoNaoEncontrada.class, excecao.getClass());
         assertEquals("Tipo de nota não encontrada para o id " +
         ID, excecao.getMessage());
    }

    @Test
    public void buscarPorNomeExistente() {
        when(repository.findByNome(ENTRADA)).thenReturn(notaFiscalTipoOpcional);

        NotaFiscalTipoDto itemEncontrado = service.buscarPorNome(ENTRADA);

        assertEquals(ENTRADA, itemEncontrado.getNome());
    }

    @Test
    public void buscarPorNomeInexistente() {
         when(repository.findByNome(anyString())).thenReturn(Optional.empty());

        Exception excecao = assertThrows(NotaFiscalTipoNaoEncontrada.class, () -> {
             service.buscarPorNome(SAIDA);
        });
        
         assertEquals("Tipo de nota não encontrada para o nome " +
         SAIDA, excecao.getMessage());
    }

    @Test
    public void deveriaNaoDeletarPorIdInexistente() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        
        Exception excecao = assertThrows(NotaFiscalTipoNaoEncontrada.class, () -> {
            service.deletarPorId(ID);
        });
        
        assertEquals("Tipo de nota não encontrada para o id " +
                        ID, excecao.getMessage());
    }

    @Test
    public void deletarPorId() {
        when(repository.findById(anyInt())).thenReturn(notaFiscalTipoOpcional);
        doNothing().when(repository).deleteById(anyInt());
        service.deletarPorId(ID);
        verify(repository, times(1)).deleteById(ID);
    }

    @Test
    public void listarTodos() {
        when(repository.findAll()).thenReturn(List.of(notaFiscalTipo, notaFiscalTipo));

        List<NotaFiscalTipoDto> listaNotasFiscaisTipos = service.listarTodos();
        
        assertNotNull(listaNotasFiscaisTipos);
        assertEquals(2, listaNotasFiscaisTipos.size());
        assertEquals(NotaFiscalTipoDto.class, listaNotasFiscaisTipos.get(0).getClass());
        assertEquals(ID, listaNotasFiscaisTipos.get(1).getId());
        verify(repository, times(1)).findAll();
    }

    private void inicializarNotaFiscalTipo() {
        notaFiscalTipo = new NotaFiscalTipo();
        notaFiscalTipo.setId(ID);
        notaFiscalTipo.setNome(ENTRADA);

        notaFiscalTipoDto = new NotaFiscalTipoDto();
        notaFiscalTipoDto.setId(ID);
        notaFiscalTipoDto.setNome(ENTRADA);

        notaFiscalTipoOpcional = Optional.of(notaFiscalTipo);

        when(mapper.converterParaEntidade(notaFiscalTipoDto)).thenReturn(notaFiscalTipo);
        when(mapper.converterParaDto(notaFiscalTipo)).thenReturn(notaFiscalTipoDto);
    }

}
