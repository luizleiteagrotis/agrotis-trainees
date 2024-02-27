package com.agrotis.trainees.crud.service.item.util;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.repository.item.ItemNotaRepository;

@Component
public class CalculadorItem {

	/**
	 * Calcula o valor total do item e retorna o item com o novo valor. Entretanto, nao salva no banco.
	 * 
	 * @param item Item para calcular valor total
	 * @return Item atualizado
	 * @throws CalculadorItemException Quando quantidade ou preco unitario forem negativos
	 */
	public ItemNota calcularValorTotal(ItemNota item) throws CalculadorItemException {
		BigDecimal precoUnitario = item.getPrecoUnitario();
		Integer quantidade = item.getQuantidade();
		if (menorDoQueZero(precoUnitario)) {
			String mensagemException = "Preco unitario nao pode ser negativo. Fornecido: " + precoUnitario;
			throw new CalculadorItemException(mensagemException);
		}
		if (quantidade < 0) {
			String mensagemException = "Quantidade nao pode ser negativa. Fornecido: " + quantidade;
			throw new CalculadorItemException(mensagemException);
		}
		BigDecimal novoValorTotal = precoUnitario.multiply(BigDecimal.valueOf(quantidade));
		item.setValorTotal(novoValorTotal);
		return item;
	}
	
	private boolean menorDoQueZero(BigDecimal valor) {
		return valor.compareTo(BigDecimal.ZERO) == -1;
	}
}
