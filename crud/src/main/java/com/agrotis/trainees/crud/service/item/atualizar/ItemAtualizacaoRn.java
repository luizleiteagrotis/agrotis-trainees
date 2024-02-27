package com.agrotis.trainees.crud.service.item.atualizar;

import org.springframework.core.Ordered;

import com.agrotis.trainees.crud.entity.ItemNota;

public interface ItemAtualizacaoRn extends Ordered{
	
	ItemNota operarSobre(ItemNota novoItem, ItemNota antigoItem);
}
