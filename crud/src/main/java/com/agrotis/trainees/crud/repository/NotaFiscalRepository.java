package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Integer> {

    List<NotaFiscal> findByTipo(String tipo);

    List<NotaFiscal> findByParceiroNegocio(ParceiroNegocio parceiroNegocio);

    Optional<NotaFiscal> findByNumero(int numero);

    List<NotaFiscal> findByData(Date data);
}
