package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.Produto;

@Repository
public interface ItemNotaFiscalRepository extends JpaRepository<ItemNotaFiscal, Integer> {

    Optional<ItemNotaFiscal> findByProduto(Produto produto);

}
