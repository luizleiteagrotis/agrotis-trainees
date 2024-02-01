package com.agrotis.trainees.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	
	 Optional<Produto> findByNome(String nome);

	Produto saveAll(ParceiroNegocio produto);

}
