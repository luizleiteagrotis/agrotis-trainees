package com.agrotis.trainees.crud.mapper.produto;

import com.agrotis.trainees.crud.dto.produto.ProdutoCadastroDto;
import com.agrotis.trainees.crud.dto.produto.ProdutoRetornoDto;
import com.agrotis.trainees.crud.entity.Produto;

public interface ProdutoMapper {
	
	Produto converterParaEntidade(ProdutoCadastroDto cadastroDto);
		
	ProdutoRetornoDto converterParaDto(Produto produto);
}
