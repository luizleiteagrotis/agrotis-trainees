package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.agrotis.trainees.crud.convert.NotaFiscalConversor;
import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.NotaFiscalExcecao;
import com.agrotis.trainees.crud.helper.Validador;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;

class NotaFiscalServiceTest {

    @Mock
    NotaFiscalRepository repository;
    @Mock
    NotaFiscalConversor notaFiscalConversor;
    @Mock
    Validador validador;

    @InjectMocks
    NotaFiscalService notaFiscalService;

    final int ID = 0;
    final String TIPO_ENTRADA = "entrada";
    final String TIPO_SAIDA = "saida";
    final ParceiroNegocio PARCEIRO_NEGOCIO = criarParceiro();
    final int NUMERO = 1010;
    final LocalDate DATA = LocalDate.of(2020, 01, 01);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    ParceiroNegocio criarParceiro() {
        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setInscricaoFiscal("74571169000999");
        entidade.setNome("AgroFertil Ltda");
        entidade.setTelefone("41992377204");
        entidade.setEndereco("Rua Antônio Africa");
        return entidade;
    }

    NotaFiscal criarNotaFiscalEntrada() {
        NotaFiscal entidade = new NotaFiscal();
        entidade.setTipo(TIPO_ENTRADA);
        entidade.setParceiroNegocio(PARCEIRO_NEGOCIO);
        entidade.setNumero(NUMERO);
        entidade.setData(DATA);
        return entidade;
    }

    NotaFiscal criarNotaFiscalSaida() {
        NotaFiscal entidade = new NotaFiscal();
        entidade.setTipo(TIPO_SAIDA);
        entidade.setParceiroNegocio(PARCEIRO_NEGOCIO);
        entidade.setNumero(NUMERO);
        entidade.setData(DATA);
        return entidade;
    }

    NotaFiscalDto criarNotaFiscalEntradaDto() {
        NotaFiscalDto entidade = new NotaFiscalDto();
        entidade.setTipo(TIPO_ENTRADA);
        entidade.setParceiroNegocio(PARCEIRO_NEGOCIO);
        entidade.setNumero(NUMERO);
        entidade.setData(DATA);
        return entidade;
    }

    NotaFiscalDto criarNotaFiscalSaidaDto() {
        NotaFiscalDto entidade = new NotaFiscalDto();
        entidade.setTipo(TIPO_SAIDA);
        entidade.setParceiroNegocio(PARCEIRO_NEGOCIO);
        entidade.setNumero(NUMERO);
        entidade.setData(DATA);
        return entidade;
    }

    @Test
    void TestarNotaFiscalTipoEntradaSendoSalva() throws NotaFiscalExcecao {
        NotaFiscalDto dto = criarNotaFiscalEntradaDto();
        NotaFiscal entidade = criarNotaFiscalEntrada();
        List<NotaFiscal> listaNota = new ArrayList<>();
        when(notaFiscalConversor.converter(dto)).thenReturn(entidade);
        when(repository.findByNumeroAndTipo(NUMERO, TIPO_ENTRADA)).thenReturn(listaNota);
        when(validador.existeParceiroPorId(ID)).thenReturn(true);
        when(repository.save(entidade)).thenReturn(entidade);
        when(notaFiscalConversor.converter(entidade)).thenReturn(dto);
        NotaFiscalDto dtoSalvo = notaFiscalService.salvar(dto);
        assertNotNull(dtoSalvo);
    }

    @Test
    void TestarNotaFiscalTipoSaidaSendoSalva() throws NotaFiscalExcecao {
        NotaFiscalDto dto = criarNotaFiscalSaidaDto();
        NotaFiscal entidade = criarNotaFiscalSaida();
        List<NotaFiscal> listaNota = new ArrayList<>();
        when(notaFiscalConversor.converter(dto)).thenReturn(entidade);
        when(repository.findByNumeroAndTipo(NUMERO, TIPO_ENTRADA)).thenReturn(listaNota);
        when(validador.existeParceiroPorId(ID)).thenReturn(true);
        when(repository.save(entidade)).thenReturn(entidade);
        when(notaFiscalConversor.converter(entidade)).thenReturn(dto);
        NotaFiscalDto dtoSalvo = notaFiscalService.salvar(dto);
        assertNotNull(dtoSalvo);
    }

