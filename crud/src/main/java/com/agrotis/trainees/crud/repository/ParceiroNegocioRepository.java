package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;

@Repository
public interface ParceiroNegocioRepository extends JpaRepository<ParceiroNegocio, Integer> {



}