package com.agrotis.trainees.crud.service;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.ValorDiferenteException;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;

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

    public NotaFiscalItem tratarNulos(NotaFiscalItem entidade, NotaFiscalItem item) {

        Field[] fields = entidade.getClass().getDeclaredFields();
        Field[] fields2 = item.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            try {
                if (field.get(entidade) == null || field.get(entidade).equals(0) || field.get(entidade).equals(new BigDecimal(0))) {

                    field.set(entidade, field.get(item));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return entidade;
    }

    public NotaFiscalItem obterValorTotal(NotaFiscalItem entidade) {
        entidade.setValorTotal(entidade.getPrecoUnitario().multiply(new BigDecimal(entidade.getQuantidade())));
        return entidade;
    }
}
