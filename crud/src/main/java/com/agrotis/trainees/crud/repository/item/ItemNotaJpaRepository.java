package com.agrotis.trainees.crud.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;

@Repository
public interface ItemNotaJpaRepository extends JpaRepository<ItemNota, Long>{

	boolean existsByProdutoAndCabecalhoNota(Produto produto, CabecalhoNota cabecalhoNota);
}
