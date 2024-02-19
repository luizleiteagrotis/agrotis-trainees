package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.Produto;

@Repository
public interface ItemNotaFiscalRepository extends JpaRepository<ItemNotaFiscal, Integer> {

    boolean existsByProdutoAndNotaFiscal(Produto produto, NotaFiscal notaFiscal);

    boolean existsByProdutoAndNotaFiscalAndIdNot(Produto produto, NotaFiscal notaFiscal, Integer id);

    ItemNotaFiscal findByProdutoAndNotaFiscal(Produto produto, NotaFiscal notaFiscal);

    List<ItemNotaFiscal> findAllByProduto(Produto produto);

}
