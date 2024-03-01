package com.agrotis.trainees.crud.service.produto.delecao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.repository.produto.ProdutoRepository;
import com.agrotis.trainees.crud.service.produto.ProdutoDelecaoService;

@Component
public class ProdutoDelecaoServiceImpl implements ProdutoDelecaoService {

	private ProdutoRepository produtoRepository;
	
	@Autowired
	public ProdutoDelecaoServiceImpl(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	@Override
	public void deletarPor(Long idProduto) {
		produtoRepository.deletar(idProduto);
	}

}
