package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.agrotis.trainees.crud.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    boolean existsByDescricao(String descricao);

    boolean existsByDescricaoAndIdNot(String descricao, Integer id);

    Optional<Produto> findByDescricao(String descricao);

}
