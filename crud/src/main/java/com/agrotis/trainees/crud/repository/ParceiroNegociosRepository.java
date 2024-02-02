package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocios;

import java.util.Optional;

@Repository
public interface ParceiroNegociosRepository extends JpaRepository<ParceiroNegocios, Integer> {

    Optional<ParceiroNegocios> findByNome(String nome);
    
    Optional<ParceiroNegocios> findByInscricaoFiscal(String inscricaoFiscal);
    
    Boolean existsByInscricaoFiscal(String inscricaoFiscal);

}
