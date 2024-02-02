package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.Produto;

@Repository
public interface ItemNotaFiscalRepository extends JpaRepository<ItemNotaFiscal, Integer> {
    List<ItemNotaFiscal> findByProduto(Produto produto);

    Optional<ItemNotaFiscal> findByNotaFiscal(NotaFiscal notaFiscal);

    List<ItemNotaFiscal> findByQuantidade(double quantidade);

    List<ItemNotaFiscal> findByPrecoUnitario(double precoUnitario);

    List<ItemNotaFiscal> findByValorTotal(double valorTotal);
}
