package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    List<Produto> findByFabricante(ParceiroNegocio fabricante);

    List<Produto> findByDataFabricacao(Date data);

    List<Produto> findByDataValidade(Date data);

    List<Produto> findByNome(String nome);

}
