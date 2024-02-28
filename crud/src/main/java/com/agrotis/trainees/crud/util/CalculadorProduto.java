package com.agrotis.trainees.crud.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.entity.Produto;

@Component
public class CalculadorProduto {

	public Produto calcularCustoMedio(Produto produto) {
		BigDecimal custoTotal = produto.getCustoTotal();
		BigDecimal estoque = BigDecimal.valueOf(produto.getEstoque());
		BigDecimal custoMedio = null;
		if (!estoque.equals(BigDecimal.ZERO)) {
			custoMedio = custoTotal.divide(estoque, RoundingMode.HALF_UP).setScale(2);
		} else {
			custoMedio = BigDecimal.ZERO;
		}
		produto.setCustoMedio(custoMedio);
		return produto;
	}
}
