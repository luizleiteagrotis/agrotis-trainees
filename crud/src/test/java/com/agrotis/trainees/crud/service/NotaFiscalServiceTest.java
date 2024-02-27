package com.agrotis.trainees.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;

@RunWith(MockitoJUnitRunner.class)
public class NotaFiscalServiceTest {

    @Mock
    private NotaFiscalRepository repository;

    @Mock
    private NotaFiscalConversaoService conversao;

    @Mock
    private ParceiroNegocioConversaoService conversaoParceiro;

    @InjectMocks
    private NotaFiscalService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Teste para o método salvar")
    public void testeSalvar() {
        NotaFiscalDto dto = new NotaFiscalDto();
        dto.setId(1);
        dto.setData(LocalDate.now());
        dto.setItens(new ArrayList<>());
        dto.setNotaFiscalTipo(NotaFiscalTipo.ENTRADA);
        dto.setNumero(123456);
        dto.setValorTotal(BigDecimal.ZERO);

        ParceiroNegocioDto parceiroDto = new ParceiroNegocioDto();
        parceiroDto.setId(1);
        parceiroDto.setNome("Parceiro");
        parceiroDto.setInscricaoFiscal("Inscricao");
        dto.setParceiroNegocio(parceiroDto);

        NotaFiscal entidade = new NotaFiscal();
        entidade.setId(dto.getId());
        entidade.setData(dto.getData());
        entidade.setItens(dto.getItens());
        entidade.setNotaFiscalTipo(dto.getNotaFiscalTipo());
        entidade.setNumero(dto.getNumero());
        entidade.setValorTotal(dto.getValorTotal());

        ParceiroNegocio parceiroEntidade = new ParceiroNegocio();
        parceiroEntidade.setId(parceiroDto.getId());
        parceiroEntidade.setNome(parceiroDto.getNome());
        parceiroEntidade.setInscricaoFiscal(parceiroDto.getInscricaoFiscal());
        entidade.setParceiroNegocio(parceiroEntidade);

        when(conversao.converterParaEntidade(dto)).thenReturn(entidade);
        when(repository.save(entidade)).thenReturn(entidade);
        when(conversao.converterParaDto(entidade)).thenReturn(dto);

        NotaFiscalDto resultado = service.salvar(dto);

        assertEquals(dto.getNumero(), resultado.getNumero());
        verify(repository, times(1)).save(entidade);

    }

    @Test
    @DisplayName("Teste para o método salvar")
    public void testeSalvarDto() {
        // to do
    }

    @Test
    @DisplayName("Teste para o método buscarPorId")
    public void testeBuscarId() throws NotFoundException {

        NotaFiscalDto dto = new NotaFiscalDto();
        dto.setId(1);
        dto.setNumero(123456);
        dto.setNotaFiscalTipo(NotaFiscalTipo.SAIDA);

        NotaFiscal entidade = new NotaFiscal();

        when(repository.findById(1)).thenReturn(Optional.of(entidade));
        when(conversao.converterParaDto(entidade)).thenReturn(dto);

        NotaFiscalDto resultado = service.buscarPorId(1);

        assertEquals(dto, resultado);

    }

    @Test
    @DisplayName("Teste para o método buscarPorId lançando NotFoundException")
    public void testeBuscarIdException() throws NotFoundException {
        when(repository.findById(1)).thenReturn(Optional.empty());
        
        assertThrows(NotFoundException.class, () -> {
            service.buscarPorId(1);
        });
    }

    @Test
    @DisplayName("Teste para o método buscarPorTipo")
    public void testeBuscarTipo() throws NotFoundException {
        NotaFiscalDto dto = new NotaFiscalDto();
        dto.setId(1);
        dto.setNumero(123456);
        dto.setNotaFiscalTipo(NotaFiscalTipo.SAIDA);

        NotaFiscal entidade = new NotaFiscal();
        entidade.setId(dto.getId());
        entidade.setNumero(dto.getNumero());
        entidade.setNotaFiscalTipo(dto.getNotaFiscalTipo());

        when(repository.findByTipo(NotaFiscalTipo.SAIDA)).thenReturn(Optional.of(entidade));
        when(conversao.converterParaDto(entidade)).thenReturn(dto);

        NotaFiscalDto resultado = service.buscarPorTipo(entidade.getNotaFiscalTipo());

        assertEquals(dto, resultado);
    }

