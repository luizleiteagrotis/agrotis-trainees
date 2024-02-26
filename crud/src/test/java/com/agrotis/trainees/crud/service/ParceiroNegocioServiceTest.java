package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.agrotis.trainees.crud.convert.ParceiroNegocioConversor;
import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.exception.ParceiroNegocioExcecao;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;

class ParceiroNegocioServiceTest {

    @Mock
    ParceiroNegocioRepository repository;
    @Mock
    ParceiroNegocioConversor parceiroNegocioConversor;
    @Mock
    ParceiroNegocio parceiroNegocio;

    final int ID = 1;
    final String NOME = "AgroFertil Ltda";
    final String INSCRICAO_FISCAL = "74571169000999";
    final String ENDERECO = "Rua Antônio Africa";
    final String TELEFONE = "41992377204";

    @InjectMocks
    public ParceiroNegocioService parceiroNegocioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void verificarParceiroSalvando() throws ParceiroNegocioExcecao {
        ParceiroNegocioDto dto = criaParceiroDto();
        ParceiroNegocio entidade = converteParceiro(dto);

        when(parceiroNegocioConversor.converter(dto)).thenReturn(entidade);
        when(repository.save(entidade)).thenReturn(entidade);
        when(parceiroNegocioConversor.converter(entidade)).thenReturn(dto);
        ParceiroNegocioDto entidadeSalva = parceiroNegocioService.salvar(dto);

        assertNotNull(entidadeSalva);

    }

    @Test
    void verificarInscricaoFiscalNulo() throws ParceiroNegocioExcecao {
        ParceiroNegocioDto dto = criaParceiroDto();
        ParceiroNegocio entidade = converteParceiro(dto);
        dto.setInscricaoFiscal(null);
        entidade.setInscricaoFiscal(null);
        when(parceiroNegocioConversor.converter(dto)).thenReturn(entidade);
        assertThrows(ParceiroNegocioExcecao.class, () -> {
            parceiroNegocioService.salvar(dto);
        });

    }

    @Test
    void verificarInscricaoFiscalTamanho() throws ParceiroNegocioExcecao {
        ParceiroNegocioDto dto = criaParceiroDto();
        ParceiroNegocio entidade = converteParceiro(dto);
        dto.setInscricaoFiscal("11");
        entidade.setInscricaoFiscal("11");
        when(parceiroNegocioConversor.converter(dto)).thenReturn(entidade);
        assertThrows(ParceiroNegocioExcecao.class, () -> {
            parceiroNegocioService.salvar(dto);
        });
    }

    @Test
    void testarTelefoneNuloNaoLancarExcecao() throws ParceiroNegocioExcecao {
        ParceiroNegocioDto dto = criaParceiroDto();
        ParceiroNegocio entidade = converteParceiro(dto);
        dto.setTelefone(null);
        entidade.setTelefone(null);
        when(parceiroNegocioConversor.converter(dto)).thenReturn(entidade);
        when(repository.save(entidade)).thenReturn(entidade);
        assertThrows(ParceiroNegocioExcecao.class, () -> {
            parceiroNegocioService.salvar(dto);
        });
    }

    @Test
    void verificarNomeNulo() throws ParceiroNegocioExcecao {
        ParceiroNegocioDto dto = criaParceiroDto();
        ParceiroNegocio entidade = converteParceiro(dto);
        dto.setNome(null);
        entidade.setNome(null);
        when(parceiroNegocioConversor.converter(dto)).thenReturn(entidade);
        assertThrows(ParceiroNegocioExcecao.class, () -> {
            parceiroNegocioService.salvar(dto);
        });
    }

    @Test
    void verificarInscricaoFiscalExistente() {
        ParceiroNegocioDto dto = criaParceiroDto();
        ParceiroNegocio entidade = converteParceiro(dto);
        when(parceiroNegocioConversor.converter(dto)).thenReturn(entidade);
        when(repository.findByInscricaoFiscal(entidade.getInscricaoFiscal())).thenReturn(Optional.of(entidade));
        assertThrows(ParceiroNegocioExcecao.class, () -> {
            parceiroNegocioService.salvar(dto);
        });
    }

