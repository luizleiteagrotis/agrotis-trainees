package com.agrotis.trainees.crud.repository.item;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.repository.wrapper.JpaRepositoryWrapper;

@Repository
public class ItemNotaRepository extends JpaRepositoryWrapper<
										ItemNota, 
										Long>{

	@Autowired
	public ItemNotaRepository(ItemNotaJpaRepository repository) {
		super(repository, nomeLogger(ItemNotaRepository.class));
	}
}
