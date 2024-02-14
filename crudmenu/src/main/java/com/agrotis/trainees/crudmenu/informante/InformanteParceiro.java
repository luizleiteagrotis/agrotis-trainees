package com.agrotis.trainees.crudmenu.informante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crudmenu.dto.parceiro.ParceiroRetornoDto;
import com.agrotis.trainees.crudmenu.opcao.criacional.OpcaoCriarParceiro;

@Component
public class InformanteParceiro {
	
	private final OpcaoCriarParceiro OPCAO_CRIAR_PARCEIRO;
	private final InformantePosicaoOpcao INFORMANTE_POSICAO;
	
	@Autowired
	public InformanteParceiro(OpcaoCriarParceiro opcaoCriarParceiro, 
			InformantePosicaoOpcao informantePosicao) {
		OPCAO_CRIAR_PARCEIRO = opcaoCriarParceiro;
		INFORMANTE_POSICAO = informantePosicao;
	}
	
	public ParceiroRetornoDto informar() {
		ParceiroRetornoDto parceiro = null;
		while (naoInformado(parceiro)) {
			System.out.println("Informe o parceiro");
			System.out.println("1 - Criar");
			System.out.println("2 - Selecionar existente");
			int opcao = INFORMANTE_POSICAO.informar(1, 2);
			System.out.println();
			if (opcao == 1) {
				OPCAO_CRIAR_PARCEIRO.executar();
				parceiro = OPCAO_CRIAR_PARCEIRO.getUltimoParceiroCriado();
			}
		}
		return parceiro;
	}
	
	private boolean naoInformado(ParceiroRetornoDto parceiro) {
		return parceiro == null;
	}
}
