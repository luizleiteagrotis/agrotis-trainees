package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ItemNotaFiscalRepository;

@Service
public class ItemNotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(ItemNotaFiscalService.class);

    private final ItemNotaFiscalRepository repository;
    private final ProdutoService produtoService;

    private NotaFiscal notaFiscal;

    public ItemNotaFiscalService(ItemNotaFiscalRepository repository, ProdutoService produtoService) {
        super();
        this.repository = repository;
        this.produtoService = produtoService;
    }

    public void adicionarItem(ItemNotaFiscal item) {
        if (item != null) {
            atualizarValorTotalNotaFiscal(item);
        }
    }

    private void atualizarValorTotalNotaFiscal(ItemNotaFiscal item) {
        setNotaFiscal(item.getNotaFiscal());
        float valorTotalItem = (float) calcularValorTotalItem(item);
        item.setValorTotal(valorTotalItem);
    }

    private double calcularValorTotalItem(ItemNotaFiscal item) {
        double valorTotal = item.getQuantidade() * item.getPreco_unitario();
        return valorTotal;
    }

    public ItemNotaFiscal buscarPorProduto(Produto produto) {
        return repository.findByProduto(produto).orElseGet(() -> {
            LOG.error("Item da nota fiscal não encontrado para o produto {}.", produto);
            return null;
        });
    }

    public ItemNotaFiscal salvar(ItemNotaFiscal entidade) {
        adicionarItem(entidade);
        return repository.save(entidade);
    }

    public ProdutoService getProdutoService() {
        return produtoService;
    }

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

}