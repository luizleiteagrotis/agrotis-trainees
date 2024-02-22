package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.exception.FabricanteDuplicadoException;
import com.agrotis.trainees.crud.exception.FabricanteNaoEncontradoException;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.utils.ParceiroNegocioDTOMapper;

public class ParceiroNegocioServiceTest {

    private final Integer ID = 1000;
    private final String NOME = "TESTETDDDD";
    private final String INSCRICAOFISCAL = "12234534345";
    private final String ENDERECO = "RUA CABRAL";
    private final String TELEFONE = "(47) 88881-8888";

    @Mock
    private ParceiroNegocioRepository repository;

    @Mock
    private ParceiroNegocioDTOMapper mapper;

    @InjectMocks
    private ParceiroNegocioService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deveriaNaosalvarQuandoJaExisteUmParceiroCadastrado() {
        // given
        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();

        parceiroNegocio.setNome(NOME);
        parceiroNegocio.setEndereco(ENDERECO);
        parceiroNegocio.setInscricaoFiscal(INSCRICAOFISCAL);
        parceiroNegocio.setTelefone(TELEFONE);

        when(repository.existsByNomeOrInscricaoFiscal(parceiroNegocio.getNome(), parceiroNegocio.getInscricaoFiscal()))
                        .thenReturn(true);

        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setNome(parceiroNegocio.getNome());
        dto.setEndereco(parceiroNegocio.getEndereco());
        dto.setInscricaoFiscal(parceiroNegocio.getInscricaoFiscal());
        dto.setTelefone(parceiroNegocio.getTelefone());

        when(mapper.conveterParaEntidade(dto)).thenReturn(parceiroNegocio);
        when(repository.save(parceiroNegocio)).thenReturn(parceiroNegocio);

        // when
        Exception excecao = assertThrows(FabricanteDuplicadoException.class, () -> {
            service.salvar(dto);
        });

        // then
        assertEquals("Nome do fabricante ou inscrição fiscal já existem", excecao.getMessage());
    }

    @Test
    public void deveInserirParceiro() {
        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();

        parceiroNegocio.setNome(NOME);
        parceiroNegocio.setEndereco(ENDERECO);
        parceiroNegocio.setInscricaoFiscal(INSCRICAOFISCAL);
        parceiroNegocio.setTelefone(TELEFONE);

        when(repository.existsByNomeOrInscricaoFiscal(parceiroNegocio.getNome(), parceiroNegocio.getInscricaoFiscal()))
                        .thenReturn(false);

        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setNome(parceiroNegocio.getNome());
        dto.setEndereco(parceiroNegocio.getEndereco());
        dto.setInscricaoFiscal(parceiroNegocio.getInscricaoFiscal());
        dto.setTelefone(parceiroNegocio.getTelefone());

        when(mapper.conveterParaEntidade(dto)).thenReturn(parceiroNegocio);
        when(repository.save(any(ParceiroNegocio.class))).thenReturn(parceiroNegocio);

        when(service.salvar(dto)).thenReturn(dto);
        ParceiroNegocioDto resultado = service.salvar(dto);
        assertEquals(parceiroNegocio.getNome(), resultado.getNome());
    }

    @Test
    public void deveriaNaoAtualizarQuandoJaExisteUmParceiroCadastradoComOMesmoNome() {
        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();

        parceiroNegocio.setNome(NOME);
        parceiroNegocio.setEndereco(ENDERECO);
        parceiroNegocio.setInscricaoFiscal(INSCRICAOFISCAL);
        parceiroNegocio.setTelefone(TELEFONE);

        when(repository.existsByNomeAndIdNot(parceiroNegocio.getNome(), parceiroNegocio.getId())).thenReturn(true);

        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setNome(parceiroNegocio.getNome());
        dto.setEndereco(parceiroNegocio.getEndereco());
        dto.setInscricaoFiscal(parceiroNegocio.getInscricaoFiscal());
        dto.setTelefone(parceiroNegocio.getTelefone());

        when(mapper.conveterParaEntidade(dto)).thenReturn(parceiroNegocio);
        when(repository.save(any(ParceiroNegocio.class))).thenReturn(parceiroNegocio);

        Exception excecao = assertThrows(FabricanteDuplicadoException.class, () -> {
            service.atualizar(dto);
        });

        assertEquals("Já existe um fabricante com o mesmo nome: " + parceiroNegocio.getNome(), excecao.getMessage());
    }

