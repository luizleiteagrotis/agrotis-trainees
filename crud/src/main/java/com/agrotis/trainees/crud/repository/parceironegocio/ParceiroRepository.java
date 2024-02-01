package com.agrotis.trainees.crud.repository.parceironegocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.wrapper.JpaRepositoryWrapper;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;

@Repository
public class ParceiroRepository extends JpaRepositoryWrapper<
										ParceiroNegocioService, 
										ParceiroNegocio, 
										Long, 
										ParceiroJpaRepository>{

	@Autowired
	public ParceiroRepository(ParceiroJpaRepository repository, 
			Class<ParceiroNegocio> entity,
			Class<ParceiroNegocioService> service) {
		super(repository, entity, service);
	}
}
