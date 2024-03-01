package com.agrotis.trainees.crud.service;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.ValorDiferenteException;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;

import enums.TipoNota;

@Component
public class ItemService {

    private final NotaFiscalService notaFiscalService;
    private final ProdutoService produtoService;
    private final NotaFiscalItemRepository repository;
    private final ValorNotaFiscalService valorNotaFiscalService;

    public ItemService(NotaFiscalService notaFiscalService, ProdutoService produtoService, NotaFiscalItemRepository repository,
                    ValorNotaFiscalService valorNotaFiscalService) {
        super();
        this.notaFiscalService = notaFiscalService;
        this.produtoService = produtoService;
        this.repository = repository;
        this.valorNotaFiscalService = valorNotaFiscalService;
    }

    public NotaFiscalItem validarNotaEItem(NotaFiscalItem entidade) throws ValorDiferenteException {
        NotaFiscal nota = notaFiscalService.buscarPorId(entidade.getIdNota().getId());
        Produto produto = produtoService.buscarPorId(entidade.getProduto().getId());
        NotaFiscalItem buscarPorProdutoAndId = repository.findByProdutoAndIdNota(produto, nota);

        if (buscarPorProdutoAndId != null) {
            if (buscarPorProdutoAndId.getPrecoUnitario().equals(entidade.getPrecoUnitario())) {
                NotaFiscalItem itemAtualizado = alterarQuantidadeItem(entidade);
                return itemAtualizado;
            }
            throw new ValorDiferenteException("Item com preço diferente do original");
        } else {
            obterValorTotal(entidade);
            valorNotaFiscalService.atualizarValorTotalNota(entidade);
            return entidade;
        }

    }

    public NotaFiscalItem alterarQuantidadeItem(NotaFiscalItem entidade) {
        NotaFiscal nota = notaFiscalService.buscarPorId(entidade.getIdNota().getId());
        Produto produto = produtoService.buscarPorId(entidade.getProduto().getId());
        NotaFiscalItem buscarPorProdutoAndId = repository.findByProdutoAndIdNota(produto, nota);

        buscarPorProdutoAndId.setQuantidade(buscarPorProdutoAndId.getQuantidade() + entidade.getQuantidade());
        obterValorTotal(buscarPorProdutoAndId);
        valorNotaFiscalService.atualizarValorTotalNota(entidade);
        return buscarPorProdutoAndId;

    }

    public NotaFiscalItem jutsuDeSubstituicao(NotaFiscalItem entidade, NotaFiscalItem item) {

        if (entidade.getIdNota() == null) {
            entidade.setIdNota(item.getIdNota());
        }

        if (entidade.getPrecoUnitario() == null || entidade.getPrecoUnitario().equals(new BigDecimal(0))) {
            entidade.setPrecoUnitario(item.getPrecoUnitario());
        }

        if (entidade.getProduto() == null) {
            entidade.setProduto(item.getProduto());
        }

        if (entidade.getQuantidade() == null || entidade.getQuantidade() == 0) {
            entidade.setQuantidade(item.getQuantidade());
        }

        return entidade;
    }

    public NotaFiscalItem obterValorTotal(NotaFiscalItem entidade) {
        entidade.setValorTotal(entidade.getPrecoUnitario().multiply(new BigDecimal(entidade.getQuantidade())));
        return entidade;
    }

    public BigDecimal obterCustoTotal(NotaFiscalItem entidade) {
        Produto produto = produtoService.buscarPorId(entidade.getProduto().getId());
        List<NotaFiscalItem> itens = repository.findByProduto(produto);
        BigDecimal custoTotal = entidade.getPrecoUnitario().multiply(new BigDecimal(entidade.getQuantidade()));

        for (NotaFiscalItem item : itens) {
            if (item.getIdNota().getTipo() == TipoNota.ENTRADA) {
                custoTotal = custoTotal.add(item.getValorTotal());
            }
        }
        return custoTotal;
    }
}
