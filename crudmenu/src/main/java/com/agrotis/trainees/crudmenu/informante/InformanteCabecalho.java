package com.agrotis.trainees.crudmenu.informante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crudmenu.dto.cabecalho.CabecalhoCadastroDto;
import com.agrotis.trainees.crudmenu.dto.cabecalho.CabecalhoRetornoDto;
import com.agrotis.trainees.crudmenu.opcao.criacional.OpcaoCriarCabecalho;

@Component
public class InformanteCabecalho extends InformanteEntidadeTemplate<CabecalhoCadastroDto, CabecalhoRetornoDto>{
	
	@Autowired
	public InformanteCabecalho(InformantePosicaoOpcao informantePosicao, 
			OpcaoCriarCabecalho opcaoCriarCabecalho) {
		super(informantePosicao, opcaoCriarCabecalho, descricao("Informe o cabecalho"));
	}
}
