package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.Produto;

@Repository
public interface NotaFiscalItemRepository extends JpaRepository<NotaFiscalItem, Integer> {

    Optional<NotaFiscalItem> findByProduto(Produto produto);

    NotaFiscalItem findByProdutoAndIdNota(Produto produto, NotaFiscal idNota);
}
