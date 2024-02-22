package com.agrotis.trainees.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.exception.NotaFiscalCabecalhoNaoEncontradaException;
import com.agrotis.trainees.crud.exception.NotaFiscalDuplicadaException;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;
import com.agrotis.trainees.crud.utils.NotaFiscalDTOMapper;

public class NotaFiscalServiceTest {

    private final String NUMERO = "123456";
    private final String NOME = "ENTRADA";

    @Mock
    private NotaFiscalRepository repository;

    @Mock
    private NotaFiscalDTOMapper mapper;

    @InjectMocks
    private NotaFiscalService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deveriaNaoSalvarNotaFiscalExistente() {
        NotaFiscal notaFiscal = new NotaFiscal();
        NotaFiscalDto notaFiscalDto = new NotaFiscalDto();
        when(mapper.converterParaEntidade(notaFiscalDto)).thenReturn(notaFiscal);
        when(repository.existsByNumeroAndNotaFiscalTipo(any(), any())).thenReturn(true);

        Exception excecao = assertThrows(NotaFiscalDuplicadaException.class, () -> {
            service.salvar(notaFiscalDto);
        });

        assertEquals("Nota fiscal já existe", excecao.getMessage());
    }

    @Test
    public void deveSalvarNotaFiscal() {
        NotaFiscal notaFiscal = new NotaFiscal();
        NotaFiscalDto notaFiscalDto = new NotaFiscalDto();
        when(mapper.converterParaEntidade(notaFiscalDto)).thenReturn(notaFiscal);
        when(repository.existsByNumeroAndNotaFiscalTipo(any(), any())).thenReturn(false);
        when(repository.save(notaFiscal)).thenReturn(notaFiscal);

        when(service.salvar(notaFiscalDto)).thenReturn(notaFiscalDto);
        verify(repository, times(1)).save(notaFiscal);
        NotaFiscalDto notaFiscalDtoSalva = service.salvar(notaFiscalDto);
        assertEquals(notaFiscalDto, notaFiscalDtoSalva);
    }

    @Test
    public void deveriaNaoAtualizarNotaFiscal() {
        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setId(1000);
        notaFiscal.setNumero(NUMERO);
        NotaFiscalTipo tipo = new NotaFiscalTipo();
        tipo.setNome(NOME);
        notaFiscal.setNotaFiscalTipo(tipo);

        NotaFiscalDto notaFiscalDto = new NotaFiscalDto();
        notaFiscalDto.setId(notaFiscal.getId());
        notaFiscalDto.setNumero(notaFiscal.getNumero());
        notaFiscal.setNotaFiscalTipo(notaFiscal.getNotaFiscalTipo());

        when(mapper.converterParaEntidade(notaFiscalDto)).thenReturn(notaFiscal);
        when(repository.existsByNumeroAndNotaFiscalTipoAndIdNot(notaFiscal.getNumero(), notaFiscal.getNotaFiscalTipo(),
                        notaFiscal.getId())).thenReturn(true);

        Exception excecao = assertThrows(NotaFiscalDuplicadaException.class, () -> {
            service.atualizar(notaFiscalDto);
        });

        assertEquals("Nota com o numero e o tipo já existe: " + NUMERO + " " + NOME, excecao.getMessage());
    }

    @Test
    public void deveAtualizarNotaFiscal() {
        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setId(1000);
        notaFiscal.setNumero(NUMERO);
        NotaFiscalTipo tipo = new NotaFiscalTipo();
        tipo.setNome(NOME);
        notaFiscal.setNotaFiscalTipo(tipo);

        NotaFiscalDto notaFiscalDto = new NotaFiscalDto();
        notaFiscalDto.setId(notaFiscal.getId());
        notaFiscalDto.setNumero(notaFiscal.getNumero());
        notaFiscal.setNotaFiscalTipo(notaFiscal.getNotaFiscalTipo());

        when(mapper.converterParaEntidade(notaFiscalDto)).thenReturn(notaFiscal);
        when(repository.existsByNumeroAndNotaFiscalTipoAndIdNot(notaFiscal.getNumero(), notaFiscal.getNotaFiscalTipo(),
                        notaFiscal.getId())).thenReturn(false);
        when(repository.save(notaFiscal)).thenReturn(notaFiscal);

        when(service.atualizar(notaFiscalDto)).thenReturn(notaFiscalDto);
        verify(repository, times(1)).save(notaFiscal);
        NotaFiscalDto notaFiscalDtoSalva = service.atualizar(notaFiscalDto);
        assertEquals(notaFiscalDto, notaFiscalDtoSalva);
    }

    @Test
    public void naoDeveriaBuscarPorIdInexistente() {
        when(repository.findById(1000)).thenReturn(Optional.empty());
        
        Exception excecao = assertThrows(NotaFiscalCabecalhoNaoEncontradaException.class, () -> {
            service.buscarPorId(1000);
        });
        
        assertEquals("Nota Fiscal não encontrada para o id " + 1000, excecao.getMessage());
    }

    @Test
    public void deveBuscarPorId() {
        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setId(1000);
        NotaFiscalDto notaFiscalDto = new NotaFiscalDto();
        when(mapper.converterParaDto(notaFiscal)).thenReturn(notaFiscalDto);
        when(repository.findById(1000)).thenReturn(Optional.of(notaFiscal));
        notaFiscalDto.setId(notaFiscal.getId());

        when(service.buscarPorId(notaFiscalDto.getId())).thenReturn(notaFiscalDto);
        verify(repository, times(1)).findById(1000);
        NotaFiscalDto notaAchada = service.buscarPorId(1000);
        assertEquals(notaFiscal.getId(), notaAchada.getId());
    }

    @Test
    public void naoDeveriaBuscarPorNomeInexistente() {
        when(repository.findAllByNumero(NUMERO)).thenReturn(new ArrayList<>());
        
        Exception excecao = assertThrows(NotaFiscalCabecalhoNaoEncontradaException.class, () -> { 
            service.buscarPorNumero(NUMERO);
        });
        
        assertEquals("Nota Fiscal não encontrada para o numero " + NUMERO , excecao.getMessage());
    }

    @Test
    public void deveBuscarNotaFiscalPorNumero() {
        List<NotaFiscal> notaFiscalLista = Arrays.asList(new NotaFiscal(), new NotaFiscal());
        when(repository.findAllByNumero(NUMERO)).thenReturn(notaFiscalLista);

        List<NotaFiscalDto> notaFiscalDtos = service.buscarPorNumero(NUMERO);
        assertNotNull(notaFiscalDtos);
        assertEquals(2, notaFiscalDtos.size());
        verify(repository, times(1)).findAllByNumero(NUMERO);
    }

    @Test
    public void listarTodos() {
        List<NotaFiscal> notaFiscalLista = Arrays.asList(new NotaFiscal(), new NotaFiscal());
        when(repository.findAll()).thenReturn(notaFiscalLista);

        List<NotaFiscalDto> notaFiscalDtos = service.listarTodos();
        assertNotNull(notaFiscalDtos);
        assertEquals(2, notaFiscalDtos.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void deveRemoverNotaFiscal() {
        service.deletarPorId(1000);
        verify(repository, times(1)).deleteById(1000);
    }
}
