package com.agrotis.trainees.crud.service.item.atualizar.rn;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.TipoNota;
import com.agrotis.trainees.crud.service.item.atualizar.ItemAtualizacaoRn;

@Component
public class CustoMedioProdutoAtualizacaoRn implements ItemAtualizacaoRn {
	
	@Override
	public ItemNota operarSobre(ItemNota novoItem, ItemNota antigoItem) {
		CabecalhoNota cabecalho = novoItem.getCabecalhoNota();
		TipoNota tipoCabecalho = cabecalho.getTipo();
		if (tipoCabecalho == TipoNota.ENTRADA) {
			Produto produto = novoItem.getProduto();
			BigDecimal custoTotalProduto = produto.getCustoTotal();
			BigDecimal estoqueProduto = BigDecimal.valueOf(produto.getEstoque());
			BigDecimal novoCustoMedio = custoTotalProduto.divide(estoqueProduto, RoundingMode.HALF_UP).setScale(2);
			produto.setCustoMedio(novoCustoMedio);
		}
		return novoItem;
	}
	
	@Override
	public int getOrder() {
		return 4;
	}
}
