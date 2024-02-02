package com.agrotis.trainees.crud.repository.notafiscal.cabecalho;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.CabecalhoNota;

@Repository
public interface CabecalhoNotaJpaRepository extends JpaRepository<CabecalhoNota, Long> {
	
}
