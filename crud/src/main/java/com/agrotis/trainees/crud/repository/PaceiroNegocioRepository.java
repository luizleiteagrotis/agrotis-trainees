package com.agrotis.trainees.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;

@Repository
public interface PaceiroNegocioRepository extends JpaRepository<ParceiroNegocio, Integer>{
	Optional <ParceiroNegocio> findByInscricaoFiscal(String inscricaoFiscal);
}
