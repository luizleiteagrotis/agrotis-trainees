package com.agrotis.trainees.crud.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.agrotis.trainees.crud.convert.ProdutoConversor;
import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.ProdutoExcecao;
import com.agrotis.trainees.crud.helper.Validador;
import com.agrotis.trainees.crud.repository.ProdutoRepository;

class ProdutoServiceTest {
    @Mock
    ProdutoRepository produtoRepository;
    @Mock
    ProdutoConversor produtoConversor;
    @Mock
    ParceiroNegocioService parceiroNegocioService;
    @Mock
    Validador validador;

    @InjectMocks
    ProdutoService produtoService;

    final int ID = 0;
    final String NOME = "fertilizante";
    final String DESCRICAO = "4-18-8";
    final ParceiroNegocio FABRICANTE = criarParceiro();
    final LocalDate DATAFABRICACAO = LocalDate.of(2020, 10, 10);
    final LocalDate DATAVALIDADE = LocalDate.of(2023, 10, 10);
    final BigDecimal ESTOQUE = BigDecimal.ZERO;
    final BigDecimal CUSTOMEDIO = BigDecimal.ZERO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    ParceiroNegocio criarParceiro() {
        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setInscricaoFiscal("74571169000999");
        entidade.setNome("AgroFertil Ltda");
        entidade.setTelefone("41992377204");
        entidade.setEndereco("Rua Antônio Africa");
        return entidade;
    }

    ParceiroNegocioDto criarParceiroDto() {
        ParceiroNegocioDto entidade = new ParceiroNegocioDto();
        entidade.setInscricaoFiscal("74571169000999");
        entidade.setNome("AgroFertil Ltda");
        entidade.setTelefone("41992377204");
        entidade.setEndereco("Rua Antônio Africa");
        return entidade;
    }

    ProdutoDto criarDto() {
        ProdutoDto dto = new ProdutoDto();
        dto.setNome(NOME);
        dto.setDescricao(DESCRICAO);
        dto.setFabricante(FABRICANTE);
        dto.setDataFabricacao(DATAFABRICACAO);
        dto.setDataValidade(DATAVALIDADE);
        dto.setCustoMedio(CUSTOMEDIO);
        dto.setEstoque(ESTOQUE);
        return dto;
    }

    Produto criarProduto() {
        Produto entidade = new Produto();
        entidade.setNome(NOME);
        entidade.setDescricao(DESCRICAO);
        entidade.setFabricante(FABRICANTE);
        entidade.setDataFabricacao(DATAFABRICACAO);
        entidade.setDataValidade(DATAVALIDADE);
        entidade.setEstoque(ESTOQUE);
        entidade.setCustoMedio(CUSTOMEDIO);
        return entidade;
    }

    List<ProdutoDto> criarListaProdutosDto() {
        ProdutoDto dto = criarDto();
        List<ProdutoDto> dtos = new ArrayList<>();
        dtos.add(dto);
        return dtos;
    }

    List<Produto> criarListaProdutos() {
        Produto entidade = criarProduto();
        List<Produto> entidades = new ArrayList<>();
        entidades.add(entidade);
        return entidades;
    }

    @Test
    void testarProdutoSendoSalvoComTodosOsCamposPreenchidos() throws ProdutoExcecao {
        ProdutoDto dto = criarDto();
        Produto entidade = criarProduto();
        ParceiroNegocioDto parceiroDto = criarParceiroDto();
        when(produtoConversor.converter(dto)).thenReturn(entidade);
        when(validador.existeParceiroPorId(ID)).thenReturn(true);
        when(parceiroNegocioService.buscarPorId(ID)).thenReturn(parceiroDto);
        when(produtoRepository.save(entidade)).thenReturn(entidade);
        when(produtoConversor.converter(entidade)).thenReturn(dto);

        ProdutoDto produtoSalvo = produtoService.salvar(dto);
        verify(validador).existeParceiroPorId(ID);
        assertNotNull(produtoSalvo);
    }

    @Test
    void testarProdutoSendoSalvoSemTerUmParceiroDeNegocioValidoEsperandoUmaExcecao() {
        ProdutoDto dto = criarDto();
        Produto entidade = criarProduto();
        when(produtoConversor.converter(dto)).thenReturn(entidade);
        when(validador.existeParceiroPorId(ID)).thenReturn(false);
        when(parceiroNegocioService.buscarPorId(ID)).thenReturn(null);
        when(produtoRepository.save(entidade)).thenReturn(entidade);
        when(produtoConversor.converter(entidade)).thenReturn(dto);
        assertThrows(ProdutoExcecao.class, () -> {
            produtoService.salvar(dto);
        });
    }

