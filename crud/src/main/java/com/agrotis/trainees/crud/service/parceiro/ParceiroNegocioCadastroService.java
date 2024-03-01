package com.agrotis.trainees.crud.service.parceiro;

import com.agrotis.trainees.crud.dto.parceiro.ParceiroCadastroDto;
import com.agrotis.trainees.crud.dto.parceiro.ParceiroRetornoDto;

public interface ParceiroNegocioCadastroService {

	ParceiroRetornoDto cadastrar(ParceiroCadastroDto cadastroDto);
}
