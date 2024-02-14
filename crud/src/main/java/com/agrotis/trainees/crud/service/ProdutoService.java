package com.agrotis.trainees.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.dto.produto.ProdutoAtualizacaoDto;
import com.agrotis.trainees.crud.dto.produto.ProdutoCadastroDto;
import com.agrotis.trainees.crud.dto.produto.ProdutoRetornoDto;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.mapper.produto.ProdutoMapper;
import com.agrotis.trainees.crud.repository.produto.ProdutoRepository;

@Service
public class ProdutoService {
	
	private ProdutoRepository repository;
	private ProdutoMapper mapper;
	
	@Autowired
	public ProdutoService(ProdutoRepository repository, ProdutoMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	public ProdutoRetornoDto salvar(ProdutoCadastroDto cadastroDto) {
		Produto produto = mapper.converterParaEntidade(cadastroDto);
		produto = repository.salvar(produto);
		return mapper.converterParaDto(produto);
	}
	
	public ProdutoRetornoDto buscarPor(Long idProduto) {
		Produto produto = repository.buscarPor(idProduto);
		return mapper.converterParaDto(produto);
	}
	
	public Page<ProdutoRetornoDto> listarTodos(Pageable pageable) {
		return repository.buscarTodos(pageable).map((produto) -> mapper.converterParaDto(produto));
	}
	
	public ProdutoRetornoDto atualizar(ProdutoAtualizacaoDto atualizacaoDto) {
		Long idProduto = atualizacaoDto.getId();
		Produto produto = repository.buscarPor(idProduto);
		atualizarProduto(atualizacaoDto, produto);
		repository.salvar(produto);
		return mapper.converterParaDto(produto);
	}
	
	public void deletar(Long idProduto) {
		repository.deletar(idProduto);
	}
	
	public Integer pegarEstoque(Long idProduto) {
		Integer quantidade = repository.getEstoque(idProduto);;
		if (quantidade == null) return 0;
		return quantidade;
	}
	
	private void atualizarProduto(ProdutoAtualizacaoDto atualizacaoDto, Produto produto) {
		if (atualizacaoDto.getNome() != null) {
			produto.setNome(atualizacaoDto.getNome());
		}
		if (atualizacaoDto.getDescricao() != null) {
			produto.setDescricao(atualizacaoDto.getDescricao());
		}
		if (atualizacaoDto.getDataFabricacao() != null) {
			produto.setDataFabricacao(atualizacaoDto.getDataFabricacao());
		}
		if (atualizacaoDto.getDataValidade() != null) {
			produto.setDataValidade(atualizacaoDto.getDataValidade());
		}
	}
}