    @Test
    void testarProdutoSendoSalvoComNomeNuloEsperandoUmaExcecao() {
        ProdutoDto dto = criarDto();
        Produto entidade = criarProduto();
        ParceiroNegocioDto parceiroDto = criarParceiroDto();
        dto.setNome(null);
        entidade.setNome(null);
        when(produtoConversor.converter(dto)).thenReturn(entidade);
        when(validador.existeParceiroPorId(ID)).thenReturn(true);
        when(parceiroNegocioService.buscarPorId(ID)).thenReturn(parceiroDto);
        when(produtoRepository.save(entidade)).thenReturn(entidade);
        when(produtoConversor.converter(entidade)).thenReturn(dto);
        assertThrows(ProdutoExcecao.class, () -> {
            produtoService.salvar(dto);
        });
    }

    @Test
    void testarProdutoSendoSalvoComNomeDataDeValidadeNullEsperandoUmaExcecao() {
        ProdutoDto dto = criarDto();
        Produto entidade = criarProduto();
        ParceiroNegocioDto parceiroDto = criarParceiroDto();
        dto.setDataValidade(null);
        entidade.setDataValidade(null);
        when(produtoConversor.converter(dto)).thenReturn(entidade);
        when(validador.existeParceiroPorId(ID)).thenReturn(true);
        when(parceiroNegocioService.buscarPorId(ID)).thenReturn(parceiroDto);
        when(produtoRepository.save(entidade)).thenReturn(entidade);
        when(produtoConversor.converter(entidade)).thenReturn(dto);
        assertThrows(ProdutoExcecao.class, () -> {
            produtoService.salvar(dto);
        });
    }

    @Test
    void testarProdutoSendoSalvoComNomeDataDeFabricacaoNullEsperandoUmaExcecao() {
        ProdutoDto dto = criarDto();
        Produto entidade = criarProduto();
        ParceiroNegocioDto parceiroDto = criarParceiroDto();
        dto.setDataFabricacao(null);
        entidade.setDataFabricacao(null);
        when(produtoConversor.converter(dto)).thenReturn(entidade);
        when(validador.existeParceiroPorId(ID)).thenReturn(true);
        when(parceiroNegocioService.buscarPorId(ID)).thenReturn(parceiroDto);
        when(produtoRepository.save(entidade)).thenReturn(entidade);
        when(produtoConversor.converter(entidade)).thenReturn(dto);
        assertThrows(ProdutoExcecao.class, () -> {
            produtoService.salvar(dto);
        });
    }

    @Test
    void testarProdutoSendoSalvoComUmaDataDeValidadeMenorQueVencimentoEsperandoUmaExcecao() {
        ProdutoDto dto = criarDto();
        Produto entidade = criarProduto();
        ParceiroNegocioDto parceiroDto = criarParceiroDto();
        dto.setDataFabricacao(DATAVALIDADE);
        entidade.setDataFabricacao(DATAVALIDADE);
        dto.setDataValidade(DATAFABRICACAO);
        entidade.setDataValidade(DATAFABRICACAO);
        when(produtoConversor.converter(dto)).thenReturn(entidade);
        when(validador.existeParceiroPorId(ID)).thenReturn(true);
        when(parceiroNegocioService.buscarPorId(ID)).thenReturn(parceiroDto);
        when(produtoRepository.save(entidade)).thenReturn(entidade);
        when(produtoConversor.converter(entidade)).thenReturn(dto);
        assertThrows(ProdutoExcecao.class, () -> {
            produtoService.salvar(dto);
        });
    }

    @Test
    void testarProdutoSendoSalvoComUmProdutoIgualJaSalvoNoBancoEsperandoUmaExcecao() {
        ProdutoDto dto = criarDto();
        Produto entidade = criarProduto();
        ParceiroNegocioDto parceiroDto = criarParceiroDto();
        when(produtoConversor.converter(dto)).thenReturn(entidade);
        when(validador.existeParceiroPorId(ID)).thenReturn(true);
        when(parceiroNegocioService.buscarPorId(ID)).thenReturn(parceiroDto);
        when(produtoRepository.findByNomeAndDataFabricacaoAndFabricanteAndDescricao(NOME, DATAFABRICACAO, FABRICANTE, DESCRICAO))
                        .thenReturn(Optional.of(entidade));
        when(produtoRepository.save(entidade)).thenReturn(entidade);
        when(produtoConversor.converter(entidade)).thenReturn(dto);
        assertThrows(ProdutoExcecao.class, () -> {
            produtoService.salvar(dto);
        });
    }

