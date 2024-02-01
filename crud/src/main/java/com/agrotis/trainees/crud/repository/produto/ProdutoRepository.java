package com.agrotis.trainees.crud.repository.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.wrapper.JpaRepositoryWrapper;
import com.agrotis.trainees.crud.service.ProdutoService;

@Repository
public class ProdutoRepository extends JpaRepositoryWrapper<
										ProdutoService, 
										Produto, 
										Long, 
										ProdutoJpaRepository>{

	@Autowired
	public ProdutoRepository(ProdutoJpaRepository repository, 
			Class<Produto> entity, 
			Class<ProdutoService> service) {
		super(repository, entity, service);
	}
}