    @Test
    void TestarNotaFiscalSendoSalvaComUmTipoNuloEsperadoException() {
        NotaFiscalDto dto = criarNotaFiscalSaidaDto();
        NotaFiscal entidade = criarNotaFiscalSaida();
        dto.setTipo(null);
        entidade.setTipo(null);
        List<NotaFiscal> listaNota = new ArrayList<>();
        when(notaFiscalConversor.converter(dto)).thenReturn(entidade);
        when(repository.findByNumeroAndTipo(NUMERO, TIPO_ENTRADA)).thenReturn(listaNota);
        when(validador.existeParceiroPorId(ID)).thenReturn(true);
        assertThrows(NotaFiscalExcecao.class, () -> {
            notaFiscalService.salvar(dto);
        });

    }

    @Test
    void TestarNotaFiscalSendoSalvaComUmTipoErradoEsperadoException() {
        NotaFiscalDto dto = criarNotaFiscalSaidaDto();
        NotaFiscal entidade = criarNotaFiscalSaida();
        dto.setTipo("dadsada");
        entidade.setTipo("dadsada");
        List<NotaFiscal> listaNota = new ArrayList<>();
        when(notaFiscalConversor.converter(dto)).thenReturn(entidade);
        when(repository.findByNumeroAndTipo(NUMERO, TIPO_SAIDA)).thenReturn(listaNota);
        when(validador.existeParceiroPorId(ID)).thenReturn(true);
        assertThrows(NotaFiscalExcecao.class, () -> {
            notaFiscalService.salvar(dto);
        });
    }

    @Test
    void TestarNotaFiscalSendoSalvaComOutraIgualSalvaNoBancoEsperadoException() {
        NotaFiscalDto dto = criarNotaFiscalSaidaDto();
        NotaFiscal entidade = criarNotaFiscalSaida();
        List<NotaFiscal> listaNota = new ArrayList<>();
        listaNota.add(entidade);
        when(notaFiscalConversor.converter(dto)).thenReturn(entidade);
        when(repository.findByNumeroAndTipo(NUMERO, TIPO_SAIDA)).thenReturn(listaNota);
        when(validador.existeParceiroPorId(ID)).thenReturn(true);
        assertThrows(NotaFiscalExcecao.class, () -> {
            notaFiscalService.salvar(dto);
        });
    }

    @Test
    void TestarNotaFiscalSendoSalvaComParceiroDeNegocioNuloEsperandoException() {
        NotaFiscalDto dto = criarNotaFiscalSaidaDto();
        NotaFiscal entidade = criarNotaFiscalSaida();
        dto.setParceiroNegocio(null);
        entidade.setParceiroNegocio(null);
        List<NotaFiscal> listaNota = new ArrayList<>();
        when(notaFiscalConversor.converter(dto)).thenReturn(entidade);
        when(repository.findByNumeroAndTipo(NUMERO, TIPO_ENTRADA)).thenReturn(listaNota);
        assertThrows(NotaFiscalExcecao.class, () -> {
            notaFiscalService.salvar(dto);
        });
    }

    @Test
    void TestarNotaFiscalSendoSalvaComNumeroVazioEsperandoException() {
        NotaFiscalDto dto = new NotaFiscalDto();
        dto.setTipo(TIPO_ENTRADA);
        dto.setParceiroNegocio(PARCEIRO_NEGOCIO);
        dto.setData(DATA);
        NotaFiscal entidade = new NotaFiscal();
        entidade.setTipo(TIPO_ENTRADA);
        entidade.setParceiroNegocio(PARCEIRO_NEGOCIO);
        entidade.setData(DATA);
        List<NotaFiscal> listaNota = new ArrayList<>();
        when(validador.existeParceiroPorId(ID)).thenReturn(true);
        when(notaFiscalConversor.converter(dto)).thenReturn(entidade);

        when(repository.findByNumeroAndTipo(NUMERO, TIPO_ENTRADA)).thenReturn(listaNota);
        assertThrows(NotaFiscalExcecao.class, () -> {
            notaFiscalService.salvar(dto);
        });
    }

    @Test
    void TestarBuscarNotaFiscalPorIDesperandoRetorno() {
        NotaFiscalDto dto = criarNotaFiscalSaidaDto();
        NotaFiscal entidade = criarNotaFiscalSaida();
        when(repository.findById(ID)).thenReturn(Optional.of(entidade));
        when(notaFiscalConversor.converter(entidade)).thenReturn(dto);
        NotaFiscalDto dtoEncontrado = notaFiscalService.buscarPorId(ID);
        assertNotNull(dtoEncontrado);
    }

