package com.agrotis.trainees.crud.service.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.dto.produto.ProdutoAtualizacaoDto;
import com.agrotis.trainees.crud.dto.produto.ProdutoCadastroDto;
import com.agrotis.trainees.crud.dto.produto.ProdutoRetornoDto;

@Service
public class ProdutoService {
	
	private ProdutoCadastroService cadastroService;
	private ProdutoBuscaService buscaService;
	private ProdutoAtualizacaoService atualizacaoService;
	private ProdutoDelecaoService delecaoService;
	
	@Autowired
	public ProdutoService(ProdutoCadastroService cadastroService, ProdutoBuscaService buscaService,
			ProdutoAtualizacaoService atualizacaoService, ProdutoDelecaoService delecaoService) {
		this.cadastroService = cadastroService;
		this.buscaService = buscaService;
		this.atualizacaoService = atualizacaoService;
		this.delecaoService = delecaoService;
	}

	public ProdutoRetornoDto cadastrar(ProdutoCadastroDto cadastroDto) {
		return cadastroService.cadastrar(cadastroDto);
	}
	
	public ProdutoRetornoDto buscarPor(Long idProduto) {
		return buscaService.buscarPor(idProduto);
	}
	
	public Page<ProdutoRetornoDto> listarTodos(Pageable pageable) {
		return buscaService.listarTodos(pageable);
	}
	
	public ProdutoRetornoDto atualizar(ProdutoAtualizacaoDto atualizacaoDto) {
		return atualizacaoService.atualizar(atualizacaoDto);	
	}
	
	public void deletarPor(Long idProduto) {
		delecaoService.deletarPor(idProduto);
	}
}