    @Test
    public void deveriaNaoAtualizarQuandoJaExisteUmParceiroCadastradoComAMesmaInscricao() {
        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();

        parceiroNegocio.setNome(NOME);
        parceiroNegocio.setEndereco(ENDERECO);
        parceiroNegocio.setInscricaoFiscal(INSCRICAOFISCAL);
        parceiroNegocio.setTelefone(TELEFONE);

        when(repository.existsByInscricaoFiscalAndIdNot(parceiroNegocio.getInscricaoFiscal(), parceiroNegocio.getId()))
                        .thenReturn(true);

        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setNome(parceiroNegocio.getNome());
        dto.setEndereco(parceiroNegocio.getEndereco());
        dto.setInscricaoFiscal(parceiroNegocio.getInscricaoFiscal());
        dto.setTelefone(parceiroNegocio.getTelefone());

        when(mapper.conveterParaEntidade(dto)).thenReturn(parceiroNegocio);
        when(repository.save(any(ParceiroNegocio.class))).thenReturn(parceiroNegocio);

        Exception excecao = assertThrows(FabricanteDuplicadoException.class, () -> {
            service.atualizar(dto);
        });

        assertTrue(excecao.getMessage().contains(parceiroNegocio.getInscricaoFiscal()));
        assertEquals("Já existe um fabricante com a mesma inscrição: " + parceiroNegocio.getInscricaoFiscal(),
                        excecao.getMessage());
    }

    @Test
    public void deveAtualizarParceiro() {
        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();

        parceiroNegocio.setNome(NOME);
        parceiroNegocio.setEndereco(ENDERECO);
        parceiroNegocio.setInscricaoFiscal(INSCRICAOFISCAL);
        parceiroNegocio.setTelefone(TELEFONE);

        when(repository.existsByInscricaoFiscalAndIdNot(parceiroNegocio.getInscricaoFiscal(), parceiroNegocio.getId()))
                        .thenReturn(false);

        when(repository.existsByNomeAndIdNot(parceiroNegocio.getNome(), parceiroNegocio.getId())).thenReturn(false);

        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setNome(parceiroNegocio.getNome());
        dto.setEndereco(parceiroNegocio.getEndereco());
        dto.setInscricaoFiscal(parceiroNegocio.getInscricaoFiscal());
        dto.setTelefone(parceiroNegocio.getTelefone());

        when(mapper.conveterParaEntidade(dto)).thenReturn(parceiroNegocio);
        when(repository.save(any(ParceiroNegocio.class))).thenReturn(parceiroNegocio);

        when(service.atualizar(dto)).thenReturn(dto);
        ParceiroNegocioDto resultado = service.atualizar(dto);
        assertEquals(parceiroNegocio.getNome(), resultado.getNome());
    }

    @Test
    public void deveriaNaoBuscarPorIdInexistente() {
        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();

        parceiroNegocio.setId(ID);

        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setId(parceiroNegocio.getId());
        when(mapper.converterParaDto(parceiroNegocio)).thenReturn(dto);
        when(repository.findById(ID)).thenReturn(Optional.empty());

        Exception excecao = assertThrows(FabricanteNaoEncontradoException.class, () -> {
            service.buscarPorId(dto.getId());
        });

        assertEquals("Fabricante não encontrado para id " + dto.getId(), excecao.getMessage());

    }

