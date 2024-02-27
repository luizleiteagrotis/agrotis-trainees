package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
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
import com.agrotis.trainees.crud.repository.ProdutoTipoRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProdutoTipoServiceTest {

    @Mock
    private ProdutoTipoRepository repository;

    @InjectMocks
    private ProdutoTipoService service;

    @Mock
    private ProdutoConversaoService conversao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testeSalvar() {
        ParceiroNegocioDto parceiroDto = new ParceiroNegocioDto();
        parceiroDto.setId(1);
        parceiroDto.setInscricaoFiscal("Inscricao");

        ParceiroNegocio parceiro = new ParceiroNegocio();
        parceiro.setId(1);
        parceiro.setNome("Parceiro");
        parceiro.setInscricaoFiscal("Inscricao");

        ProdutoDto dto = new ProdutoDto();
        dto.setDescricao("Descricao");
        dto.setFabricante(parceiroDto);
        dto.setDataFabricacao(LocalDate.now().minusDays(1));
        dto.setDataValidade(LocalDate.now().plusDays(10));
        dto.setEstoque(Integer.valueOf(1));

        Produto entidade = new Produto();
        entidade.setDescricao("Descricao");
        entidade.setFabricante(parceiro);
        entidade.setDataFabricacao(LocalDate.now().minusDays(1));
        entidade.setDataValidade(LocalDate.now().plusDays(10));
        entidade.setEstoque(Integer.valueOf(1));

        when(conversao.converterParaEntidade(dto)).thenReturn(entidade);
        when(repository.save(entidade)).thenReturn(entidade);
        when(conversao.converterParaDto(entidade)).thenReturn(dto);

        ProdutoDto resultado = service.salvar(dto);

        assertEquals(dto.getDescricao(), resultado.getDescricao());
        verify(repository, times(1)).save(entidade);
    }

    @Test
    public void testeInserir() {
        ProdutoDto dto = new ProdutoDto();
        dto.setDescricao("Descrição do Produto Teste");
        dto.setDataFabricacao(LocalDate.now());
        dto.setDataValidade(LocalDate.now().plusDays(30));
        dto.setEstoque(Integer.SIZE);

        ParceiroNegocioDto parceiroDto = new ParceiroNegocioDto();
        parceiroDto.setId(1);
        parceiroDto.setNome("Parceiro de Negócio Teste");
        parceiroDto.setInscricaoFiscal("123456789");

        dto.setFabricante(parceiroDto);

        Produto entidade = new Produto();
        entidade.setDescricao(dto.getDescricao());
        entidade.setFabricante(ParceiroNegocioTipoService.converterParaEntidade(parceiroDto));
        entidade.setDataFabricacao(dto.getDataFabricacao());
        entidade.setDataValidade(dto.getDataValidade());
        entidade.setEstoque(dto.getEstoque());

        when(conversao.converterParaEntidade(dto)).thenReturn(entidade);
        when(repository.save(entidade)).thenReturn(entidade);

        Produto resultado = service.inserir(dto);

        assertEquals(dto.getDescricao(), resultado.getDescricao());
        assertEquals(entidade, resultado);
    }

    @Test
    public void testeInserirException() {
        ProdutoDto dto = new ProdutoDto();

        assertThrows(RuntimeException.class, () -> {
            service.inserir(dto);
        });
    }

    @Test
    public void testeAtualizar() {
        ProdutoDto dto = new ProdutoDto();
        dto.setId(1);
        dto.setDescricao("Milho");
        dto.setDataFabricacao(LocalDate.now().minusDays(10));
        dto.setDataValidade(LocalDate.now().plusDays(20));
        dto.setEstoque(5);

        ParceiroNegocioDto parceiroDto = new ParceiroNegocioDto();
        parceiroDto.setId(1);
        parceiroDto.setNome("Agrotis");
        dto.setFabricante(parceiroDto);

        Produto entidade = new Produto();
        entidade.setId(dto.getId());
        entidade.setDescricao(dto.getDescricao());
        entidade.setFabricante(ParceiroNegocioTipoService.converterParaEntidade(parceiroDto));
        entidade.setDataFabricacao(dto.getDataFabricacao());
        entidade.setDataValidade(dto.getDataValidade());
        entidade.setEstoque(dto.getEstoque());

        when(conversao.converterParaEntidade(dto)).thenReturn(entidade);
        when(repository.save(entidade)).thenReturn(entidade);
        when(conversao.converterParaDto(entidade)).thenReturn(dto);

        ProdutoDto resultado = service.atualizar(dto);

        assertEquals(dto.getId(), resultado.getId());
        assertEquals(dto.getDescricao(), resultado.getDescricao());

        verify(repository, times(1)).save(entidade);
    }

    @Test
    public void testeDeletar() {
        service.deletarPorId(1);

        verify(repository).deleteById(1);
    }

    @Test
    public void testeBuscarPorId() throws NotFoundException {
        Produto produto = new Produto();
        produto.setId(1);
        produto.setDescricao("Produto");

        ProdutoDto dto = new ProdutoDto();
        dto.setId(produto.getId());
        dto.setDescricao(produto.getDescricao());

        when(repository.findById(1)).thenReturn(Optional.of(produto));
        when(conversao.converterParaDto(produto)).thenReturn(dto);

        ProdutoDto resultado = service.buscarPorId(1);

        assertEquals(dto, resultado);
    }

    @Test
    public void testeBuscarIdException() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            service.buscarPorId(1);
        });
    }

    @Test
    public void testeBuscaFabricante() {
        Produto produto = new Produto();
        produto.setId(1);
        produto.setDescricao("Milho");

        ParceiroNegocio parceiro = new ParceiroNegocio();
        parceiro.setId(1);
        parceiro.setNome("Agrotis");

        produto.setFabricante(parceiro);

        when(repository.findByFabricante("Agrotis")).thenReturn(Optional.of(produto));

        Produto resultado = service.buscarPorFabricante("Agrotis");

        assertEquals(produto, resultado);
    }

    @Test
    public void testeBuscaFabricanteException() {
        when(repository.findByFabricante(any(String.class))).thenReturn(Optional.empty());

        Produto produto = service.buscarPorFabricante("Fabricante");

        verify(repository).findByFabricante("Fabricante");

        assertNull(produto);
    }

    @Test
    public void testeBuscaDataFabricacaoException() {
        LocalDate dataFabricacao = LocalDate.now().plusDays(10);

        when(repository.findByDataFabricacao(dataFabricacao)).thenReturn(Optional.empty());

        Produto produto = service.buscarPorDataFabricacao(dataFabricacao);

        verify(repository).findByDataFabricacao(dataFabricacao);

        assertNull(produto);
    }

    @Test
    public void testeBuscaDataValidade() {
        Produto produto = new Produto();
        produto.setId(1);
        produto.setDescricao("Milho");
        produto.setDataValidade(LocalDate.of(2025, 1, 8));

        when(repository.findByDataValidade(LocalDate.of(2014, 12, 9))).thenReturn(Optional.of(produto));

        Produto resultado = service.buscarPorDataValidade(LocalDate.of(2014, 12, 9));

        assertNotNull(resultado);
        assertEquals(produto.getId(), resultado.getId());
        assertEquals(produto.getDescricao(), resultado.getDescricao());
        assertEquals(produto.getDataValidade(), resultado.getDataValidade());
    }

    @Test
    public void testeBuscaDataValidadeException() {
        when(repository.findByDataValidade(any(LocalDate.class))).thenReturn(Optional.empty());

        Produto produto = service.buscarPorDataValidade(LocalDate.now().plusDays(10));

        verify(repository).findByDataValidade(LocalDate.now().plusDays(10));

        assertNull(produto);
    }

    @Test
    public void testeListaTodos() {
        ParceiroNegocio parceiro1 = new ParceiroNegocio();
        parceiro1.setId(1);
        parceiro1.setNome("Agrotis");

        ParceiroNegocio parceiro2 = new ParceiroNegocio();
        parceiro2.setId(2);
        parceiro2.setNome("Agrotis informática");

        List<Produto> entidades = new ArrayList<>();
        entidades.add(new Produto("Algodão", parceiro1, "Agrotis", LocalDate.now().minusDays(10), LocalDate.now().plusDays(20)));
        entidades.add(new Produto("Milho", parceiro2, "Agrotis informática", LocalDate.now().minusDays(10),
                        LocalDate.now().plusDays(20)));

        when(repository.findAll()).thenReturn(entidades);

        List<ProdutoDto> esperados = new ArrayList<>();
        for (Produto produto : entidades) {
            ProdutoDto dto = new ProdutoDto();
            dto.setDescricao(produto.getDescricao());
            esperados.add(dto);
        }

        when(conversao.converterParaDto(any(Produto.class))).thenAnswer(invocation -> {
            Produto produto = invocation.getArgument(0);
            ProdutoDto dto = new ProdutoDto();
            dto.setDescricao(produto.getDescricao());
            return dto;
        });

        List<ProdutoDto> resultado = service.listarTodos();

        assertEquals(esperados.size(), resultado.size());
        for (int i = 0; i < esperados.size(); i++) {
            assertEquals(esperados.get(i).getDescricao(), resultado.get(i).getDescricao());
        }
    }
}
