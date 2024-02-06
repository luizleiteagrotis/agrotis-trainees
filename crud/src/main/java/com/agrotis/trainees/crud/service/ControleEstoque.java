package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.Produto;

@Service
public class ControleEstoque {

    private static final Logger LOG = LoggerFactory.getLogger(ControleEstoque.class);
    private final ProdutoService produtoService;

    ControleEstoque(ProdutoService produtoService) {
        this.produtoService = produtoService;

    }

    public int controlarQuantidadeEstoque(ItemNotaFiscal itemNotaFiscal) {
        String tipoNotaFiscal = itemNotaFiscal.getNotaFiscal().getTipo();
        Produto produto = produtoService.buscarPorId(itemNotaFiscal.getProduto().getId());
        double quantidadeEstoque = produto.getEstoque();

        if (tipoNotaFiscal.equals("entrada")) {
            quantidadeEstoque += itemNotaFiscal.getQuantidade();
            produto.setEstoque(quantidadeEstoque);
            produtoService.salvar(produto);
            return 1;
        }

        if (verificarQuantidade(quantidadeEstoque, itemNotaFiscal.getQuantidade()) && tipoNotaFiscal.equals("saida")) {
            quantidadeEstoque -= itemNotaFiscal.getQuantidade();
            produto.setEstoque(quantidadeEstoque);
            produtoService.salvar(produto);
            return 1;
        } else {
            LOG.error("A quantidade de saida é maior do que tem em estoque");
            return -1;
        }
    }

    private boolean verificarQuantidade(double quantidadeEstoque, double quantidadeNota) {
        return quantidadeEstoque > quantidadeNota;
    }

}
