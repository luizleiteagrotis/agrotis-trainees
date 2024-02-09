package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    List<Produto> findByFabricante(ParceiroNegocio fabricante);

    List<Produto> findByDataFabricacao(LocalDate data);

    List<Produto> findByDataValidade(LocalDate data);

    List<Produto> findByNome(String nome);

    Optional<Produto> findByNomeAndDataFabricacaoAndFabricanteAndDescricao(String nome, LocalDate data, ParceiroNegocio fabricante,
                    String descricao);

}
