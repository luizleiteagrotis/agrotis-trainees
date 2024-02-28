package com.agrotis.trainees.crud.service.item.deletar.rn;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.TipoNota;
import com.agrotis.trainees.crud.repository.produto.ProdutoRepository;
import com.agrotis.trainees.crud.service.item.deletar.ItemDelecaoRn;

@Component
public class CustoTotalProdutoDelecaoRn implements ItemDelecaoRn {
	
	@Override
	public ItemNota operarSobre(ItemNota item) {
		Produto produto = item.getProduto();
		CabecalhoNota cabecalho = item.getCabecalhoNota();
		TipoNota tipoCabecalho = cabecalho.getTipo();
		BigDecimal custoTotalProduto = produto.getCustoTotal();
		BigDecimal novoCustoTotal = null;
		switch (tipoCabecalho) {
			case ENTRADA:
				BigDecimal valorTotalItem = item.getValorTotal();
				novoCustoTotal = custoTotalProduto.subtract(valorTotalItem);
				break;
			case SAIDA:
				BigDecimal quantidadeItem = BigDecimal.valueOf(item.getQuantidade());
				BigDecimal custoMedio = produto.getCustoMedio();
				novoCustoTotal = custoTotalProduto.subtract(quantidadeItem.multiply(custoMedio)); 
		}
		produto.setCustoTotal(novoCustoTotal);
		return item;
	}

	@Override
	public int getOrder() {
		return 3;
	}
}
