package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.NotaFiscalTipo;

import java.util.Optional;

@Repository
public interface NotaFiscalTipoRepository extends JpaRepository<NotaFiscalTipo, Integer> {

    Optional<NotaFiscalTipo> findByNome(String nome);


}
