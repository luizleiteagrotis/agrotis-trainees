package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Integer> {

    @Query("SELECT CASE WHEN COUNT(nf) > 0 THEN true ELSE false END FROM NotaFiscal nf WHERE nf.numero = :numero AND nf.notaFiscalTipo = :notaFiscalTipo")
    boolean hasDuplicates(@Param("numero") String numero, @Param("notaFiscalTipo") NotaFiscalTipo tipo);

    List<NotaFiscal> findAllByNumero(String numero);
}