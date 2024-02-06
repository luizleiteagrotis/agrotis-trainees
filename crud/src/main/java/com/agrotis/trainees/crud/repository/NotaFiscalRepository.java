package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Integer> {

    Optional<NotaFiscal> findByTipoAndNumero(NotaFiscalTipo tipo, Integer numero);

    @Query("SELECT MAX(nf.numero) FROM NotaFiscal nf WHERE nf.tipo = :id_tipo")
    Optional<Integer> findMaxNumeroByTipo(@Param("id_tipo") NotaFiscalTipo tipo);
}
