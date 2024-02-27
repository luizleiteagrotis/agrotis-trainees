package com.agrotis.trainees.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.exception.NotaFiscalCabecalhoNaoEncontradaException;
import com.agrotis.trainees.crud.exception.NotaFiscalDuplicadaException;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;
import com.agrotis.trainees.crud.utils.NotaFiscalDTOMapper;

public class NotaFiscalServiceTest {

    private final Integer ID = 1;
    private final LocalDate DATA = LocalDate.now();
    private final String NUMERO = "123456";
    private final BigDecimal VALORTOTAL = new BigDecimal(0);
    private final List<ItemNotaFiscal> ITENS = new ArrayList<>();

    private final String TIPO = "ENTRADA";

    @Mock
    private NotaFiscalRepository repository;

    @Mock
    private NotaFiscalDTOMapper mapper;

    @Mock
    private ParceiroNegocio fabricante;

    @Mock
    private NotaFiscalTipo notaFiscalTipo;

    @InjectMocks
    private NotaFiscalService service;

    private NotaFiscal notaFiscal;

    private NotaFiscalDto notaFiscalDto;

    private Optional<NotaFiscal> notaFiscalOpcional;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        inicializarNotaFiscal();
    }

    @Test
    public void deveriaNaoSalvarNotaFiscalExistente() {
        when(repository.existsByNumeroAndNotaFiscalTipo(any(), any())).thenReturn(true);

        Exception excecao = assertThrows(NotaFiscalDuplicadaException.class, () -> {
            service.salvar(notaFiscalDto);
        });
        
        assertEquals(NotaFiscalDuplicadaException.class, excecao.getClass());
        assertEquals("Nota fiscal já existe", excecao.getMessage());
    }

    @Test
    public void deveSalvarNotaFiscal() {
        when(repository.existsByNumeroAndNotaFiscalTipo(any(), any())).thenReturn(false);
        when(repository.save(any())).thenReturn(notaFiscal);
        
        NotaFiscalDto notaSalva = service.salvar(notaFiscalDto);
        
        verify(repository, times(1)).save(notaFiscal);
        assertNotNull(notaSalva);
        assertEquals(NotaFiscalDto.class, notaSalva.getClass());
        assertEquals(ID, notaSalva.getId());
    }

    @Test
    public void deveriaNaoAtualizarNotaFiscal() {
        when(repository.existsByNumeroAndNotaFiscalTipoAndIdNot(anyString(), any(),
                        anyInt())).thenReturn(true);
        
        Exception excecao = assertThrows(NotaFiscalDuplicadaException.class, () -> {
            service.atualizar(notaFiscalDto);
        });

         assertEquals("Nota com o numero e o tipo já existe: " + NUMERO + " " + TIPO, excecao.getMessage());
    }

    @Test
    public void deveAtualizarNotaFiscal() {
        when(repository.existsByNumeroAndNotaFiscalTipoAndIdNot(anyString(), any(),
                        anyInt())).thenReturn(false);
        when(repository.save(any())).thenReturn(notaFiscal);

        NotaFiscalDto notaFiscalAtualizada = service.atualizar(notaFiscalDto);

        verify(repository, times(1)).save(notaFiscal);
        assertNotNull(notaFiscalAtualizada);
        assertEquals(NotaFiscalDto.class, notaFiscalAtualizada.getClass());
        assertEquals(ID, notaFiscalAtualizada.getId());
    }

    @Test
    public void naoDeveriaBuscarPorIdInexistente() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        
        Exception excecao = assertThrows(NotaFiscalCabecalhoNaoEncontradaException.class, () -> {
            service.buscarPorId(ID);
        });
        
        assertEquals(NotaFiscalCabecalhoNaoEncontradaException.class, excecao.getClass());
        assertEquals("Nota Fiscal não encontrada para o id " + ID, excecao.getMessage());
    }

    @Test
    public void deveBuscarPorIdExistente() {
        when(repository.findById(anyInt())).thenReturn(notaFiscalOpcional);

        NotaFiscalDto notaAchada = service.buscarPorId(ID);

        assertNotNull(notaAchada);
        assertEquals(NotaFiscalDto.class, notaAchada.getClass());
        assertEquals(ID, notaAchada.getId());
        verify(repository, times(1)).findById(ID);
    }

    @Test
    public void naoDeveriaBuscarPorNumeroInexistente() {
        when(repository.findAllByNumero(anyString())).thenReturn(new ArrayList<>());
        
        Exception excecao = assertThrows(NotaFiscalCabecalhoNaoEncontradaException.class, () -> { 
            service.buscarPorNumero(NUMERO);
        });
        
        assertEquals("Nota Fiscal não encontrada para o numero " + NUMERO , excecao.getMessage());
    }

    @Test
    public void deveBuscarNotaFiscalPorNumero() {
        when(repository.findAllByNumero(NUMERO)).thenReturn(List.of(notaFiscal, notaFiscal));

        List<NotaFiscalDto> notasFiscais = service.buscarPorNumero(NUMERO);

        assertNotNull(notasFiscais);
        assertEquals(2, notasFiscais.size());
        assertEquals(NotaFiscalDto.class, notasFiscais.get(0).getClass());
        assertEquals(ID, notasFiscais.get(1).getId());        
        verify(repository, times(1)).findAllByNumero(NUMERO);
    }

    @Test
    public void listarTodos() {
        when(repository.findAll()).thenReturn(List.of(notaFiscal, notaFiscal));

        List<NotaFiscalDto> notasFiscais = service.listarTodos();
        
        assertNotNull(notasFiscais);
        assertEquals(2, notasFiscais.size());
        assertEquals(NotaFiscalDto.class, notasFiscais.get(0).getClass());
        assertEquals(ID, notasFiscais.get(1).getId());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void  deveriaNaoDeletarNotaIdInexistente() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        
        Exception excecao = assertThrows(NotaFiscalCabecalhoNaoEncontradaException.class, () -> {
            service.deletarPorId(ID);
        });
            
            assertEquals(NotaFiscalCabecalhoNaoEncontradaException.class, excecao.getClass());
            assertEquals("Nota Fiscal não encontrada para o id " + ID, excecao.getMessage());
    }

    @Test
    public void deveRemoverNotaFiscal() {
        when(repository.findById(anyInt())).thenReturn(notaFiscalOpcional);
        doNothing().when(repository).deleteById(anyInt());
        service.deletarPorId(ID);
        verify(repository, times(1)).deleteById(anyInt());
    }

    private void inicializarNotaFiscal() {
        notaFiscalTipo = new NotaFiscalTipo();
        notaFiscalTipo.setNome(TIPO);

        notaFiscal = new NotaFiscal();
        notaFiscal.setId(ID);
        notaFiscal.setNumero(NUMERO);
        notaFiscal.setData(DATA);
        notaFiscal.setValorTotal(VALORTOTAL);
        notaFiscal.setItens(ITENS);
        notaFiscal.setParceiroNegocio(fabricante);
        notaFiscal.setNotaFiscalTipo(notaFiscalTipo);

        notaFiscalDto = new NotaFiscalDto();
        notaFiscalDto.setId(ID);
        notaFiscalDto.setNumero(NUMERO);
        notaFiscalDto.setData(DATA);
        notaFiscalDto.setValorTotal(VALORTOTAL);
        notaFiscalDto.setItens(ITENS);
        notaFiscalDto.setParceiroNegocio(fabricante);
        notaFiscalDto.setNotaFiscalTipo(notaFiscalTipo);

        notaFiscalOpcional = Optional.of(notaFiscal);

        when(mapper.converterParaEntidade(notaFiscalDto)).thenReturn(notaFiscal);
        when(mapper.converterParaDto(notaFiscal)).thenReturn(notaFiscalDto);
    }

}
