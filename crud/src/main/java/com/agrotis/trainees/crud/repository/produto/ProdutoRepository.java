package com.agrotis.trainees.crud.repository.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.wrapper.JpaRepositoryWrapper;

@Repository
public class ProdutoRepository extends JpaRepositoryWrapper<
										Produto, 
										Long>{

	@Autowired
	public ProdutoRepository(ProdutoJpaRepository repository) {
		super(repository, nomeLogger(ProdutoRepository.class));
	}
	
	public Integer getEstoque(Long idProduto) {
		ProdutoJpaRepository repository = (ProdutoJpaRepository) REPOSITORY;
		return repository.getEstoque(idProduto);
	}
}
