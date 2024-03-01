package com.agrotis.trainees.crud.service.parceiro.busca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.dto.parceiro.ParceiroRetornoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.mapper.parceiro.ParceiroMapper;
import com.agrotis.trainees.crud.repository.parceiro.ParceiroRepository;
import com.agrotis.trainees.crud.service.parceiro.ParceiroNegocioBuscaService;

@Component
public class ParceiroNegocioBuscaServiceImpl implements ParceiroNegocioBuscaService{
	
	private ParceiroRepository parceiroRepository;
	private ParceiroMapper parceiroMapper;
	
	@Autowired
	public ParceiroNegocioBuscaServiceImpl(ParceiroRepository parceiroRepository, ParceiroMapper parceiroMapper) {
		this.parceiroRepository = parceiroRepository;
		this.parceiroMapper = parceiroMapper;
	}

	@Override
	public ParceiroRetornoDto buscarPor(Long idParceiro) {
		ParceiroNegocio parceiro = parceiroRepository.buscarPor(idParceiro);
		return parceiroMapper.converterParaDto(parceiro);
	}

	@Override
	public Page<ParceiroRetornoDto> listarTodos(Pageable pageable) {
		Page<ParceiroNegocio> parceiros = parceiroRepository.buscarTodos(pageable);
		return parceiros.map((parceiro) -> parceiroMapper.converterParaDto(parceiro));
	}

}
