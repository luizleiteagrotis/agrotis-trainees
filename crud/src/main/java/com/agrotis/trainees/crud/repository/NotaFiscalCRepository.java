package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrotis.trainees.crud.entity.NotaFiscalC;

import dto.CabecalhoDto;

public interface NotaFiscalCRepository extends JpaRepository<NotaFiscalC, Integer> {

    CabecalhoDto save(CabecalhoDto cabecalho);

}
