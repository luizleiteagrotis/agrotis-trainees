package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Integer> {

    Optional<NotaFiscal> findByNotaFiscalTipo(String notaFiscalTipo2);

    Optional<NotaFiscal> findByParceiroNegocio(ParceiroNegocio parceiroNegocio);

    Optional<NotaFiscal> findByNumero(Integer numero);

    Optional<NotaFiscal> findByDataEmissao(LocalDate dataEmissao);

}
