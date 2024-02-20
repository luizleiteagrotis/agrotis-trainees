package com.agrotis.trainees.crudmenu.informante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crudmenu.dto.produto.ProdutoCadastroDto;
import com.agrotis.trainees.crudmenu.dto.produto.ProdutoRetornoDto;
import com.agrotis.trainees.crudmenu.opcao.criacional.OpcaoCriarProduto;
import com.agrotis.trainees.crudmenu.opcao.leitura.OpcaoBuscarPorIdProduto;

@Component
public class InformanteProduto extends InformanteEntidadeTemplate<ProdutoCadastroDto, ProdutoRetornoDto>{
	
	@Autowired
	public InformanteProduto(InformantePosicaoOpcao informantePosicao, OpcaoCriarProduto opcaoCriarProduto, 
			OpcaoBuscarPorIdProduto opcaoBuscarPorIdProduto) {
		super(informantePosicao, opcaoCriarProduto, opcaoBuscarPorIdProduto, descricao("Informe o produto"));
	}
}
