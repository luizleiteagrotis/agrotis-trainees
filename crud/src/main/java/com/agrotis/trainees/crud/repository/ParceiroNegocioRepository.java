package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;

@Repository
public interface ParceiroNegocioRepository extends JpaRepository<ParceiroNegocio, Integer> {

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM ParceiroNegocio p WHERE p.nome = :nome OR p.inscricao_fiscal = :inscricaoFiscal")
    boolean findByNomeOrInscricaoFiscal(String nome, String inscricaoFiscal);

    Optional<ParceiroNegocio> findByNome(String nome);

}
