package com.agrotis.trainees.crud.menu.informante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.menu.opcao.criacional.OpcaoCriarParceiro;

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
	
	public ParceiroNegocio informar() {
		ParceiroNegocio parceiro = null;
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
	
	private boolean naoInformado(ParceiroNegocio parceiro) {
		return parceiro == null;
	}
}
