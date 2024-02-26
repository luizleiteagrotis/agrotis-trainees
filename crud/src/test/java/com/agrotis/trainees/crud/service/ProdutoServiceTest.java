package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
    public void testInserir() {
        ProdutoDto dto = new ProdutoDto();
        dto.setNome("ProdutoTeste");
        dto.setDescricao("Descrição do Produto Teste");
        dto.setFabricante("Fabricante do Produto Teste");
        dto.setDataFabricacao(LocalDate.now());
        dto.setDataValidade(LocalDate.now().plusDays(30));
        dto.setEstoque(10);

        ParceiroNegocioDto parceiroDto = new ParceiroNegocioDto();
        parceiroDto.setId(1);
        parceiroDto.setNome("Parceiro de Negócio Teste");
        parceiroDto.setInscricaoFiscal("123456789");

        Produto entidade = new Produto();
        entidade.setNome(dto.getNome());
        entidade.setDescricao(dto.getDescricao());
        entidade.setFabricante(dto.getFabricante());
        entidade.setDataFabricacao(dto.getDataFabricacao());
        entidade.setDataValidade(dto.getDataValidade());
        entidade.setEstoque(dto.getEstoque());

        ParceiroNegocio parceiroEntidade = new ParceiroNegocio();
        parceiroEntidade.setId(parceiroDto.getId());
        parceiroEntidade.setNome(parceiroDto.getNome());
        parceiroEntidade.setInscricaoFiscal(parceiroDto.getInscricaoFiscal());

        entidade.setParceiroNegocio(parceiroEntidade);

        when(conversao.converterParaEntidade(dto)).thenReturn(entidade);
        when(repository.save(any(Produto.class))).thenReturn(entidade);

        Produto resultado = service.inserir(dto);

        assertEquals(dto.getNome(), resultado.getNome());
        assertEquals(entidade, resultado);
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
        when(repository.findByFabricante(any(String.class))).thenReturn(Optional.empty());

        ProdutoService service = new ProdutoService(repository);

        String fabricante = "Fabricante";

        Produto produto = service.buscarPorFabricante(fabricante);

        verify(repository).findByFabricante(fabricante);

        assertNull(produto);
    }

    @Test
    @DisplayName("Teste para o método buscarPorDataFabricacao")
    public void testeBuscarDataFabricacao() {
        // to do
    }

    @Test
    @DisplayName("Teste para o método buscarPorDataFabricacao jogando NotFoundException")
    public void testeBuscaDataFabricacaoException() {
        when(repository.findByDataFabricacao(any(LocalDate.class))).thenReturn(Optional.empty());

        ProdutoService service = new ProdutoService(repository);

        LocalDate dataFabricacao = LocalDate.now().plusDays(10);

        Produto produto = service.buscarPorDataFabricacao(dataFabricacao);

        verify(repository).findByDataFabricacao(dataFabricacao);

        assertNull(produto);
    }

    @Test
    @DisplayName("Teste para o método buscarPorDataDeValidade")
    public void testeBuscaDataValidade() {
        // to do
    }

    @Test
    @DisplayName("Teste para o método buscarPorDataValidade jogando NotFoundException")
    public void testeBuscaDataValidadeException() {
        when(repository.findByDataValidade(any(LocalDate.class))).thenReturn(Optional.empty());

        ProdutoService service = new ProdutoService(repository);

        LocalDate dataValidade = LocalDate.now().plusDays(10);

        Produto produto = service.buscarPorDataValidade(dataValidade);

        verify(repository).findByDataValidade(dataValidade);

        assertNull(produto);
    }

    @Test
    @DisplayName("Teste para o método listarTodos")
    public void testeListaTodos() {
        // to do
    }

}
