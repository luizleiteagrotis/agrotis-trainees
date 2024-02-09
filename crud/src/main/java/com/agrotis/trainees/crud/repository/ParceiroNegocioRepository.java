package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;

@Repository
public interface ParceiroNegocioRepository extends JpaRepository<ParceiroNegocio, Integer> {

    List<ParceiroNegocio> findByNomeContainingOrderById(String nome);

    Optional<ParceiroNegocio> findByInscricaoFiscal(String inscricaoFiscal);

    Boolean existsByInscricaoFiscal(String inscricaoFiscal);

}
