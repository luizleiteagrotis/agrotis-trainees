package com.agrotis.trainees.crud.wrapper;

import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;

@Component
public class NotaFiscalWrapper implements ConversorNotaFiscal {

    public NotaFiscalDto converterParaDto(NotaFiscal entidade) {
        NotaFiscalDto dto = new NotaFiscalDto();
        dto.setId(entidade.getId());
        dto.setTipo(entidade.getTipo());
        dto.setParceiro(entidade.getParceiro());
        dto.setNumero(entidade.getNumero());
        dto.setDataEmissao(entidade.getDataEmissao());
        dto.setValorTotal(entidade.getValorTotal());

        return dto;
    }

    public NotaFiscal converterParaEntidade(NotaFiscalDto dto) {
        NotaFiscal entidade = new NotaFiscal();
        entidade.setId(dto.getId());
        entidade.setTipo(dto.getTipo());
        entidade.setParceiro(dto.getParceiro());
        entidade.setNumero(dto.getNumero());
        entidade.setDataEmissao(dto.getDataEmissao());
        entidade.setValorTotal(dto.getValorTotal());

        return entidade;
    }
}
