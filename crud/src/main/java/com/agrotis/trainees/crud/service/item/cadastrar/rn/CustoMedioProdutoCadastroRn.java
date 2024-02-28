package com.agrotis.trainees.crud.service.item.cadastrar.rn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.TipoNota;
import com.agrotis.trainees.crud.service.item.cadastrar.ItemCadastroRn;
import com.agrotis.trainees.crud.util.CalculadorProduto;

@Component
public class CustoMedioProdutoCadastroRn implements ItemCadastroRn {
	
	private CalculadorProduto calculadorProduto;
	
	@Autowired
	public CustoMedioProdutoCadastroRn(CalculadorProduto calculadorProduto) {
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
		return 5;
	}
}