    @Test
    @DisplayName("Teste para o método buscarPorTipo lançando NotFoundException")
    public void testeBuscarTipoException() throws NotFoundException {
        when(repository.findByTipo(NotaFiscalTipo.SAIDA)).thenReturn(Optional.empty());
        
        assertThrows(NotFoundException.class, () -> {
            service.buscarPorTipo(NotaFiscalTipo.SAIDA);
        });
    }

    @Test
    @DisplayName("Teste para o método buscarPorParceiroNegocio")
    public void testeBuscarParceiro() throws NotFoundException {
        NotaFiscalDto dto = new NotaFiscalDto();
        dto.setId(1);
        dto.setNumero(123456);

        ParceiroNegocioDto parceiroDto = new ParceiroNegocioDto();
        parceiroDto.setId(2);
        dto.setParceiroNegocio(parceiroDto);

        NotaFiscal entidade = new NotaFiscal();
        entidade.setId(dto.getId());
        entidade.setNumero(dto.getNumero());

        ParceiroNegocio parceiroEntidade = new ParceiroNegocio();
        parceiroEntidade.setId(parceiroDto.getId());
        entidade.setParceiroNegocio(parceiroEntidade);

        when(repository.findByParceiroNegocio(parceiroDto)).thenReturn(Optional.of(entidade));
        when(conversao.converterParaDto(entidade)).thenReturn(dto);

        NotaFiscalDto resultado = service.buscarPorParceiroNegocio(parceiroDto);

        assertEquals(dto, resultado);

    }

