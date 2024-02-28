package com.agrotis.trainees.crud.service.item.cadastrar.rn;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.TipoNota;
import com.agrotis.trainees.crud.repository.cabecalho.CabecalhoNotaRepository;
import com.agrotis.trainees.crud.service.item.cadastrar.ItemCadastroRnException;
import com.agrotis.trainees.crud.util.CalculadorItem;
import com.agrotis.trainees.crud.util.CalculadorItemException;
import com.agrotis.trainees.crud.service.item.cadastrar.ItemCadastroRn;

@Component
public class ValorTotalCadastroRn implements ItemCadastroRn {

	private CalculadorItem calculadorItem;
	
	@Autowired
	public ValorTotalCadastroRn(CalculadorItem calculadorItem) {
		this.calculadorItem = calculadorItem;
	}

	@Override
	public ItemNota operarSobre(ItemNota item) {
		CabecalhoNota cabecalho = somarValorTotalCabecalho(item);
		item.setCabecalhoNota(cabecalho);
		return item;
	}

	private CabecalhoNota somarValorTotalCabecalho(ItemNota item) {
		try {
			item = calculadorItem.calcularValorTotal(item);
		} catch (CalculadorItemException e) {
			throw new ItemCadastroRnException(e.getMessage());
		}
		CabecalhoNota cabecalho = item.getCabecalhoNota();
		BigDecimal valorTotalCabecalho = cabecalho.getValorTotal();
		BigDecimal valorTotalItem = item.getValorTotal();
		cabecalho.setValorTotal(valorTotalCabecalho.add(valorTotalItem));
		return cabecalho;
	}

	@Override
	public int getOrder() {
		return 2;
	}
}
