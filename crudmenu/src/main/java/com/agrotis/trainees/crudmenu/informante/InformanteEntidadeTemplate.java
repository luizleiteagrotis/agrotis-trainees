package com.agrotis.trainees.crudmenu.informante;

import com.agrotis.trainees.crudmenu.opcao.criacional.OpcaoCriacionalTemplate;

public class InformanteEntidadeTemplate<CadastroDto, RetornoDto> {

	private final InformantePosicaoOpcao INFORMANTE_POSICAO;
	private final OpcaoCriacionalTemplate<CadastroDto, RetornoDto> OPCAO_CRIACIONAL;
	private final String DESCRICAO;
	
	public InformanteEntidadeTemplate(InformantePosicaoOpcao informantePosicao,
			OpcaoCriacionalTemplate<CadastroDto, RetornoDto> opcaoCriacional,
			String descricao) {
		INFORMANTE_POSICAO = informantePosicao;
		OPCAO_CRIACIONAL = opcaoCriacional;
		DESCRICAO = descricao;
	}
	
	public RetornoDto informar() {
		RetornoDto retornoDto = null;
		while (naoInformado(retornoDto)) {
			System.out.println(DESCRICAO);;
			System.out.println("1 - Criar");
			System.out.println("2 - Selecionar existente");
			int opcao = INFORMANTE_POSICAO.informar(1, 2);
			System.out.println();
			if (opcao == 1) {
				OPCAO_CRIACIONAL.executar();
				retornoDto = OPCAO_CRIACIONAL.getUltimaEntidadeCriada();
			}
		}
		return retornoDto;
	}
	
	private boolean naoInformado(RetornoDto retornoDto) {
		return retornoDto == null;
	}
	
	/**
	 * Metodo para aumentar a legibilidade do construtor das classes filhas
	 * 
	 * @param descricao Descricao da opcao
	 * @return Mesma instancia
	 */
	protected static String descricao(String descricao) {
		return descricao;
	}
}