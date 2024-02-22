package com.agrotis.trainees.crud.utils;

import java.math.BigDecimal;

import com.agrotis.trainees.crud.dtos.ProdutoDto;
import com.agrotis.trainees.crud.service.exceptions.QuantidadeEmEstoqueException;

public class ValidacaoUtils {
    
    public static <T> boolean isEmptyOrNull(T obj) {
        return obj == null || (obj instanceof String && ((String) obj).isEmpty());
    }
    
    public static void validarQuantidadeNaoNegativa(BigDecimal quantidade) {
        if (quantidade == null || quantidade.compareTo(BigDecimal.ZERO) < 0) {
            throw new QuantidadeEmEstoqueException("A quantidade deve ser um valor não negativo");
        }
    }


    public static void validarQuantidadeEstoqueSuficiente(BigDecimal quantidadeAtual, BigDecimal quantidadeRemover) {
        if (quantidadeAtual == null || quantidadeRemover == null || quantidadeAtual.compareTo(quantidadeRemover) < 0) {
            throw new QuantidadeEmEstoqueException("Não há quantidade suficiente em estoque");
        }
    }
    
    public static boolean isProdutoFieldEmptyOrNull(ProdutoDto produto) {
        if (produto == null) {
            return true;
        }
        if (produto.getDescricao() == null || produto.getFabricante() == null ||
            produto.getDataFabricacao() == null || produto.getDataValidade() == null) {
            return true;
        }
        return false;
    }

}
