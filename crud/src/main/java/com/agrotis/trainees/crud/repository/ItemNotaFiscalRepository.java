package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.Produto;

@Repository
public interface ItemNotaFiscalRepository extends JpaRepository<ItemNotaFiscal, Integer> {
    List<ItemNotaFiscal> findByProduto(Produto produto);

    List<ItemNotaFiscal> findByNotaFiscal(NotaFiscal notaFiscal);

    List<ItemNotaFiscal> findByQuantidade(BigDecimal quantidade);

    List<ItemNotaFiscal> findByPrecoUnitario(BigDecimal precoUnitario);

    List<ItemNotaFiscal> findByValorTotal(BigDecimal valorTotal);

    List<ItemNotaFiscal> findByNotaFiscalAndProduto(NotaFiscal notaFiscal, Produto produto);
}
