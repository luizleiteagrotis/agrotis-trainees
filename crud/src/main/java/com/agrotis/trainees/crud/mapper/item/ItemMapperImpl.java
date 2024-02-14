package com.agrotis.trainees.crud.mapper.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.dto.item.ItemCadastroDto;
import com.agrotis.trainees.crud.dto.item.ItemRetornoDto;
import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.cabecalho.CabecalhoNotaRepository;
import com.agrotis.trainees.crud.repository.produto.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ItemMapperImpl implements ItemMapper {

	private ObjectMapper mapper;
	private CabecalhoNotaRepository cabecalhoRepository;
	private ProdutoRepository produtoRepository;
	
	@Autowired
	public ItemMapperImpl(ObjectMapper mapper, CabecalhoNotaRepository cabecalhoRepository, ProdutoRepository produtoRepository) {
		this.mapper = mapper;
		this.cabecalhoRepository = cabecalhoRepository;
		this.produtoRepository = produtoRepository;
	}

	@Override
	public ItemNota converterParaEntidade(ItemCadastroDto cadastroDto) {
		ItemNota item = mapper.convertValue(cadastroDto, ItemNota.class);
		Long idProduto = cadastroDto.getIdProduto();
		Produto produto = produtoRepository.buscarPor(idProduto);
		item.setProduto(produto);
		Long idCabecalho = cadastroDto.getIdCabecalho();
		CabecalhoNota cabecalho = cabecalhoRepository.buscarPor(idCabecalho);
		item.setCabecalhoNota(cabecalho);
		return item;
	}

	@Override
	public ItemRetornoDto converterParaDto(ItemNota item) {
		ItemRetornoDto retornoDto = mapper.convertValue(item, ItemRetornoDto.class);
		Produto produto = item.getProduto();
		retornoDto.setIdProduto(produto.getId());
		CabecalhoNota cabecalho = item.getCabecalhoNota();
		retornoDto.setIdCabecalho(cabecalho.getId());
		return retornoDto;
	}
}
