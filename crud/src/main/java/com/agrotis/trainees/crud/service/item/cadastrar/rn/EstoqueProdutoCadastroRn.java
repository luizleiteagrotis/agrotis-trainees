package com.agrotis.trainees.crud.service.item.cadastrar.rn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.TipoNota;
import com.agrotis.trainees.crud.repository.produto.ProdutoRepository;
import com.agrotis.trainees.crud.service.item.cadastrar.ItemCadastroRnException;
import com.agrotis.trainees.crud.service.item.cadastrar.ItemCadastroRn;

@Component
public class EstoqueProdutoCadastroRn implements ItemCadastroRn {

	@Override
	public ItemNota operarSobre(ItemNota item) {
		CabecalhoNota cabecalho = item.getCabecalhoNota();
		Produto produto = item.getProduto();
		TipoNota tipoNota = cabecalho.getTipo();
		switch (tipoNota) {
		case ENTRADA:
			somarEstoque(produto, item.getQuantidade());
			break;
		case SAIDA:
			subtrairEstoque(produto, item.getQuantidade());
			break;
		}
		item.setProduto(produto);
		return item;
	}
	
	private void somarEstoque(Produto produto, Integer quantidadeItem) {
		Integer estoqueProduto = produto.getEstoque();
		produto.setEstoque(estoqueProduto + quantidadeItem);
	}
	
	private void subtrairEstoque(Produto produto, Integer quantidadeItem) {
		Integer estoqueProduto = produto.getEstoque();
		Integer novoEstoque = estoqueProduto - quantidadeItem;
		if (novoEstoque < 0) {
			String mensagemErro = "Estoque insuficiente para emitir item de saida."
					+ " EstoqueAtual=" + estoqueProduto
					+ " TentandoRetirar=" + quantidadeItem;
			throw new ItemCadastroRnException(mensagemErro);
		}
		produto.setEstoque(estoqueProduto - quantidadeItem);
	}

	@Override
	public int getOrder() {
		return 3;
	}
}
