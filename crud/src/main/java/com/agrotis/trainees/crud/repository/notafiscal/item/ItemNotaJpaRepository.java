package com.agrotis.trainees.crud.repository.notafiscal.item;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;

@Repository
public interface ItemNotaJpaRepository extends JpaRepository<ItemNota, Long>{
	
	@Query("SELECT SUM(i.quantidade * i.precoUnitario) FROM ItemNota i "
			+ "WHERE i.cabecalhoNota = :cabecalhoNota")
	BigDecimal calcularValorTotal(CabecalhoNota cabecalhoNota);
}
