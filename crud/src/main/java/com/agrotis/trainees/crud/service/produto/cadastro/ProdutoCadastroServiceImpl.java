package com.agrotis.trainees.crud.service.produto.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.dto.produto.ProdutoCadastroDto;
import com.agrotis.trainees.crud.dto.produto.ProdutoRetornoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.mapper.produto.ProdutoMapper;
import com.agrotis.trainees.crud.repository.produto.ProdutoRepository;
import com.agrotis.trainees.crud.service.produto.ProdutoCadastroService;

@Component
public class ProdutoCadastroServiceImpl implements ProdutoCadastroService {
	
	private ProdutoMapper produtoMapper;
	private ProdutoRepository produtoRepository;
	
	@Autowired
	public ProdutoCadastroServiceImpl(ProdutoMapper produtoMapper,
			ProdutoRepository produtoRepository) {
		this.produtoMapper = produtoMapper;
		this.produtoRepository = produtoRepository;
	}

	@Override
	public ProdutoRetornoDto cadastrar(ProdutoCadastroDto cadastroDto) {
		Produto produto = produtoMapper.converterParaEntidade(cadastroDto);
		String nome = produto.getNome();
		String descricao = produto.getDescricao();
		ParceiroNegocio fabricante = produto.getFabricante();
		if (produtoRepository.existeInstanciaCom(nome, descricao, fabricante)) {
			String mensagem = "Ja existe produto com nome {" + nome + "} e"
					+ " descricao {" + descricao + "} do"
					+ " fabricante {" + fabricante.getNome() + "}";
			throw new ProdutoCadastroRnException(mensagem);
		} 
		produtoRepository.salvar(produto);
		return produtoMapper.converterParaDto(produto);
	}

}
