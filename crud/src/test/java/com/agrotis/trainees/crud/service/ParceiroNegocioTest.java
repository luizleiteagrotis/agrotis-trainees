package com.agrotis.trainees.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agrotis.trainees.crud.dtos.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;

@ExtendWith(MockitoExtension.class)
public class ParceiroNegocioTest {

    private final String NOME = "AgroFertil Ltda";
    private final String INSCRICAO_FISCAL = "154474-114";
    private final String ENDERECO = "Rua dos Calvos, 71";
    private final String TELEFONE = "4335256025";

    @Mock
    private ParceiroNegocioRepository repository;

    @InjectMocks
    private ParceiroNegocioService service;

    @Test
    public void inserirParceiroNegocio() {
        ParceiroNegocio parceiroNegocio = criarParceiroNegocio();
        when(repository.save(any(ParceiroNegocio.class))).thenReturn(parceiroNegocio);

        ParceiroNegocioDto dto = criarParceiroNegocioDto();
        ParceiroNegocioDto result = service.salvar(dto);

        assertNotNull(result);
        assertEquals(parceiroNegocio.getId(), result.getId());
        verify(repository).save(any(ParceiroNegocio.class));
    }

    @Test
    void verificarBuscarPorId() {
        ParceiroNegocio parceiroNegocio = criarParceiroNegocio();
        when(repository.findById(1)).thenReturn(Optional.of(parceiroNegocio));

        ParceiroNegocioDto result = service.buscarPorId(1);

        assertNotNull(result);
        assertEquals(parceiroNegocio.getNome(), result.getNome());
    }

