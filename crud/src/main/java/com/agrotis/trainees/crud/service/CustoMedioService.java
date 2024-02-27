package com.agrotis.trainees.crud.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.exception.CrudException;

public interface CustoMedioService {

    public static void calcularCustoMedioProdutoExistente(boolean subtrair, ItemNotaFiscal entidade) {
        BigDecimal custoTotal = entidade.getProduto().getCustoMedio()
                        .multiply(BigDecimal.valueOf(entidade.getProduto().getQuantidadeEstoque()))
                        .setScale(2, RoundingMode.HALF_UP);

        BigDecimal novoCusto;
        int quantidadeTotalSalva;

        if (subtrair) {
            novoCusto = custoTotal.subtract(entidade.getValorTotal());
            quantidadeTotalSalva = entidade.getProduto().getQuantidadeEstoque() - entidade.getQuantidade();
        } else {
            novoCusto = custoTotal.add(entidade.getValorTotal());
            quantidadeTotalSalva = entidade.getProduto().getQuantidadeEstoque() + entidade.getQuantidade();
        }
        if (quantidadeTotalSalva == 0) {
            entidade.getProduto().setCustoMedio(BigDecimal.ZERO);
        } else {
            BigDecimal custoMedio = calcularCustoMedio(novoCusto, BigDecimal.valueOf(quantidadeTotalSalva));
            entidade.getProduto().setCustoMedio(custoMedio);
        }

    }

    public static BigDecimal calcularCustoMedio(BigDecimal custoTotal, BigDecimal quantidadeTotal) {
        if (quantidadeTotal.compareTo(BigDecimal.ZERO) <= 0) {
            throw new CrudException("Quantidade Total do item deve ser maior que zero.");
        }
        return custoTotal.divide(quantidadeTotal, 2, RoundingMode.HALF_UP);
    }

}
