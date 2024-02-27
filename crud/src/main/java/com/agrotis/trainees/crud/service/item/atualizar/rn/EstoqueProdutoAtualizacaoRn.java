package com.agrotis.trainees.crud.service.item.atualizar.rn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.TipoNota;
import com.agrotis.trainees.crud.repository.produto.ProdutoRepository;
import com.agrotis.trainees.crud.service.item.atualizar.ItemAtualizacaoRn;
import com.agrotis.trainees.crud.service.item.atualizar.ItemAtualizacaoRnException;

@Component
public class EstoqueProdutoAtualizacaoRn implements ItemAtualizacaoRn{

	private ProdutoRepository produtoRepository;

	@Autowired
	public EstoqueProdutoAtualizacaoRn(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	@Override
	public ItemNota operarSobre(ItemNota novoItem, ItemNota antigoItem) {
		CabecalhoNota cabecalho = novoItem.getCabecalhoNota();
		TipoNota tipoCabecalho = cabecalho.getTipo();
		Produto produto = novoItem.getProduto();
		Integer novoEstoque = 0;
		switch (tipoCabecalho) {
		case ENTRADA:
			novoEstoque = produto.getEstoque() - antigoItem.getQuantidade() + novoItem.getQuantidade();
			break;
		case SAIDA:
			novoEstoque = produto.getEstoque() + antigoItem.getQuantidade() - novoItem.getQuantidade();
			break;
		}
		if (novoEstoque < 0) {
			String mensagemEsperada = "Estoque insuficiente para atualizar saida." 
					+ " EstoqueProduto=" + produto.getEstoque()
					+ " QuantidadeAntigaItem=" + antigoItem.getQuantidade()
					+ " QuantidadeNovaItem=" + novoItem.getQuantidade();
			throw new ItemAtualizacaoRnException(mensagemEsperada);
		}
		produto.setEstoque(novoEstoque);
		produto = produtoRepository.salvar(produto);
		novoItem.setProduto(produto);
		return novoItem;
	}

	@Override
	public int getOrder() {
		return 2;
	}
}
