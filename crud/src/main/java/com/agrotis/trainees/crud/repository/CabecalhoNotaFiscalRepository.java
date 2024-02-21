package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrotis.trainees.crud.entity.CabecalhoNotaFiscal;

public interface CabecalhoNotaFiscalRepository extends JpaRepository<CabecalhoNotaFiscal, Integer> {

    // Optional<CabecalhoNotaFiscal> findByNumero(Integer numero);
    //
    // Optional<List<CabecalhoNotaFiscal>> findByTipo(String tipo);
    //
    // Optional<List<CabecalhoNotaFiscal>> findByParceiro(Integer idParceiro);

}
