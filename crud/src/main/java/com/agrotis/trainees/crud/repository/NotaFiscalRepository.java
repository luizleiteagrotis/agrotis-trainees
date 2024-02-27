package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;

public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Integer> {

    Optional<NotaFiscal> findByParceiroNegocio(ParceiroNegocioDto parceiroNegocioDto);

    Optional<NotaFiscal> findByNumero(Integer numeroNota);

    Optional<NotaFiscal> findByData(LocalDate data);

    Optional<NotaFiscal> findByTipo(NotaFiscalTipo tipoNota);

    Optional<NotaFiscal> findById(NotaFiscal notaFiscal); // adicionado após
                                                          // erro estranho,
                                                          // verificar

    void deleteById(NotaFiscal notaFiscal); // adicionado após erro estranho,
                                            // verificar

}