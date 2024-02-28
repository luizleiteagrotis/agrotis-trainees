package com.agrotis.trainees.crud.service.item.deletar.rn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.TipoNota;
import com.agrotis.trainees.crud.repository.produto.ProdutoRepository;
import com.agrotis.trainees.crud.service.item.deletar.ItemDelecaoRn;
import com.agrotis.trainees.crud.service.item.deletar.ItemDelecaoRnException;

@Component
public class EstoqueProdutoDelecaoRn implements ItemDelecaoRn {

	@Override
	public ItemNota operarSobre(ItemNota item) {
		CabecalhoNota cabecalho = item.getCabecalhoNota();
		Produto produto = item.getProduto();
		Integer estoqueProduto = produto.getEstoque();
		Integer quantidadeItem = item.getQuantidade();
		TipoNota tipoCabecalho = cabecalho.getTipo();
		Integer novoEstoqueProduto = null;
		switch (tipoCabecalho) {
			case ENTRADA:
				novoEstoqueProduto = estoqueProduto - quantidadeItem;
				break;
			case SAIDA:
				novoEstoqueProduto = estoqueProduto + quantidadeItem;
				break;
		}
		if (novoEstoqueProduto < 0) {
			String mensagemException = "Atual estoque produto {" + produto.getEstoque()
										+ "} nao possui valor suficiente para deletar quantidade item {" 
										+ quantidadeItem + "}";
			throw new ItemDelecaoRnException(mensagemException);
		}
		produto.setEstoque(novoEstoqueProduto);
		item.setProduto(produto);
		return item;
	}

	@Override
	public int getOrder() {
		return 2;
	}

}
