package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

import com.agrotis.trainees.crud.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    // Optional<Produto> findByFornecedor(ParceiroNegocio fornecedor);

    Optional<Produto> findByDataFabricacao(Date data);

    Optional<Produto> findByDataValidade(Date data);

    Optional<Produto> findByNome(String nome);

}
