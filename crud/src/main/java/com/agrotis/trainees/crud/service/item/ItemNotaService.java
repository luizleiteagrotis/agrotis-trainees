package com.agrotis.trainees.crud.service.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.dto.item.ItemAtualizacaoDto;
import com.agrotis.trainees.crud.dto.item.ItemCadastroDto;
import com.agrotis.trainees.crud.dto.item.ItemRetornoDto;

@Service
public class ItemNotaService {
	
	private final ItemCadastroService CADASTRO_SERVICE;
	private final ItemBuscaService BUSCA_SERVICE;
	private final ItemAtualizacaoService ATUALIZACAO_SERVICE;
	private final ItemDelecaoService DELECAO_SERVICE;

	@Autowired
	public ItemNotaService(ItemCadastroService cadastroService,
			ItemBuscaService buscaService,
			ItemAtualizacaoService atualizacaoService,
			ItemDelecaoService delecaoService) {
		CADASTRO_SERVICE = cadastroService;
		BUSCA_SERVICE = buscaService;
		ATUALIZACAO_SERVICE = atualizacaoService;
		DELECAO_SERVICE = delecaoService;
	}

	public ItemRetornoDto cadastrar(ItemCadastroDto cadastroDto) {
		return CADASTRO_SERVICE.cadastrar(cadastroDto);
	}
	 
	public ItemRetornoDto buscarPor(Long id) {
		return BUSCA_SERVICE.buscarPor(id);
	}
		
	public Page<ItemRetornoDto> listarTodos(Pageable pageable) {
		return BUSCA_SERVICE.listarTodos(pageable);
	}
	
	public ItemRetornoDto atualizar(ItemAtualizacaoDto atualizacaoDto) {
		return ATUALIZACAO_SERVICE.atualizar(atualizacaoDto);
	}
	
	public void deletar(Long id) {
		DELECAO_SERVICE.deletar(id);
	}
}
