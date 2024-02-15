package com.agrotis.trainees.crud.mapper.cabecalho;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.dto.cabecalho.CabecalhoCadastroDto;
import com.agrotis.trainees.crud.dto.cabecalho.CabecalhoRetornoDto;
import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.parceiro.ParceiroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CabecalhoMapperImpl implements CabecalhoMapper {

	private ObjectMapper mapper;
	private ParceiroRepository parceiroRepository;
	
	@Autowired
	public CabecalhoMapperImpl(ObjectMapper mapper, ParceiroRepository parceiroRepository) {
		this.mapper = mapper;
		this.parceiroRepository = parceiroRepository;
	}

	@Override
	public CabecalhoNota converterParaEntidade(CabecalhoCadastroDto cadastroDto) {
		CabecalhoNota cabecalho = mapper.convertValue(cadastroDto, CabecalhoNota.class);
		Long idParceiro = cadastroDto.getIdParceiro();
		if (idParceiro != null) {
			ParceiroNegocio parceiro = parceiroRepository.buscarPor(idParceiro);
			cabecalho.setParceiro(parceiro);
		}
		cabecalho.setValorTotal(BigDecimal.ZERO);
		return cabecalho;
	}

	@Override
	public CabecalhoRetornoDto converterParaDto(CabecalhoNota cabecalho) {
		CabecalhoRetornoDto retornoDto = mapper.convertValue(cabecalho, CabecalhoRetornoDto.class);
		Long idParceiro = cabecalho.getParceiro().getId();
		retornoDto.setIdParceiro(idParceiro);
		return retornoDto;
	}
}
