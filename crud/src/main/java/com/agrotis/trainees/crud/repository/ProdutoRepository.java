package com.agrotis.trainees.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.agrotis.trainees.crud.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
    Optional<Produto> findByNome(String nomeProduto);
	Optional<Produto> findById(Integer id);

	void deleteById(Integer id);

}
