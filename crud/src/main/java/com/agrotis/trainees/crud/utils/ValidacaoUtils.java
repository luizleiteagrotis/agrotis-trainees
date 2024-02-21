package com.agrotis.trainees.crud.utils;

import com.agrotis.trainees.crud.dtos.ProdutoDto;
import com.agrotis.trainees.crud.service.exceptions.QuantidadeEmEstoqueException;

public class ValidacaoUtils {
    
    public static <T> boolean isEmptyOrNull(T obj) {
        return obj == null || (obj instanceof String && ((String) obj).isEmpty());
    }
    
    public static void validarQuantidadeNaoNegativa(Integer quantidade) {
        if (quantidade == null || quantidade < 0) {
            throw new QuantidadeEmEstoqueException("A quantidade deve ser um valor não negativo");
        }
    }

    public static void validarQuantidadeEstoqueSuficiente(Integer quantidadeAtual, Integer quantidadeRemover) {
        if (quantidadeAtual == null || quantidadeRemover == null || quantidadeAtual < quantidadeRemover) {
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
