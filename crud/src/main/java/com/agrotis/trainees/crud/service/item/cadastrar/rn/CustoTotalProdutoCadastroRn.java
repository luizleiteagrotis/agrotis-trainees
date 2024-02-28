package com.agrotis.trainees.crud.service.item.cadastrar.rn;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.TipoNota;
import com.agrotis.trainees.crud.repository.produto.ProdutoRepository;
import com.agrotis.trainees.crud.service.item.cadastrar.ItemCadastroRn;

@Component
public class CustoTotalProdutoCadastroRn implements ItemCadastroRn {
	
	@Override
	public ItemNota operarSobre(ItemNota item) {
		Produto produto = item.getProduto();
		CabecalhoNota cabecalho = item.getCabecalhoNota();
		BigDecimal valorTotalItem = item.getValorTotal();
		BigDecimal custoTotalProduto = produto.getCustoTotal();
		BigDecimal novoCustoTotal = null;
		TipoNota tipoCabecalho = cabecalho.getTipo();
		switch (tipoCabecalho) {
			case ENTRADA:
				novoCustoTotal = custoTotalProduto.add(valorTotalItem);
				break;
			case SAIDA:
				BigDecimal quantidadeItem = BigDecimal.valueOf(item.getQuantidade());
				BigDecimal custoMedio = produto.getCustoMedio();
				novoCustoTotal = custoTotalProduto.subtract(quantidadeItem.multiply(custoMedio));
				break;
		}
		produto.setCustoTotal(novoCustoTotal);
		return item;
	}

	@Override
	public int getOrder() {
		return 4;
	}
}
