package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;

import javassist.NotFoundException;

public class ParceiroNegocioServiceTest {

    private ParceiroNegocioService service;
    private ParceiroNegocioRepository repository;

    @Before
    public void setUp() {
        repository = mock(ParceiroNegocioRepository.class);
        service = new ParceiroNegocioService(repository);
    }

    @Test
    @DisplayName("Teste para o método salvar")
    public void testeSalvar() {
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
    @DisplayName("Teste para o método inserir")
    public void testeInserir() {
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
    @DisplayName("Teste para o método atualizar")
    public void testeAtualizar() {
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
    @DisplayName("Teste para o método deletarPorId")
    public void testeDeletar() {
        service.deletarPorId(1);

        verify(repository).deleteById(1);
    }

    @Test
    @DisplayName("Teste para o método buscarPorId com valores válidos")
    public void testeBuscaId() throws NotFoundException, org.springframework.data.crossstore.ChangeSetPersister.NotFoundException {
        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setId(1);

        when(repository.findById(1)).thenReturn(java.util.Optional.of(entidade));

        ParceiroNegocioDto retorno = service.buscarPorId(1);

        assertEquals(1, retorno.getId().intValue());
    }

    @Test
    @DisplayName("Teste para o método buscarPorId jogando NotFoundException")
    public void testeBuscaIdException() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            service.buscarPorId(1);
        });
    }

    @Test
    @DisplayName("Teste para o método buscarPorInscricaoFiscal")
    public void testeBuscaInscricao()
                    throws NotFoundException, org.springframework.data.crossstore.ChangeSetPersister.NotFoundException {
        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setInscricaoFiscal("000.000.000/0001-00");

        when(repository.findByInscricaoFiscal("000.000.000/0001-00")).thenReturn(java.util.Optional.of(entidade));

        ParceiroNegocioDto retorno = service.buscarPorInscricaoFiscal("000.000.000/0001-00");

        assertEquals("000.000.000/0001-00", retorno.getInscricaoFiscal());
    }

    @Test
    @DisplayName("Teste para o método buscarPorInscricaoFiscal jogando NotFoundException")
    public void testeBuscaInscricaoException() {
        when(repository.findByInscricaoFiscal("Teste")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            service.buscarPorInscricaoFiscal("Teste");
        });
    }

    @Test
    @DisplayName("Teste para o método listarTodos")
    public void testeListarTodos() {
        List<ParceiroNegocio> entidades = new ArrayList<>();
        entidades.add(new ParceiroNegocio("Nome1", "Inscricao1", "Endereco1", "Telefone1"));
        entidades.add(new ParceiroNegocio("Nome2", "Inscricao2", "Endereco2", "Telefone2"));
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
