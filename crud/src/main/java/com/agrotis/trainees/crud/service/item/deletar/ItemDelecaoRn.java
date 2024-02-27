package com.agrotis.trainees.crud.service.item.deletar;

import org.springframework.core.Ordered;

import com.agrotis.trainees.crud.entity.ItemNota;

public interface ItemDelecaoRn extends Ordered{

	ItemNota operarSobre(ItemNota item);
}
