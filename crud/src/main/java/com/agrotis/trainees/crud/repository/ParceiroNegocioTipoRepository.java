package com.agrotis.trainees.crud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;

@Repository
public interface ParceiroNegocioTipoRepository extends JpaRepository<ParceiroNegocio, Integer> {
    Optional<ParceiroNegocio> findByNome( String nome);

}
