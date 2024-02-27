package com.agrotis.trainees.crud.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.exception.CrudException;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;
import com.agrotis.trainees.crud.wrapper.NotaFiscalWrapper;

public class NotaFiscalServiceTest {

    @Mock
    NotaFiscalRepository repository;

    @Mock
    NotaFiscalWrapper wrapper;

    @Mock
    NumeroService numeroService;

    @Mock
    ModelMapper mapper;

    @Mock
    NotaFiscalTipoService tipoService;

    @InjectMocks
    NotaFiscalService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Teste inserir com sucesso")
    void deveInserirComSucesso() {
        ParceiroNegocio parceiro = new ParceiroNegocio();
        parceiro.setId(1);
        parceiro.setInscricaoFiscal("123456789");

        NotaFiscalDto dto = new NotaFiscalDto();
        dto.setId(1);
        dto.setParceiro(parceiro);

        NotaFiscal entidade = new NotaFiscal();
        entidade.setId(1);
        entidade.setParceiro(parceiro);

        when(wrapper.converterParaEntidade(dto)).thenReturn(entidade);
        when(repository.save(entidade)).thenReturn(entidade);
        when(wrapper.converterParaDto(entidade)).thenReturn(dto);

        assertDoesNotThrow(() -> {
            service.inserir(dto);
        });

        verify(repository, times(1)).save(entidade);
    }

    @Test
    @DisplayName("Teste para atualizar com sucesso")
    void deveAtualizarComSucesso() {
        NotaFiscalTipo tipo = new NotaFiscalTipo();
        tipo.setId(1);

        NotaFiscalTipo tipo2 = new NotaFiscalTipo();
        tipo.setId(2);

        ParceiroNegocio parceiro = new ParceiroNegocio();
        parceiro.setId(1);
        parceiro.setInscricaoFiscal("123456789");

        NotaFiscalDto dto = new NotaFiscalDto();
        dto.setId(1);
        dto.setParceiro(parceiro);
        dto.setTipo(tipo2);

        NotaFiscal entidade = new NotaFiscal();
        entidade.setId(1);
        entidade.setParceiro(parceiro);
        entidade.setTipo(tipo);

        NotaFiscal entidade2 = new NotaFiscal();
        entidade2.setId(1);
        entidade2.setParceiro(parceiro);
        entidade2.setTipo(tipo2);

        when(repository.findById(dto.getId())).thenReturn(Optional.of(entidade));
        when(repository.save(entidade)).thenReturn(entidade2);
        when(wrapper.converterParaDto(entidade2)).thenReturn(dto);

        assertDoesNotThrow(() -> {
            service.atualizar(dto);
        });

        assertEquals(dto.getTipo().getId(), entidade2.getTipo().getId());
        verify(repository, times(1)).save(entidade);
    }

    @Test
    @DisplayName("Teste atualizar id nulo")
    void deveRetornarErroIdNulo() {

        NotaFiscalDto dto = new NotaFiscalDto();
        dto.setNumero(1);

        Exception excecao = assertThrows(CrudException.class, () -> {
            service.atualizar(dto);
        });

        assertEquals("Obrigatório preencher o id", excecao.getMessage());
    }

    @Test
    @DisplayName("Teste onde não acha pelo id")
    void deveFalharEmBuscarPorId() {

        when(repository.findById(1)).thenReturn(Optional.empty());

        Exception excecao = assertThrows(NoSuchElementException.class, () -> {
            service.buscarPorId(1);
        });

        assertEquals("Informações não encontradas para o id 1", excecao.getMessage());
    }

    @Test
    @DisplayName("Teste para buscar por tipo e numero com sucesso")
    void deveBuscarPorTipoENumero() {
        NotaFiscalTipo tipo = new NotaFiscalTipo();
        tipo.setId(1);

        ParceiroNegocio parceiro = new ParceiroNegocio();
        parceiro.setId(1);
        parceiro.setInscricaoFiscal("123456789");

        NotaFiscal entidade = new NotaFiscal();
        entidade.setId(1);
        entidade.setParceiro(parceiro);
        entidade.setNumero(1);

        when(repository.findByTipoAndNumero(tipo, 1)).thenReturn(Optional.of(entidade));

        assertDoesNotThrow(() -> {
            service.buscarPorTipoeNumero(tipo, 1);
        });
        verify(repository, times(1)).findByTipoAndNumero(tipo, 1);
    }

    @Test
    @DisplayName("Teste erro ao não encontrar tipo e numero")
    void deveRetornarErroBuscarTipoENumero() {
        NotaFiscalTipo tipo = new NotaFiscalTipo();
        tipo.setId(1);

        when(repository.findByTipoAndNumero(tipo, 1)).thenReturn(Optional.empty());

        Exception excecao = assertThrows(NoSuchElementException.class, () -> {
            service.buscarPorTipoeNumero(tipo, 1);
        });

        assertEquals("Informações não encontradas para o id e numero informados ", excecao.getMessage());
    }

    @Test
    @DisplayName("Teste para listar por tipo")
    void deveListarPorTipo() {
        NotaFiscalTipo tipo = new NotaFiscalTipo();
        tipo.setId(1);

        ParceiroNegocio parceiro = new ParceiroNegocio();
        parceiro.setId(1);
        parceiro.setInscricaoFiscal("123456789");

        NotaFiscal entidade = new NotaFiscal();
        entidade.setId(1);
        entidade.setParceiro(parceiro);
        entidade.setNumero(1);

        List<NotaFiscal> notas = new ArrayList();
        notas.add(entidade);

        when(tipoService.buscarPorId(1)).thenReturn(tipo);
        when(repository.findByTipo(tipo)).thenReturn(notas);

        assertDoesNotThrow(() -> {
            service.listarPorTipo(tipo.getId());
        });

        verify(repository, times(1)).findByTipo(tipo);

    }

    @Test
    @DisplayName("Teste para listar por numero")
    void deveListarPorNumero() {
        ParceiroNegocio parceiro = new ParceiroNegocio();
        parceiro.setId(1);
        parceiro.setInscricaoFiscal("123456789");

        NotaFiscal entidade = new NotaFiscal();
        entidade.setId(1);
        entidade.setParceiro(parceiro);
        entidade.setNumero(1);

        List<NotaFiscal> notas = new ArrayList();
        notas.add(entidade);

        when(repository.findByNumero(1)).thenReturn(notas);

        assertDoesNotThrow(() -> {
            service.listarPorNumero(1);
        });

        verify(repository, times(1)).findByNumero(1);

    }

    @Test
    @DisplayName("Teste para listar todos")
    void deveListarTodos() {

        NotaFiscal entidade = new NotaFiscal();
        entidade.setId(1);
        entidade.setNumero(1);

        NotaFiscal entidade2 = new NotaFiscal();
        entidade2.setId(2);
        entidade2.setNumero(2);

        List<NotaFiscal> notas = new ArrayList();
        notas.add(entidade);
        notas.add(entidade2);

        when(repository.findAll()).thenReturn(notas);

        assertDoesNotThrow(() -> {
            service.listarTodos();
        });

        verify(repository, times(1)).findAll();

    }

    @Test
    @DisplayName("teste ao deletar com sucesso")
    void deveDeletarComSucesso() {

        assertDoesNotThrow(() -> {
            service.deletarPorId(1);
        });

        verify(repository, times(1)).deleteById(1);
    }
}