    @Test
    @DisplayName("Teste para o método buscarPorParceiroNegocio lançando NotFoundException")
    public void testeBuscarParceiroException() throws NotFoundException {
        ParceiroNegocioDto parceiroDto = new ParceiroNegocioDto();
        parceiroDto.setId(1);

        when(repository.findByParceiroNegocio(parceiroDto)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            service.buscarPorParceiroNegocio(parceiroDto);
        });
    }

    @Test
    @DisplayName("Teste para o método buscarPorNumero")
    public void testeBuscarNumero() throws NotFoundException {
        NotaFiscalDto dto = new NotaFiscalDto();
        dto.setId(1);
        dto.setNumero(123456);

        NotaFiscal entidade = new NotaFiscal();
        entidade.setId(dto.getId());
        entidade.setNumero(dto.getNumero());

        when(conversao.converterParaDto(entidade)).thenReturn(dto);
        when(repository.findByNumero(dto.getNumero())).thenReturn(Optional.of(entidade));

        NotaFiscalDto resultado = service.buscarPorNumero(dto.getNumero());

        assertEquals(dto, resultado);
    }

    @Test
    @DisplayName("Teste para o método buscarPorNumero lançando NotFoundException")
    public void testeBuscarNumeroException() throws NotFoundException {
        when(repository.findByNumero(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            service.buscarPorNumero(1);
        });
    }

    @Test
    @DisplayName("Teste para o método buscarPorData")
    public void testeBuscarData() throws NotFoundException {
        NotaFiscalDto dto = new NotaFiscalDto();
        dto.setId(1);
        dto.setNumero(123456);
        dto.setData(LocalDate.now());

        NotaFiscal entidade = new NotaFiscal();
        entidade.setId(dto.getId());
        entidade.setNumero(dto.getNumero());
        entidade.setData(dto.getData());

        when(conversao.converterParaDto(entidade)).thenReturn(dto);
        when(repository.findByData(LocalDate.now())).thenReturn(Optional.of(entidade));

        NotaFiscalDto resultado = service.buscarPorData(LocalDate.now());

        assertEquals(dto, resultado);
    }

    @Test
    @DisplayName("Teste para o método buscarPorData lançando NotFoundException")
    public void testeBuscarDataException() throws NotFoundException {
        when(repository.findByData(LocalDate.now().minusDays(10))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            service.buscarPorData(LocalDate.now().minusDays(10));
        });
    }

    @Test
    @DisplayName("Teste para o método listarTodos")
    public void testeListar() {
        NotaFiscal notaFiscal1 = new NotaFiscal();
        notaFiscal1.setId(1);
        notaFiscal1.setNotaFiscalTipo(NotaFiscalTipo.SAIDA);
        notaFiscal1.setParceiroNegocio(new ParceiroNegocio());
        notaFiscal1.setNumero(123456);
        notaFiscal1.setData(LocalDate.now());
        notaFiscal1.setItens(new ArrayList<>());
        notaFiscal1.setValorTotal(BigDecimal.valueOf(100.00));

        NotaFiscal notaFiscal2 = new NotaFiscal();
        notaFiscal2.setId(2);
        notaFiscal2.setNotaFiscalTipo(NotaFiscalTipo.ENTRADA);
        notaFiscal2.setParceiroNegocio(new ParceiroNegocio());
        notaFiscal2.setNumero(654321);
        notaFiscal2.setData(LocalDate.now().minusDays(1));
        notaFiscal2.setItens(new ArrayList<>());
        notaFiscal2.setValorTotal(BigDecimal.valueOf(200.00));

        when(repository.findAll()).thenReturn(Arrays.asList(notaFiscal1, notaFiscal2));

        when(conversao.converterParaDto(any(NotaFiscal.class))).thenAnswer(invocation -> {
            NotaFiscal notaFiscal = invocation.getArgument(0);
            NotaFiscalDto dto = new NotaFiscalDto();
            dto.setId(notaFiscal.getId());
            dto.setNotaFiscalTipo(notaFiscal.getNotaFiscalTipo());
            dto.setParceiroNegocio(conversaoParceiro.converterParaDto(notaFiscal.getParceiroNegocio()));
            dto.setNumero(notaFiscal.getNumero());
            dto.setData(notaFiscal.getData());
            dto.setItens(notaFiscal.getItens());
            dto.setValorTotal(notaFiscal.getValorTotal());
            return dto;
        });

        List<NotaFiscalDto> resultado = service.listarTodos();

        assertEquals(2, resultado.size());

        assertEquals(notaFiscal1.getId(), resultado.get(0).getId());
        assertEquals(notaFiscal1.getNotaFiscalTipo(), resultado.get(0).getNotaFiscalTipo());
        assertEquals(notaFiscal1.getParceiroNegocio(), resultado.get(0).getParceiroNegocio());
        assertEquals(notaFiscal1.getNumero(), resultado.get(0).getNumero());
        assertEquals(notaFiscal1.getData(), resultado.get(0).getData());
        assertEquals(notaFiscal1.getItens(), resultado.get(0).getItens());
        assertEquals(notaFiscal1.getValorTotal(), resultado.get(0).getValorTotal());

        assertEquals(notaFiscal2.getId(), resultado.get(1).getId());
        assertEquals(notaFiscal2.getNotaFiscalTipo(), resultado.get(1).getNotaFiscalTipo());
        assertEquals(notaFiscal2.getParceiroNegocio(), resultado.get(1).getParceiroNegocio());
        assertEquals(notaFiscal2.getNumero(), resultado.get(1).getNumero());
        assertEquals(notaFiscal2.getData(), resultado.get(1).getData());
        assertEquals(notaFiscal2.getItens(), resultado.get(1).getItens());
        assertEquals(notaFiscal2.getValorTotal(), resultado.get(1).getValorTotal());
    }

    @Test
    @DisplayName("Teste para o método deletarPorId")
    public void testeDeletarId() {
        // to do
    }

    @Test
    @DisplayName("Teste para o método inserir")
    public void testeInserir() {
        // to do
    }

    @Test
    @DisplayName("Teste para o método atualizar")
    public void testeAtualizar() {
        // to do
    }

    // Para os métodos de conversão e para o método de atualizarNotaFiscal,
    // criar novas services e testar separadamente

}