    @Test
    void testarBuscarPorIDComRetorno() {
        ProdutoDto dto = criarDto();
        Produto entidade = criarProduto();
        when(produtoRepository.findById(ID)).thenReturn(Optional.of(entidade));
        when(produtoConversor.converter(entidade)).thenReturn(dto);
        ProdutoDto dtoEsperado = produtoService.buscarPorId(ID);
        assertNotNull(dtoEsperado);
    }

    @Test
    void testarBuscarPorIDSemRetorno() {
        ProdutoDto dto = criarDto();
        Produto entidade = criarProduto();
        when(produtoRepository.findById(ID)).thenReturn(Optional.empty());
        when(produtoConversor.converter(entidade)).thenReturn(dto);
        ProdutoDto dtoEsperado = produtoService.buscarPorId(ID);
        assertNull(dtoEsperado);
    }

    @Test
    void testarBuscarPorDataFabricacaoComRetorno() throws ProdutoExcecao {
        List<ProdutoDto> dtos = criarListaProdutosDto();
        List<Produto> entidade = criarListaProdutos();
        when(produtoRepository.findByDataFabricacao(DATAFABRICACAO)).thenReturn(entidade);
        when(produtoConversor.converter(entidade)).thenReturn(dtos);
        List<ProdutoDto> entidades = produtoService.buscarPorDataFabricacao(DATAFABRICACAO);
        assertNotNull(entidades);
    }

    @Test
    void testarBuscarPorDataFabricacaoRetornandoListaVazia() throws ProdutoExcecao {
        List<Produto> entidade = new ArrayList<>();
        when(produtoRepository.findByDataFabricacao(DATAFABRICACAO)).thenReturn(entidade);
        List<ProdutoDto> entidades = produtoService.buscarPorDataFabricacao(DATAFABRICACAO);
        assertEquals(0, entidades.size());
    }

    @Test
    void testarBuscarPorDataFabricacaoComParametroNull() {
        assertThrows(ProdutoExcecao.class, () -> {
            produtoService.buscarPorDataFabricacao(null);
        });
    }

    @Test
    void testarBuscarPorDataValidadeComRetorno() throws ProdutoExcecao {
        List<ProdutoDto> dtos = criarListaProdutosDto();
        List<Produto> entidade = criarListaProdutos();
        when(produtoRepository.findByDataValidade(DATAVALIDADE)).thenReturn(entidade);
        when(produtoConversor.converter(entidade)).thenReturn(dtos);
        List<ProdutoDto> entidades = produtoService.buscarPorDataValidade(DATAVALIDADE);
        assertNotNull(entidades);
    }

    @Test
    void testarBuscarPorDataValidadeRetornandoListaVazia() throws ProdutoExcecao {
        List<Produto> entidade = new ArrayList<>();
        when(produtoRepository.findByDataValidade(DATAVALIDADE)).thenReturn(entidade);
        List<ProdutoDto> entidades = produtoService.buscarPorDataValidade(DATAVALIDADE);
        assertEquals(0, entidades.size());
    }

    @Test
    void testarBuscarPorDataValidadeComParametroNull() throws ProdutoExcecao {
        assertThrows(ProdutoExcecao.class, () -> {
            produtoService.buscarPorDataValidade(null);
        });
    }

    //

    @Test
    void testarBuscarPorNomeComRetorno() throws ProdutoExcecao {
        List<ProdutoDto> dtos = criarListaProdutosDto();
        List<Produto> entidade = criarListaProdutos();
        when(produtoRepository.findByNome(NOME)).thenReturn(entidade);
        when(produtoConversor.converter(entidade)).thenReturn(dtos);
        List<ProdutoDto> entidades = produtoService.buscarPorNome(NOME);
        assertNotNull(entidades);
    }

    @Test
    void testarBuscarPorNomeRetornandoListaVazia() throws ProdutoExcecao {
        List<Produto> entidade = new ArrayList<>();
        when(produtoRepository.findByNome(NOME)).thenReturn(entidade);
        List<ProdutoDto> entidades = produtoService.buscarPorNome(NOME);
        assertEquals(0, entidades.size());
    }

    @Test
    void testarBuscarPorNomeComParametroNull() throws ProdutoExcecao {
        assertThrows(ProdutoExcecao.class, () -> {
            produtoService.buscarPorNome(null);
        });
    }

    //

