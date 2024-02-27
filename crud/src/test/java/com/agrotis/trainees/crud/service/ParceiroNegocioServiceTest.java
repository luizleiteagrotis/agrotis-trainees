package com.agrotis.trainees.crud.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
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

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.exception.CrudException;
import com.agrotis.trainees.crud.exception.InscricaoExisteException;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.wrapper.ParceiroNegocioWrapper;

public class ParceiroNegocioServiceTest {

    @Mock
    ParceiroNegocioRepository repository;

    @Mock
    ParceiroNegocioWrapper wrapper;

    @Mock
    InscricaoService inscricaoService;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    ParceiroNegocioService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // Testar Inserir
    // verificar Inscricao
    @Test
    @DisplayName("Teste positivo para inserção")
    void deveInserirSucesso() throws InscricaoExisteException {
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setId(1);
        dto.setNome("Marcos");
        dto.setInscricaoFiscal("123456789");

        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setId(1);
        entidade.setNome("Marcos");
        entidade.setInscricaoFiscal("123456789");

        doNothing().when(inscricaoService).verificarInscricao(dto.getInscricaoFiscal());
        when(wrapper.converterParaEntidade(dto)).thenReturn(entidade);
        when(repository.save(entidade)).thenReturn(entidade);
        when(wrapper.converterParaDto(entidade)).thenReturn(dto);

        assertDoesNotThrow(() -> {
            service.inserir(dto);
        });

        verify(repository, times(1)).save(entidade);

    }

    @Test
    @DisplayName("Teste para inscrição existente em inserção")
    void devePegarErroDeInscrição() throws InscricaoExisteException {
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setId(1);
        dto.setNome("Marcos");
        dto.setInscricaoFiscal("123456789");

        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setId(1);
        entidade.setNome("Marcos");
        entidade.setInscricaoFiscal("123456789");

        doThrow(new InscricaoExisteException("A inscrição fiscal já existe")).when(inscricaoService)
                        .verificarInscricao(dto.getInscricaoFiscal());
        when(wrapper.converterParaEntidade(dto)).thenReturn(null);
        when(repository.save(entidade)).thenReturn(null);
        when(wrapper.converterParaDto(entidade)).thenReturn(null);

        Exception excecao = assertThrows(InscricaoExisteException.class, () -> {
            service.inserir(dto);
        });

        assertEquals("A inscrição fiscal já existe", excecao.getMessage());

    }

    @Test
    @DisplayName("Teste positivo para atualização")
    void deveAtualizar() {
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setId(1);
        dto.setNome("Marcos");
        dto.setInscricaoFiscal("123456789");

        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setId(1);
        entidade.setNome("Juliano");
        entidade.setInscricaoFiscal("154678989");

        ParceiroNegocio entidadeAtualizada = new ParceiroNegocio();
        entidadeAtualizada.setId(dto.getId());
        entidadeAtualizada.setNome(dto.getNome());
        entidadeAtualizada.setInscricaoFiscal(dto.getInscricaoFiscal());

        when(wrapper.converterParaDto(entidade)).thenReturn(dto);
        when(repository.save(entidade)).thenReturn(entidade);
        when(repository.findById(dto.getId())).thenReturn(Optional.of(entidade));
        doNothing().when(modelMapper).map(dto, entidade);

        assertDoesNotThrow(() -> {
            service.atualizar(dto);
        });

        assertEquals(dto.getNome(), entidadeAtualizada.getNome());
        verify(repository, times(1)).save(entidade);

    }

    @Test
    @DisplayName("Teste positivo para atualização")
    void deveRetornarErroIdNulo() {
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setNome("Marcos");
        dto.setInscricaoFiscal("123456789");

        Exception excecao = assertThrows(CrudException.class, () -> {
            service.atualizar(dto);
        });

        assertEquals("Obrigatório preencher o id do parceiro.", excecao.getMessage());

    }

    @Test
    @DisplayName("Teste em que não encontra o id")
    void deveRetornarErroAoBuscarId() {

        when(repository.findById(2)).thenReturn(Optional.empty());

        Exception excecao = assertThrows(NoSuchElementException.class, () -> {
            service.buscarPorId(2);
        });

        assertEquals("Nota não encontrada para o id 2", excecao.getMessage());
    }

    @Test
    @DisplayName("Buscar por nome coverage")
    void deveBuscarnome() {
        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setNome("Marcos");
        entidade.setInscricaoFiscal("123456789");

        List<ParceiroNegocio> lista = new ArrayList<>();
        lista.add(entidade);

        when(repository.findByNomeContainingOrderById(entidade.getNome())).thenReturn(lista);

        assertDoesNotThrow(() -> {
            service.buscarPorNome(entidade.getNome());
        });

        verify(repository, times(1)).findByNomeContainingOrderById(entidade.getNome());
    }

    @Test
    @DisplayName("Busca por inscrição fiscal")
    void deveBuscarPorinscriçãoFiscal() {
        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setNome("Marcos");
        entidade.setInscricaoFiscal("123456789");

        when(repository.findByInscricaoFiscal(entidade.getInscricaoFiscal())).thenReturn(Optional.of(entidade));
        assertDoesNotThrow(() -> {
            service.buscarPorInscricaoFiscal(entidade.getInscricaoFiscal());
        });

        verify(repository, times(1)).findByInscricaoFiscal(entidade.getInscricaoFiscal());

    }

    @Test
    @DisplayName("Busca por inscrição fiscal falha")
    void deveFalharNaBuscaPorInscricao() {
        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setNome("Marcos");
        entidade.setInscricaoFiscal("123456789");

        when(repository.findByInscricaoFiscal(entidade.getNome())).thenReturn(Optional.empty());

        Exception excecao = assertThrows(NoSuchElementException.class, () -> {
            service.buscarPorInscricaoFiscal("123456788");
        });

        assertEquals("Nota não encontrada para a inscrição 123456788", excecao.getMessage());

    }

    @Test
    @DisplayName("Deletou com sucesso")
    void deveDeletarComSucesso() {

        doNothing().when(repository).deleteById(1);

        assertDoesNotThrow(() -> {
            service.deletarPorId(1);
        });

    }

    @Test
    @DisplayName("Testa o findAll")
    void deveRetornarLista() {
        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setNome("Marcos");
        entidade.setInscricaoFiscal("123456789");

        ParceiroNegocio entidade2 = new ParceiroNegocio();
        entidade2.setNome("Lucas");
        entidade2.setInscricaoFiscal("987654321");

        ParceiroNegocio entidade3 = new ParceiroNegocio();
        entidade3.setNome("Bruno");
        entidade3.setInscricaoFiscal("546798213");

        List<ParceiroNegocio> lista = new ArrayList<>();
        lista.add(entidade);
        lista.add(entidade2);
        lista.add(entidade3);

        when(repository.findAll()).thenReturn(lista);

        List<ParceiroNegocio> resultado = service.listarTodos();

        assertEquals(3, resultado.size());

    }
}
