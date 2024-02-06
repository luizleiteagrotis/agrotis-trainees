package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.Produto;

public interface NotaFiscalItemRepository extends JpaRepository<NotaFiscalItem, Integer> {

    Optional<NotaFiscalItem> findByNotaFiscalId(NotaFiscal notaFiscal);

    Optional<NotaFiscalItem> findByNotaFiscal(NotaFiscal notaFiscal);

    Optional<NotaFiscalItem> findByProduto(Produto produto);

}