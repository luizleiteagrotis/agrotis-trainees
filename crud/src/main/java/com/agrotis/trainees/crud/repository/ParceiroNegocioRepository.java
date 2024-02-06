package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;

import java.util.Optional;

@Repository
public interface ParceiroNegocioRepository extends JpaRepository<ParceiroNegocio, Integer> {

    Optional<ParceiroNegocio> findByNome(String nome);
    
    Optional<ParceiroNegocio> findByInscricaoFiscal(String inscricaoFiscal);
    
    Boolean existsByInscricaoFiscal(String inscricaoFiscal);

}