    @Test
    void testarListarTodosComRetorno() {
        List<ProdutoDto> dtos = criarListaProdutosDto();
        List<Produto> entidade = criarListaProdutos();
        when(produtoRepository.findAll()).thenReturn(entidade);
        when(produtoConversor.converter(entidade)).thenReturn(dtos);
        List<ProdutoDto> entidades = produtoService.listarTodos();
        assertEquals(1, entidades.size());
        assertNotNull(entidades);
    }

    @Test
    void testarListarTodosComRetornoVazio() {
        List<ProdutoDto> dtos = new ArrayList<>();
        List<Produto> entidade = new ArrayList<>();
        when(produtoRepository.findAll()).thenReturn(entidade);
        when(produtoConversor.converter(entidade)).thenReturn(dtos);
        List<ProdutoDto> entidades = produtoService.listarTodos();
        assertEquals(0, entidades.size());
    }

    @Test
    void atualizarTodosOsCampos() throws ProdutoExcecao {
        ProdutoDto dto = criarDto();
        Produto entidade = criarProduto();
        Produto novaEntidade = new Produto("Feijão", "5-10-5", new ParceiroNegocio(), LocalDate.of(2022, 10, 10),
                        LocalDate.of(2024, 10, 10));
        when(produtoConversor.converter(dto)).thenReturn(novaEntidade);
        when(produtoRepository.findById(ID)).thenReturn(Optional.of(entidade));
        produtoService.atualizar(dto, ID);
        assertEquals(entidade.getNome(), novaEntidade.getNome());
        assertEquals(entidade.getFabricante(), novaEntidade.getFabricante());
        assertEquals(entidade.getDescricao(), novaEntidade.getDescricao());
        assertEquals(entidade.getDataFabricacao(), novaEntidade.getDataFabricacao());
        assertEquals(entidade.getDataValidade(), novaEntidade.getDataValidade());

        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    @Test
    void atualizarProdutoInexistente() throws ProdutoExcecao {
        ProdutoDto dto = criarDto();
        Produto novaEntidade = new Produto("Feijão", "5-10-5", new ParceiroNegocio(), LocalDate.of(2022, 10, 10),
                        LocalDate.of(2024, 10, 10));
        when(produtoConversor.converter(dto)).thenReturn(novaEntidade);
        when(produtoRepository.findById(ID)).thenReturn(Optional.empty());
        assertThrows(ProdutoExcecao.class, () -> {
            produtoService.atualizar(dto, ID);
        });

        verify(produtoRepository, times(0)).save(any(Produto.class));
    }

    @Test
    void atualizarNomeDoProduto() throws ProdutoExcecao {
        ProdutoDto dto = criarDto();
        Produto entidade = criarProduto();
        Produto novaEntidade = new Produto();
        novaEntidade.setNome("Feijão");
        when(produtoConversor.converter(dto)).thenReturn(novaEntidade);
        when(produtoRepository.findById(ID)).thenReturn(Optional.of(entidade));
        produtoService.atualizar(dto, ID);
        assertEquals(entidade.getNome(), novaEntidade.getNome());

        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    @Test
    void atualizarNomeDoProdutoComNomeInvalidoEsperandoExcecao() throws ProdutoExcecao {
        ProdutoDto dto = criarDto();
        Produto entidade = criarProduto();
        Produto novaEntidade = new Produto();
        novaEntidade.setNome("");
        when(produtoConversor.converter(dto)).thenReturn(novaEntidade);
        when(produtoRepository.findById(ID)).thenReturn(Optional.of(entidade));
        assertThrows(ProdutoExcecao.class, () -> {
            produtoService.atualizar(dto, ID);
        });

        verify(produtoRepository, times(0)).save(any(Produto.class));
    }

    @Test
    void atualizarDescricaoDoProduto() throws ProdutoExcecao {
        ProdutoDto dto = criarDto();
        Produto entidade = criarProduto();
        Produto novaEntidade = new Produto();
        novaEntidade.setDescricao("10-10-10");
        when(produtoConversor.converter(dto)).thenReturn(novaEntidade);
        when(produtoRepository.findById(ID)).thenReturn(Optional.of(entidade));
        produtoService.atualizar(dto, ID);
        assertEquals(entidade.getDescricao(), novaEntidade.getDescricao());

        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    @Test
    void atualizarDescricaoDoProdutoVazia() throws ProdutoExcecao {
        ProdutoDto dto = criarDto();
        Produto entidade = criarProduto();
        Produto novaEntidade = new Produto();
        novaEntidade.setDescricao("");
        when(produtoConversor.converter(dto)).thenReturn(novaEntidade);
        when(produtoRepository.findById(ID)).thenReturn(Optional.of(entidade));
        produtoService.atualizar(dto, ID);
        assertEquals(entidade.getDescricao(), novaEntidade.getDescricao());

        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    @Test
    void atualizarParceiroNegocio() throws ProdutoExcecao {
        ProdutoDto dto = criarDto();
        Produto entidade = criarProduto();
        Produto novaEntidade = new Produto();
        novaEntidade.setFabricante(new ParceiroNegocio());
        when(produtoConversor.converter(dto)).thenReturn(novaEntidade);
        when(produtoRepository.findById(ID)).thenReturn(Optional.of(entidade));
        produtoService.atualizar(dto, ID);
        assertEquals(entidade.getFabricante(), novaEntidade.getFabricante());

        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    @Test
    void atualizarParceiroDeNegocioComUmProdutoIgualJaCadastradoNoBancoEsperandoExcecao() throws ProdutoExcecao {
        ProdutoDto dto = criarDto();
        Produto entidade = criarProduto();
        Produto novaEntidade = criarProduto();
        when(produtoConversor.converter(dto)).thenReturn(novaEntidade);
        when(produtoRepository.findById(ID)).thenReturn(Optional.of(entidade));
        when(produtoRepository.findByNomeAndDataFabricacaoAndFabricanteAndDescricao(NOME, DATAFABRICACAO, FABRICANTE, DESCRICAO))
                        .thenReturn(Optional.of(entidade));
        assertThrows(ProdutoExcecao.class, () -> {
            produtoService.atualizar(dto, ID);
        });

        verify(produtoRepository, times(0)).save(any(Produto.class));
    }

    @Test
    void atualizarDataDeFabricacao() throws ProdutoExcecao {
        ProdutoDto dto = criarDto();
        Produto entidade = criarProduto();
        Produto novaEntidade = new Produto();
        novaEntidade.setDataFabricacao(LocalDate.of(2015, 01, 01));
        when(produtoConversor.converter(dto)).thenReturn(novaEntidade);
        when(produtoRepository.findById(ID)).thenReturn(Optional.of(entidade));
        produtoService.atualizar(dto, ID);

        assertEquals(entidade.getDataFabricacao(), novaEntidade.getDataFabricacao());
        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    @Test
    void atualizarDataDeFabricacaoSendoMaiorQueDataDeValidadeEsperandoExcecao() throws ProdutoExcecao {
        ProdutoDto dto = criarDto();
        Produto entidade = criarProduto();
        Produto novaEntidade = new Produto();
        novaEntidade.setDataFabricacao(LocalDate.of(2030, 01, 01));
        when(produtoConversor.converter(dto)).thenReturn(novaEntidade);
        when(produtoRepository.findById(ID)).thenReturn(Optional.of(entidade));
        assertThrows(ProdutoExcecao.class, () -> {
            produtoService.atualizar(dto, ID);
        });

        verify(produtoRepository, times(0)).save(any(Produto.class));
    }

    @Test
    void atualizarDataDeValidade() throws ProdutoExcecao {
        ProdutoDto dto = criarDto();
        Produto entidade = criarProduto();
        Produto novaEntidade = new Produto();
        novaEntidade.setDataValidade(LocalDate.of(2022, 01, 01));
        when(produtoConversor.converter(dto)).thenReturn(novaEntidade);
        when(produtoRepository.findById(ID)).thenReturn(Optional.of(entidade));
        produtoService.atualizar(dto, ID);

        assertEquals(entidade.getDataValidade(), novaEntidade.getDataValidade());
        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    @Test
    void atualizarDataDeValidadeSendoMenorQueDataDeValidadeEsperandoExcecao() throws ProdutoExcecao {
        ProdutoDto dto = criarDto();
        Produto entidade = criarProduto();
        Produto novaEntidade = new Produto();
        novaEntidade.setDataValidade(LocalDate.of(2010, 01, 01));
        when(produtoConversor.converter(dto)).thenReturn(novaEntidade);
        when(produtoRepository.findById(ID)).thenReturn(Optional.of(entidade));
        assertThrows(ProdutoExcecao.class, () -> {
            produtoService.atualizar(dto, ID);
        });

        verify(produtoRepository, times(0)).save(any(Produto.class));
    }

    @Test
    void excluirUmProdutoExistente() {

        when(validador.existeProdutoPorId(ID)).thenReturn(true);
        produtoService.deletarPorId(ID);
        verify(produtoRepository, times(1)).deleteById(ID);

    }

    @Test
    void testarExcluirUmProdutoNaoExistente() {

        when(validador.existeProdutoPorId(ID)).thenReturn(false);
        produtoService.deletarPorId(ID);
        verify(produtoRepository, times(0)).deleteById(ID);

    }

}
