package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.NotaFiscalTipo;

import java.util.Optional;

@Repository
public interface NotaFiscalTipoRepository extends JpaRepository<NotaFiscalTipo, Integer> {
	
	//Chamando método que busca nome (esse método não está incluso na classe JPA);
    Optional<NotaFiscalTipo> findByNome(String nome);

}