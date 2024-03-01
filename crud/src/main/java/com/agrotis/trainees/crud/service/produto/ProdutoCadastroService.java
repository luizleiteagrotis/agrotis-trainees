package com.agrotis.trainees.crud.service.produto;

import com.agrotis.trainees.crud.dto.produto.ProdutoCadastroDto;
import com.agrotis.trainees.crud.dto.produto.ProdutoRetornoDto;

public interface ProdutoCadastroService {

	ProdutoRetornoDto cadastrar(ProdutoCadastroDto cadastroDto);
}
