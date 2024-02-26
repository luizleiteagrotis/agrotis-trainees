package com.agrotis.trainees.crud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrotis.trainees.crud.entity.CabecalhoNotaFiscal;


public interface CabecalhoNotaFiscalRepository extends JpaRepository<CabecalhoNotaFiscal, Integer> {

     Optional<CabecalhoNotaFiscal> findByNumero(Integer numero);
    
     Optional<List<CabecalhoNotaFiscal>> findByTipo(String tipo);
    
     Optional<List<CabecalhoNotaFiscal>> findByParceiro(Integer idParceiro);

}
