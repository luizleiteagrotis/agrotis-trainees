package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Integer> {

    boolean existsByNumeroAndNotaFiscalTipo(String numero, NotaFiscalTipo notaFiscalTipo);

    boolean existsByNumeroAndNotaFiscalTipoAndIdNot(String numero, NotaFiscalTipo notaFiscalTipo, Integer id);

    List<NotaFiscal> findAllByNumero(String numero);
}