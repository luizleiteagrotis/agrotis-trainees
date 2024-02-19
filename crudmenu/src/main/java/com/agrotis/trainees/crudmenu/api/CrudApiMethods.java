package com.agrotis.trainees.crudmenu.api;

import com.agrotis.trainees.crudmenu.exception.ApiMethodException;

public interface CrudApiMethods <CadastroDto, RetornoDto>{

	public RetornoDto cadastrar(CadastroDto cadastroDto) throws ApiMethodException;
}
