package com.agrotis.trainees.crud.service.item.atualizar.rn;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.repository.cabecalho.CabecalhoNotaRepository;
import com.agrotis.trainees.crud.service.item.atualizar.ItemAtualizacaoRn;
import com.agrotis.trainees.crud.service.item.atualizar.ItemAtualizacaoRnException;
import com.agrotis.trainees.crud.service.item.util.CalculadorItem;
import com.agrotis.trainees.crud.service.item.util.CalculadorItemException;

@Component
public class ValorTotalAtualizacaoRn implements ItemAtualizacaoRn{

	private CabecalhoNotaRepository cabecalhoRepository;
	private CalculadorItem calculadorItem;
		
	@Autowired
	public ValorTotalAtualizacaoRn(CabecalhoNotaRepository cabecalhoRepository, CalculadorItem calculadorItem) {
		this.cabecalhoRepository = cabecalhoRepository;
		this.calculadorItem = calculadorItem;
	}

	@Override
	public ItemNota operarSobre(ItemNota novoItem, ItemNota antigoItem) {
		try {
			novoItem = calculadorItem.calcularValorTotal(novoItem);			
		} catch (CalculadorItemException e) {
			throw new ItemAtualizacaoRnException(e.getMessage());
		}
		CabecalhoNota cabecalho = novoItem.getCabecalhoNota();
		BigDecimal valorAtualCabecalho = cabecalho.getValorTotal();
		BigDecimal antigoValorItem = antigoItem.getValorTotal();		
		BigDecimal novoValorItem = novoItem.getValorTotal();
		BigDecimal novoValorCabecalho = valorAtualCabecalho.subtract(antigoValorItem).add(novoValorItem);
		cabecalho.setValorTotal(novoValorCabecalho);
		cabecalhoRepository.salvar(cabecalho);
		return novoItem;
	}

	@Override
	public int getOrder() {
		return 1;
	}
}
