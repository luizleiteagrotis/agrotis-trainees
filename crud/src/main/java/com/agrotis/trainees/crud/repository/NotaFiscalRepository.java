package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.agrotis.trainees.crud.entity.NotaFiscal;

import enums.TipoNota;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Integer> {

    Optional<NotaFiscal> findByTipoAndNumero(TipoNota tipo, Integer numero);

    @Query("SELECT MAX(nf.numero) FROM NotaFiscal nf WHERE nf.tipo = :id_tipo")
    Optional<Integer> findMaxNumeroByTipo(@Param("id_tipo") TipoNota tipo);

    @Query("SELECT nf FROM NotaFiscal nf WHERE nf.tipo = :id_tipo")
    List<NotaFiscal> findByTipo(@Param("id_tipo") TipoNota tipo);

    List<NotaFiscal> findByNumero(Integer numero);
}
