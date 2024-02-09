package com.agrotis.trainees.crud.menu.informante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.menu.opcao.criacional.OpcaoCriarCabecalho;
import com.agrotis.trainees.crud.menu.opcao.leitura.OpcaoBuscarCabecalhoPorId;

@Component
public class InformanteCabecalho {
	
	private final InformantePosicaoOpcao INFORMANTE_POSICAO;
	private final OpcaoCriarCabecalho OPCAO_CRIAR_CABECALHO;
	private final OpcaoBuscarCabecalhoPorId OPCAO_BUSCAR_CABECALHO_POR_ID;
	
	@Autowired
	public InformanteCabecalho(InformantePosicaoOpcao informantePosicao, 
			OpcaoCriarCabecalho opcaoCriarCabecalho,
			OpcaoBuscarCabecalhoPorId opcaoBuscarCabecalhoPorId) {
		INFORMANTE_POSICAO = informantePosicao;
		OPCAO_CRIAR_CABECALHO = opcaoCriarCabecalho;
		OPCAO_BUSCAR_CABECALHO_POR_ID = opcaoBuscarCabecalhoPorId;
	}
	
	public CabecalhoNota informar() {
		CabecalhoNota cabecalho = null;
		while (naoInformado(cabecalho)) {
			System.out.println("Informe o cabecalho");
			System.out.println("1 - Criar");
			System.out.println("2 - Informar existente");
			int opcao = INFORMANTE_POSICAO.informar(1, 2);
			System.out.println();
			if (opcao == 1) {
				OPCAO_CRIAR_CABECALHO.executar();
				cabecalho = OPCAO_CRIAR_CABECALHO.getUltimoCabecalhoCriado();
			} else {
				OPCAO_BUSCAR_CABECALHO_POR_ID.executar();
				cabecalho = OPCAO_BUSCAR_CABECALHO_POR_ID.getUltimoCabecalhoBuscado();
			}
		}
		return cabecalho;
	}
	
	private boolean naoInformado(CabecalhoNota cabecalho) {
		return cabecalho == null;
	}
}
