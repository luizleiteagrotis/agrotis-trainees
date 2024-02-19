package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;

@Repository
public interface ParceiroNegocioRepository extends JpaRepository<ParceiroNegocio, Integer> {

    boolean existsByNomeOrInscricaoFiscal(String nome, String inscricaoFiscal);

    boolean existsByNomeAndIdNot(String nome, Integer id);

    boolean existsByInscricaoFiscalAndIdNot(String InscricaoFiscal, Integer id);

    Optional<ParceiroNegocio> findByNome(String nome);

    Optional<ParceiroNegocio> findByInscricaoFiscal(String inscricaoFiscal);

}