    @Test
    void TestarBuscaNotaFiscalPorIdSemRetorno() {
        when(repository.findById(ID)).thenReturn(Optional.empty());
        NotaFiscalDto dtoEncontrado = notaFiscalService.buscarPorId(ID);
        assertNull(dtoEncontrado);
    }

    @Test
    void TestarBuscarPorTipoNotaFiscalComRetorno() throws NotaFiscalExcecao {
        NotaFiscalDto dto = criarNotaFiscalEntradaDto();
        NotaFiscal entidade = criarNotaFiscalSaida();
        List<NotaFiscal> listaNota = new ArrayList<>();
        listaNota.add(entidade);
        List<NotaFiscalDto> listaNotaDto = new ArrayList<>();
        listaNotaDto.add(dto);
        when(repository.findByTipo(TIPO_ENTRADA)).thenReturn(listaNota);
        when(notaFiscalConversor.converter(listaNota)).thenReturn(listaNotaDto);
        List<NotaFiscalDto> dtos = notaFiscalService.buscarPorTipoNotaFiscal(TIPO_ENTRADA);
        assertNotNull(dtos);
    }

    @Test
    void TestarBuscarPorTipoNotaFiscalSendoNull() {
        List<NotaFiscal> listaNota = new ArrayList<>();
        when(repository.findByTipo(null)).thenReturn(listaNota);
        assertThrows(NotaFiscalExcecao.class, () -> {

            notaFiscalService.buscarPorTipoNotaFiscal("");
        });
    }

    @Test
    void TestarBuscarPorTipoNotaFiscalComTipoErrado() {
        NotaFiscalDto dto = criarNotaFiscalEntradaDto();
        NotaFiscal entidade = criarNotaFiscalSaida();
        entidade.setTipo("adapdadpad");
        dto.setTipo("adapdadpad");
        List<NotaFiscal> listaNota = new ArrayList<>();
        listaNota.add(entidade);
        List<NotaFiscalDto> listaNotaDto = new ArrayList<>();
        listaNotaDto.add(dto);
        when(repository.findByTipo(entidade.getTipo())).thenReturn(listaNota);
        assertThrows(NotaFiscalExcecao.class, () -> {
            notaFiscalService.buscarPorTipoNotaFiscal("tipo errado");
        });
    }

    @Test
    void TestarBuscarPorNumeroComRetorno() throws NotaFiscalExcecao {
        NotaFiscalDto dto = criarNotaFiscalEntradaDto();
        NotaFiscal entidade = criarNotaFiscalSaida();
        List<NotaFiscal> listaNota = new ArrayList<>();
        listaNota.add(entidade);
        List<NotaFiscalDto> listaNotaDto = new ArrayList<>();
        listaNotaDto.add(dto);
        when(repository.findByNumero(NUMERO)).thenReturn(listaNota);
        when(notaFiscalConversor.converter(listaNota)).thenReturn(listaNotaDto);
        List<NotaFiscalDto> dtos = notaFiscalService.buscarPorNumero(NUMERO);
        assertNotNull(dtos);
    }

    @Test
    void TestarBuscarPorNumeroSemRetornoEsperandoExcecao() {
        List<NotaFiscal> listaNota = new ArrayList<>();
        List<NotaFiscalDto> listaNotaDto = new ArrayList<>();
        when(repository.findByNumero(101)).thenReturn(null);
        when(notaFiscalConversor.converter(listaNota)).thenReturn(listaNotaDto);
        assertThrows(NotaFiscalExcecao.class, () -> {
            notaFiscalService.buscarPorNumero(101);
        });
    }

    @Test
    void TestarBuscarPorDataComRetornoEsperado() throws NotaFiscalExcecao {
        NotaFiscalDto dto = criarNotaFiscalEntradaDto();
        NotaFiscal entidade = criarNotaFiscalSaida();
        List<NotaFiscal> listaNota = new ArrayList<>();
        listaNota.add(entidade);
        List<NotaFiscalDto> listaNotaDto = new ArrayList<>();
        listaNotaDto.add(dto);
        when(repository.findByData(DATA)).thenReturn(listaNota);
        when(notaFiscalConversor.converter(listaNota)).thenReturn(listaNotaDto);
        List<NotaFiscalDto> dtos = notaFiscalService.buscarPorData(DATA);
        assertNotNull(dtos);
    }

