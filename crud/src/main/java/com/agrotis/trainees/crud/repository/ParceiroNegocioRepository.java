package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;

@Repository
public interface ParceiroNegocioRepository extends JpaRepository<ParceiroNegocio, Integer> {

    Optional<ParceiroNegocio> findByNome(String nome);

    @Query("SELECT p FROM ParceiroNegocio p WHERE p.inscricaoFiscal = :inscricaoFiscal")
    Optional<ParceiroNegocio> findByInscricaoFiscal(String inscricaoFiscal);

}