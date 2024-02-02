package com.agrotis.trainees.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.NotaFiscalCampos;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;

@Repository
public interface NotaFiscalCamposRepository extends JpaRepository<NotaFiscalCampos, Integer>{
	
	Optional<NotaFiscalCampos> findByTipoAndNumero(NotaFiscalTipo tipo, Integer numero);
	
	@Query("SELECT MAX(nc.numero) FROM NotaFiscalCampos nc WHERE nc.tipo = :tipo")
	Optional<Integer> findMaxNumeroByTipo(@Param("tipo") NotaFiscalTipo tipo);
}
