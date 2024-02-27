package com.agrotis.trainees.crud.service.item.cadastrar;

import org.springframework.core.Ordered;

import com.agrotis.trainees.crud.entity.ItemNota;

public interface ItemCadastroRn extends Ordered{
		
	ItemNota operarSobre(ItemNota item);
}
