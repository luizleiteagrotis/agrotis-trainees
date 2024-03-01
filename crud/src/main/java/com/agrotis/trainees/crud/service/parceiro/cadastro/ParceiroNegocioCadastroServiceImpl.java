package com.agrotis.trainees.crud.service.parceiro.cadastro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.dto.parceiro.ParceiroCadastroDto;
import com.agrotis.trainees.crud.dto.parceiro.ParceiroRetornoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.mapper.parceiro.ParceiroMapper;
import com.agrotis.trainees.crud.repository.parceiro.ParceiroRepository;
import com.agrotis.trainees.crud.service.parceiro.ParceiroNegocioCadastroService;

@Component
public class ParceiroNegocioCadastroServiceImpl implements ParceiroNegocioCadastroService{

	private List<ParceiroNegocioCadastroRn> cadastroRns;
	private ParceiroMapper parceiroMapper;
	private ParceiroRepository parceiroRepository;
	
	@Autowired
	public ParceiroNegocioCadastroServiceImpl(List<ParceiroNegocioCadastroRn> cadastroRns,
			ParceiroMapper parceiroMapper, ParceiroRepository parceiroRepository) {
		this.cadastroRns = cadastroRns;
		this.parceiroMapper = parceiroMapper;
		this.parceiroRepository = parceiroRepository;
	}

	@Override
	public ParceiroRetornoDto cadastrar(ParceiroCadastroDto cadastroDto) {
		ParceiroNegocio parceiro = parceiroMapper.converterParaEntidade(cadastroDto);
		
		for (int i = 0; i < cadastroRns.size(); i++) {
			ParceiroNegocioCadastroRn cadastroRn = cadastroRns.get(i);
			parceiro = cadastroRn.operarSobre(parceiro);
		}
		
		parceiroRepository.salvar(parceiro);
		return parceiroMapper.converterParaDto(parceiro);
	}
}
