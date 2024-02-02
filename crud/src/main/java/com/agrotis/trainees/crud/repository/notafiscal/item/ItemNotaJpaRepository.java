package com.agrotis.trainees.crud.repository.notafiscal.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.ItemNota;

@Repository
public interface ItemNotaJpaRepository extends JpaRepository<ItemNota, Long>{

}
