package com.agrotis.trainees.crud.repository.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.wrapper.JpaRepositoryWrapper;

@Repository
public class ItemNotaRepository extends JpaRepositoryWrapper<
										ItemNota, 
										Long>{

	@Autowired
	public ItemNotaRepository(ItemNotaJpaRepository repository) {
		super(repository, nomeLogger(ItemNotaRepository.class));
	}
	
	public boolean existe(ItemNota item) {
		if (item.getId() == null) return false;
		return REPOSITORY.existsById(item.getId());
	}
	
	public boolean existeInstanciaCom(Produto produto, CabecalhoNota cabecalho) {
		ItemNotaJpaRepository repository = (ItemNotaJpaRepository) REPOSITORY;
		return repository.existsByProdutoAndCabecalhoNota(produto, cabecalho);
	}
}
