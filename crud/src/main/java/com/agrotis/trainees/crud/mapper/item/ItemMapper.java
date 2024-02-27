package com.agrotis.trainees.crud.mapper.item;

import com.agrotis.trainees.crud.dto.item.ItemAtualizacaoDto;
import com.agrotis.trainees.crud.dto.item.ItemCadastroDto;
import com.agrotis.trainees.crud.dto.item.ItemRetornoDto;
import com.agrotis.trainees.crud.entity.ItemNota;

public interface ItemMapper {

	ItemNota converterParaEntidade(ItemCadastroDto cadastroDto);
	
	ItemRetornoDto converterParaDto(ItemNota item);
	
	/**
	 * Converte para entidade com os atributos especificados no dto. 
	 * Outros atributos que precisam ser calculados nao sao atualizados.
	 * 
	 * @param atualizacaoDto Id e atributos para serem inseridos na entidade 
	 * @return Entidade atualizada
	 */
	ItemNota converterParaEntidade(ItemAtualizacaoDto atualizacaoDto);
}
