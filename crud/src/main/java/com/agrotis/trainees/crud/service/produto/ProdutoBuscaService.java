package com.agrotis.trainees.crud.service.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.agrotis.trainees.crud.dto.produto.ProdutoRetornoDto;

public interface ProdutoBuscaService {

	ProdutoRetornoDto buscarPor(Long idProduto);
	
	Page<ProdutoRetornoDto> listarTodos(Pageable pageable);
}
