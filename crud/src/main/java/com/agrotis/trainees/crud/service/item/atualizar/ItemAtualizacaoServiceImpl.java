package com.agrotis.trainees.crud.service.item.atualizar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.agrotis.trainees.crud.dto.item.ItemAtualizacaoDto;
import com.agrotis.trainees.crud.dto.item.ItemRetornoDto;
import com.agrotis.trainees.crud.entity.ItemNota;
import com.agrotis.trainees.crud.mapper.item.ItemMapper;
import com.agrotis.trainees.crud.repository.item.ItemNotaRepository;
import com.agrotis.trainees.crud.service.item.ItemAtualizacaoService;
import com.agrotis.trainees.crud.util.ItemNotaFactory;
import com.agrotis.trainees.crud.util.SalvadorEmCascata;

@Component
public class ItemAtualizacaoServiceImpl implements ItemAtualizacaoService{

	private ItemMapper itemMapper;
	private ItemNotaFactory itemNotaFactory;
	private SalvadorEmCascata salvadorEmCascata;
	private List<ItemAtualizacaoRn> atualizacaoRns;
	
	@Autowired
	public ItemAtualizacaoServiceImpl(ItemMapper itemMapper, ItemNotaFactory itemNotaFactory, 
			SalvadorEmCascata salvadorEmCascata, List<ItemAtualizacaoRn> atualizacaoRns) {
		this.itemMapper = itemMapper;
		this.itemNotaFactory = itemNotaFactory;
		this.salvadorEmCascata = salvadorEmCascata;
		this.atualizacaoRns = atualizacaoRns;
	}

	@Override
	@Transactional(readOnly = false)
	public ItemRetornoDto atualizar(ItemAtualizacaoDto atualizacaoDto) {
		ItemNota itemClone = itemNotaFactory.criarClone(atualizacaoDto.getId());
		ItemNota itemOriginal = itemMapper.converterParaEntidade(atualizacaoDto);
		
		atualizacaoRns.forEach((rn) -> rn.operarSobre(itemOriginal, itemClone));
		
		salvadorEmCascata.salvar(itemOriginal);
		return itemMapper.converterParaDto(itemOriginal);
	}
}
