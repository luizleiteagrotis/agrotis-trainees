package com.agrotis.trainees.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.CabecalhoNota;

@Repository
public interface CabecalhoNotaRepository extends JpaRepository<CabecalhoNota, Integer> {
    
    @Query("SELECT cn FROM CabecalhoNota cn WHERE cn.numero = :numero")
    Optional<CabecalhoNota> findByNumero(@Param("numero") Integer numero);

}