    @Test
    void verificaTelefoneInvalido() {
        ParceiroNegocioDto dto = criaParceiroDto();
        ParceiroNegocio entidade = converteParceiro(dto);
        dto.setTelefone("11");
        entidade.setTelefone("11");
        when(parceiroNegocioConversor.converter(dto)).thenReturn(entidade);
        assertThrows(ParceiroNegocioExcecao.class, () -> {
            parceiroNegocioService.salvar(dto);
        });
    }

    @Test
    void verificarSeEstaRetornandoOBuscarPorID() {
        ParceiroNegocioDto dto = criaParceiroDto();
        ParceiroNegocio entidade = converteParceiro(dto);
        when(repository.findById(ID)).thenReturn(Optional.of(entidade));
        when(parceiroNegocioService.buscarPorId(ID)).thenReturn(dto);
        ParceiroNegocioDto parceiro = parceiroNegocioService.buscarPorId(ID);
        assertNotNull(parceiro);

    }

    @Test
    void verificarSeORetornoDeBuscarPorIdENull() {
        when(repository.findById(ID)).thenReturn(Optional.empty());
        ParceiroNegocioDto parceiro = parceiroNegocioService.buscarPorId(ID);
        assertNull(parceiro);

    }

    @Test
    void verificaParceiroNulo() {
        ParceiroNegocioDto dto = criaParceiroDto();
        when(parceiroNegocioConversor.converter(dto)).thenReturn(null);
        assertThrows(ParceiroNegocioExcecao.class, () -> {
            parceiroNegocioService.salvar(dto);
        });
    }

    @Test
    void verificarBuscarPorInscricaoFiscal() {
        ParceiroNegocioDto dto = criaParceiroDto();
        ParceiroNegocio entidade = converteParceiro(dto);
        when(parceiroNegocioConversor.converter(entidade)).thenReturn(dto);
        when(repository.findByInscricaoFiscal(INSCRICAO_FISCAL)).thenReturn(Optional.of(entidade));
        ParceiroNegocioDto parceiro = parceiroNegocioService.buscarPorInscricaoFiscal(INSCRICAO_FISCAL);
        assertNotNull(parceiro);
    }

    @Test
    void verificarBuscarPorInscricaoFiscalNaoEncontrada() {
        ParceiroNegocioDto dto = criaParceiroDto();
        ParceiroNegocio entidade = converteParceiro(dto);
        when(parceiroNegocioConversor.converter(entidade)).thenReturn(dto);
        when(repository.findByInscricaoFiscal(INSCRICAO_FISCAL)).thenReturn(Optional.empty());
        ParceiroNegocioDto parceiro = parceiroNegocioService.buscarPorInscricaoFiscal(INSCRICAO_FISCAL);
        assertNull(parceiro);
    }

    @Test
    void verificarListarTodosComRetorno() {
        when(repository.findAll()).thenReturn(listarParceiro());
        List<ParceiroNegocioDto> parceirosDto = parceiroNegocioService.listarTodos();
        assertNotNull(parceirosDto);
    }

    /*
     * TO-DO VALIDAR O ATUALIZAR
     * 
     * @Test void verificarSeMetodoAtualizarEstaFuncionando() {
     * 
     * }
     */

    @Test
    void excluirUmParceiroNegocioExistente() {
        when(repository.existsById(ID)).thenReturn(true);
        parceiroNegocioService.deletarPorId(ID);
        verify(repository, times(1)).deleteById(ID);
    }

    @Test
    void excluirUmParceiroNegocioNaoExistente() {
        when(repository.existsById(ID)).thenReturn(false);
        parceiroNegocioService.deletarPorId(ID);
        verify(repository, times(0)).deleteById(ID);
    }

    ParceiroNegocioDto criaParceiroDto() {
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setInscricaoFiscal(INSCRICAO_FISCAL);
        dto.setNome(NOME);
        dto.setTelefone(TELEFONE);
        dto.setEndereco(ENDERECO);
        return dto;
    }

    ParceiroNegocio converteParceiro(ParceiroNegocioDto dto) {
        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setInscricaoFiscal(INSCRICAO_FISCAL);
        entidade.setNome(NOME);
        entidade.setTelefone(TELEFONE);
        entidade.setEndereco(ENDERECO);
        return entidade;
    }

    List<ParceiroNegocio> listarParceiro() {
        ParceiroNegocioDto dto = criaParceiroDto();
        ParceiroNegocio entidade = converteParceiro(dto);
        List<ParceiroNegocio> parceiros = new ArrayList<>();
        parceiros.add(entidade);
        return parceiros;
    }

}
