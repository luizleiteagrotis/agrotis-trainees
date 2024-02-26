package com.agrotis.trainees.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agrotis.trainees.crud.dtos.CabecalhoNotaDto;
import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.enums.TipoNota;
import com.agrotis.trainees.crud.repository.CabecalhoNotaRepository;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;
import com.agrotis.trainees.crud.service.impl.CabecalhoNotaServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CabecalhoNotaServiceTest {

    @Mock
    private CabecalhoNotaRepository cabecalhoNotaRepository;

    @Mock
    private ParceiroNegocioRepository parceiroNegocioRepository;

    @InjectMocks
    private CabecalhoNotaServiceImpl cabecalhoNotaService;

    @Test
    void testSalvarCabecalhoNota() {
        CabecalhoNotaDto cabecalhoDto = criarCabecalhoNotaDto();
        CabecalhoNota cabecalhoEsperado = criarCabecalhoNota();
        when(parceiroNegocioRepository.save(any(ParceiroNegocio.class))).thenReturn(cabecalhoEsperado.getParceiroNegocio());
        when(cabecalhoNotaRepository.save(any(CabecalhoNota.class))).thenReturn(cabecalhoEsperado);

        CabecalhoNotaDto resultado = cabecalhoNotaService.salvar(cabecalhoDto);

        assertEquals(cabecalhoEsperado.getId(), resultado.getId());
        verify(parceiroNegocioRepository).save(any(ParceiroNegocio.class));
        verify(cabecalhoNotaRepository).save(any(CabecalhoNota.class));
    }

    @Test
    void testBuscarPorIdCabecalhoNota() {
        int id = 1;
        CabecalhoNota cabecalhoEsperado = criarCabecalhoNota();
        when(cabecalhoNotaRepository.findById(id)).thenReturn(Optional.of(cabecalhoEsperado));

        CabecalhoNotaDto resultado = cabecalhoNotaService.buscarPorId(id);

        assertEquals(cabecalhoEsperado.getId(), resultado.getId());
    }

    @Test
    void testListarTodosCabecalhosNota() {
        List<CabecalhoNota> cabecalhosEsperados = new ArrayList<>();
        cabecalhosEsperados.add(criarCabecalhoNota());
        when(cabecalhoNotaRepository.findAll()).thenReturn(cabecalhosEsperados);

        List<CabecalhoNotaDto> resultados = cabecalhoNotaService.listarTodos();

        assertEquals(cabecalhosEsperados.size(), resultados.size());
    }

    @Test
    void testAtualizarCabecalhoNota() {
        int id = 1;
        CabecalhoNotaDto cabecalhoDto = criarCabecalhoNotaDto();
        CabecalhoNota cabecalhoExistente = criarCabecalhoNota();
        when(cabecalhoNotaRepository.findById(id)).thenReturn(Optional.of(cabecalhoExistente));
        when(parceiroNegocioRepository.save(any(ParceiroNegocio.class))).thenReturn(cabecalhoExistente.getParceiroNegocio());
        when(cabecalhoNotaRepository.save(any(CabecalhoNota.class))).thenReturn(cabecalhoExistente);

        CabecalhoNotaDto resultado = cabecalhoNotaService.atualizar(id, cabecalhoDto);

        assertEquals(cabecalhoExistente.getId(), resultado.getId());
    }

    @Test
    void testDeletarPorIdCabecalhoNota() {
        int id = 1;
        CabecalhoNota cabecalhoExistente = criarCabecalhoNota();
        when(cabecalhoNotaRepository.findById(id)).thenReturn(Optional.of(cabecalhoExistente));

        cabecalhoNotaService.deletarPorId(id);
        
        verify(cabecalhoNotaRepository).deleteById(id);
    }
    
    @Test
    void testAtualizarCabecalhoNotaEntidadeNaoEncontrada() {
        int id = 1;
        CabecalhoNotaDto cabecalhoDto = criarCabecalhoNotaDto();
        when(cabecalhoNotaRepository.findById(id)).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            cabecalhoNotaService.atualizar(id, cabecalhoDto);
        });
        assertEquals("Cabechalho com o ID " + id + " não encontrado", exception.getMessage());
    }

    @Test
    void testDeletarPorIdCabecalhoNotaEntidadeNaoEncontrada() {
        int id = 1;
        when(cabecalhoNotaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> {
            cabecalhoNotaService.deletarPorId(id);
        });
    }

    private CabecalhoNotaDto criarCabecalhoNotaDto() {
        CabecalhoNotaDto dto = new CabecalhoNotaDto();
        dto.setData(LocalDate.now());
        dto.setNotaFiscalTipo(TipoNota.ENTRADA);
        dto.setNumero(123456);
        ParceiroNegocio fabricante = new ParceiroNegocio();
        fabricante.setNome("Fabricante Teste");
        dto.setParceiroNegocio(fabricante);
        return dto;
    }

    
    private CabecalhoNota criarCabecalhoNota() {
        CabecalhoNota cabecalho = new CabecalhoNota();
        cabecalho.setData(LocalDate.now());
        cabecalho.setNotaFiscalTipo(TipoNota.ENTRADA);
        cabecalho.setNumero(123456);
        ParceiroNegocio fabricante = new ParceiroNegocio();
        fabricante.setNome("Fabricante Teste");
        cabecalho.setParceiroNegocio(fabricante);
        return cabecalho;
    }
}
