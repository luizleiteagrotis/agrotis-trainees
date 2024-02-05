package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.Produto;

@Repository
public interface NotaFiscalItemRepository extends JpaRepository<NotaFiscalItem, Integer> {

    Optional<NotaFiscalItem> findByProduto(Produto produto);

    @Query("SELECT SUM(ni.total) FROM NotaFiscalItem ni")
    Optional<Double> sumAllTotal();
}