    @Test
    public void deveEncontrarParceiroPorIdExistente() {
        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();

        parceiroNegocio.setId(ID);

        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setId(parceiroNegocio.getId());
        when(mapper.converterParaDto(parceiroNegocio)).thenReturn(dto);
        when(repository.findById(ID)).thenReturn(Optional.of(parceiroNegocio));

        when(service.buscarPorId(ID)).thenReturn(dto);
        ParceiroNegocioDto parceiroAchado = service.buscarPorId(dto.getId());
        assertEquals(dto.getNome(), parceiroAchado.getNome());
    }

    @Test
    public void deveriaNaoBuscarPorNomeInexistente() {
        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();

        parceiroNegocio.setNome(NOME);

        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setNome(parceiroNegocio.getNome());
        when(mapper.converterParaDto(parceiroNegocio)).thenReturn(dto);
        when(repository.findByNome(NOME)).thenReturn(Optional.empty());

        Exception excecao = assertThrows(FabricanteNaoEncontradoException.class, () -> {
            service.buscarPorNome(dto.getNome());
        });

        assertEquals("Fabricante não encontrado para nome " + dto.getNome(), excecao.getMessage());

    }

    @Test
    public void deveEncontrarParceiroPorNomeExistente() {
        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();

        parceiroNegocio.setNome(NOME);

        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setNome(parceiroNegocio.getNome());
        when(mapper.converterParaDto(parceiroNegocio)).thenReturn(dto);
        when(repository.findByNome(NOME)).thenReturn(Optional.of(parceiroNegocio));

        when(service.buscarPorNome(NOME)).thenReturn(dto);
        ParceiroNegocioDto parceiroAchado = service.buscarPorNome(dto.getNome());
        assertEquals(dto, parceiroAchado);
    }

    @Test
    public void deveriaNaoBuscarPosInscricaoFiscalInexistente() {
        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();

        parceiroNegocio.setInscricaoFiscal(INSCRICAOFISCAL);

        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setInscricaoFiscal(parceiroNegocio.getInscricaoFiscal());
        when(mapper.converterParaDto(parceiroNegocio)).thenReturn(dto);
        when(repository.findByInscricaoFiscal(INSCRICAOFISCAL)).thenReturn(Optional.empty());

        Exception excecao = assertThrows(FabricanteNaoEncontradoException.class, () -> {
            service.buscarPorInscricao(dto.getInscricaoFiscal());
        });

        assertEquals("Fabricante não encontrado para inscrição fiscal " + dto.getInscricaoFiscal(), excecao.getMessage());
    }

    @Test
    public void deveEncontrarParceiroPorInscricaoFiscal() {
        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();

        parceiroNegocio.setInscricaoFiscal(INSCRICAOFISCAL);

        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setInscricaoFiscal(parceiroNegocio.getInscricaoFiscal());
        when(mapper.converterParaDto(parceiroNegocio)).thenReturn(dto);
        when(repository.findByInscricaoFiscal(INSCRICAOFISCAL)).thenReturn(Optional.of(parceiroNegocio));

        when(service.buscarPorInscricao(INSCRICAOFISCAL)).thenReturn(dto);
        ParceiroNegocioDto parceiroAchado = service.buscarPorInscricao(dto.getInscricaoFiscal());
        assertEquals(dto, parceiroAchado);
    }

    @Test
    public void listarTodos() {
        List<ParceiroNegocio> lista = Arrays.asList(new ParceiroNegocio(), new ParceiroNegocio());
        when(repository.findAll()).thenReturn(lista);

        List<ParceiroNegocioDto> listaDto = service.listarTodos();
        assertNotNull(listaDto);
        assertEquals(2, listaDto.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void deletarPorId() {
        int id = 1;
        doNothing().when(repository).deleteById(id);
        service.deletarPorId(id);
        verify(repository, times(1)).deleteById(id);
    }

}
