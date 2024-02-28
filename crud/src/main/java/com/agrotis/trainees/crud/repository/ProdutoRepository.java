package com.agrotis.trainees.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;

import dto.ProdutoDto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	
	 Optional<Produto> findByNome(String nome);

    Optional<Produto> findByNome(Produto produto);

    Optional<Produto> findById(Produto produto);

    Object findByDescricao(String descricao);

    ProdutoDto save(ProdutoDto entidade);


}
