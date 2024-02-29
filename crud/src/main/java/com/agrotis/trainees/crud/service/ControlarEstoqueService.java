package com.agrotis.trainees.crud.service;

import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.exception.CrudException;

public class ControlarEstoqueService {

    private ProdutoService service;
    private ProdutoConversaoService conversao;

    public void controlarEstoque(NotaFiscalItem item) {
        ProdutoDto produtoDto = conversao.converterParaDto(item.getProduto());
        produtoDto.setId(item.getProduto().getId());

        int quantidade = item.getQuantidade();

        if (item.getNotaFiscal().getNotaFiscalTipo() == NotaFiscalTipo.SAIDA) {
            if (produtoDto.getEstoque() < quantidade || (produtoDto.getEstoque() - quantidade) < 0) {
                throw new CrudException("Estoque insuficiente para o produto: " + produtoDto.getNome());
            }
            produtoDto.setEstoque(produtoDto.getEstoque() - quantidade);
        } else {
            produtoDto.setEstoque(produtoDto.getEstoque() + quantidade);
        }

        service.salvar(produtoDto);
    }

}
