package com.agrotis.trainees.crud.mapper.produto;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.dto.produto.ProdutoAtualizacaoDto;
import com.agrotis.trainees.crud.dto.produto.ProdutoCadastroDto;
import com.agrotis.trainees.crud.dto.produto.ProdutoRetornoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.parceiro.ParceiroRepository;
import com.agrotis.trainees.crud.repository.produto.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProdutoMapperImpl implements ProdutoMapper {

	private ObjectMapper mapper;
	private ParceiroRepository parceiroRepository;
	private ProdutoRepository produtoRepository;
	
	@Autowired
	public ProdutoMapperImpl(ObjectMapper mapper, ParceiroRepository parceiroRepository, ProdutoRepository produtoRepository) {
		this.mapper = mapper;
		this.parceiroRepository = parceiroRepository;
		this.produtoRepository = produtoRepository;
	}
	
	@Override
	public Produto converterParaEntidade(ProdutoCadastroDto cadastroDto) {
		Produto produto = mapper.convertValue(cadastroDto, Produto.class);
		Long idFabricante = cadastroDto.getIdFabricante();
		if (idFabricante != null) {
			ParceiroNegocio fabricante = parceiroRepository.buscarPor(idFabricante);
			produto.setFabricante(fabricante);
		}
		produto.setEstoque(0);
		produto.setCustoTotal(BigDecimal.ZERO);
		produto.setCustoMedio(BigDecimal.ZERO);
		return produto;
	}

	@Override
	public ProdutoRetornoDto converterParaDto(Produto produto) {
		ProdutoRetornoDto retornoDto = mapper.convertValue(produto, ProdutoRetornoDto.class);
		ParceiroNegocio fabricante = produto.getFabricante();
		retornoDto.setIdFabricante(fabricante.getId());
		return retornoDto;
	}

	@Override
	public Produto converterParaEntidade(ProdutoAtualizacaoDto atualizacaoDto) {
		Long idProduto = atualizacaoDto.getId();
		Produto produto = produtoRepository.buscarPor(idProduto);
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
		return produto;
	}
}
