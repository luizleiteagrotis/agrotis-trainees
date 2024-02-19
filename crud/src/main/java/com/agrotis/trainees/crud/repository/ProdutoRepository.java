package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    Produto findByNome(String nome);

    Optional<Produto> findByFabricante(String fabricante);

    Optional<Produto> findByDataFabricacao(LocalDate dataFabricacao);

    Optional<Produto> findByDataValidade(LocalDate dataValidade);

    Optional<Produto> findByParceiroNegocio(ParceiroNegocio parceiroNegocio);

}
