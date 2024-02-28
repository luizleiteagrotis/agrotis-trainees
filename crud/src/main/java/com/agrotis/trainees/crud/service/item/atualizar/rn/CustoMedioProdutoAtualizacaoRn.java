package com.agrotis.trainees.crud.service.item.atualizar.rn;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.entity.TipoNota;
import com.agrotis.trainees.crud.service.item.atualizar.ItemAtualizacaoRn;
import com.agrotis.trainees.crud.util.CalculadorProduto;

@Component
public class CustoMedioProdutoAtualizacaoRn implements ItemAtualizacaoRn {
	
	private CalculadorProduto calculadorProduto;
	
	@Autowired
	public CustoMedioProdutoAtualizacaoRn(CalculadorProduto calculadorProduto) {
		this.calculadorProduto = calculadorProduto;
	}
	
	@Override
	public ItemNota operarSobre(ItemNota novoItem, ItemNota antigoItem) {
		CabecalhoNota cabecalho = novoItem.getCabecalhoNota();
		TipoNota tipoCabecalho = cabecalho.getTipo();
		if (tipoCabecalho == TipoNota.ENTRADA) {
			calculadorProduto.calcularCustoMedio(novoItem.getProduto());
		}
		return novoItem;
	}
	
	@Override
	public int getOrder() {
		return 4;
	}
}
