package com.agrotis.trainees.crud.wrapper;

import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.dto.NotaFiscalItemDto;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;

@Component
public class NotaFiscalItemWrapper implements ConversorNotaFiscalItem {

    public NotaFiscalItemDto converterParaDto(NotaFiscalItem entidade) {
        NotaFiscalItemDto dto = new NotaFiscalItemDto();
        dto.setId(entidade.getId());
        dto.setProduto(entidade.getProduto());
        dto.setQuantidade(entidade.getQuantidade());
        dto.setPrecoUnitario(entidade.getPrecoUnitario());
        dto.setValorTotal(entidade.getValorTotal());
        dto.setIdNota(entidade.getIdNota());

        return dto;
    }

    public NotaFiscalItem converterParaEntidade(NotaFiscalItemDto dto) {
        NotaFiscalItem entidade = new NotaFiscalItem();
        entidade.setId(dto.getId());
        entidade.setProduto(dto.getProduto());
        entidade.setQuantidade(dto.getQuantidade());
        entidade.setPrecoUnitario(dto.getPrecoUnitario());
        entidade.setValorTotal(dto.getValorTotal());
        entidade.setIdNota(dto.getIdNota());

        return entidade;
    }

}
