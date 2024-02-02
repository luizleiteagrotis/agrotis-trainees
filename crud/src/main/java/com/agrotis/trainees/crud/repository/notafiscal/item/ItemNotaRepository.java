package com.agrotis.trainees.crud.repository.notafiscal.item;

import org.springframework.stereotype.Repository;

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
}
