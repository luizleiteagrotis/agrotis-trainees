package com.agrotis.trainees.crudmenu.opcao.leitura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crudmenu.api.parceiro.ParceiroApi;
import com.agrotis.trainees.crudmenu.api.produto.ProdutoApi;
import com.agrotis.trainees.crudmenu.dto.parceiro.ParceiroRetornoDto;
import com.agrotis.trainees.crudmenu.dto.produto.ProdutoRetornoDto;
import com.agrotis.trainees.crudmenu.informante.InformanteEntidadeId;
import com.agrotis.trainees.crudmenu.printer.ParceiroPrinter;
import com.agrotis.trainees.crudmenu.printer.ProdutoPrinter;

@Component
public class OpcaoBuscarPorIdProduto extends OpcaoBuscarPorIdTemplate<ProdutoRetornoDto> {

	@Autowired
	public OpcaoBuscarPorIdProduto(ProdutoApi produtoApi, InformanteEntidadeId informanteEntidadeId, 
			ProdutoPrinter produtoPrinter) {
		super(produtoApi, descricao("Buscar produto por id"), informanteEntidadeId, produtoPrinter);
	}
}
