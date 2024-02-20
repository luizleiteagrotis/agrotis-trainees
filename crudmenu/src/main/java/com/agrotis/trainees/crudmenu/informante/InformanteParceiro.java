package com.agrotis.trainees.crudmenu.informante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crudmenu.dto.parceiro.ParceiroCadastroDto;
import com.agrotis.trainees.crudmenu.dto.parceiro.ParceiroRetornoDto;
import com.agrotis.trainees.crudmenu.opcao.criacional.OpcaoCriarParceiro;
import com.agrotis.trainees.crudmenu.opcao.leitura.OpcaoBuscarPorIdParceiro;

@Component
public class InformanteParceiro extends InformanteEntidadeTemplate<ParceiroCadastroDto, ParceiroRetornoDto> {
	
	@Autowired
	public InformanteParceiro(OpcaoCriarParceiro opcaoCriarParceiro, 
			InformantePosicaoOpcao informantePosicao, OpcaoBuscarPorIdParceiro opcaoBuscarPorIdParceiro) {
		super(informantePosicao, opcaoCriarParceiro, opcaoBuscarPorIdParceiro, descricao("Informe o parceiro"));
	}
}
