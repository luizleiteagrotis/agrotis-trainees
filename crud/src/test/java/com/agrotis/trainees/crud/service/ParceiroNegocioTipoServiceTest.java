package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.ParceiroNegocioTipoRepository;

public class ParceiroNegocioTipoServiceTest {
    private ParceiroNegocioTipoService service;
    private ParceiroNegocioTipoRepository repository;

    @Before
    public void setUp() {
        repository = mock(ParceiroNegocioTipoRepository.class);
        service = new ParceiroNegocioTipoService(repository);
    }

    @Test
    public void testConverterParaDtoComParceiroNegocioNulo() {
        ParceiroNegocio parceiroNegocioEntidade = null;

        ParceiroNegocioDto resultado = ParceiroNegocioTipoService.converterParaDto(parceiroNegocioEntidade);

        assertNull(resultado);
    }

    @Test
    public void testConverterParaDtoComParceiroNegocioValido() {
        ParceiroNegocio parceiroNegocioEntidade = new ParceiroNegocio();
        parceiroNegocioEntidade.setId(1);
        parceiroNegocioEntidade.setNome("Teste");
        parceiroNegocioEntidade.setInscricaoFiscal("123456");
        parceiroNegocioEntidade.setEndereco("Rua Teste, 123");
        parceiroNegocioEntidade.setTelefone("987654321");

        ParceiroNegocioDto resultado = ParceiroNegocioTipoService.converterParaDto(parceiroNegocioEntidade);

        assertEquals(parceiroNegocioEntidade.getId(), resultado.getId());
        assertEquals(parceiroNegocioEntidade.getNome(), resultado.getNome());
        assertEquals(parceiroNegocioEntidade.getInscricaoFiscal(), resultado.getInscricaoFiscal());
        assertEquals(parceiroNegocioEntidade.getEndereco(), resultado.getEndereco());
        assertEquals(parceiroNegocioEntidade.getTelefone(), resultado.getTelefone());
    }

    @Test
    public void testConverterParaEntidadeComParceiroNegocioNulo() {
        ParceiroNegocioDto parceiroNegocioDto = null;

        ParceiroNegocio resultado = ParceiroNegocioTipoService.converterParaEntidade(parceiroNegocioDto);

        assertNull(resultado);
    }

    @Test
    public void testConverterParaEntidadeComParceiroNegocioValido() {
        ParceiroNegocioDto parceiroNegocioDto = new ParceiroNegocioDto();
        parceiroNegocioDto.setId(1);
        parceiroNegocioDto.setNome("Teste");
        parceiroNegocioDto.setInscricaoFiscal("123456");
        parceiroNegocioDto.setEndereco("Rua Teste, 123");
        parceiroNegocioDto.setTelefone("987654321");

        ParceiroNegocio resultado = ParceiroNegocioTipoService.converterParaEntidade(parceiroNegocioDto);

        assertEquals(parceiroNegocioDto.getId(), resultado.getId());
        assertEquals(parceiroNegocioDto.getNome(), resultado.getNome());
        assertEquals(parceiroNegocioDto.getInscricaoFiscal(), resultado.getInscricaoFiscal());
        assertEquals(parceiroNegocioDto.getEndereco(), resultado.getEndereco());
        assertEquals(parceiroNegocioDto.getTelefone(), resultado.getTelefone());
    }

    @Test
    public void testSalvar() {
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setNome("AgroTeste");

        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setId(1);
        entidade.setNome("AgroTestes");

        when(repository.save(any(ParceiroNegocio.class))).thenReturn(entidade);

        ParceiroNegocioDto resultado = service.salvar(dto);

        assertEquals(dto.getNome(), resultado.getNome());
        verify(repository, times(1)).save(any(ParceiroNegocio.class));
    }

    @Test
    public void testInserir() {
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setId(1);
        dto.setNome("Nome1");
        dto.setInscricaoFiscal("Inscricao1");
        dto.setEndereco("Endereco1");
        dto.setTelefone("Telefone1");

        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setId(1);
        entidade.setNome("Nome1");
        entidade.setInscricaoFiscal("Inscricao1");
        entidade.setEndereco("Endereco1");
        entidade.setTelefone("Telefone1");

        when(repository.save(Mockito.any(ParceiroNegocio.class))).thenReturn(entidade);

        ParceiroNegocio resultado = service.inserir(dto);

        assertEquals(entidade.getId(), resultado.getId());
        assertEquals(entidade.getNome(), resultado.getNome());
        assertEquals(entidade.getInscricaoFiscal(), resultado.getInscricaoFiscal());
        assertEquals(entidade.getEndereco(), resultado.getEndereco());
        assertEquals(entidade.getTelefone(), resultado.getTelefone());
    }

