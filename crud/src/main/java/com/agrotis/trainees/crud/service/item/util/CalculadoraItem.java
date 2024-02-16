package com.agrotis.trainees.crud.service.item.util;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.dto.item.ItemAtualizacaoDto;
import com.agrotis.trainees.crud.entity.ItemNota;

@Component
public class CalculadoraItem {
	
	private final Logger LOGGER;
	
	@Autowired
	public CalculadoraItem() {
		LOGGER = LoggerFactory.getLogger(CalculadoraItem.class);
	}

	public BigDecimal calcularValorTotal(Integer quantidade, BigDecimal precoUnitario) {
		LOGGER.info("Calculando valor total de Item com quantidade {} e preco unitario {}...");
		BigDecimal valorTotal = precoUnitario.multiply(BigDecimal.valueOf(quantidade));
		LOGGER.info("Calculado valor total de Item: {} x {} = {}", quantidade, precoUnitario, valorTotal);
		return valorTotal;
	}
}
