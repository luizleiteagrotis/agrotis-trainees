package com.agrotis.trainees.crud.repository.parceiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.wrapper.JpaRepositoryWrapper;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;

@Repository
public class ParceiroRepository extends JpaRepositoryWrapper<
										ParceiroNegocio, 
										Long>{

	@Autowired
	public ParceiroRepository(ParceiroJpaRepository repository) {
		super(repository, nomeLogger(ParceiroRepository.class));
	}
}
