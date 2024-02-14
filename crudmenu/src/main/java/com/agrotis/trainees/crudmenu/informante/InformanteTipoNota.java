package com.agrotis.trainees.crudmenu.informante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crudmenu.dto.cabecalho.TipoNota;

@Component
public class InformanteTipoNota {

	private final InformantePosicaoOpcao INFORMANTE_POSICAO;
	
	@Autowired
	public InformanteTipoNota(InformantePosicaoOpcao informantePosicao) {
		INFORMANTE_POSICAO = informantePosicao;
	}
	
	public TipoNota informar() {
		TipoNota tipo = null;
		while (naoInformado(tipo)) {
			System.out.println("Informe o tipo da nota");
			System.out.println("1 - ENTRADA");
			System.out.println("2 - SAIDA");
			int opcao = INFORMANTE_POSICAO.informar(1, 2);
			System.out.println();
			if (opcao == 1) tipo = TipoNota.ENTRADA;
			else tipo = TipoNota.SAIDA;
		}
		return tipo;
	}
	
	private boolean naoInformado(TipoNota tipo) {
		return tipo == null;
	}
}
