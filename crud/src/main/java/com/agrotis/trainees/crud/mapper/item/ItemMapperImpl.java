package com.agrotis.trainees.crud.mapper.item;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.dto.item.ItemAtualizacaoDto;
import com.agrotis.trainees.crud.dto.item.ItemCadastroDto;
import com.agrotis.trainees.crud.dto.item.ItemRetornoDto;
import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.cabecalho.CabecalhoNotaRepository;
import com.agrotis.trainees.crud.repository.item.ItemNotaRepository;
import com.agrotis.trainees.crud.repository.produto.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ItemMapperImpl implements ItemMapper {

	private ObjectMapper mapper;
	private CabecalhoNotaRepository cabecalhoRepository;
	private ProdutoRepository produtoRepository;
	private ItemNotaRepository itemRepository;
	
	@Autowired
	public ItemMapperImpl(ObjectMapper mapper,
			Validator validator,
			CabecalhoNotaRepository cabecalhoRepository, 
			ProdutoRepository produtoRepository,
			ItemNotaRepository itemRepository) {
		this.mapper = mapper;
		this.cabecalhoRepository = cabecalhoRepository;
		this.produtoRepository = produtoRepository;
		this.itemRepository = itemRepository;
	}

	@Override
	public ItemNota converterParaEntidade(ItemCadastroDto cadastroDto) {
		ItemNota item = mapper.convertValue(cadastroDto, ItemNota.class);
		item.setProduto(buscarProduto(cadastroDto));
		item.setCabecalhoNota(buscarCabecalho(cadastroDto));
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
	
	@Override
	public ItemNota converterParaEntidade(ItemAtualizacaoDto atualizacaoDto) {
		Long idItem = atualizacaoDto.getId();
		ItemNota item = itemRepository.buscarPor(idItem);
		if (atualizacaoDto.getPrecoUnitario() != null) {
			item.setPrecoUnitario(atualizacaoDto.getPrecoUnitario());
		}
		if (atualizacaoDto.getQuantidade() != null) {
			item.setQuantidade(atualizacaoDto.getQuantidade());
		}
		return item;
	}
	
	private Produto buscarProduto(ItemCadastroDto cadastroDto) {
		Produto produto = null;
		Long idProduto = cadastroDto.getIdProduto();
		if (informado(idProduto)) {
			produto = produtoRepository.buscarPor(idProduto);
		}
		return produto;
	}
	
	private CabecalhoNota buscarCabecalho(ItemCadastroDto cadastroDto) {
		CabecalhoNota cabecalho = null;
		Long idCabecalho = cadastroDto.getIdCabecalho();
		if (informado(idCabecalho)) {
			cabecalho = cabecalhoRepository.buscarPor(idCabecalho);
		}
		return cabecalho;
	}
	
	private boolean informado(Long valor) {
		return valor != null;
	}
}
