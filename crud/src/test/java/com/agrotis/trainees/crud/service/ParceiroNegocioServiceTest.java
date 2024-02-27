package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.exception.FabricanteDuplicadoException;
import com.agrotis.trainees.crud.exception.FabricanteNaoEncontradoException;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.utils.ParceiroNegocioDTOMapper;

public class ParceiroNegocioServiceTest {

    private final Integer ID = 1;
    private final String NOME = "Cocapec";
    private final String INSCRICAOFISCAL = "12345";
    private final String ENDERECO = "Rua São Paulo";
    private final String TELEFONE = "41 89191-9191";

    @Mock
    private ParceiroNegocioRepository repository;

    @Mock
    private ParceiroNegocioDTOMapper mapper;

    @InjectMocks
    private ParceiroNegocioService service;

    private ParceiroNegocio parceiroNegocio;

    private ParceiroNegocioDto parceiroNegocioDto;

    private Optional<ParceiroNegocio> parceiroNegocioOpcional;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        inicializaParceiroNegocio();
    }

    @Test
    public void deveriaNaosalvarQuandoJaExisteUmParceiroCadastrado() {
        // given
        when(mapper.conveterParaEntidade(parceiroNegocioDto)).thenReturn(parceiroNegocio);
        when(repository.existsByNomeOrInscricaoFiscal(anyString(), anyString()))
        .thenReturn(true);

        // when
        Exception excecao = assertThrows(FabricanteDuplicadoException.class, () -> {
            service.salvar(parceiroNegocioDto);
        });

        // then
        assertEquals(FabricanteDuplicadoException.class, excecao.getClass());
        assertEquals("Nome do fabricante ou inscrição fiscal já existem", excecao.getMessage());
    }

    @Test
    public void deveInserirParceiro() {
        when(mapper.conveterParaEntidade(parceiroNegocioDto)).thenReturn(parceiroNegocio);
        when(mapper.converterParaDto(parceiroNegocio)).thenReturn(parceiroNegocioDto);
        when(repository.existsByNomeOrInscricaoFiscal(anyString(), anyString()))
                        .thenReturn(false);
        when(repository.save(any())).thenReturn(parceiroNegocio);

        ParceiroNegocioDto parceiroNegocioSalvo = service.salvar(parceiroNegocioDto);
        
        assertNotNull(parceiroNegocioSalvo);
        assertEquals(ParceiroNegocioDto.class, parceiroNegocioSalvo.getClass());
        assertEquals(ID, parceiroNegocioSalvo.getId());
    }

    @Test
    public void deveriaNaoAtualizarQuandoJaExisteUmParceiroCadastradoComOMesmoNome() {
        when(mapper.conveterParaEntidade(parceiroNegocioDto)).thenReturn(parceiroNegocio);
        when(repository.existsByNomeAndIdNot(anyString(), anyInt())).thenReturn(true);        

        Exception excecao = assertThrows(FabricanteDuplicadoException.class, () -> {
            service.atualizar(parceiroNegocioDto);
        });

        assertEquals(FabricanteDuplicadoException.class, excecao.getClass());
        assertEquals("Já existe um fabricante com o mesmo nome: " + NOME, excecao.getMessage());
    }

    @Test
    public void deveriaNaoAtualizarQuandoJaExisteUmParceiroCadastradoComAMesmaInscricao() {
        when(mapper.conveterParaEntidade(parceiroNegocioDto)).thenReturn(parceiroNegocio);
        when(repository.existsByInscricaoFiscalAndIdNot(anyString(), anyInt()))
                        .thenReturn(true);

        Exception excecao = assertThrows(FabricanteDuplicadoException.class, () -> {
            service.atualizar(parceiroNegocioDto);
        });

        assertEquals(FabricanteDuplicadoException.class, excecao.getClass());
        assertEquals("Já existe um fabricante com a mesma inscrição: " + INSCRICAOFISCAL,
                        excecao.getMessage());
    }

    @Test
    public void deveAtualizarParceiro() {
        when(mapper.conveterParaEntidade(parceiroNegocioDto)).thenReturn(parceiroNegocio);
        when(mapper.converterParaDto(parceiroNegocio)).thenReturn(parceiroNegocioDto);
        when(repository.existsByInscricaoFiscalAndIdNot(parceiroNegocio.getInscricaoFiscal(), parceiroNegocio.getId()))
        .thenReturn(false);
        when(repository.existsByNomeAndIdNot(parceiroNegocio.getNome(), parceiroNegocio.getId())).thenReturn(false);
        when(repository.save(any())).thenReturn(parceiroNegocio);

        ParceiroNegocioDto parceiroNegocioSalvo = service.atualizar(parceiroNegocioDto);
        
        assertNotNull(parceiroNegocioSalvo);
        assertEquals(ParceiroNegocioDto.class, parceiroNegocioSalvo.getClass());
        assertEquals(ID, parceiroNegocioSalvo.getId());
    }

    @Test
    public void deveriaNaoBuscarPorIdInexistente() {
        when(mapper.converterParaDto(parceiroNegocio)).thenReturn(parceiroNegocioDto);
        when(repository.findById(anyInt())).thenReturn(Optional.empty());

        Exception excecao = assertThrows(FabricanteNaoEncontradoException.class, () -> {
            service.buscarPorId(ID);
        });

        assertEquals(FabricanteNaoEncontradoException.class, excecao.getClass());
        assertEquals("Fabricante não encontrado para id " + ID, excecao.getMessage());

    }

    @Test
    public void deveEncontrarParceiroPorIdExistente() {
        when(mapper.converterParaDto(parceiroNegocio)).thenReturn(parceiroNegocioDto);
        when(repository.findById(anyInt())).thenReturn(parceiroNegocioOpcional);

        ParceiroNegocioDto parceiroAchado = service.buscarPorId(ID);
        
        assertNotNull(parceiroAchado);
        assertEquals(ParceiroNegocioDto.class, parceiroAchado.getClass());
        assertEquals(ID, parceiroAchado.getId());
    }

    @Test
    public void deveriaNaoBuscarPorNomeInexistente() {
        when(mapper.converterParaDto(parceiroNegocio)).thenReturn(parceiroNegocioDto);
        when(repository.findByNome(any())).thenReturn(Optional.empty());

        Exception excecao = assertThrows(FabricanteNaoEncontradoException.class, () -> {
            service.buscarPorNome(NOME);
        });

        assertEquals(FabricanteNaoEncontradoException.class, excecao.getClass());
        assertEquals("Fabricante não encontrado para nome " + NOME, excecao.getMessage());
    }

    @Test
    public void deveEncontrarParceiroPorNomeExistente() {
        when(mapper.converterParaDto(parceiroNegocio)).thenReturn(parceiroNegocioDto);
        when(repository.findByNome(any())).thenReturn(parceiroNegocioOpcional);

        ParceiroNegocioDto parceiroAchado = service.buscarPorNome(NOME);
        
        assertNotNull(parceiroAchado);
        assertEquals(ParceiroNegocioDto.class, parceiroAchado.getClass());
        assertEquals(NOME, parceiroAchado.getNome());
    }

    @Test
    public void deveriaNaoBuscarPorInscricaoFiscalInexistente() {
        when(mapper.converterParaDto(parceiroNegocio)).thenReturn(parceiroNegocioDto);
        when(repository.findByInscricaoFiscal(any())).thenReturn(Optional.empty());

        Exception excecao = assertThrows(FabricanteNaoEncontradoException.class, () -> {
            service.buscarPorInscricao(INSCRICAOFISCAL);
        });

        assertEquals(FabricanteNaoEncontradoException.class, excecao.getClass());
        assertEquals("Fabricante não encontrado para inscrição fiscal " + INSCRICAOFISCAL, excecao.getMessage());
    }

    @Test
    public void deveEncontrarParceiroPorInscricaoFiscalExistente() {
        when(mapper.converterParaDto(parceiroNegocio)).thenReturn(parceiroNegocioDto);
        when(repository.findByInscricaoFiscal(any())).thenReturn(parceiroNegocioOpcional);

        ParceiroNegocioDto parceiroAchado = service.buscarPorInscricao(INSCRICAOFISCAL);
        
        assertNotNull(parceiroAchado);
        assertEquals(ParceiroNegocioDto.class, parceiroAchado.getClass());
        assertEquals(INSCRICAOFISCAL, parceiroAchado.getInscricaoFiscal());
    }

    @Test
    public void listarTodos() {
        when(mapper.converterParaDto(parceiroNegocio)).thenReturn(parceiroNegocioDto);
        when(repository.findAll()).thenReturn(List.of(parceiroNegocio, parceiroNegocio));
        
        List<ParceiroNegocioDto> parceirosNegocios = service.listarTodos();
       
        assertNotNull(parceirosNegocios);
        assertEquals(2, parceirosNegocios.size());
        assertEquals(ParceiroNegocioDto.class, parceirosNegocios.get(0).getClass());
        assertEquals(ID, parceirosNegocios.get(1).getId());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void deveriaNaoDeletarParceiroIdInexistente() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        
        Exception excecao = assertThrows(FabricanteNaoEncontradoException.class, () -> {
            service.deletarPorId(ID);
        });
            
        assertEquals(FabricanteNaoEncontradoException.class, excecao.getClass());
        assertEquals("Fabricante não encontrado para id " + ID, excecao.getMessage());
    }

    @Test
    public void deletarPorId() {
        when(mapper.converterParaDto(parceiroNegocio)).thenReturn(parceiroNegocioDto);
        when(repository.findById(anyInt())).thenReturn(parceiroNegocioOpcional);
        doNothing().when(repository).deleteById(anyInt());
        service.deletarPorId(ID);
        verify(repository, times(1)).deleteById(anyInt());
    }

    private void inicializaParceiroNegocio() {

        parceiroNegocio = new ParceiroNegocio();
        parceiroNegocio.setId(ID);
        parceiroNegocio.setNome(NOME);
        parceiroNegocio.setInscricaoFiscal(INSCRICAOFISCAL);
        parceiroNegocio.setEndereco(ENDERECO);
        parceiroNegocio.setTelefone(TELEFONE);

        parceiroNegocioDto = new ParceiroNegocioDto();
        parceiroNegocioDto.setId(ID);
        parceiroNegocioDto.setNome(NOME);
        parceiroNegocioDto.setInscricaoFiscal(INSCRICAOFISCAL);
        parceiroNegocioDto.setEndereco(ENDERECO);
        parceiroNegocioDto.setTelefone(TELEFONE);

        parceiroNegocioOpcional = Optional.of(parceiroNegocio);

    }

}
