package com.agrotis.trainees.crud.utils;

import org.springframework.context.annotation.Configuration;

import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.Produto;

@Configuration
public class ProdutoDTOMapper {

    public ProdutoDto converterParaDto(Produto entidade) {
        return new ProdutoDto(entidade);
    }

    public Produto converterParaEntidade(ProdutoDto dto) {
        return new Produto(dto);

    }

}
