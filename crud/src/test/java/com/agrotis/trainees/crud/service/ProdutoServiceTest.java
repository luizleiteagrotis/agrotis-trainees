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
import java.util.ArrayList;
import java.util.List;
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
    private ProdutoConversaoService conversao;

    @InjectMocks
    private ProdutoService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Teste para o método salvar")
    public void testeSalvar() {
        ParceiroNegocioDto parceiroDto = new ParceiroNegocioDto();
        parceiroDto.setId(1);
        parceiroDto.setNome("Parceiro");
        parceiroDto.setInscricaoFiscal("Inscricao");

        ParceiroNegocio parceiro = new ParceiroNegocio();
        parceiro.setId(1);
        parceiro.setNome("Parceiro");
        parceiro.setInscricaoFiscal("Inscricao");

        ProdutoDto dto = new ProdutoDto();
        dto.setNome("Nome");
        dto.setDescricao("Descricao");
        dto.setFabricante("Fabricante");
        dto.setDataFabricacao(LocalDate.now().minusDays(1));
        dto.setDataValidade(LocalDate.now().plusDays(10));
        dto.setEstoque(1);
        dto.setParceiroNegocio(parceiroDto);

        Produto entidade = new Produto();
        entidade.setNome("Nome");
        entidade.setDescricao("Descricao");
        entidade.setFabricante("Fabricante");
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
        verify(repository, times(1)).save(entidade);

    }

    @Test
    @DisplayName("Teste para o método inserir")
    public void testeInserir() {
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
        when(repository.save(entidade)).thenReturn(entidade);

        Produto resultado = service.inserir(dto);

        assertEquals(dto.getNome(), resultado.getNome());
        assertEquals(entidade, resultado);
    }

    @Test
    @DisplayName("Teste para o método Inserir jogando RuntimeException")
    public void testeInserirException() {
        ProdutoDto dto = new ProdutoDto();

        assertThrows(RuntimeException.class, () -> {
            service.inserir(dto);
        });
    }

    @Test
    @DisplayName("Teste para o método atualizar")
    public void testeAtualizar() {
        ProdutoDto dto = new ProdutoDto();
        dto.setId(1);
        dto.setNome("Nome");
        dto.setDescricao("Descricao");
        dto.setFabricante("Fabricante");
        dto.setDataFabricacao(LocalDate.now().minusDays(10));
        dto.setDataValidade(LocalDate.now().plusDays(20));
        dto.setEstoque(5);

        ParceiroNegocioDto parceiroDto = new ParceiroNegocioDto();
        parceiroDto.setId(1);
        parceiroDto.setNome("Parceiro");
        dto.setParceiroNegocio(parceiroDto);

        Produto entidade = new Produto();
        entidade.setId(dto.getId());
        entidade.setNome(dto.getNome());
        entidade.setDescricao(dto.getDescricao());
        entidade.setFabricante(dto.getFabricante());
        entidade.setDataFabricacao(dto.getDataFabricacao());
        entidade.setDataValidade(dto.getDataValidade());
        entidade.setEstoque(dto.getEstoque());

        ParceiroNegocio parceiro = new ParceiroNegocio();
        parceiro.setId(parceiroDto.getId());
        parceiro.setNome(parceiroDto.getNome());
        entidade.setParceiroNegocio(parceiro);

        when(conversao.converterParaEntidade(dto)).thenReturn(entidade);
        when(repository.save(entidade)).thenReturn(entidade);
        when(conversao.converterParaDto(entidade)).thenReturn(dto);

        ProdutoDto resultado = service.atualizar(dto);

        assertEquals(dto.getId(), resultado.getId());
        assertEquals(dto.getNome(), resultado.getNome());

        verify(repository, times(1)).save(entidade);
    }

    @Test
    @DisplayName("Teste para o método deletarPorId")
    public void testeDeletar() {
        service.deletarPorId(1);

        verify(repository).deleteById(1);
    }

    @Test
    @DisplayName("Teste para o método buscarPorId")
    public void testeBuscarPorId() throws NotFoundException {
        Produto entidade = new Produto();
        entidade.setId(1);
        entidade.setNome("Produto");

        ProdutoDto dto = new ProdutoDto();
        dto.setId(entidade.getId());
        dto.setNome(entidade.getNome());

        when(repository.findById(1)).thenReturn(Optional.of(entidade));
        when(conversao.converterParaDto(entidade)).thenReturn(dto);

        ProdutoDto resultado = service.buscarPorId(1);

        assertEquals(dto, resultado);
    }

    @Test
    @DisplayName("Teste para o método buscarPorId jogando NotFoundException")
    public void testeBuscarIdException() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            service.buscarPorId(1);
        });
    }

    @Test
    @DisplayName("Teste para o método buscarPorParceiro")
    public void testeBuscarParceiro() {
        ParceiroNegocio parceiro = new ParceiroNegocio();
        parceiro.setId(1);
        parceiro.setNome("Parceiro");

        Produto produto = new Produto();
        produto.setId(1);
        produto.setNome("Produto");
        produto.setParceiroNegocio(parceiro);

        when(repository.findByParceiroNegocio(parceiro)).thenReturn(Optional.of(produto));

        Produto resultado = service.buscarPorParceiro(parceiro);

        assertEquals(produto, resultado);
    }

    @Test
    @DisplayName("Teste para o método buscarPorParceiro dando erro")
    public void testeBuscaParceiroErro() {
        ParceiroNegocio parceiro = new ParceiroNegocio();
        parceiro.setId(1);

        when(repository.findByParceiroNegocio(parceiro)).thenReturn(Optional.empty());

        Produto produto = service.buscarPorParceiro(parceiro);

        assertNull(produto);

    }

    @Test
    @DisplayName("Teste para o método buscarPorFabricante")
    public void testeBuscaFabricante() {
        Produto produto = new Produto();
        produto.setId(1);
        produto.setNome("Produto");
        produto.setFabricante("Fabricante");

        when(repository.findByFabricante("Fabricante")).thenReturn(Optional.of(produto));

        Produto resultado = service.buscarPorFabricante("Fabricante");

        assertEquals(produto, resultado);
    }

    @Test
    @DisplayName("Teste para o método buscarPorFabricante jogando NotFoundException")
    public void testeBuscaFabricanteException() {
        when(repository.findByFabricante(any(String.class))).thenReturn(Optional.empty());

        Produto produto = service.buscarPorFabricante("Fabricante");

        verify(repository).findByFabricante("Fabricante");

        assertNull(produto);
    }

    @Test
    @DisplayName("Teste para o método buscarPorDataFabricacao")
    public void testeBuscarDataFabricacao() {
        Produto produto = new Produto();
        produto.setId(1);
        produto.setNome("Produto");
        produto.setDataFabricacao(LocalDate.of(2024, 2, 26));

        when(repository.findByDataFabricacao(LocalDate.of(2024, 2, 26))).thenReturn(Optional.of(produto));

        Produto resultado = service.buscarPorDataFabricacao(LocalDate.of(2024, 2, 26));

        assertEquals(produto, resultado);
    }

    @Test
    @DisplayName("Teste para o método buscarPorDataFabricacao jogando NotFoundException")
    public void testeBuscaDataFabricacaoException() {
        when(repository.findByDataFabricacao(any(LocalDate.class))).thenReturn(Optional.empty());

        Produto produto = service.buscarPorDataFabricacao(LocalDate.now().plusDays(10));

        verify(repository).findByDataFabricacao(LocalDate.now().plusDays(10));

        assertNull(produto);
    }

    @Test
    @DisplayName("Teste para o método buscarPorDataDeValidade")
    public void testeBuscaDataValidade() {
        Produto produto = new Produto();
        produto.setId(1);
        produto.setNome("Produto");
        produto.setDataFabricacao(LocalDate.of(2024, 2, 26));

        when(repository.findByDataFabricacao(LocalDate.of(2024, 2, 26))).thenReturn(Optional.of(produto));

        Produto resultado = service.buscarPorDataFabricacao(LocalDate.of(2024, 2, 26));

        assertEquals(produto, resultado);
    }

    @Test
    @DisplayName("Teste para o método buscarPorDataValidade jogando NotFoundException")
    public void testeBuscaDataValidadeException() {
        when(repository.findByDataValidade(any(LocalDate.class))).thenReturn(Optional.empty());

        Produto produto = service.buscarPorDataValidade(LocalDate.now().plusDays(10));

        verify(repository).findByDataValidade(LocalDate.now().plusDays(10));

        assertNull(produto);
    }

    @Test
    @DisplayName("Teste para o método listarTodos")
    public void testeListaTodos() {
        ParceiroNegocio parceiro1 = new ParceiroNegocio();
        parceiro1.setId(1);
        parceiro1.setNome("Parceiro1");

        ParceiroNegocio parceiro2 = new ParceiroNegocio();
        parceiro2.setId(2);
        parceiro2.setNome("Parceiro2");

        List<Produto> entidades = new ArrayList<>();
        entidades.add(new Produto("Nome1", "Descricao1", parceiro1, "Fabricante1", LocalDate.now().minusDays(10),
                        LocalDate.now().plusDays(20)));
        entidades.add(new Produto("Nome2", "Descricao2", parceiro2, "Fabricante2", LocalDate.now().minusDays(10),
                        LocalDate.now().plusDays(20)));

        when(repository.findAll()).thenReturn(entidades);

        List<ProdutoDto> esperados = new ArrayList<>();
        for (Produto produto : entidades) {
            ProdutoDto dto = new ProdutoDto();
            dto.setNome(produto.getNome());
            dto.setDescricao(produto.getDescricao());
            esperados.add(dto);
        }

        when(conversao.converterParaDto(any(Produto.class))).thenAnswer(invocation -> {
            Produto produto = invocation.getArgument(0);
            ProdutoDto dto = new ProdutoDto();
            dto.setNome(produto.getNome());
            dto.setDescricao(produto.getDescricao());
            return dto;
        });

        List<ProdutoDto> resultado = service.listarTodos();

        assertEquals(esperados.size(), resultado.size());
        for (int i = 0; i < esperados.size(); i++) {
            assertEquals(esperados.get(i).getNome(), resultado.get(i).getNome());
            assertEquals(esperados.get(i).getDescricao(), resultado.get(i).getDescricao());
        }

    }

}
