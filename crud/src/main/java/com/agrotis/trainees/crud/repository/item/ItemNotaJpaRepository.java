package com.agrotis.trainees.crud.repository.item;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;

@Repository
public interface ItemNotaJpaRepository extends JpaRepository<ItemNota, Long>{
	
	@Query("SELECT i.valorTotal FROM ItemNota i "
			+ "WHERE i.id = :idItem")
	BigDecimal getValorTotal(Long idItem);
	
	@Query("SELECT i.quantidade FROM ItemNota i "
			+ "WHERE i.id = :idItem")
	Integer getQuantidade(Long idItem);
}
