package com.agrotis.trainees.crud.service.produto;

import com.agrotis.trainees.crud.dto.produto.ProdutoAtualizacaoDto;
import com.agrotis.trainees.crud.dto.produto.ProdutoRetornoDto;

public interface ProdutoAtualizacaoService {

	ProdutoRetornoDto atualizar(ProdutoAtualizacaoDto atualizacaoDto);
}
