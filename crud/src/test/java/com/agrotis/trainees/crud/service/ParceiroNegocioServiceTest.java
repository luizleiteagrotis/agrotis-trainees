package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;

@RunWith(MockitoJUnitRunner.class)
public class ParceiroNegocioServiceTest {

    @Mock
    private ParceiroNegocioConversaoService conversao;

    @Mock
    private ParceiroNegocioRepository repository;

    @InjectMocks
    private ParceiroNegocioService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(service);
    }

    @Test
    @DisplayName("Teste para o método salvar")
    public void testeSalvar() {
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setId(1);
        dto.setNome("AgroTeste");

        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setId(dto.getId());
        entidade.setNome(dto.getNome());

        when(conversao.converterParaEntidade(dto)).thenReturn(entidade);
        when(repository.save(entidade)).thenReturn(entidade);
        when(conversao.converterParaDto(entidade)).thenReturn(dto);

        ParceiroNegocioDto resultado = service.salvar(dto);

        assertEquals(dto.getNome(), resultado.getNome());
        verify(repository, times(1)).save(any(ParceiroNegocio.class));
        // Criar teste para falha:
        // verify(repository, times(0)).save(any(ParceiroNegocio.class));
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

        when(conversao.converterParaEntidade(dto)).thenReturn(entidade);
        when(repository.save(entidade)).thenReturn(entidade);

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

        when(conversao.converterParaEntidade(dto)).thenReturn(entidade);
        when(repository.save(entidade)).thenReturn(entidade);
        when(conversao.converterParaDto(entidade)).thenReturn(dto);

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
    public void testeBuscaId() throws NotFoundException {
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setId(1);

        ParceiroNegocio entidade = new ParceiroNegocio();
        // entidade.setId(dto.getId());

        when(repository.findById(1)).thenReturn(Optional.of(entidade));
        when(conversao.converterParaDto(entidade)).thenReturn(dto);
        // when(conversao.converterParaEntidade(dto)).thenReturn(entidade);

        ParceiroNegocioDto retorno = service.buscarPorId(1);

        assertEquals(dto, retorno);
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
    public void testeBuscaInscricao() throws NotFoundException {
        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setInscricaoFiscal("000.000.000/0001-00");

        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setInscricaoFiscal(entidade.getInscricaoFiscal());

        when(repository.findByInscricaoFiscal(entidade.getInscricaoFiscal())).thenReturn(java.util.Optional.of(entidade));
        when(conversao.converterParaDto(entidade)).thenReturn(dto);

        ParceiroNegocioDto retorno = service.buscarPorInscricaoFiscal("000.000.000/0001-00");

        assertEquals(entidade.getInscricaoFiscal(), retorno.getInscricaoFiscal());
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
        ParceiroNegocio parceiro1 = new ParceiroNegocio();
        parceiro1.setId(1);
        parceiro1.setNome("Parceiro1");
        parceiro1.setInscricaoFiscal("123456789");
        parceiro1.setEndereco("Endereco1");
        parceiro1.setTelefone("Telefone1");

        ParceiroNegocio parceiro2 = new ParceiroNegocio();
        parceiro2.setId(2);
        parceiro2.setNome("Parceiro2");
        parceiro2.setInscricaoFiscal("987654321");
        parceiro2.setEndereco("Endereco2");
        parceiro2.setTelefone("Telefone2");

        List<ParceiroNegocio> parceiros = Arrays.asList(parceiro1, parceiro2);
        when(repository.findAll()).thenReturn(parceiros);

        when(conversao.converterParaDto(any(ParceiroNegocio.class))).thenAnswer(invocation -> {
            ParceiroNegocio parceiro = invocation.getArgument(0);
            ParceiroNegocioDto dto = new ParceiroNegocioDto();
            dto.setId(parceiro.getId());
            dto.setNome(parceiro.getNome());
            dto.setInscricaoFiscal(parceiro.getInscricaoFiscal());
            dto.setEndereco(parceiro.getEndereco());
            dto.setTelefone(parceiro.getTelefone());
            return dto;
        });

        List<ParceiroNegocioDto> dtos = service.listarTodos();

        assertEquals(parceiros.size(), dtos.size());
        for (int i = 0; i < parceiros.size(); i++) {
            ParceiroNegocio parceiro = parceiros.get(i);
            ParceiroNegocioDto dto = dtos.get(i);
            assertEquals(parceiro.getId(), dto.getId());
            assertEquals(parceiro.getNome(), dto.getNome());
            assertEquals(parceiro.getInscricaoFiscal(), dto.getInscricaoFiscal());
            assertEquals(parceiro.getEndereco(), dto.getEndereco());
            assertEquals(parceiro.getTelefone(), dto.getTelefone());
        }
    }

}