    @Test
    void TestarBuscarPorDataComTipoNull() throws NotaFiscalExcecao {
        NotaFiscalDto dto = criarNotaFiscalEntradaDto();
        NotaFiscal entidade = criarNotaFiscalSaida();
        List<NotaFiscal> listaNota = new ArrayList<>();
        listaNota.add(entidade);
        List<NotaFiscalDto> listaNotaDto = new ArrayList<>();
        listaNotaDto.add(dto);
        when(repository.findByData(null)).thenReturn(null);
        assertThrows(NotaFiscalExcecao.class, () -> {
            notaFiscalService.buscarPorData(null);
        });
    }

    @Test
    void TestarBuscarPorDataSemRetorno() throws NotaFiscalExcecao {
        List<NotaFiscal> listaNota = new ArrayList<>();
        List<NotaFiscalDto> listaNotaDto = new ArrayList<>();
        when(repository.findByData(DATA)).thenReturn(listaNota);
        when(notaFiscalConversor.converter(listaNota)).thenReturn(listaNotaDto);
        assertThrows(NotaFiscalExcecao.class, () -> {
            notaFiscalService.buscarPorData(DATA);
        });
    }

    @Test
    void TestarListarTodosComRetorno() throws NotaFiscalExcecao {
        NotaFiscalDto dto = criarNotaFiscalEntradaDto();
        NotaFiscal entidade = criarNotaFiscalSaida();
        List<NotaFiscal> listaNota = new ArrayList<>();
        listaNota.add(entidade);
        List<NotaFiscalDto> listaNotaDto = new ArrayList<>();
        listaNotaDto.add(dto);
        when(repository.findAll()).thenReturn(listaNota);
        when(notaFiscalConversor.converter(listaNota)).thenReturn(listaNotaDto);
        List<NotaFiscalDto> dtos = notaFiscalService.listarTodos();
        assertNotNull(dtos);
    }

    @Test
    void TestarListarTodosSemetorno() throws NotaFiscalExcecao {
        List<NotaFiscal> listaNota = new ArrayList<>();
        List<NotaFiscalDto> listaNotaDto = new ArrayList<>();
        when(repository.findAll()).thenReturn(listaNota);
        when(notaFiscalConversor.converter(listaNota)).thenReturn(listaNotaDto);
        assertThrows(NotaFiscalExcecao.class, () -> {
            notaFiscalService.listarTodos();
        });
    }

    @Test
    void atualizarTodosOsCampos() throws NotaFiscalExcecao {
        NotaFiscalDto dto = criarNotaFiscalEntradaDto();
        NotaFiscal entidade = criarNotaFiscalEntrada();
        NotaFiscal novaEntidade = new NotaFiscal("saida", new ParceiroNegocio(), 1001, LocalDate.of(2050, 10, 10));
        when(notaFiscalConversor.converter(dto)).thenReturn(novaEntidade);
        when(repository.findById(ID)).thenReturn(Optional.of(entidade));
        when(validador.existeParceiroPorId(ID)).thenReturn(true);
        when(repository.findByNumeroAndTipo(novaEntidade.getNumero(), novaEntidade.getTipo())).thenReturn(Collections.emptyList());
        notaFiscalService.atualizar(dto, ID);
        assertEquals(entidade.getTipo(), novaEntidade.getTipo());
        assertEquals(entidade.getData(), novaEntidade.getData());
        assertEquals(entidade.getNumero(), novaEntidade.getNumero());
        assertEquals(entidade.getParceiroNegocio(), novaEntidade.getParceiroNegocio());
        verify(repository, times(1)).save(any(NotaFiscal.class));
    }

    @Test
    void atualizarUmaNotaInexistenteEsperandoExcecao() throws NotaFiscalExcecao {
        NotaFiscalDto dto = criarNotaFiscalEntradaDto();
        NotaFiscal entidade = criarNotaFiscalEntrada();
        NotaFiscal novaEntidade = new NotaFiscal("saida", new ParceiroNegocio(), 1001, LocalDate.of(2050, 10, 10));
        when(notaFiscalConversor.converter(dto)).thenReturn(novaEntidade);
        when(repository.findById(ID)).thenReturn(Optional.empty());
        when(validador.existeParceiroPorId(ID)).thenReturn(true);
        when(repository.findByNumeroAndTipo(novaEntidade.getNumero(), novaEntidade.getTipo())).thenReturn(Collections.emptyList());
        assertThrows(NotaFiscalExcecao.class, () -> {
            notaFiscalService.atualizar(dto, ID);
        });
        verify(repository, times(0)).save(any(NotaFiscal.class));
    }

