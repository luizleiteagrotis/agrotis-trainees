package com.agrotis.trainees.crudmenu.informante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crudmenu.dto.produto.ProdutoCadastroDto;
import com.agrotis.trainees.crudmenu.dto.produto.ProdutoRetornoDto;
import com.agrotis.trainees.crudmenu.opcao.criacional.OpcaoCriarProduto;

@Component
public class InformanteProduto extends InformanteEntidadeTemplate<ProdutoCadastroDto, ProdutoRetornoDto>{
	
	@Autowired
	public InformanteProduto(InformantePosicaoOpcao informantePosicao, OpcaoCriarProduto opcaoCriarProduto) {
		super(informantePosicao, opcaoCriarProduto, descricao("Informe o produto"));
	}
}
