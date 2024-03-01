package com.agrotis.trainees.crud.mapper.parceiro;

import com.agrotis.trainees.crud.dto.parceiro.ParceiroAtualizacaoDto;
import com.agrotis.trainees.crud.dto.parceiro.ParceiroCadastroDto;
import com.agrotis.trainees.crud.dto.parceiro.ParceiroRetornoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;

public interface ParceiroMapper {
	
	ParceiroNegocio converterParaEntidade(ParceiroCadastroDto cadastroDto);
		
	ParceiroRetornoDto converterParaDto(ParceiroNegocio parceiro);
	
	/**
	 * Converte para entidade com os atributos especificados no dto. 
	 * Outros atributos que precisam ser calculados nao sao atualizados.
	 * 
	 * @param atualizacaoDto Id e atributos para serem inseridos na entidade 
	 * @return Entidade atualizada
	 */
	ParceiroNegocio converterParaEntidade(ParceiroAtualizacaoDto atualizacaoDto);
}
