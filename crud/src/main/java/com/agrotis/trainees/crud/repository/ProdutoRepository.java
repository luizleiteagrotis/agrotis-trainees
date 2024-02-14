package com.agrotis.trainees.crud.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.agrotis.trainees.crud.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	 
	Optional<Produto> findBydescricao(String descricao);
	Optional<Produto> findByNome(String nome);
	Optional<Produto> findByFabricante(String parceiroFabricante);
	
	
}


