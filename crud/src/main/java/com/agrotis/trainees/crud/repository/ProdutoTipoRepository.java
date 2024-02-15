package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.agrotis.trainees.crud.entity.Produto;

@Repository
public interface ProdutoTipoRepository extends JpaRepository<Produto, Integer> {
    Optional<Produto> findBydescricao(String descricao);

}