    @Test
    void verificaBuscarTodos() {
        List<ParceiroNegocio> parceiros = new ArrayList<>();
        parceiros.add(criarParceiroNegocio());
        when(repository.findAll()).thenReturn(parceiros);

        List<ParceiroNegocioDto> result = servipackage com.agrotis.trainees.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agrotis.trainees.crud.dtos.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;

@ExtendWith(MockitoExtension.class)
public class ParceiroNegocioTest {

    private final String NOME = "AgroFertil Ltda";
    private final String INSCRICAO_FISCAL = "154474-114";
    private final String ENDERECO = "Rua dos Calvos, 71";
    private final String TELEFONE = "4335256025";

    @Mock
    private ParceiroNegocioRepository repository;

    @InjectMocks
    private ParceiroNegocioService service;

    @Test
    public void inserirParceiroNegocio() {
        ParceiroNegocio parceiroNegocio = criarParceiroNegocio();
        when(repository.save(any(ParceiroNegocio.class))).thenReturn(parceiroNegocio);

        ParceiroNegocioDto dto = criarParceiroNegocioDto();
        ParceiroNegocioDto result = service.salvar(dto);

        assertNotNull(result);
        assertEquals(parceiroNegocio.getId(), result.getId());
        verify(repository).save(any(ParceiroNegocio.class));
    }

    @Test
    void verificarBuscarPorId() {
        ParceiroNegocio parceiroNegocio = criarParceiroNegocio();
        when(repository.findById(1)).thenReturn(Optional.of(parceiroNegocio));

        ParceiroNegocioDto result = service.buscarPorId(1);

        assertNotNull(result);
        assertEquals(parceiroNegocio.getNome(), result.getNome());
    }

    @Test
    void verificaBuscarTodos() {
        List<ParceiroNegocio> parceiros = new ArrayList<>();
        parceiros.add(criarParceiroNegocio());
        when(repository.findAll()).thenReturn(parceiros);

        List<ParceiroNegocioDto> result = service.listarTodos();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(parceiros.get(0).getNome(), result.get(0).getNome());
    }

    @Test
    void verificarBuscarPorNome() {
        ParceiroNegocio parceiroNegocio = criarParceiroNegocio();
        when(repository.findByNome(NOME)).thenReturn(Optional.of(parceiroNegocio));

        ParceiroNegocioDto result = service.buscarPorNome(NOME);

        assertNotNull(result);
        assertEquals(parceiroNegocio.getNome(), result.getNome());
    }

    @Test
    void deletarPorId() {
        ParceiroNegocio parceiroNegocio = criarParceiroNegocio();
        when(repository.findById(1)).thenReturn(Optional.of(parceiroNegocio));
        
        service.deletarPorId(1);
        
        verify(repository).deleteById(1);
    }

    @Test
    void verificaSeAtualizaOParceiro() {
        ParceiroNegocio parceiroNegocio = criarParceiroNegocio();
        ParceiroNegocioDto dto = criarParceiroNegocioDto();
        when(repository.findById(1)).thenReturn(Optional.of(parceiroNegocio));
        when(repository.save(any(ParceiroNegocio.class))).thenReturn(parceiroNegocio);

        ParceiroNegocioDto result = service.atualizar(1, dto);

        assertNotNull(result);
        assertEquals(parceiroNegocio.getNome(), result.getNome());
    }
    
    @Test
    void buscarPorNomeDeveLancarExcecaoQuandoNaoEncontrarParceiroNegocio() {
        String nome = "Nome Inexistente";
        when(repository.findByNome(nome)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> {
            service.buscarPorNome(nome);
        });
    }
    
    @Test
    void atualizarDeveLancarExcecaoQuandoNaoEncontrarParceiroNegocio() {
        int idInexistente = 1;
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setNome("Novo Nome");

        when(repository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> {
            service.atualizar(idInexistente, dto);
        });
    }

    @Test
    void atualizarDeveAtualizarParceiroNegocioQuandoEncontrar() {
        int idExistente = 1;
        ParceiroNegocio parceiroExistente = criarParceiroNegocio();
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setNome("Novo Nome");

        when(repository.findById(anyInt())).thenReturn(Optional.of(parceiroExistente));
        when(repository.save(any(ParceiroNegocio.class))).thenReturn(parceiroExistente);

        ParceiroNegocioDto result = service.atualizar(idExistente, dto);

        verify(repository).findById(idExistente);
        verify(repository).save(parceiroExistente);
        assertEquals(dto.getNome(), result.getNome());
    }
    
    @Test
    void buscarPorIdDeveRetornarParceiroNegocioDto_QuandoEncontrarParceiroNegocio() {
        ParceiroNegocio parceiroNegocio = criarParceiroNegocio();
        when(repository.findById(1)).thenReturn(Optional.of(parceiroNegocio));

        ParceiroNegocioDto result = service.buscarPorId(1);

        assertNotNull(result);
        assertEquals(parceiroNegocio.getNome(), result.getNome());
    }

    @Test
    void buscarPorIdDeveLancarExcecao_QuandoNaoEncontrarParceiroNegocio() {
        int id = 1;
        when(repository.findById(id)).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            service.buscarPorId(id);
        });

        assertEquals("Entidade não encontrada com o ID: " + id, exception.getMessage());
    }
    
