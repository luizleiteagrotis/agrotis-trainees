package com.agrotis.trainees.crud.service;

import com.agrotis.trainees.crud.dto.NotaFiscalItemDto;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;

public class NotaFiscalItemConversaoService {

    private NotaFiscalConversaoService conversaoNota;
    private ProdutoConversaoService conversaoProduto;

    public NotaFiscalItemDto converterParaDto(NotaFiscalItem entidade) {
        NotaFiscalItemDto dto = new NotaFiscalItemDto();
        dto.setId(entidade.getId());
        dto.setNotaFiscal(conversaoNota.converterParaDto(entidade.getNotaFiscal()));
        dto.setProduto(conversaoProduto.converterParaDto(entidade.getProduto()));
        dto.setQuantidade(entidade.getQuantidade());
        dto.setPrecoUnitario(entidade.getPrecoUnitario());
        dto.setValorTotal(entidade.getValorTotal());

        return dto;
    }

    public NotaFiscalItem converterParaEntidade(NotaFiscalItemDto dto) {
        NotaFiscalItem entidade = new NotaFiscalItem();
        entidade.setId(dto.getId());
        entidade.setNotaFiscal(conversaoNota.converterParaEntidade(dto.getNotaFiscal()));
        entidade.setProduto(conversaoProduto.converterParaEntidade(dto.getProduto()));
        entidade.setQuantidade(dto.getQuantidade());
        entidade.setPrecoUnitario(dto.getPrecoUnitario());
        entidade.setValorTotal(dto.getValorTotal());

        return entidade;
    }

}
