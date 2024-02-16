package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.Produto;

@Repository
public interface NotaFiscalItemRepository extends JpaRepository<NotaFiscalItem, Integer> {

    Optional<NotaFiscalItem> findByProduto(Produto produto);

    NotaFiscalItem findByProdutoAndIdNota(Produto produto, NotaFiscal idNota);

    Boolean existsByProdutoAndIdNota(Produto produto, NotaFiscal idNota);

    @Query("SELECT nfi FROM NotaFiscalItem nfi WHERE nfi.idNota = :id_nota")
    List<NotaFiscalItem> findByNota(@Param("id_nota") NotaFiscal idNota);
}
