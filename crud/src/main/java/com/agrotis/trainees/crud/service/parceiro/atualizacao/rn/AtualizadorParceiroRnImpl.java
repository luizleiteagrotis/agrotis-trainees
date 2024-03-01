package com.agrotis.trainees.crud.service.parceiro.atualizacao.rn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.dto.parceiro.ParceiroAtualizacaoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.mapper.parceiro.ParceiroMapper;
import com.agrotis.trainees.crud.repository.parceiro.ParceiroRepository;
import com.agrotis.trainees.crud.service.parceiro.atualizacao.AtualizadorParceiroRn;
import com.agrotis.trainees.crud.service.parceiro.atualizacao.ParceiroNegocioAtualizacaoRnException;

@Component
public class AtualizadorParceiroRnImpl implements AtualizadorParceiroRn {

	private ParceiroMapper parceiroMapper;
	private ParceiroRepository parceiroRepository;
		
	@Autowired
	public AtualizadorParceiroRnImpl(ParceiroMapper parceiroMapper, ParceiroRepository parceiroRepository) {
		this.parceiroMapper = parceiroMapper;
		this.parceiroRepository = parceiroRepository;
	}

	@Override
	public ParceiroNegocio operarSobre(ParceiroAtualizacaoDto atualizacaoDto) {
		String nome = atualizacaoDto.getNome();
		ParceiroNegocio parceiroPorNome = parceiroRepository.buscarPorNome(nome);
		if (parceiroPorNome != null && parceiroPorNome.getId() != atualizacaoDto.getId()) {
			String mensagem = "Ja existe parceiro com nome {" + nome + "}";
			throw new ParceiroNegocioAtualizacaoRnException(mensagem);
		}
		
		String inscricaoFiscal = atualizacaoDto.getInscricaoFiscal();
		ParceiroNegocio parceiroPorInscricao = parceiroRepository.buscarPorInscricaoFiscal(inscricaoFiscal);
		if (parceiroPorInscricao != null && parceiroPorInscricao.getId() != atualizacaoDto.getId()) {
			String mensagem = "Ja existe parceiro com inscricao fiscal {" + inscricaoFiscal + "}";
			throw new ParceiroNegocioAtualizacaoRnException(mensagem);
		}
		
		return parceiroMapper.converterParaEntidade(atualizacaoDto);
	}
}