    @Test
    void buscarPorIdDeveLancarEntidadeNaoEncontradaException_QuandoIdNaoForEncontrado() {
        int id = 1;
        when(repository.findById(id)).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            service.buscarPorId(id);
        });

        assertEquals("Entidade não encontrada com o ID: " + id, exception.getMessage());
    }

    @Test
    void buscarPorIdDeveLancarRuntimeException_QuandoOcorrerOutroTipoDeExcecao() {
        int id = 1;
        when(repository.findById(id)).thenThrow(new RuntimeException("Erro ao buscar entidade"));

        assertThrows(RuntimeException.class, () -> {
            service.buscarPorId(id);
        });
    }
    
    @Test
    void deletarPorIdDeveLancarEntidadeNaoEncontradaException_QuandoNaoEncontrarEntidade() {
        int id = 1;
        when(repository.findById(id)).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            service.deletarPorId(id);
        });

        assertEquals("Entidade com o ID " + id + " não encontrada", exception.getMessage());
    }

    @Test
    void deletarPorIdDeveDeletarEntidadeComSucesso_QuandoEncontrarEntidade() {
        int id = 1;
        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
        when(repository.findById(id)).thenReturn(Optional.of(parceiroNegocio));

        service.deletarPorId(id);

        verify(repository).deleteById(id);
    }




    private ParceiroNegocio criarParceiroNegocio() {
        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setNome(NOME);
        entidade.setEndereco(ENDERECO);
        entidade.setInscricaoFiscal(INSCRICAO_FISCAL);
        entidade.setTelefone(TELEFONE);
        return entidade;
    }

    private ParceiroNegocioDto criarParceiroNegocioDto() {
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setNome(NOME);
        dto.setEndereco(ENDERECO);
        dto.setInscricaoFiscal(INSCRICAO_FISCAL);
        dto.setTelefone(TELEFONE);
        return dto;
    }
}
ce.listarTodos();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(parceiros.get(0).getNome(), result.get(0).getNome());
    }

    @Test
    void verificarBuscarPorNome() {
        ParceiroNegocio parceiroNegocio = criarParceiroNegocio();
        when(repository.findByNome(NOME)).thenReturn(Optional.of(parceiroNegocio));

        ParceiroNegocioDto result = service.buscarPorNome(NOME);

        assertNotNull(result);
        assertEquals(parceiroNegocio.getNome(), result.getNome());
    }

    @Test
    void deletarPorId() {
        ParceiroNegocio parceiroNegocio = criarParceiroNegocio();
        when(repository.findById(1)).thenReturn(Optional.of(parceiroNegocio));
        
        service.deletarPorId(1);
        
        verify(repository).deleteById(1);
    }

    @Test
    void verificaSeAtualizaOParceiro() {
        ParceiroNegocio parceiroNegocio = criarParceiroNegocio();
        ParceiroNegocioDto dto = criarParceiroNegocioDto();
        when(repository.findById(1)).thenReturn(Optional.of(parceiroNegocio));
        when(repository.save(any(ParceiroNegocio.class))).thenReturn(parceiroNegocio);

        ParceiroNegocioDto result = service.atualizar(1, dto);

        assertNotNull(result);
        assertEquals(parceiroNegocio.getNome(), result.getNome());
    }
    
    @Test
    void buscarPorNomeDeveLancarExcecaoQuandoNaoEncontrarParceiroNegocio() {
        String nome = "Nome Inexistente";
        when(repository.findByNome(nome)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> {
            service.buscarPorNome(nome);
        });
    }
    
    @Test
    void atualizarDeveLancarExcecaoQuandoNaoEncontrarParceiroNegocio() {
        int idInexistente = 1;
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setNome("Novo Nome");

        when(repository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> {
            service.atualizar(idInexistente, dto);
        });
    }

    @Test
    void atualizarDeveAtualizarParceiroNegocioQuandoEncontrar() {
        int idExistente = 1;
        ParceiroNegocio parceiroExistente = criarParceiroNegocio();
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setNome("Novo Nome");

        when(repository.findById(anyInt())).thenReturn(Optional.of(parceiroExistente));
        when(repository.save(any(ParceiroNegocio.class))).thenReturn(parceiroExistente);

        ParceiroNegocioDto result = service.atualizar(idExistente, dto);

        verify(repository).findById(idExistente);
        verify(repository).save(parceiroExistente);
        assertEquals(dto.getNome(), result.getNome());
    }

    private ParceiroNegocio criarParceiroNegocio() {
        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setNome(NOME);
        entidade.setEndereco(ENDERECO);
        entidade.setInscricaoFiscal(INSCRICAO_FISCAL);
        entidade.setTelefone(TELEFONE);
        return entidade;
    }

    private ParceiroNegocioDto criarParceiroNegocioDto() {
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setNome(NOME);
        dto.setEndereco(ENDERECO);
        dto.setInscricaoFiscal(INSCRICAO_FISCAL);
        dto.setTelefone(TELEFONE);
        return dto;
    }
}
