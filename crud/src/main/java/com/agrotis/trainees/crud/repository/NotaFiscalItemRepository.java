package com.agrotis.trainees.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.Produto;

public interface NotaFiscalItemRepository extends JpaRepository<NotaFiscalItem, Integer>{
	
	Optional<NotaFiscalItem> findByProduto(Produto produto);
}
