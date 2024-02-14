package com.agrotis.trainees.crudmenu.informante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crudmenu.dto.cabecalho.CabecalhoRetornoDto;
import com.agrotis.trainees.crudmenu.opcao.criacional.OpcaoCriarCabecalho;
import com.agrotis.trainees.crudmenu.opcao.leitura.OpcaoBuscarCabecalhoPorId;

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
	
	public CabecalhoRetornoDto informar() {
		CabecalhoRetornoDto cabecalho = null;
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
	
	private boolean naoInformado(CabecalhoRetornoDto cabecalho) {
		return cabecalho == null;
	}
}
