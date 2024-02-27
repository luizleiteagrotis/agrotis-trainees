package com.agrotis.trainees.crud.service.item.cadastrar.rn;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.TipoNota;
import com.agrotis.trainees.crud.repository.produto.ProdutoRepository;
import com.agrotis.trainees.crud.service.item.cadastrar.ItemCadastroRn;

@Component
public class CustoMedioProdutoCadastroRn implements ItemCadastroRn {

	private ProdutoRepository produtoRepository;
	
	public CustoMedioProdutoCadastroRn(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	@Override
	public ItemNota operarSobre(ItemNota item) {
		CabecalhoNota cabecalho = item.getCabecalhoNota();
		TipoNota tipoCabecalho = cabecalho.getTipo();
		if (tipoCabecalho == TipoNota.ENTRADA) {
			Produto produto = item.getProduto();
			BigDecimal custoTotal = produto.getCustoTotal();
			BigDecimal estoque = BigDecimal.valueOf(produto.getEstoque());
			BigDecimal novoCustoMedio = custoTotal.divide(estoque, RoundingMode.HALF_UP).setScale(2);
			produto.setCustoMedio(novoCustoMedio);
			produtoRepository.salvar(produto);
		}
		return item;
	}

	@Override
	public int getOrder() {
		return 5;
	}
}
