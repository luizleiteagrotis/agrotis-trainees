package com.agrotis.trainees.crud.service;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.enums.NotaFiscalTipo;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class NotaFiscalServiceTest {

    @Mock
    private NotaFiscalRepository notaFiscalRepository;

    @Mock
    private ParceiroNegocioRepository parceiroNegocioRepository;

    @InjectMocks
    private NotaFiscalService notaFiscalService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    

    @Test
    public void testSalvarNotaFiscal() {
        NotaFiscalDto cabecalhoDto = criarNotaFiscalDto();
        NotaFiscal cabecalhoEsperado = criarNotaFiscal();
        when(parceiroNegocioRepository.save(any(ParceiroNegocio.class))).thenReturn(cabecalhoEsperado.getParceiroNegocio());
        when(notaFiscalRepository.save(any(NotaFiscal.class))).thenReturn(cabecalhoEsperado);

        NotaFiscalDto resultado = notaFiscalService.salvar(cabecalhoDto);

        assertEquals(cabecalhoEsperado.getId(), resultado.getId());
        verify(parceiroNegocioRepository).save(any(ParceiroNegocio.class));
        verify(notaFiscalRepository).save(any(NotaFiscal.class));
    }
    
    
    
//    @Test(expected = EntidadeNaoEncontradaException.class)
//    public void testSalvarNotaFiscalParceiroNegocioNulo() {
//        // Criação do DTO com parceiro de negócio nulo
//        NotaFiscalDto cabecalhoDto = criarNotaFiscalDto();
//        cabecalhoDto.setParceiroNegocio(null);
//        cabecalhoDto.setId(null);
//
//        // Mock para o repositório de parceiro de negócio
//        when(parceiroNegocioRepository.save(any(ParceiroNegocio.class))).thenReturn(null);
//
//        // Mock para o ID do parceiro de negócio
//        when(parceiroNegocioRepository.findById(any(Integer.class))).thenReturn(Optional.empty());
//
//        // Execução do método sob teste
//        notaFiscalService.salvar(cabecalhoDto);
//    }
    
     

    @Test
    public void testBuscarPorIdNotaFiscal() {
        int id = 1;
        NotaFiscal cabecalhoEsperado = criarNotaFiscal();
       when(notaFiscalRepository.findById(id)).thenReturn(Optional.of(cabecalhoEsperado));

       NotaFiscalDto resultado = notaFiscalService.buscarPorId(id);

       assertEquals(cabecalhoEsperado.getId(), resultado.getId());
    }

    @Test
    public void testListarTodosNotaFiscal() {
        List<NotaFiscal> cabecalhosEsperados = new ArrayList<>();
        cabecalhosEsperados.add(criarNotaFiscal());
        when(notaFiscalRepository.findAll()).thenReturn(cabecalhosEsperados);

        List<NotaFiscalDto> resultados = notaFiscalService.listarTodos();

        assertEquals(cabecalhosEsperados.size(), resultados.size());
    }

    @Test
    public void testAtualizarNotaFiscal() {
        int id = 1;
        NotaFiscalDto cabecalhoDto = criarNotaFiscalDto();
        NotaFiscal cabecalhoExistente = criarNotaFiscal();
        when(notaFiscalRepository.findById(id)).thenReturn(Optional.of(cabecalhoExistente));
        when(parceiroNegocioRepository.save(any(ParceiroNegocio.class))).thenReturn(cabecalhoExistente.getParceiroNegocio());
        when(notaFiscalRepository.save(any(NotaFiscal.class))).thenReturn(cabecalhoExistente);

        NotaFiscalDto resultado = notaFiscalService.update(id, cabecalhoDto);

        assertEquals(cabecalhoExistente.getId(), resultado.getId());
    }

    @Test
    public void testDeletarPorIdNotaFiscal() {
        int id = 1;
        NotaFiscal cabecalhoExistente = criarNotaFiscal();
        when(notaFiscalRepository.findById(id)).thenReturn(Optional.of(cabecalhoExistente));

        notaFiscalService.deletarPorId(id);

        verify(notaFiscalRepository).deleteById(id);
    }

    @Test
    public void testUpdateNotaFiscalEntidadeNaoEncontrada() {
        int id = 1;
        NotaFiscalDto cabecalhoDto = criarNotaFiscalDto();
        when(notaFiscalRepository.findById(id)).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class,
                () -> notaFiscalService.update(id, cabecalhoDto));
        assertEquals("Cabechalho com o ID " + id + " não encontrado", exception.getMessage());
    }

    @Test
    public void testDeletarPorIdNotaFiscalEntidadeNaoEncontrada() {
        int id = 1;
        when(notaFiscalRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> notaFiscalService.deletarPorId(id));
    }
    
    @Test
    public void testCalcularValorTotalNotaFiscal() {
        NotaFiscal notaFiscal = criarNotaFiscal();
        BigDecimal valorTotalEsperado = BigDecimal.valueOf(0); // valor esperado baseado nos itens da nota

        BigDecimal valorTotalCalculado = notaFiscalService.calcularValorTotal(notaFiscal);

        assertEquals(valorTotalEsperado, valorTotalCalculado);
    }
    
    
    
    
    @Test
    public void calcularValorTotal_DeveRetornarZero_QuandoCabecalhoSemItens() {
        // Criando um cabeçalho de nota sem itens
        NotaFiscal cabecalho = new NotaFiscal();
        
        // Criando uma instância do serviço
        
        // Verificando se o valor total é zero
        assertEquals(BigDecimal.ZERO, notaFiscalService.calcularValorTotal(cabecalho));
    }
    
    

    private NotaFiscalDto criarNotaFiscalDto() {
        NotaFiscalDto dto = new NotaFiscalDto();
        dto.setId(1);
        dto.setData(LocalDate.now());
        dto.setTipo(NotaFiscalTipo.ENTRADA);
        dto.setNumero(123456);
        ParceiroNegocio fabricante = new ParceiroNegocio();
        fabricante.setNome("Fabricante Teste");
        dto.setParceiroNegocio(fabricante);
        return dto;
    }
    
    private NotaFiscal criarNotaFiscal() {
        NotaFiscal cabecalho = new NotaFiscal();
        cabecalho.setId(1);
        cabecalho.setData(LocalDate.now());
        cabecalho.setTipo(NotaFiscalTipo.ENTRADA);
        cabecalho.setNumero(123456);
        ParceiroNegocio fabricante = new ParceiroNegocio();
        fabricante.setNome("Fabricante Teste");
        cabecalho.setParceiroNegocio(fabricante);
        return cabecalho;
    }
}

    
    
    
    

