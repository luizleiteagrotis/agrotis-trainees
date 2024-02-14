package com.agrotis.trainees.crudmenu.api.produto;

import com.agrotis.trainees.crudmenu.dto.produto.ProdutoCadastroDto;
import com.agrotis.trainees.crudmenu.dto.produto.ProdutoRetornoDto;

public interface ProdutoApi {
	
	ProdutoRetornoDto cadastrar(ProdutoCadastroDto cadastroDto);
}
