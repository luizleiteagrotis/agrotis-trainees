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

import java.time.LocalDate;
import java.util.Optional;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ProdutoRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository repository;

    @Mock
    private ProdutoConversao conversao;

    @InjectMocks
    private ProdutoService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSalvar() {
        ParceiroNegocioDto parceiroDto = new ParceiroNegocioDto();
        parceiroDto.setId(1);
        parceiroDto.setNome("Parceiro1");
        parceiroDto.setInscricaoFiscal("Inscricao1");

        ParceiroNegocio parceiro = new ParceiroNegocio();
        parceiro.setId(1);
        parceiro.setNome("Parceiro1");
        parceiro.setInscricaoFiscal("Inscricao1");

        ProdutoDto dto = new ProdutoDto();
        dto.setNome("Nome1");
        dto.setDescricao("Descricao1");
        dto.setFabricante("Fabricante1");
        dto.setDataFabricacao(LocalDate.now().minusDays(1));
        dto.setDataValidade(LocalDate.now().plusDays(10));
        dto.setEstoque(1);
        dto.setParceiroNegocio(parceiroDto);

        Produto entidade = new Produto();
        entidade.setNome("Nome1");
        entidade.setDescricao("Descricao1");
        entidade.setFabricante("Fabricante1");
        entidade.setDataFabricacao(LocalDate.now().minusDays(1));
        entidade.setDataValidade(LocalDate.now().plusDays(10));
        entidade.setEstoque(1);
        entidade.setParceiroNegocio(parceiro);

        when(conversao.converterParaEntidade(dto)).thenReturn(entidade);
        when(repository.save(entidade)).thenReturn(entidade); // esse método é
                                                              // do spy
        when(conversao.converterParaDto(entidade)).thenReturn(dto);

        ProdutoDto resultado = service.salvar(dto);

        assertEquals(dto.getNome(), resultado.getNome());
        verify(repository, times(1)).save(any(Produto.class));

    }

    @Test
    @DisplayName("Teste para o método inserir")
    public void testeInserir() {
        // to do
    }

    @Test
    @DisplayName("Teste para o método atualizar")
    public void testeAtualizar() {
        // to do
    }

    @Test
    @DisplayName("Teste para o método deletarPorId")
    public void testeDeletar() {
        service.deletarPorId(1);

        verify(repository).deleteById(1);
    }

    @Test
    @DisplayName("Teste para o método buscarPorId")
    public void testeBuscaId() {
        // to do
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
    @DisplayName("Teste para o método buscarPorFabricante")
    public void testeBuscaFabricante() {
        // to do
    }

    @Test
    @DisplayName("Teste para o método buscarPorFabricante jogando NotFoundException")
    public void testeBuscaFabricanteException() {
        when(repository.findByFabricante("Teste")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            service.buscarPorFabricante("Teste");
        });
    }

    @Test
    @DisplayName("Teste para o método buscarPorDataFabricacao")
    public void testeBuscarDataFabricacao() {
        // to do
    }

    @Test
    @DisplayName("Teste para o método buscarPorDataFabricacao jogando NotFoundException")
    public void testeBuscaDataFabricacaoException() {
        when(repository.findByDataFabricacao(LocalDate.now().minusDays(1))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            service.buscarPorDataFabricacao(LocalDate.now().minusDays(1));
        });
    }

    @Test
    @DisplayName("Teste para o método buscarPorDataDeValidade")
    public void testeBuscaDataValidade() {
        // to do
    }

    @Test
    @DisplayName("Teste para o método buscarPorDataValidade jogando NotFoundException")
    public void testeBuscaDataValidadeException() {
        when(repository.findByDataValidade(LocalDate.now().plusDays(10))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            service.buscarPorDataValidade(LocalDate.now().plusDays(10));
        });
    }

    @Test
    @DisplayName("Teste para o método listarTodos")
    public void testeListaTodos() {
        // to do
    }

}
