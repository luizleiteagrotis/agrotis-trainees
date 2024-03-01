package com.agrotis.trainees.crud.service.produto.busca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.dto.produto.ProdutoRetornoDto;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.mapper.produto.ProdutoMapper;
import com.agrotis.trainees.crud.repository.produto.ProdutoRepository;
import com.agrotis.trainees.crud.service.produto.ProdutoBuscaService;

@Component
public class ProdutoBuscaServiceImpl implements ProdutoBuscaService{

	private ProdutoRepository produtoRepository;
	private ProdutoMapper produtoMapper;
	
	@Autowired
	public ProdutoBuscaServiceImpl(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper) {
		this.produtoRepository = produtoRepository;
		this.produtoMapper = produtoMapper;
	}
	
	@Override
	public ProdutoRetornoDto buscarPor(Long idProduto) {
		Produto produto = produtoRepository.buscarPor(idProduto);
		return produtoMapper.converterParaDto(produto);
	}

	@Override
	public Page<ProdutoRetornoDto> listarTodos(Pageable pageable) {
		Page<Produto> produtos = produtoRepository.buscarTodos(pageable);
		return produtos.map((p) -> produtoMapper.converterParaDto(p));
	}

}
