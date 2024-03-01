package com.agrotis.trainees.crud.repository.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
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
	
	public boolean existeInstanciaCom(String nome, String descricao, ParceiroNegocio fabricante) {
		ProdutoJpaRepository repository = (ProdutoJpaRepository) REPOSITORY; 
		return repository.existsByNomeAndDescricaoAndFabricante(nome, descricao, fabricante);
	}
	
	public boolean existeInstanciaCom(String nome, String descricao, ParceiroNegocio fabricante, Long naoIdProduto) {
		ProdutoJpaRepository repository = (ProdutoJpaRepository) REPOSITORY; 
		return repository.existsByNomeAndDescricaoAndFabricanteAndIdNot(nome, descricao, fabricante, naoIdProduto);
	}
}
