package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;

public class NotaFiscalServiceTest {

    @Mock
    private NotaFiscalRepository repository;

    @InjectMocks
    private NotaFiscalService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testeSalvar() throws NotFoundException {
        NotaFiscalDto notaDto = new NotaFiscalDto();
        notaDto.setId(578);
        notaDto.setDataEmissao(LocalDate.now());
        notaDto.setItensNota(new ArrayList<>());
        notaDto.setNotaFiscalTipo(NotaFiscalTipo.ENTRADA);
        notaDto.setNumero(123456);
        notaDto.setValorTotal(BigDecimal.ZERO);

        ParceiroNegocioDto parceiroDto = new ParceiroNegocioDto();
        parceiroDto.setId(578);
        parceiroDto.setNome("Parceiro");
        parceiroDto.setInscricaoFiscal("Inscricao");
        notaDto.setParceiroNegocio(parceiroDto);

        NotaFiscal entidade = new NotaFiscal();
        entidade.setId(notaDto.getId());
        entidade.setDataEmissao(notaDto.getDataEmissao());
        entidade.setItensNota(notaDto.getItensNota());
        entidade.setNotaFiscalTipo(notaDto.getNotaFiscalTipo());
        entidade.setNumero(notaDto.getNumero());
        entidade.setValorTotal(notaDto.getValorTotal());

        ParceiroNegocio parceiroEntidade = new ParceiroNegocio();
        parceiroEntidade.setId(parceiroDto.getId());
        parceiroEntidade.setNome(parceiroDto.getNome());
        parceiroEntidade.setInscricaoFiscal(parceiroDto.getInscricaoFiscal());
        entidade.setParceiroNegocio(parceiroEntidade);

        when(repository.existsByNumeroAndNotaFiscalTipo(any(Integer.class), any(NotaFiscalTipo.class)))
                        .thenReturn(Optional.empty());

        when(repository.save(any())).thenReturn(entidade);

        NotaFiscalDto resultado = service.salvar(notaDto);

        assertEquals(notaDto.getNumero(), resultado.getNumero());
        verify(repository, times(1)).save(any());
    }

    @Test
    public void testeDeletarId() {
        int id = 578;
        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(notaFiscal));
        doNothing().when(repository).deleteById(id);

        assertDoesNotThrow(() -> {
            service.deletarPorId(id);
        });

