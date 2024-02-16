package com.agrotis.trainees.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;

@Repository
public interface ParceiroNegocioRepository extends JpaRepository<ParceiroNegocio, Integer>{

	
    Optional<ParceiroNegocio> findByNome(String nome);

    static Optional<Produto> findById(byte[] bytes) {
        // TODO Auto-generated method stub
        return null;
    }

    static ParceiroNegocio save(String fabricante) {
        // TODO Auto-generated method stub
        return null;
    }
    

	
}