    @Test
    void atualizarComUmParceiroInexistenteEsperandoExcecao() throws NotaFiscalExcecao {
        NotaFiscalDto dto = criarNotaFiscalEntradaDto();
        NotaFiscal entidade = criarNotaFiscalEntrada();
        NotaFiscal novaEntidade = new NotaFiscal("saida", new ParceiroNegocio(), 1001, LocalDate.of(2050, 10, 10));
        when(notaFiscalConversor.converter(dto)).thenReturn(novaEntidade);
        when(repository.findById(ID)).thenReturn(Optional.of(entidade));
        when(validador.existeParceiroPorId(ID)).thenReturn(false);
        assertThrows(NotaFiscalExcecao.class, () -> {
            notaFiscalService.atualizar(dto, ID);
        });

        verify(repository, times(0)).save(any(NotaFiscal.class));
    }

    @Test
    void atualizarComUmNotaExistenteEsperandoExcecao() throws NotaFiscalExcecao {
        NotaFiscalDto dto = criarNotaFiscalEntradaDto();
        NotaFiscal entidade = criarNotaFiscalEntrada();
        List<NotaFiscal> listaEntidade = new ArrayList<>();
        listaEntidade.add(entidade);
        NotaFiscal novaEntidade = new NotaFiscal(TIPO_ENTRADA, new ParceiroNegocio(), NUMERO, LocalDate.of(2050, 10, 10));
        when(notaFiscalConversor.converter(dto)).thenReturn(novaEntidade);
        when(repository.findById(ID)).thenReturn(Optional.of(entidade));
        when(validador.existeParceiroPorId(ID)).thenReturn(true);
        when(repository.findByNumeroAndTipo(NUMERO, TIPO_ENTRADA)).thenReturn(listaEntidade);
        assertThrows(NotaFiscalExcecao.class, () -> {
            notaFiscalService.atualizar(dto, ID);
        });

        verify(repository, times(0)).save(any(NotaFiscal.class));
    }

    @Test
    void atualizarTipoNotaEsperandoDarCerto() throws NotaFiscalExcecao {
        NotaFiscalDto dto = criarNotaFiscalEntradaDto();
        NotaFiscal entidade = criarNotaFiscalEntrada();
        NotaFiscal novaEntidade = new NotaFiscal();
        novaEntidade.setTipo(TIPO_SAIDA);
        when(notaFiscalConversor.converter(dto)).thenReturn(novaEntidade);
        when(repository.findById(ID)).thenReturn(Optional.of(entidade));
        when(validador.existeParceiroPorId(ID)).thenReturn(true);
        when(repository.findByNumeroAndTipo(novaEntidade.getNumero(), novaEntidade.getTipo())).thenReturn(Collections.emptyList());
        notaFiscalService.atualizar(dto, ID);
        assertEquals(entidade.getTipo(), novaEntidade.getTipo());
        verify(repository, times(1)).save(any(NotaFiscal.class));
    }

    @Test
    void atualizarTipoNotaComUmTipoInvalidoEsperandoExcecao() throws NotaFiscalExcecao {
        NotaFiscalDto dto = criarNotaFiscalEntradaDto();
        NotaFiscal entidade = criarNotaFiscalEntrada();
        NotaFiscal novaEntidade = new NotaFiscal();
        novaEntidade.setTipo("das");
        when(notaFiscalConversor.converter(dto)).thenReturn(novaEntidade);
        when(repository.findById(ID)).thenReturn(Optional.of(entidade));
        when(validador.existeParceiroPorId(ID)).thenReturn(true);
        when(repository.findByNumeroAndTipo(novaEntidade.getNumero(), novaEntidade.getTipo())).thenReturn(Collections.emptyList());
        assertThrows(NotaFiscalExcecao.class, () -> {
            notaFiscalService.atualizar(dto, ID);
        });
        verify(repository, times(0)).save(any(NotaFiscal.class));
    }

