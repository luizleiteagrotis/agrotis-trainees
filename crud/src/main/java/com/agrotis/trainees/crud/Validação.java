package com.agrotis.trainees.crud;

import com.agrotis.trainees.crud.entity.Produto;

public class Validação {

    public static <T> boolean isEmptyOrNull(T obj) {
        return obj == null || (obj instanceof String && ((String) obj).isEmpty());
        
    }
    public static void validarQuantidadePositiva(Integer quantidade) {
        if (quantidade == null || quantidade < 0); 
    }
    public static void validarQuantidadeEstoque(Integer quantidadeAtual, Integer quantidadeRemover) {
        if (quantidadeAtual == null || quantidadeRemover == null || quantidadeAtual < quantidadeRemover);
    }
    public static boolean isProdutoEmptyOrNull (Produto produto) {
        if (produto == null) {
            return true;
        }
        if (produto.getDescricao() == null || produto.getFabricante() == null ||
            produto.getDataFabricacao() == null || produto.getDataValidade() == null ||
            produto.getQuantidade() == null) {
            return true;
            
        }
        return false;
    }
}
