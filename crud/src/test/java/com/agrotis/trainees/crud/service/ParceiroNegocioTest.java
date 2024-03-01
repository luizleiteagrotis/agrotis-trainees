package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;

import dto.ParceiroNegocioDto;

@ExtendWith(MockitoExtension.class)
public class ParceiroNegocioTest {
    private static final Integer ID = null;
    private final String NOME = "Agrotis";
    private final String INSCRICAO_FISCAL = "400289-22";
    private final String ENDERECO = "Avenidade Grega";
    private final String TELEFONE = "41998567895";
    
    @Mock
    private ParceiroNegocioRepository repository;

    @InjectMocks
    private ParceiroNegocioService service;
    
    @Test
    void deveInserirParceiroDeNegocio() {
        ParceiroNegocio parceiroNegocio = criarParceiroNegocio();
        when(repository.save(any(ParceiroNegocio.class))).thenReturn(parceiroNegocio);

        ParceiroNegocioDto dto = criarParceiroNegocioDto();
        ParceiroNegocioDto result = service.salvar(dto);

        assertNotNull(result);
        assertEquals(parceiroNegocio.getId(), result.getId());
        verify(repository).save(any(ParceiroNegocio.class));
    }
    
    @Test
    void deveLancarExcecaoQuandoJaHouverInscricaoFiscalNoBanco() {
        when(repository.save(any(ParceiroNegocio.class))).thenAnswer(invocation -> {
            ParceiroNegocio parceiroNegocio = invocation.getArgument(0);
            if ("inscricaoFiscalExistente".equals(parceiroNegocio.getInscricaoFiscal())) {
                throw new RuntimeException("Inscrição fiscal já existente no banco de dados");
            }
            return parceiroNegocio;
        });

        ParceiroNegocioDto dto = criarParceiroNegocioDto();
        dto.setInscricao("inscricaoFiscalExistente");
        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.salvar(dto));
        assertEquals("Inscrição fiscal já existente no banco de dados", exception.getMessage());
    }

    
    

    @Test
    void verificarBuscarPorId() {
        ParceiroNegocio parceiroNegocio = criarParceiroNegocio();
        when(repository.findById(ID)).thenReturn(Optional.of(parceiroNegocio));

        ParceiroNegocio result = service.buscarPorId(ID);

        assertNotNull(result);
        assertEquals(parceiroNegocio.getNome(), result.getNome());
        assertEquals(parceiroNegocio.getId(), result.getId());
    }
    
    
    @Test
    void verificaBuscarTodos() {
        List<ParceiroNegocio> parceiros = new ArrayList<>();
        parceiros.add(criarParceiroNegocio());
        when(repository.findAll()).thenReturn(parceiros);

        List<ParceiroNegocio> result = service.listarTodos();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(parceiros.get(0).getNome(), result.get(0).getNome());
    }

    @Test
    void verificarBuscarPorNome() {
        ParceiroNegocio parceiroNegocio = criarParceiroNegocio();
        when(repository.findByNome(NOME)).thenReturn(Optional.of(parceiroNegocio));

        ParceiroNegocio result = service.buscarPorNome(NOME);

        assertNotNull(result);
        assertEquals(parceiroNegocio.getNome(), result.getNome());
    }

    @Test
    void deletarPorIdSucesso() {
        ParceiroNegocio parceiroNegocio = criarParceiroNegocio();
        when(repository.findById(ID)).thenReturn(Optional.of(parceiroNegocio));

        service.deletarPorId(ID);

        verify(repository).deleteById(ID);
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
    void buscarPorNome() {
        String nome = "Nome Existente";
        ParceiroNegocio parceiro = new ParceiroNegocio();
        parceiro.setNome(nome);

        when(repository.findByNome(nome)).thenReturn(Optional.of(parceiro));

        ParceiroNegocio resultado = service.buscarPorNome(nome);

        assertNotNull(resultado);
        assertEquals(nome, resultado.getNome());
    }


   
    

    @Test
    void atualizarDeveAtualizarParceiro() {
        ParceiroNegocio parceiroExistente = criarParceiroNegocio();
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setNome("Novo Nome");

        when(repository.findById(anyInt())).thenReturn(Optional.of(parceiroExistente));
        when(repository.save(any(ParceiroNegocio.class))).thenReturn(parceiroExistente);

        ParceiroNegocioDto result = service.atualizar(ID, dto);

        verify(repository).findById(ID);
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
        dto.setInscricao(INSCRICAO_FISCAL);
        dto.setTelefone(TELEFONE);
        return dto;
    }
}
