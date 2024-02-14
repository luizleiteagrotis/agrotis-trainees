package com.agrotis.trainees.crudmenu.api.cabecalho;

import com.agrotis.trainees.crudmenu.dto.cabecalho.CabecalhoCadastroDto;
import com.agrotis.trainees.crudmenu.dto.cabecalho.CabecalhoRetornoDto;

public interface CabecalhoApi {

	CabecalhoRetornoDto cadastrar(CabecalhoCadastroDto cadastroDto);
	
	CabecalhoRetornoDto buscarPor(Long id);
}
