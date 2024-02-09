package com.agrotis.trainees.crud.repository.cabecalho;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.TipoNota;

@Repository
public interface CabecalhoNotaJpaRepository extends JpaRepository<CabecalhoNota, Long> {
	
	boolean existsByTipoAndNumero(TipoNota tipo, long numero);
}