    @Test
    void atualizarNumeroEsperandoDarCerto() throws NotaFiscalExcecao {
        NotaFiscalDto dto = criarNotaFiscalEntradaDto();
        NotaFiscal entidade = criarNotaFiscalEntrada();
        NotaFiscal novaEntidade = new NotaFiscal();
        novaEntidade.setNumero(999999);
        when(notaFiscalConversor.converter(dto)).thenReturn(novaEntidade);
        when(repository.findById(ID)).thenReturn(Optional.of(entidade));
        when(validador.existeParceiroPorId(ID)).thenReturn(true);
        when(repository.findByNumeroAndTipo(novaEntidade.getNumero(), novaEntidade.getTipo())).thenReturn(Collections.emptyList());
        notaFiscalService.atualizar(dto, ID);
        assertEquals(entidade.getNumero(), novaEntidade.getNumero());
        verify(repository, times(1)).save(any(NotaFiscal.class));
    }

    @Test
    void atualizarNumeroNegativoEsperandoExcecao() throws NotaFiscalExcecao {
        NotaFiscalDto dto = criarNotaFiscalEntradaDto();
        NotaFiscal entidade = criarNotaFiscalEntrada();
        NotaFiscal novaEntidade = new NotaFiscal();
        novaEntidade.setNumero(-999999);
        when(notaFiscalConversor.converter(dto)).thenReturn(novaEntidade);
        when(repository.findById(ID)).thenReturn(Optional.of(entidade));
        when(validador.existeParceiroPorId(ID)).thenReturn(true);
        when(repository.findByNumeroAndTipo(novaEntidade.getNumero(), novaEntidade.getTipo())).thenReturn(Collections.emptyList());
        assertThrows(NotaFiscalExcecao.class, () -> {
            notaFiscalService.atualizar(dto, ID);
        });

        verify(repository, times(0)).save(any(NotaFiscal.class));
    }

    @Test
    void atualizarDataEsperandoDarCerto() throws NotaFiscalExcecao {
        NotaFiscalDto dto = criarNotaFiscalEntradaDto();
        NotaFiscal entidade = criarNotaFiscalEntrada();
        NotaFiscal novaEntidade = new NotaFiscal();
        novaEntidade.setData(LocalDate.of(2030, 01, 01));
        when(notaFiscalConversor.converter(dto)).thenReturn(novaEntidade);
        when(repository.findById(ID)).thenReturn(Optional.of(entidade));
        when(validador.existeParceiroPorId(ID)).thenReturn(true);
        when(repository.findByNumeroAndTipo(novaEntidade.getNumero(), novaEntidade.getTipo())).thenReturn(Collections.emptyList());
        notaFiscalService.atualizar(dto, ID);
        assertEquals(entidade.getData(), novaEntidade.getData());
        verify(repository, times(1)).save(any(NotaFiscal.class));
    }

    @Test
    void TestarDeletarPorIdComUmaNotaFiscalComEsteIdExistente() {
        NotaFiscalDto dto = criarNotaFiscalEntradaDto();
        when(notaFiscalService.buscarPorId(ID)).thenReturn(dto);
        notaFiscalService.deletarPorId(ID);
        verify(repository, times(1)).deleteById(ID);
    }

    @Test
    void TestarDeletarPorIdComUmaNotaFiscalComEsteIdNaoExistente() {
        when(notaFiscalService.buscarPorId(ID)).thenReturn(null);
        notaFiscalService.deletarPorId(ID);
        verify(repository, times(0)).deleteById(ID);
    }

    @Test
    void persistirValorTotalComDoisItensEsperandoOvalorDeUmMilhaoETrintaEOitoMilEQuinhentos() {
        NotaFiscal entidade = criarNotaFiscalEntrada();
        List<ItemNotaFiscal> itemNota = new ArrayList<>();
        ItemNotaFiscal maca = new ItemNotaFiscal(entidade, new Produto(), new BigDecimal(10000), new BigDecimal(65.90));
        maca.setValorTotal(new BigDecimal(659_000.00));
        ItemNotaFiscal peira = new ItemNotaFiscal(entidade, new Produto(), new BigDecimal(5000), new BigDecimal(75.90));
        peira.setValorTotal(new BigDecimal(379_500.00));
        itemNota.add(peira);
        itemNota.add(maca);
        entidade.setItemNotaFiscal(itemNota);

        when(repository.findById(ID)).thenReturn(Optional.of(entidade));
        notaFiscalService.persistirValorTotal(ID);
        assertEquals(new BigDecimal(1_038_500.00).setScale(2, RoundingMode.HALF_UP), entidade.getValorTotal());
        verify(repository, times(1)).save(any(NotaFiscal.class));
    }
}
