package com.agrotis.trainees.crud.service.item.deletar.rn;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.repository.cabecalho.CabecalhoNotaRepository;
import com.agrotis.trainees.crud.service.item.deletar.ItemDelecaoRn;
import com.agrotis.trainees.crud.service.item.deletar.ItemDelecaoRnException;

@Component
public class ValorTotalCabecalhoDelecaoRn implements ItemDelecaoRn {

	private CabecalhoNotaRepository cabecalhoRepository;
	
	@Autowired
	public ValorTotalCabecalhoDelecaoRn(CabecalhoNotaRepository cabecalhoRepository) {
		this.cabecalhoRepository = cabecalhoRepository;
	}

	@Override
	public ItemNota operarSobre(ItemNota item) {
		CabecalhoNota cabecalho = item.getCabecalhoNota();
		BigDecimal valorCabecalho = cabecalho.getValorTotal();
		BigDecimal valorItem = item.getValorTotal();
		BigDecimal novoValorCabecalho = valorCabecalho.subtract(valorItem);
		if (negativo(novoValorCabecalho)) {
			String mensagemException = "Valor total cabecalho resultante nao pode ficar negativo."
										+ " TentandoRetirar=" + valorItem
										+ " deValorTotalCabecalho=" + valorCabecalho;
			throw new ItemDelecaoRnException(mensagemException);
		}
		cabecalho.setValorTotal(novoValorCabecalho);
		item.setCabecalhoNota(cabecalho);
		cabecalhoRepository.salvar(cabecalho);
		return item;
	}
	
	private boolean negativo(BigDecimal valor) {
		return valor.compareTo(BigDecimal.ZERO) == -1;
	}

	@Override
	public int getOrder() {
		return 1;
	}
}
