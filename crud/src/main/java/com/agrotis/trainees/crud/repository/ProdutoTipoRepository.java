package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;

@Repository
public interface ProdutoTipoRepository extends JpaRepository<Produto, Integer> {
    Optional<Produto> findByDescricao(String descricao);

    Optional<Produto> findByDataFabricacao(LocalDate dataFabricacao);

    Optional<Produto> findByFabricante(String fabricante);

    Optional<Produto> findByDataValidade(LocalDate dataValidade);

    Optional<Produto> findByParceiroNegocio(ParceiroNegocio parceiroNegocio);

}