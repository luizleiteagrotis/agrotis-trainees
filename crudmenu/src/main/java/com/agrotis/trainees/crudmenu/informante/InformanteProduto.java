package com.agrotis.trainees.crudmenu.informante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crudmenu.dto.produto.ProdutoRetornoDto;
import com.agrotis.trainees.crudmenu.opcao.criacional.OpcaoCriarProduto;

@Component
public class InformanteProduto {

	private final InformantePosicaoOpcao INFORMANTE_POSICAO;
	private final OpcaoCriarProduto OPCAO_CRIAR_PRODUTO;
	
	@Autowired
	public InformanteProduto(InformantePosicaoOpcao informantePosicao, OpcaoCriarProduto opcaoCriarProduto) {
		INFORMANTE_POSICAO = informantePosicao;
		OPCAO_CRIAR_PRODUTO = opcaoCriarProduto;
	}
	
	public ProdutoRetornoDto informar() {
		ProdutoRetornoDto produto = null;
		while (naoInformado(produto)) {
			System.out.println("Informe o produto");
			System.out.println("1 - Criar");
			System.out.println("2 - Selecionar existente");
			int opcao = INFORMANTE_POSICAO.informar(1, 2);
			System.out.println();
			if (opcao == 1) {
				OPCAO_CRIAR_PRODUTO.executar();
				produto = OPCAO_CRIAR_PRODUTO.getUltimaEntidadeCriada();
			}
		}
		return produto;
	}
	
	private boolean naoInformado(ProdutoRetornoDto produto) {
		return produto == null;
	}
}
