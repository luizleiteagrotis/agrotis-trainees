package com.agrotis.trainees.crudmenu.informante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crudmenu.dto.parceiro.ParceiroCadastroDto;
import com.agrotis.trainees.crudmenu.dto.parceiro.ParceiroRetornoDto;
import com.agrotis.trainees.crudmenu.opcao.criacional.OpcaoCriarParceiro;

@Component
public class InformanteParceiro extends InformanteEntidadeTemplate<ParceiroCadastroDto, ParceiroRetornoDto> {
	
	@Autowired
	public InformanteParceiro(OpcaoCriarParceiro opcaoCriarParceiro, 
			InformantePosicaoOpcao informantePosicao) {
		super(informantePosicao, opcaoCriarParceiro, descricao("Informe o parceiro"));
	}
}
