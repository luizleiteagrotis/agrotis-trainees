package com.agrotis.trainees.crudmenu.api.produto;

import org.springframework.stereotype.Component;

import com.agrotis.trainees.crudmenu.api.CrudApiMethodsTemplate;
import com.agrotis.trainees.crudmenu.dto.produto.ProdutoCadastroDto;
import com.agrotis.trainees.crudmenu.dto.produto.ProdutoRetornoDto;

@Component
public class ProdutoApiImpl extends CrudApiMethodsTemplate<ProdutoCadastroDto, ProdutoRetornoDto> implements ProdutoApi {

	@Override
	protected String getUrl() {
		return "http://localhost:8081/crud/api/produtos";
	}

	@Override
	protected Class<ProdutoRetornoDto> getRetornoDtoClass() {
		return ProdutoRetornoDto.class;
	}
}
