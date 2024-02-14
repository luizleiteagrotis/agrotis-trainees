package com.agrotis.trainees.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.dto.parceiro.ParceiroAtualizacaoDto;
import com.agrotis.trainees.crud.dto.parceiro.ParceiroCadastroDto;
import com.agrotis.trainees.crud.dto.parceiro.ParceiroRetornoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.mapper.parceiro.ParceiroMapper;
import com.agrotis.trainees.crud.repository.parceiro.ParceiroRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ParceiroNegocioService {
	
	private ParceiroMapper mapper;
	private ParceiroRepository repository;
	
	@Autowired
	public ParceiroNegocioService(ParceiroRepository repository, ParceiroMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	public ParceiroRetornoDto salvar(ParceiroCadastroDto criacaoDto) {
		ParceiroNegocio parceiro = mapper.converterParaEntidade(criacaoDto);
		parceiro = repository.salvar(parceiro);
		return mapper.converterParaDto(parceiro);
	}
	
	public ParceiroRetornoDto buscarPorId(Long idParceiro) {
		ParceiroNegocio parceiro = repository.buscarPor(idParceiro);
		return mapper.converterParaDto(parceiro);
	}
	
	public Page<ParceiroRetornoDto> listarTodos(Pageable pageable) {
		return repository.buscarTodos(pageable).map((parceiro) -> mapper.converterParaDto(parceiro));
	}
	
	public ParceiroRetornoDto atualizar(ParceiroAtualizacaoDto atualizacaoDto) {
		Long idParceiro = atualizacaoDto.getId();
		ParceiroNegocio parceiro = repository.buscarPor(idParceiro);
		atualizarParceiro(atualizacaoDto, parceiro);
		repository.salvar(parceiro);
		return mapper.converterParaDto(parceiro);
	}
	
	public void deletar(Long idParceiro) {
		repository.deletar(idParceiro);
	}
	
	private void atualizarParceiro(ParceiroAtualizacaoDto atualizacaoDto, ParceiroNegocio parceiro) {
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
			parceiro.setTelefone(atualizacaoDto.getTelefone());
		}
	}
}
