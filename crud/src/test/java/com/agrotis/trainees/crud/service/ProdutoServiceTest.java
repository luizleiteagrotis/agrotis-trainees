package com.agrotis.trainees.crud.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.CrudException;
import com.agrotis.trainees.crud.exception.DescricaoExisteException;
import com.agrotis.trainees.crud.repository.ProdutoRepository;
import com.agrotis.trainees.crud.wrapper.ProdutoWrapper;

public class ProdutoServiceTest {

    @Mock
    ProdutoRepository repository;

    @Mock
    ProdutoWrapper wrapper;

    @Mock
    DescricaoService descricaoService;

    @Mock
    ModelMapper mapper;

    @InjectMocks
    ProdutoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Teste para inserir com sucesso")
    void deveInserirComSucesso() throws DescricaoExisteException {
        ParceiroNegocio parceiro = new ParceiroNegocio();
        parceiro.setId(1);
        parceiro.setInscricaoFiscal("123456789");

        ProdutoDto dto = new ProdutoDto();
        dto.setId(1);
        dto.setDescricao("Oliva");
        dto.setIdParceiro(parceiro);

        Produto entidade = new Produto();
        entidade.setId(dto.getId());
        entidade.setDescricao(dto.getDescricao());
        entidade.setIdParceiro(dto.getIdParceiro());

        when(wrapper.converterParaEntidade(dto)).thenReturn(entidade);
        doNothing().when(descricaoService).verificarDescricao(entidade);
        when(repository.save(entidade)).thenReturn(entidade);
        when(wrapper.converterParaDto(entidade)).thenReturn(dto);

        assertDoesNotThrow(() -> {
            service.inserir(dto);
        });

        verify(repository, times(1)).save(entidade);

    }

    @Test
    @DisplayName("Teste para inserir falhou")
    void deveRetornarErroAoInserir() throws DescricaoExisteException {
        ParceiroNegocio parceiro = new ParceiroNegocio();
        parceiro.setId(1);
        parceiro.setInscricaoFiscal("123456789");

        ProdutoDto dto = new ProdutoDto();
        dto.setId(1);
        dto.setDescricao("Oliva");
        dto.setIdParceiro(parceiro);

        Produto entidade = new Produto();
        entidade.setId(dto.getId());
        entidade.setDescricao(dto.getDescricao());
        entidade.setIdParceiro(dto.getIdParceiro());

        doThrow(new DescricaoExisteException("A descrição ja existe")).when(descricaoService).verificarDescricao(entidade);
        when(wrapper.converterParaEntidade(dto)).thenReturn(entidade);
        when(repository.save(entidade)).thenReturn(null);
        when(wrapper.converterParaDto(entidade)).thenReturn(dto);

        Exception excecao = assertThrows(DescricaoExisteException.class, () -> {
            service.inserir(dto);
        });

        assertEquals("A descrição ja existe", excecao.getMessage());

    }

    @Test
    @DisplayName("Teste para o atualizar sucesso")
    void deveAtualizarComSucesso() throws DescricaoExisteException {
        ParceiroNegocio parceiro = new ParceiroNegocio();
        parceiro.setId(1);
        parceiro.setInscricaoFiscal("123456789");

        ProdutoDto dto = new ProdutoDto();
        dto.setId(1);
        dto.setDescricao("Oliva");
        dto.setIdParceiro(parceiro);

        Produto entidade = new Produto();
        entidade.setId(1);
        entidade.setDescricao("Maçã");
        entidade.setIdParceiro(parceiro);

        ProdutoDto entidadeAtualizada = new ProdutoDto();
        entidadeAtualizada.setId(1);
        entidadeAtualizada.setDescricao("Oliva");
        entidadeAtualizada.setIdParceiro(parceiro);

        when(repository.findById(dto.getId())).thenReturn(Optional.of(entidade));
        doNothing().when(mapper).map(dto, entidade);
        doNothing().when(descricaoService).verificarDescricaoEId(entidade);
        when(wrapper.converterParaDto(entidade)).thenReturn(entidadeAtualizada);
        when(repository.save(entidade)).thenReturn(entidade);

        assertDoesNotThrow(() -> {
            service.atualizar(dto);
        });

        assertEquals(dto.getDescricao(), entidadeAtualizada.getDescricao());
        verify(repository, times(1)).save(entidade);

    }

