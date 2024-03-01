package com.agrotis.trainees.crud.service.parceiro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.agrotis.trainees.crud.dto.parceiro.ParceiroRetornoDto;

public interface ParceiroNegocioBuscaService {

	ParceiroRetornoDto buscarPor(Long idParceiro);
	
	Page<ParceiroRetornoDto> listarTodos(Pageable pageable);
}
