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

    private double calcularValorTotal(ItemNotaFiscal item) {
        double valorTotal = item.getQuantidade() * item.getPreco_unitario();
        return valorTotal;
    }

    public void adicionarItem(ItemNotaFiscal item) {
        if (item != null) {
            atualizaTotal(item);
        }
    }

    private void atualizaTotal(ItemNotaFiscal item) {
        setNotaFiscal(item.getNotaFiscal());
        float valorTotal = (float) calcularValorTotal(item);
        item.setValorTotal(valorTotal);
    }

    public ItemNotaFiscal buscarPorProduto(Produto produto) {
        return repository.findByProduto(produto).orElseGet(() -> {
            LOG.error("Item não encontrado{}.", produto);
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