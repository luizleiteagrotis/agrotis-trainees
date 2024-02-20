package com.agrotis.trainees.crudmenu.opcao.leitura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crudmenu.api.cabecalho.CabecalhoApi;
import com.agrotis.trainees.crudmenu.dto.cabecalho.CabecalhoRetornoDto;
import com.agrotis.trainees.crudmenu.informante.InformanteEntidadeId;
import com.agrotis.trainees.crudmenu.printer.CabecalhoPrinter;

@Component
public class OpcaoBuscarPorIdCabecalho extends OpcaoBuscarPorIdTemplate<CabecalhoRetornoDto>{

	@Autowired
	public OpcaoBuscarPorIdCabecalho(CabecalhoApi cabecalhoApi, InformanteEntidadeId informanteEntidadeId, 
			CabecalhoPrinter cabecalhoPrinter) {
		super(cabecalhoApi, descricao("Buscar cabecalho por id"), informanteEntidadeId, cabecalhoPrinter);
	}
}
