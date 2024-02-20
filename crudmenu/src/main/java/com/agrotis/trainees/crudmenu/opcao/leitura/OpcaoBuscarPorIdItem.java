package com.agrotis.trainees.crudmenu.opcao.leitura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crudmenu.api.item.ItemApi;
import com.agrotis.trainees.crudmenu.dto.item.ItemRetornoDto;
import com.agrotis.trainees.crudmenu.informante.InformanteEntidadeId;
import com.agrotis.trainees.crudmenu.printer.ItemPrinter;

@Component
public class OpcaoBuscarPorIdItem extends OpcaoBuscarPorIdTemplate<ItemRetornoDto> {

	@Autowired
	public OpcaoBuscarPorIdItem(ItemApi itemApi, InformanteEntidadeId informanteEntidadeId, 
			ItemPrinter itemPrinter) {
		super(itemApi, descricao("Buscar item por id"), informanteEntidadeId, itemPrinter);
	}
}
