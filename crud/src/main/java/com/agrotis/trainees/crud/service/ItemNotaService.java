package com.agrotis.trainees.crud.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.dtos.ItemNotaDto;
import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.enums.TipoNota;
import com.agrotis.trainees.crud.repository.CabecalhoNotaRepository;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;
import com.agrotis.trainees.crud.repository.ProdutoRepository;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;
import com.agrotis.trainees.crud.utils.DtoUtils;
import com.agrotis.trainees.crud.utils.ValidacaoUtils;

@Service
public class ItemNotaService {

    private static final Logger LOG = LoggerFactory.getLogger(ItemNotaService.class);

    private final NotaFiscalItemRepository repository;
    private final ProdutoRepository produtoRepository;
    private final ProdutoService produtoService;
    private final CabecalhoNotaRepository cabecalhoNotaRepository;

    public ItemNotaService(NotaFiscalItemRepository repository, ProdutoService produtoService, ProdutoRepository produtoRepository,
                    CabecalhoNotaRepository cabecalhoNotaRepository) {
        this.repository = repository;
        this.produtoService = produtoService;
        this.produtoRepository = produtoRepository;
        this.cabecalhoNotaRepository = cabecalhoNotaRepository;
    }

    @Transactional
    public ItemNotaDto salvar(ItemNotaDto dto) {
        ItemNota entidade = DtoUtils.converteParaEntidade(dto);

        CabecalhoNota cabecalhoNota = entidade.getCabecalhoNota();
        CabecalhoNota cabecalhoSalvo = salvarOuBuscarCabecalho(cabecalhoNota);
        entidade.setCabecalhoNota(cabecalhoSalvo);

        Produto produtoDoItemNota = entidade.getProduto();
        Produto salvarOuBuscarProduto = salvarOuBuscarProduto(produtoDoItemNota);
        BigDecimal valorTotal = calcularValorTotal(entidade);
        BigDecimal calculoCustoMedio = CustoMedioService.calcularCustoMedio(valorTotal, entidade.getQuantidade());
        salvarOuBuscarProduto.setCustoMedio(calculoCustoMedio);
        produtoRepository.save(salvarOuBuscarProduto);
        entidade.setProduto(salvarOuBuscarProduto);

        atualizarEstoque(entidade);

        entidade = repository.save(entidade);

        adicionarValorTotalCabecalho(entidade);

        return DtoUtils.converteParaDto(entidade);
    }

    public List<ItemNotaDto> listarTodos() {
        return repository.findAll().stream().map(DtoUtils::converteParaDto).collect(Collectors.toList());
    }

    public ItemNotaDto buscarPorId(Integer id) {
        return repository.findById(id).map(DtoUtils::converteParaDto)
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada com o ID: " + id));
    }

    @Transactional
    public ItemNotaDto atualizar(Integer id, ItemNotaDto notaFiscalItemDto) {
        ItemNota itemNotaExistente = repository.findById(id)
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada com o ID: " + id));

        atualizarItemNota(itemNotaExistente, notaFiscalItemDto);

        ItemNota itemNotaAtualizada = repository.save(itemNotaExistente);
        return DtoUtils.converteParaDto(itemNotaAtualizada);
    }

    private void atualizarItemNota(ItemNota itemNota, ItemNotaDto notaFiscalItemDto) {
        CabecalhoNota cabecalhoNota = notaFiscalItemDto.getCabecalhoNota();
        itemNota.setCabecalhoNota(cabecalhoNota);
        itemNota.setPrecoUnitario(notaFiscalItemDto.getPrecoUnitario());
        itemNota.setProduto(notaFiscalItemDto.getProduto());
        itemNota.setQuantidade(notaFiscalItemDto.getQuantidade());
        itemNota.setValorTotal(notaFiscalItemDto.getValorTotal());
    }

    public void deletarPorId(Integer id) {
        repository.findById(id).map(entidade -> {
            repository.deleteById(id);
            LOG.info("Deletado com sucesso");
            return entidade;
        }).orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada com o ID: " + id));

    }

    public BigDecimal calcularValorTotal(ItemNota notaFiscalItem) {
        BigDecimal quantidade = notaFiscalItem.getQuantidade();
        BigDecimal precoUnitario = notaFiscalItem.getPrecoUnitario();

        if (quantidade != null && precoUnitario != null) {
            BigDecimal valorTotal = quantidade.multiply(precoUnitario);
            notaFiscalItem.setValorTotal(valorTotal);
            return valorTotal;
        }

        return BigDecimal.ZERO;
    }

    private void atualizarEstoque(ItemNota itemNota) {
        Produto produto = itemNota.getProduto();
        BigDecimal quantidade = itemNota.getQuantidade();
        TipoNota notaFiscalTipo = itemNota.getCabecalhoNota().getNotaFiscalTipo();
        produto.setQuantidadeEstoque(quantidade);
        BigDecimal quantidadeProduto = produto.getQuantidadeEstoque();

        ValidacaoUtils.validarQuantidadeNaoNegativa(quantidade);

        if (notaFiscalTipo == TipoNota.SAIDA) {
            ValidacaoUtils.validarQuantidadeEstoqueSuficiente(quantidadeProduto, quantidade);
            produto.setQuantidadeEstoque(quantidadeProduto.subtract(quantidade));
        } else if (notaFiscalTipo == TipoNota.ENTRADA) {
            produto.setQuantidadeEstoque(quantidadeProduto.add(quantidade));
        }

        produtoService.salvar(DtoUtils.converteParaDto(produto));
    }

    private void adicionarValorTotalCabecalho(ItemNota itemNota) {
        CabecalhoNota cabecalhoNota = itemNota.getCabecalhoNota();
        BigDecimal valorTotalCabecalho = cabecalhoNota.getValorTotal();
        BigDecimal valorTotalItem = itemNota.getValorTotal();
        valorTotalCabecalho = valorTotalCabecalho.add(valorTotalItem);
        cabecalhoNota.setValorTotal(valorTotalCabecalho);
    }

    private Produto salvarOuBuscarProduto(Produto produto) {
        if (produto.getId() != null) {
            return produtoRepository.findById(produto.getId()).orElseThrow(
                            () -> new EntidadeNaoEncontradaException("Produto com o ID " + produto.getId() + " não encontrado"));
        } else {
            return produtoRepository.findById(produto.getId()).orElseThrow(
                            () -> new EntidadeNaoEncontradaException("Produto com o ID " + produto.getId() + " não encontrado"));
        }
    }

    private CabecalhoNota salvarOuBuscarCabecalho(CabecalhoNota cabecalhoNota) {
        if (cabecalhoNota.getId() != null) {
            return cabecalhoNotaRepository.findById(cabecalhoNota.getId()).orElseThrow(() -> new EntidadeNaoEncontradaException(
                            "Cabeçalho com o ID " + cabecalhoNota.getId() + " não encontrado"));
        } else {
            return cabecalhoNotaRepository.save(cabecalhoNota);
        }
    }

}
