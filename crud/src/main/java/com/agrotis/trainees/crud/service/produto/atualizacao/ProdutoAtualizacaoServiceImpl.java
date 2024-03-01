package com.agrotis.trainees.crud.service.produto.atualizacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.dto.produto.ProdutoAtualizacaoDto;
import com.agrotis.trainees.crud.dto.produto.ProdutoRetornoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.mapper.produto.ProdutoMapper;
import com.agrotis.trainees.crud.repository.produto.ProdutoRepository;
import com.agrotis.trainees.crud.service.produto.ProdutoAtualizacaoService;

@Component
public class ProdutoAtualizacaoServiceImpl implements ProdutoAtualizacaoService{

	private ProdutoMapper produtoMapper;
	private ProdutoRepository produtoRepository;
	
	@Autowired
	public ProdutoAtualizacaoServiceImpl(ProdutoMapper produtoMapper, ProdutoRepository produtoRepository) {
		this.produtoMapper = produtoMapper;
		this.produtoRepository = produtoRepository;
	}

	@Override
	public ProdutoRetornoDto atualizar(ProdutoAtualizacaoDto atualizacaoDto) {
		Long idProduto = atualizacaoDto.getId();
		Produto produto = produtoRepository.buscarPor(idProduto);
		String nome = atualizacaoDto.getNome();
		String descricao = atualizacaoDto.getDescricao();
		ParceiroNegocio fabricante = produto.getFabricante();
		Long naoIdProduto = idProduto;
		if (produtoRepository.existeInstanciaCom(nome, descricao, fabricante, naoIdProduto)) {
			String mensagem = "Ja existe produto com nome {" + nome + "} e"
							+ " descricao {" + descricao + "} do"
							+ " fabricante {" + fabricante.getNome() + "}";
			throw new ProdutoAtualizacaoRnException(mensagem);
		}
		produto = produtoMapper.converterParaEntidade(atualizacaoDto);
		produto = produtoRepository.salvar(produto);
		return produtoMapper.converterParaDto(produto);
	}
}
