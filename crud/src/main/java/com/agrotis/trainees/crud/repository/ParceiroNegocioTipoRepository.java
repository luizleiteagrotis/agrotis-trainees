package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;

@Repository
public interface ParceiroNegocioTipoRepository extends JpaRepository<ParceiroNegocio, Long> {
    Optional<ParceiroNegocio> findByNome(String nome);

    Optional<ParceiroNegocio> findById(Integer id);

    void deleteById(Integer id);

}
