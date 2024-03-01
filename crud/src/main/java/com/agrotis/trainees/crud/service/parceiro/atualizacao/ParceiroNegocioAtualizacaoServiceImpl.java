package com.agrotis.trainees.crud.service.parceiro.atualizacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.agrotis.trainees.crud.dto.parceiro.ParceiroAtualizacaoDto;
import com.agrotis.trainees.crud.dto.parceiro.ParceiroRetornoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.mapper.parceiro.ParceiroMapper;
import com.agrotis.trainees.crud.repository.parceiro.ParceiroRepository;
import com.agrotis.trainees.crud.service.parceiro.ParceiroNegocioAtualizacaoService;

@Component
public class ParceiroNegocioAtualizacaoServiceImpl implements ParceiroNegocioAtualizacaoService {

	private AtualizadorParceiroRn atualizadorParceiroRn;
	private ParceiroRepository parceiroRepository;
	private ParceiroMapper parceiroMapper;
	
	@Autowired
	public ParceiroNegocioAtualizacaoServiceImpl(AtualizadorParceiroRn atualizadorParceiro, 
			ParceiroRepository parceiroRepository, ParceiroMapper parceiroMapper) {
		this.atualizadorParceiroRn = atualizadorParceiro;
		this.parceiroRepository = parceiroRepository;
		this.parceiroMapper = parceiroMapper;
	}

	@Override
	@Transactional(readOnly = false)
	public ParceiroRetornoDto atualizar(ParceiroAtualizacaoDto atualizacaoDto) {
		ParceiroNegocio parceiro = atualizadorParceiroRn.operarSobre(atualizacaoDto);
		parceiro = parceiroRepository.salvar(parceiro);
		return parceiroMapper.converterParaDto(parceiro);
	}
}
