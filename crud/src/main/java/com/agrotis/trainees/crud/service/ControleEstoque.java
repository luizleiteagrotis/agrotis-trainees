package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.exception.ControleEstoqueException;
import com.agrotis.trainees.crud.repository.ProdutoRepository;

@Service
public class ControleEstoque {

    private static final Logger LOG = LoggerFactory.getLogger(ControleEstoque.class);
    private final ProdutoService produtoService;
    private final ProdutoRepository produtoRepository;

    ControleEstoque(ProdutoService produtoService, ProdutoRepository produtoRepository) {
        this.produtoService = produtoService;
        this.produtoRepository = produtoRepository;

    }

    public int controlarQuantidadeEstoque(ItemNotaFiscal itemNotaFiscal) {
        try {
            if (itemNotaFiscal == null || itemNotaFiscal.getNotaFiscal() == null || itemNotaFiscal.getProduto() == null) {
                throw new ControleEstoqueException("Informe um produto ou nota fiscal válida");
            }
        } catch (ControleEstoqueException exp) {
            return -1;
        }

        String tipoNotaFiscal = itemNotaFiscal.getNotaFiscal().getTipo();
        Produto produto = produtoService.buscarPorId(itemNotaFiscal.getProduto().getId());
        double quantidadeEstoque = produto.getEstoque();

        if (tipoNotaFiscal.equals("entrada")) {
            quantidadeEstoque += itemNotaFiscal.getQuantidade().doubleValue();
            produto.setEstoque(quantidadeEstoque);
            produtoRepository.save(produto);
            return 1;
        }

        if (verificarQuantidade(quantidadeEstoque, itemNotaFiscal.getQuantidade().doubleValue())
                        && tipoNotaFiscal.equals("saida")) {
            quantidadeEstoque -= itemNotaFiscal.getQuantidade().doubleValue();
            produto.setEstoque(quantidadeEstoque);
            produtoRepository.save(produto);
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
