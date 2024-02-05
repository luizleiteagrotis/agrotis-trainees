package com.agrotis.trainees.crud.repository.parceiro;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;

@Repository
public interface ParceiroJpaRepository extends JpaRepository<ParceiroNegocio, Long>{

}
