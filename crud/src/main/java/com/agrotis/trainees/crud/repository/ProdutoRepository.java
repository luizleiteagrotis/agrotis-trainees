package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrotis.trainees.crud.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
