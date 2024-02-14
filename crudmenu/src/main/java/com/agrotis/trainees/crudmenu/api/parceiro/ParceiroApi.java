package com.agrotis.trainees.crudmenu.api.parceiro;

import com.agrotis.trainees.crudmenu.dto.parceiro.ParceiroCadastroDto;
import com.agrotis.trainees.crudmenu.dto.parceiro.ParceiroRetornoDto;

public interface ParceiroApi {
	
	ParceiroRetornoDto cadastrar(ParceiroCadastroDto cadastroDto);
}
