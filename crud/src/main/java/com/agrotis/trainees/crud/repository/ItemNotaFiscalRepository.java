package com.agrotis.trainees.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;

@Repository
public interface ItemNotaFiscalRepository  extends JpaRepository<ItemNotaFiscal, Integer>{

}
