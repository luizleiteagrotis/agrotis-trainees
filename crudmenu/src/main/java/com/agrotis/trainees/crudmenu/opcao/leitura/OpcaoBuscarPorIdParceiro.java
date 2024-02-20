package com.agrotis.trainees.crudmenu.opcao.leitura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crudmenu.api.parceiro.ParceiroApi;
import com.agrotis.trainees.crudmenu.dto.parceiro.ParceiroRetornoDto;
import com.agrotis.trainees.crudmenu.informante.InformanteEntidadeId;
import com.agrotis.trainees.crudmenu.printer.ParceiroPrinter;
import com.agrotis.trainees.crudmenu.printer.Printer;

@Component
public class OpcaoBuscarPorIdParceiro extends OpcaoBuscarPorIdTemplate<ParceiroRetornoDto> {

	@Autowired
	public OpcaoBuscarPorIdParceiro(ParceiroApi parceiroApi, InformanteEntidadeId informanteEntidadeId, 
			ParceiroPrinter parceiroPrinter) {
		super(parceiroApi, descricao("Buscar parceiro por id"), informanteEntidadeId, parceiroPrinter);
	}
}
