package com.agrotis.trainees.crud.wrapper;

import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.Produto;

@Component
public interface ConversorProduto {

    Produto converterParaEntidade(ProdutoDto dto);

    ProdutoDto converterParaDto(Produto entidade);
}
