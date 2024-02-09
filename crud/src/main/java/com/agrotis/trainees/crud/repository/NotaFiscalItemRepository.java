package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.ItemNota;

@Repository
public interface NotaFiscalItemRepository extends JpaRepository<ItemNota, Integer> {


}
