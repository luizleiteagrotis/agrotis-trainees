package com.agrotis.trainees.crud.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;

public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Integer>{

	
	Optional<NotaFiscal> findByParceiroNegocio(ParceiroNegocio parceiroNegocio);
	Optional<NotaFiscal> findByNumeroNota(Integer numeroNota);
	Optional<NotaFiscal> findByDataNota(LocalDate dataNota);
	Optional<NotaFiscal> findByNotaFiscalTipo(NotaFiscalTipo tipoNota);
	
}
