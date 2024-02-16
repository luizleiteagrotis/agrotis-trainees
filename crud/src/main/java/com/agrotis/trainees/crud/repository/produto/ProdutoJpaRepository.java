package com.agrotis.trainees.crud.repository.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;

@Repository
public interface ProdutoJpaRepository extends JpaRepository<Produto, Long> {
	
	boolean existsByNomeAndDescricaoAndFabricante(String nome, String descricao, ParceiroNegocio fabricante);
}
