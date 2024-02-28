package com.agrotis.trainees.crud.service.item.deletar.rn;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.TipoNota;
import com.agrotis.trainees.crud.repository.produto.ProdutoRepository;
import com.agrotis.trainees.crud.service.item.deletar.ItemDelecaoRn;
import com.agrotis.trainees.crud.util.CalculadorProduto;

@Component
public class CustoMedioProdutoDelecaoRn implements ItemDelecaoRn {

	private CalculadorProduto calculadorProduto;
	
	@Autowired
	public CustoMedioProdutoDelecaoRn(CalculadorProduto calculadorProduto) {
		this.calculadorProduto = calculadorProduto;
	}
	
	@Override
	public ItemNota operarSobre(ItemNota item) {
		CabecalhoNota cabecalho = item.getCabecalhoNota();
		TipoNota tipoCabecalho = cabecalho.getTipo();
		if (tipoCabecalho == TipoNota.ENTRADA) {
			calculadorProduto.calcularCustoMedio(item.getProduto());
		}
		return item;
	}

	@Override
	public int getOrder() {
		return 4;
	}
}
