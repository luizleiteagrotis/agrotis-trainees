package com.agrotis.trainees.crud.mapper.parceiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.dto.parceiro.ParceiroAtualizacaoDto;
import com.agrotis.trainees.crud.dto.parceiro.ParceiroCadastroDto;
import com.agrotis.trainees.crud.dto.parceiro.ParceiroRetornoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.parceiro.ParceiroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ParceiroMapperImpl implements ParceiroMapper {
	
	private ObjectMapper mapper;
	private ParceiroRepository parceiroRepository;
	
	@Autowired
	public ParceiroMapperImpl(ObjectMapper mapper, ParceiroRepository parceiroRepository) {
		this.mapper = mapper;
		this.parceiroRepository = parceiroRepository;
	}

	@Override
	public ParceiroNegocio converterParaEntidade(ParceiroCadastroDto cadastroDto) {
		return mapper.convertValue(cadastroDto, ParceiroNegocio.class);
	}

	@Override
	public ParceiroRetornoDto converterParaDto(ParceiroNegocio parceiro) {
		return mapper.convertValue(parceiro, ParceiroRetornoDto.class);
	}

	@Override
	public ParceiroNegocio converterParaEntidade(ParceiroAtualizacaoDto atualizacaoDto) {
		Long idParceiro = atualizacaoDto.getId();
		ParceiroNegocio parceiro = parceiroRepository.buscarPor(idParceiro);
		if (atualizacaoDto.getNome() != null) {
			parceiro.setNome(atualizacaoDto.getNome());
		}
		if (atualizacaoDto.getInscricaoFiscal() != null) {
			parceiro.setInscricaoFiscal(atualizacaoDto.getInscricaoFiscal());
		}
		if (atualizacaoDto.getEndereco() != null) {
			parceiro.setEndereco(atualizacaoDto.getEndereco());
		}
		if (atualizacaoDto.getTelefone() != null) {
			parceiro.setEndereco(atualizacaoDto.getEndereco());
		}
		return parceiro;
	}
}
