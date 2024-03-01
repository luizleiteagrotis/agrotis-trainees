package com.agrotis.trainees.crud.service.parceiro.delecao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.repository.parceiro.ParceiroRepository;
import com.agrotis.trainees.crud.service.parceiro.ParceiroNegocioDelecaoService;

@Component
public class ParceiroNegocioDelecaoServiceImpl implements ParceiroNegocioDelecaoService{

	private ParceiroRepository parceiroRepository;
	
	@Autowired	
	public ParceiroNegocioDelecaoServiceImpl(ParceiroRepository parceiroRepository) {
		this.parceiroRepository = parceiroRepository;
	}

	@Override
	public void deletarPor(Long idParceiro) {
		parceiroRepository.deletar(idParceiro);
	}
}