    @Test
    public void testAtualizar() {
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setId(1);
        dto.setNome("Nome1");
        dto.setInscricaoFiscal("Inscricao1");
        dto.setEndereco("Endereco1");
        dto.setTelefone("Telefone1");

        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setId(1);
        entidade.setNome("Nome1");
        entidade.setInscricaoFiscal("Inscricao1");
        entidade.setEndereco("Endereco1");
        entidade.setTelefone("Telefone1");

        when(repository.save(Mockito.any(ParceiroNegocio.class))).thenReturn(entidade);

        ParceiroNegocioDto resultado = service.atualizar(dto);

        assertEquals(entidade.getId(), resultado.getId());
        assertEquals(entidade.getNome(), resultado.getNome());
        assertEquals(entidade.getInscricaoFiscal(), resultado.getInscricaoFiscal());
        assertEquals(entidade.getEndereco(), resultado.getEndereco());
        assertEquals(entidade.getTelefone(), resultado.getTelefone());

    }

    @Test
    public void testDeletar() {
        service.deletarPorId(1);

        verify(repository).deleteById(1);
    }

    @Test
    public void testBuscaId() throws NotFoundException, org.springframework.data.crossstore.ChangeSetPersister.NotFoundException {
        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setId(1);

        when(repository.findById(1)).thenReturn(java.util.Optional.of(entidade));

        ParceiroNegocioDto retorno = service.buscarPorId(1);

        assertEquals(1, retorno.getId().intValue());
    }

    @Test
    public void testBuscaIdException() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            service.buscarPorId(1);
        });
    }

    @Test
    public void testBuscaInscricao()
                    throws NotFoundException, org.springframework.data.crossstore.ChangeSetPersister.NotFoundException {
        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setInscricaoFiscal("000.000.000/0001-00");

        when(repository.findByInscricaoFiscal("000.000.000/0001-00")).thenReturn(java.util.Optional.of(entidade));

        ParceiroNegocioDto retorno = service.buscarPorInscricaoFiscal("000.000.000/0001-00");

        assertEquals("000.000.000/0001-00", retorno.getInscricaoFiscal());
    }

    @Test
    public void testBuscaInscricaoException() {
        when(repository.findByInscricaoFiscal("Teste")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            service.buscarPorInscricaoFiscal("Teste");
        });
    }

    @Test
    public void testeListarTodos() {
        List<ParceiroNegocio> entidades = new ArrayList<>();
        ParceiroNegocio parceiroNegocio = new ParceiroNegocio();
        parceiroNegocio.setId(parceiroNegocio.getId());
        parceiroNegocio.setNome(parceiroNegocio.getNome());
        parceiroNegocio.setInscricaoFiscal(parceiroNegocio.getInscricaoFiscal());
        parceiroNegocio.setEndereco(parceiroNegocio.getEndereco());
        parceiroNegocio.setTelefone(parceiroNegocio.getTelefone());

        ParceiroNegocio parceiro = new ParceiroNegocio();
        parceiro.setId(parceiro.getId());
        parceiro.setNome(parceiro.getNome());
        parceiro.setInscricaoFiscal(parceiro.getInscricaoFiscal());
        parceiro.setEndereco(parceiro.getEndereco());
        parceiro.setTelefone(parceiro.getTelefone());

        when(repository.findAll()).thenReturn(entidades);

        List<ParceiroNegocioDto> dtos = service.listarTodos();

        assertEquals(entidades.size(), dtos.size());
        for (int i = 0; i < entidades.size(); i++) {
            assertEquals(entidades.get(i).getId(), dtos.get(i).getId());
            assertEquals(entidades.get(i).getNome(), dtos.get(i).getNome());
            assertEquals(entidades.get(i).getEndereco(), dtos.get(i).getEndereco());
            assertEquals(entidades.get(i).getTelefone(), dtos.get(i).getTelefone());

        }

    }

}