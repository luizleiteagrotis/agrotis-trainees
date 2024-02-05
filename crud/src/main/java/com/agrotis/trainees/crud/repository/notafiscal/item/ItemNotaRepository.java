package com.agrotis.trainees.crud.repository.notafiscal.item;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.repository.wrapper.JpaRepositoryWrapper;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;

@Repository
public class ItemNotaRepository extends JpaRepositoryWrapper<
										ParceiroNegocioService, 
										ItemNota, 
										Long, 
										ItemNotaJpaRepository>{

	public ItemNotaRepository(ItemNotaJpaRepository repository, Class<ItemNota> entity,
			Class<ParceiroNegocioService> service) {
		super(repository, entity, service);
	}
	
	public BigDecimal calcularValorTotal(CabecalhoNota cabecalhoNota) {
		LOG.info("Buscando valor total de {} com id {}", NOME_ENTITY, cabecalhoNota.getId());
		BigDecimal valorTotal = REPOSITORY.calcularValorTotal(cabecalhoNota);
		LOG.info("Valor total de {} com id {}: {}", NOME_ENTITY, cabecalhoNota.getId(), valorTotal);
		return valorTotal;
	}
}
