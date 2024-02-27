package com.agrotis.trainees.crud.service.item.atualizar.rn;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.TipoNota;
import com.agrotis.trainees.crud.repository.produto.ProdutoRepository;
import com.agrotis.trainees.crud.service.item.atualizar.ItemAtualizacaoRn;

@Component
public class CustoTotalProdutoAtualizacaoRn implements ItemAtualizacaoRn {

	private ProdutoRepository produtoRepository;

	@Autowired
	public CustoTotalProdutoAtualizacaoRn(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	@Override
	public ItemNota operarSobre(ItemNota novoItem, ItemNota antigoItem) {
		CabecalhoNota cabecalho = novoItem.getCabecalhoNota();
		TipoNota tipoCabecalho = cabecalho.getTipo();
		Produto produto = novoItem.getProduto();
		BigDecimal valorTotalAntigo = antigoItem.getValorTotal();
		BigDecimal valorTotalNovo = novoItem.getValorTotal();
		BigDecimal custoTotalProduto = produto.getCustoTotal();
		BigDecimal novoCustoTotal = null;
		switch (tipoCabecalho) {
			case ENTRADA:
				novoCustoTotal = custoTotalProduto.subtract(valorTotalAntigo).add(valorTotalNovo);
				break;
			case SAIDA:
				novoCustoTotal = custoTotalProduto.add(valorTotalAntigo).subtract(valorTotalNovo);
				break;
		}
		produto.setCustoTotal(novoCustoTotal);
		produtoRepository.salvar(produto);
		return novoItem;
	}
	
	@Override
	public int getOrder() {
		return 3;
	}
}
