package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.NotaFiscalItemRepository;

@Service
public class NotaFiscalItemService {

    private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalItemService.class);

    private final NotaFiscalItemRepository repository;
    private final ProdutoService produtoService;

    public NotaFiscalItemService(NotaFiscalItemRepository repository, ProdutoService produtoService) {
        super();
        this.repository = repository;
        this.produtoService = produtoService;
    }

    public NotaFiscalItem salvar(NotaFiscalItem entidade) {
        adicionarItem(entidade);
        return repository.save(entidade);
    }

    public NotaFiscalItem buscarPorId(Integer id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Item da nota fiscal não encontrado para o id {}.", id);
            return null;
        });
    }

    public NotaFiscalItem buscarPorNotaFiscalId(NotaFiscal notaFiscal) {
        return repository.findByNotaFiscalId(notaFiscal).orElseGet(() -> {
            LOG.error("Item da nota fiscal não encontrado para a NF {}.", notaFiscal);
            return null;
        });
    }

    public NotaFiscalItem buscarPorProduto(Produto produto) {
        return repository.findByProduto(produto).orElseGet(() -> {
            LOG.error("Item da nota fiscal não encontrado para o produto {}.", produto);
            return null;
        });
    }

    public List<NotaFiscalItem> listarTodos() {
        return repository.findAll();
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("Deletado com sucesso");
    }

    public void adicionarItem(NotaFiscalItem item) {
        if (item != null) {
            atualizarValorTotalNotaFiscal(item);
        }
    }

    private void atualizarValorTotalNotaFiscal(NotaFiscalItem item) {
        NotaFiscal notaFiscal = item.getNotaFiscal();
        double valorTotalItem = calcularValorTotalItem(item);
        item.setValorTotal(valorTotalItem);
    }

    private double calcularValorTotalItem(NotaFiscalItem item) {
        double valorTotal = item.getQuantidade() * item.getPrecoUnitario();
        return valorTotal;
    }

    public void controlarEstoque(NotaFiscalItem item) {
        Produto produto = item.getProduto();
        if (item.getNotaFiscal().getNotaFiscalTipo() != item.getNotaFiscal().getNotaFiscalTipo().SAIDA) {
            produto.setEstoque(produto.getEstoque() + item.getQuantidade());
        } else {
            produto.setEstoque(produto.getEstoque() - item.getQuantidade());
        }
        produtoService.salvar(produto);
        atualizarValorTotalNotaFiscal(item);
    }

}