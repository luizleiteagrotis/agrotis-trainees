package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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

    public BigDecimal controlarQuantidadeEstoque(ItemNotaFiscal itemNotaFiscal) throws ControleEstoqueException {

        try {
            String tipoNotaFiscal = itemNotaFiscal.getNotaFiscal().getTipo();
            BigDecimal quantidadeEstoque = itemNotaFiscal.getProduto().getEstoque();

            if (tipoNotaFiscal.equals("entrada")) {
                quantidadeEstoque = somarEstoque(quantidadeEstoque, itemNotaFiscal.getQuantidade());
            }

            if (tipoNotaFiscal.equals("saida")) {
                if (verificarQuantidade(quantidadeEstoque, itemNotaFiscal.getQuantidade())) {
                    quantidadeEstoque = diminuirEstoque(quantidadeEstoque, itemNotaFiscal.getQuantidade());
                } else {
                    throw new ControleEstoqueException(
                                    "Falha ao calcular a quantidade de estoque: A quantidade de saída é maior que o estoque atual.");
                }

            }

            return quantidadeEstoque;
        } catch (ControleEstoqueException exp) {
            LOG.error(exp.getMessage());
            throw exp;
        } catch (NullPointerException npe) {
            LOG.error(npe.getMessage());
            throw npe;
        }
    }

    public ItemNotaFiscal atualizarEstoque(ItemNotaFiscal itemNotaFiscal, ItemNotaFiscal itemNotaAtualizar)
                    throws ControleEstoqueException {

        try {
            BigDecimal estoque = itemNotaAtualizar.getProduto().getEstoque();
            BigDecimal qtdAntiga = itemNotaAtualizar.getQuantidade();
            BigDecimal qtdNova = itemNotaFiscal.getQuantidade();
            if (itemNotaAtualizar.getNotaFiscal().getTipo().equalsIgnoreCase("entrada")) {
                estoque = estoque.subtract(qtdAntiga).add(qtdNova);
            }

            if (itemNotaAtualizar.getNotaFiscal().getTipo().equalsIgnoreCase("saida")) {
                estoque = estoque.add(qtdAntiga).subtract(qtdNova);
                if (estoque.compareTo(BigDecimal.ZERO) < 0) {
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
            throw cee;
        }
    }

    public boolean verificarQuantidade(BigDecimal quantidadeEstoque, BigDecimal quantidadeNota) {
        return quantidadeEstoque.subtract(quantidadeNota).compareTo(BigDecimal.ZERO) >= 0;
    }

    public BigDecimal somarEstoque(BigDecimal quantidadeEstoque, BigDecimal quantidade) {
        return quantidadeEstoque.add(quantidade);
    }

    public BigDecimal diminuirEstoque(BigDecimal quantidadeEstoque, BigDecimal quantidade) {
        return quantidadeEstoque.subtract(quantidade);

    }

}