    @Test
    @DisplayName("Teste id nulo")
    void deveMostrarErroPorIdNulo() {
        ParceiroNegocio parceiro = new ParceiroNegocio();
        parceiro.setId(1);
        parceiro.setInscricaoFiscal("123456789");

        ProdutoDto dto = new ProdutoDto();
        dto.setDescricao("Oliva");
        dto.setIdParceiro(parceiro);

        Exception excecao = assertThrows(CrudException.class, () -> {
            service.atualizar(dto);
        });

        assertEquals("Obrigatório preencher o id do produto.", excecao.getMessage());
    }

    @Test
    @DisplayName("Teste descrição existente")
    void deveRetornarErroPorDescricaoExistente() throws DescricaoExisteException {
        ParceiroNegocio parceiro = new ParceiroNegocio();
        parceiro.setId(1);
        parceiro.setInscricaoFiscal("123456789");

        ProdutoDto dto = new ProdutoDto();
        dto.setId(1);
        dto.setDescricao("Oliva");
        dto.setIdParceiro(parceiro);

        Produto entidade = new Produto();
        entidade.setId(1);
        entidade.setDescricao("Oliva");
        entidade.setIdParceiro(parceiro);

        when(repository.findById(dto.getId())).thenReturn(Optional.of(entidade));
        doThrow(new DescricaoExisteException("A descrição já existe.")).when(descricaoService).verificarDescricaoEId(entidade);

        service.atualizar(dto);

        verify(descricaoService, times(1)).verificarDescricaoEId(entidade);
    }

    @Test
    @DisplayName("Teste busca por id erro")
    void deveRetornarErroAoBuscarPorId() {

        when(repository.findById(1)).thenReturn(Optional.empty());

        Exception excecao = assertThrows(NoSuchElementException.class, () -> {
            service.buscarPorId(1);
        });

        assertEquals("Informações não encontradas para o id 1", excecao.getMessage());
    }

    @Test
    @DisplayName("teste de sucesso ao buscar por descrição")
    void deveBuscarPorDescricao() {
        ParceiroNegocio parceiro = new ParceiroNegocio();
        parceiro.setId(1);
        parceiro.setInscricaoFiscal("123456789");

        ProdutoDto dto = new ProdutoDto();
        dto.setId(1);
        dto.setDescricao("Oliva");
        dto.setIdParceiro(parceiro);

        Produto entidade = new Produto();
        entidade.setId(1);
        entidade.setDescricao("Oliva");
        entidade.setIdParceiro(parceiro);

        when(repository.findByDescricao(dto.getDescricao())).thenReturn(Optional.of(entidade));

        assertDoesNotThrow(() -> {
            service.buscarPorDescricao(dto.getDescricao());
        });

        verify(repository, times(1)).findByDescricao(dto.getDescricao());
    }

    @Test
    @DisplayName("Teste falho para buscar por Descrição")
    void deveRetornarErroPorDescricaoInexistente() {
        when(repository.findByDescricao("descrição")).thenReturn(Optional.empty());

        Exception excecao = assertThrows(NoSuchElementException.class, () -> {
            service.buscarPorDescricao("descrição");
        });

        assertEquals("Informações não encontradas para a descrição descrição", excecao.getMessage());
    }

    @Test
    @DisplayName("Teste Deletado com sucesso")
    void deveDeletarProduto() {

        assertDoesNotThrow(() -> {
            service.deletarPorId(1);
        });
        verify(repository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Teste listar todos com sucesso")
    void deveListarTodos() {
        ParceiroNegocio parceiro = new ParceiroNegocio();
        parceiro.setId(1);
        parceiro.setInscricaoFiscal("123456789");

        Produto entidade = new Produto();
        entidade.setId(1);
        entidade.setDescricao("Maçã");
        entidade.setIdParceiro(parceiro);

        Produto entidade2 = new Produto();
        entidade2.setId(2);
        entidade2.setDescricao("Azeitona");
        entidade2.setIdParceiro(parceiro);

        List<Produto> produtos = new ArrayList();
        produtos.add(entidade);
        produtos.add(entidade2);

        when(repository.findAll()).thenReturn(produtos);
        List<Produto> produtos1 = service.listarTodos();

        assertEquals(2, produtos1.size());

    }

}
