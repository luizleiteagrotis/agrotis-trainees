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

    public boolean controlarQuantidadeEstoque(ItemNotaFiscal itemNotaFiscal) {

        try {
            String tipoNotaFiscal = itemNotaFiscal.getNotaFiscal().getTipo();
            Produto produto = produtoService.verificarPorId(itemNotaFiscal.getProduto().getId());
            double quantidadeEstoque = produto.getEstoque();

            if (tipoNotaFiscal.equals("entrada")) {
                quantidadeEstoque = somarEstoque(quantidadeEstoque, itemNotaFiscal.getQuantidade().doubleValue());
            }

            if (tipoNotaFiscal.equals("saida")) {
                if (verificarQuantidade(quantidadeEstoque, itemNotaFiscal.getQuantidade().doubleValue())) {
                    quantidadeEstoque = diminuirEstoque(quantidadeEstoque, itemNotaFiscal.getQuantidade().doubleValue());
                } else {
                    throw new ControleEstoqueException(
                                    "Falha ao calcular a quantidade de estoque: A quantidade de saída é maior que o estoque atual.");
                }

            }
            produto.setEstoque(quantidadeEstoque);
            produtoRepository.save(produto);
            return true;
        } catch (ControleEstoqueException exp) {
            LOG.error(exp.getMessage());
            return false;
        } catch (NullPointerException npe) {
            LOG.error(npe.getMessage());
            return false;
        }
    }

    public ItemNotaFiscal atualizarEstoque(ItemNotaFiscal itemNotaFiscal, ItemNotaFiscal itemNotaAtualizar) {

        try {
            double estoque = itemNotaAtualizar.getProduto().getEstoque();
            double qtdAntiga = itemNotaAtualizar.getQuantidade().doubleValue();
            double qtdNova = itemNotaFiscal.getQuantidade().doubleValue();
            if (itemNotaAtualizar.getNotaFiscal().getTipo().equalsIgnoreCase("entrada")) {
                estoque = estoque - qtdAntiga + qtdNova;
            }

            if (itemNotaAtualizar.getNotaFiscal().getTipo().equalsIgnoreCase("saida")) {
                estoque = estoque + qtdAntiga - qtdNova;
                if (estoque < 0) {
                    throw new ControleEstoqueException(
                                    "Falha ao calcular a quantidade de estoque: A quantidade de saída é maior que o estoque atual.");

                }
            }
            Produto produtoEstoque = produtoService.verificarPorId(itemNotaFiscal.getProduto().getId());
            produtoEstoque.setEstoque(estoque);
            produtoRepository.save(produtoEstoque);
            itemNotaAtualizar.setProduto(produtoEstoque);
            return itemNotaAtualizar;
        } catch (ControleEstoqueException cee) {
            LOG.error(cee.getMessage());
            return null;
        }
    }

    protected boolean verificarQuantidade(double quantidadeEstoque, double quantidadeNota) {
        return quantidadeEstoque - quantidadeNota >= 0;
    }

    protected double somarEstoque(double quantidadeEstoque, double quantidade) {
        quantidadeEstoque += quantidade;
        return quantidadeEstoque;
    }

    protected double diminuirEstoque(double quantidadeEstoque, double quantidade) {
        quantidadeEstoque -= quantidade;
        return quantidadeEstoque;
    }

}
