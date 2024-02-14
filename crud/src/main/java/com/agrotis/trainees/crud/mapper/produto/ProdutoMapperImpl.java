package com.agrotis.trainees.crud.mapper.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.dto.produto.ProdutoCadastroDto;
import com.agrotis.trainees.crud.dto.produto.ProdutoRetornoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.parceiro.ParceiroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProdutoMapperImpl implements ProdutoMapper {

	private ObjectMapper mapper;
	private ParceiroRepository parceiroRepository;
	
	@Autowired
	public ProdutoMapperImpl(ObjectMapper mapper, ParceiroRepository parceiroRepository) {
		this.mapper = mapper;
		this.parceiroRepository = parceiroRepository;
	}
	
	@Override
	public Produto converterParaEntidade(ProdutoCadastroDto cadastroDto) {
		Produto produto = mapper.convertValue(cadastroDto, Produto.class);
		Long idFabricante = cadastroDto.getIdFabricante();
		ParceiroNegocio fabricante = parceiroRepository.buscarPor(idFabricante);
		produto.setFabricante(fabricante);
		produto.setEstoque(0);
		return produto;
	}

	@Override
	public ProdutoRetornoDto converterParaDto(Produto produto) {
		ProdutoRetornoDto retornoDto = mapper.convertValue(produto, ProdutoRetornoDto.class);
		ParceiroNegocio fabricante = produto.getFabricante();
		retornoDto.setIdFabricante(fabricante.getId());
		return retornoDto;
	}
}
