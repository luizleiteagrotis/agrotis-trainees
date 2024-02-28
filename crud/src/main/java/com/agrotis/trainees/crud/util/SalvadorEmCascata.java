package com.agrotis.trainees.crud.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.cabecalho.CabecalhoNotaRepository;
import com.agrotis.trainees.crud.repository.item.ItemNotaRepository;
import com.agrotis.trainees.crud.repository.parceiro.ParceiroRepository;
import com.agrotis.trainees.crud.repository.produto.ProdutoRepository;

@Component
public class SalvadorEmCascata {

	private ParceiroRepository parceiroRepository;
	private ProdutoRepository produtoRepository;
	private CabecalhoNotaRepository cabecalhoNotaRepository;
	private ItemNotaRepository itemNotaRepository;
	
	@Autowired
	public SalvadorEmCascata(ParceiroRepository parceiroRepository, ProdutoRepository produtoRepository,
			CabecalhoNotaRepository cabecalhoNotaRepository, ItemNotaRepository itemNotaRepository) {
		this.parceiroRepository = parceiroRepository;
		this.produtoRepository = produtoRepository;
		this.cabecalhoNotaRepository = cabecalhoNotaRepository;
		this.itemNotaRepository = itemNotaRepository;
	}
	
	@Transactional(readOnly = false)
	public ItemNota salvar(ItemNota item) {
		Produto produto = item.getProduto();
		ParceiroNegocio fabricante = produto.getFabricante();
		produtoRepository.salvar(produto);
		parceiroRepository.salvar(fabricante);
		
		CabecalhoNota cabecalho = item.getCabecalhoNota();
		ParceiroNegocio parceiroNegocio = cabecalho.getParceiro();
		cabecalhoNotaRepository.salvar(cabecalho);
		parceiroRepository.salvar(parceiroNegocio);
		
		return itemNotaRepository.salvar(item);
	}
}