        verify(repository, times(1)).deleteById(id);
    }

    @Test
    public void testeDeletarIdNaoEncontrado() {
        int id = 578;

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.deletarPorId(id));

        verify(repository, never()).deleteById(id);
    }

    @Test
    public void testeInserir() throws NotFoundException {
        NotaFiscalDto notaDto = new NotaFiscalDto();
        notaDto.setId(1);
        notaDto.setDataEmissao(LocalDate.now());
        notaDto.setItensNota(new ArrayList<>());
        notaDto.setNotaFiscalTipo(NotaFiscalTipo.ENTRADA);
        notaDto.setNumero(123456);
        notaDto.setValorTotal(BigDecimal.ZERO);

        ParceiroNegocioDto parceiroDto = new ParceiroNegocioDto();
        parceiroDto.setId(1);
        parceiroDto.setNome("Parceiro");
        parceiroDto.setInscricaoFiscal("Inscricao");
        notaDto.setParceiroNegocio(parceiroDto);

        NotaFiscal entidade = new NotaFiscal();
        entidade.setId(notaDto.getId());
        entidade.setDataEmissao(notaDto.getDataEmissao());
        entidade.setItensNota(notaDto.getItensNota());
        entidade.setNotaFiscalTipo(notaDto.getNotaFiscalTipo());
        entidade.setNumero(notaDto.getNumero());
        entidade.setValorTotal(notaDto.getValorTotal());

        ParceiroNegocio parceiroEntidade = new ParceiroNegocio();
        parceiroEntidade.setId(parceiroDto.getId());
        parceiroEntidade.setNome(parceiroDto.getNome());
        parceiroEntidade.setInscricaoFiscal(parceiroDto.getInscricaoFiscal());
        entidade.setParceiroNegocio(parceiroEntidade);

        when(repository.existsByNumeroAndNotaFiscalTipo(any(Integer.class), any(NotaFiscalTipo.class)))
                        .thenReturn(Optional.of(new NotaFiscal()));

        when(repository.save(any())).thenReturn(entidade).thenReturn(null);
        NotaFiscalDto resultado = service.salvar(notaDto);

        assertEquals(notaDto.getNumero(), resultado.getNumero());
        verify(repository, times(1)).save(any());
    }

    @Test
    public void testeBuscarPorId() throws NotFoundException {
        int id = 578;
        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(notaFiscal));

        NotaFiscalDto resultado = service.buscarPorId(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(repository, times(1)).findById(id);
    }

    @Test
    public void testeBuscarPorIdNaoEncontrado() {
        int id = 578;

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> {
            service.buscarPorId(id);
        });

        verify(repository, times(1)).findById(id);
    }

    @Test
    public void testeBuscarPorNumero() {
        int numero = 1;
        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setNumero(numero);

        when(repository.findByNumero(numero)).thenReturn(Optional.of(notaFiscal));

        NotaFiscal resultado = service.buscarPorNumero(numero);

        assertNotNull(resultado);
        assertEquals(numero, resultado.getNumero());
        verify(repository, times(1)).findByNumero(numero);
    }

    @Test
    public void testeBuscarPorNotaFiscalTipo() {
        NotaFiscalTipo tipo = NotaFiscalTipo.ENTRADA;
        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setNotaFiscalTipo(tipo);

        when(repository.findByNotaFiscalTipo(tipo)).thenReturn(Optional.of(notaFiscal));

        NotaFiscal resultado = service.buscarPorNotaFiscalTipo(tipo);

        assertNotNull(resultado);
        assertEquals(tipo, resultado.getNotaFiscalTipo());
        verify(repository, times(1)).findByNotaFiscalTipo(tipo);
    }

    @Test
    public void testeBuscarPorNotaFiscalTipoNaoEncontrado() {
        NotaFiscalTipo tipo = NotaFiscalTipo.ENTRADA;

        when(repository.findByNotaFiscalTipo(tipo)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> {
            service.buscarPorNotaFiscalTipo(tipo);
        });

        verify(repository, times(1)).findByNotaFiscalTipo(tipo);
    }

    @Test
    public void testeBuscarPorParceiroNegocio() {
        int parceiroId = 559;

        ParceiroNegocio parceiro = new ParceiroNegocio();
        parceiro.setId(parceiroId);

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setParceiroNegocio(parceiro);

        when(repository.findByParceiroNegocio(parceiro)).thenReturn(Optional.of(notaFiscal));

        Optional<NotaFiscal> resultado = service.buscarPorParceiroNegocio(parceiro);

        assertTrue(resultado.isPresent());
        NotaFiscal notaFiscalRetornada = resultado.get();
        assertEquals(parceiroId, notaFiscalRetornada.getParceiroNegocio().getId());
        verify(repository, times(1)).findByParceiroNegocio(parceiro);
    }

    @Test
    public void testeBuscarPorParceiroNegocioNaoEncontrado() {
        int parceiroId = 559;

        ParceiroNegocio parceiro = new ParceiroNegocio();
        parceiro.setId(parceiroId);

        when(repository.findByParceiroNegocio(parceiro)).thenReturn(Optional.empty());

        Optional<NotaFiscal> resultado = service.buscarPorParceiroNegocio(parceiro);

        assertFalse(resultado.isPresent());
        verify(repository, times(1)).findByParceiroNegocio(parceiro);
    }

    @Test
    public void testeListarTodos() {
        List<NotaFiscal> listaNotas = Arrays.asList(new NotaFiscal(), new NotaFiscal());
        when(repository.findAll()).thenReturn(listaNotas);

        List<NotaFiscalDto> resultado = service.listarTodos();

        assertNotNull(resultado);
        assertEquals(listaNotas.size(), resultado.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testeListarTodosVazio() {
        when(repository.findAll()).thenReturn(new ArrayList<>());

        List<NotaFiscalDto> resultado = service.listarTodos();

        assertNotNull(resultado);
        assertEquals(0, resultado.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testeAtualizar() throws NotFoundException {
        int id = 578;
        NotaFiscalDto notaDto = new NotaFiscalDto();
        notaDto.setId(id);
        notaDto.setDataEmissao(LocalDate.now());
        notaDto.setItensNota(new ArrayList<>());
        notaDto.setNotaFiscalTipo(NotaFiscalTipo.ENTRADA);
        notaDto.setNumero(123456);
        notaDto.setValorTotal(BigDecimal.ZERO);

        ParceiroNegocioDto parceiroDto = new ParceiroNegocioDto();
        parceiroDto.setId(559);
        parceiroDto.setNome("Parceiro");
        parceiroDto.setInscricaoFiscal("Inscricao");
        notaDto.setParceiroNegocio(parceiroDto);

        NotaFiscal entidade = new NotaFiscal();
        entidade.setId(notaDto.getId());
        entidade.setDataEmissao(notaDto.getDataEmissao());
        entidade.setItensNota(notaDto.getItensNota());
        entidade.setNotaFiscalTipo(notaDto.getNotaFiscalTipo());
        entidade.setNumero(notaDto.getNumero());
        entidade.setValorTotal(notaDto.getValorTotal());

        ParceiroNegocio parceiroEntidade = new ParceiroNegocio();
        parceiroEntidade.setId(parceiroDto.getId());
        parceiroEntidade.setNome(parceiroDto.getNome());
        parceiroEntidade.setInscricaoFiscal(parceiroDto.getInscricaoFiscal());
        entidade.setParceiroNegocio(parceiroEntidade);

        when(repository.findById(eq(578))).thenReturn(Optional.of(entidade));
        when(repository.save(any())).thenReturn(entidade);

        NotaFiscalDto resultado = service.atualizar(id, notaDto);

        assertEquals(notaDto.getNumero(), resultado.getNumero());
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(any());

        verify(service, times(1)).atualizarValorTotal(entidade);
    }

    @Test
    public void testeAtualizarNaoEncontrado() {
        int id = 1;

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.atualizar(id, new NotaFiscalDto()));

        verify(repository, never()).save(any());
    }

    @Test
    public void testeAtualizarValorTotal() throws NotFoundException {
        int id = 578;
        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setId(id);
        notaFiscal.setDataEmissao(LocalDate.parse("2024-02-16"));
        notaFiscal.setNumero(1);
        notaFiscal.setNotaFiscalTipo(NotaFiscalTipo.ENTRADA);

        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
        parceiroNegocio.setId(559);
        notaFiscal.setParceiroNegocio(parceiroNegocio);

        notaFiscal.setValorTotal(BigDecimal.ZERO);

        NotaFiscalItem item1 = new NotaFiscalItem(new BigDecimal("50.00"), new BigDecimal("30.00"));
        NotaFiscalItem item2 = new NotaFiscalItem(new BigDecimal("30.00"), new BigDecimal("20.00"));

        item1.setNotaFiscal(notaFiscal);
        item2.setNotaFiscal(notaFiscal);

        notaFiscal.setItensNota(Arrays.asList(item1, item2));

        when(repository.findById(any())).thenReturn(Optional.of(notaFiscal));
        when(repository.save(any())).thenReturn(notaFiscal);

        service.atualizarValorTotal(entidade);
        verify(repository, times(1)).save(notaFiscal);
        assertEquals(new BigDecimal("100.00"), notaFiscal.getValorTotal());
    }

    @Test
    public void testAtualizarValorTotal() throws NotFoundException {
        testAtualizarValorTotalParaTipo(NotaFiscalTipo.ENTRADA, new BigDecimal("80.00"));
        testAtualizarValorTotalParaTipo(NotaFiscalTipo.SAIDA, new BigDecimal("-80.00"));
        testAtualizarValorTotalParaItens(null, BigDecimal.ZERO);
        testAtualizarValorTotalParaItens(new ArrayList<>(), BigDecimal.ZERO);

        List<NotaFiscalItem> itens = Arrays.asList(new NotaFiscalItem(new BigDecimal("50.00"), new BigDecimal("20.00")),
                        new NotaFiscalItem(new BigDecimal("30.00"), new BigDecimal("20.00")));
        testAtualizarValorTotalParaItens(itens, new BigDecimal("80.00"));
    }

    private void testAtualizarValorTotalParaTipo(NotaFiscalTipo tipo, BigDecimal totalEsperado) throws NotFoundException {
        Integer id = 578;
        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setId(id);
        notaFiscal.setNotaFiscalTipo(tipo);
        notaFiscal.setItensNota(Collections.emptyList());

        when(repository.findById(id)).thenReturn(Optional.of(notaFiscal));
        when(repository.save(any())).thenReturn(notaFiscal);

        service.atualizarValorTotal(id);

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(notaFiscal);

        assertEquals(totalEsperado, notaFiscal.getValorTotal());
    }

    private void testAtualizarValorTotalParaItens(List<NotaFiscalItem> itens, BigDecimal totalEsperado) throws NotFoundException {
        int id = 578;
        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setId(id);
        notaFiscal.setNotaFiscalTipo(NotaFiscalTipo.ENTRADA);
        notaFiscal.setItensNota(itens);

        when(repository.findById(id)).thenReturn(Optional.of(notaFiscal));
        when(repository.save(any())).thenReturn(notaFiscal);

        service.atualizarValorTotal(notaFiscal);

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(notaFiscal);

        assertEquals(totalEsperado, notaFiscal.getValorTotal());
    }

}
