package com.agrotis.trainees.crud.repository.notafiscal.tipo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.NotaFiscalTipo;

@Service
public interface NotaFiscalTipoJpaRepository extends JpaRepository<NotaFiscalTipo, Integer> {

}
