package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.NotaFiscalC;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.NotaFiscalCRepository;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.repository.ProdutoRepository;

import dto.CabecalhoDto;

@ExtendWith(MockitoExtension.class)

public class CabecalhoNotaTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ParceiroNegocioRepository parceiroNegocioRepository1;

    @InjectMocks
    private ProdutoService service;


    @Mock
    private NotaFiscalCRepository cabecalhoNotaRepository;

    @Mock
    private ParceiroNegocioRepository parceiroNegocioRepository;

    @InjectMocks
    private NotaFiscalCService cabecalhoNotaService;

    
    @Test
    void testAtualizarCabecalhoNota() {
        int id = 1;
        CabecalhoDto cabecalhoDto = criarCabecalhoNotaDto();
        NotaFiscalC cabecalhoExistente = criarCabecalhoNota();
        when(cabecalhoNotaRepository.findById(id)).thenReturn(Optional.of(cabecalhoExistente));
        when(parceiroNegocioRepository1.save(any(ParceiroNegocio.class))).thenReturn(cabecalhoExistente.getParceiroNegocio());

        CabecalhoDto resultado = (CabecalhoDto) cabecalhoNotaService.atualizar(id, cabecalhoDto);

        assertEquals(cabecalhoExistente.getId(), resultado.getId());
    }

    @Test
    void testDeletarPorId() {
        int id = 1;
        NotaFiscalC cabecalhoExistente = criarCabecalhoNota();
        when(cabecalhoNotaRepository.findById(id)).thenReturn(Optional.of(cabecalhoExistente));

        cabecalhoNotaService.deleterPorId(id);
        
        verify(cabecalhoNotaRepository).deleteById(id);
    }
    
    
    

    private CabecalhoDto criarCabecalhoNotaDto() {
        CabecalhoDto dto = new CabecalhoDto();
        dto.setData(LocalDate.now());
        dto.setNotaFiscalTipo(ItemNota.ENTRADA);
        dto.setNumero(123456);
        ParceiroNegocio fabricante = new ParceiroNegocio();
        fabricante.setNome("Fabricante Teste");
        dto.setParceiroNegocio(fabricante);
        return dto;
    }

    
    private NotaFiscalC criarCabecalhoNota() {
        NotaFiscalC cabecalho = new NotaFiscalC();
        cabecalho.setData(LocalDate.now());
        cabecalho.setNotaFiscalTipo(ItemNota.ENTRADA);
        cabecalho.setNumeroNota(123456);
        ParceiroNegocio fabricante = new ParceiroNegocio();
        fabricante.setNome("Fabricante Teste");
        cabecalho.setParceiroNegocio(fabricante);
        return cabecalho;
    }

}
