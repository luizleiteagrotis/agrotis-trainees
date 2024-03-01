package com.agrotis.trainees.crud.service.parceiro.cadastro.rn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.parceiro.ParceiroRepository;
import com.agrotis.trainees.crud.service.parceiro.cadastro.ParceiroNegocioCadastroRn;
import com.agrotis.trainees.crud.service.parceiro.cadastro.ParceiroNegocioCadastroRnException;

@Component
public class ParceiroExistenteCadastroRn implements ParceiroNegocioCadastroRn {

	private ParceiroRepository parceiroRepository;
	
	@Autowired
	public ParceiroExistenteCadastroRn(ParceiroRepository parceiroRepository) {
		this.parceiroRepository = parceiroRepository;
	}

	@Override
	public ParceiroNegocio operarSobre(ParceiroNegocio parceiroNegocio) {
		String nome = parceiroNegocio.getNome();
		String inscricaoFiscal = parceiroNegocio.getInscricaoFiscal();
		if (existeParceiroCom(nome, inscricaoFiscal)) {
			StringBuilder mensagem = new StringBuilder();
			mensagem.append("Ja existe parceiro com nome {")
					.append(nome)
					.append("} ou inscricao fiscal {")
					.append(inscricaoFiscal)
					.append("}");
			throw new ParceiroNegocioCadastroRnException(mensagem.toString());
		}
		return parceiroNegocio;
	}
	
	private boolean existeParceiroCom(String nome, String descricao) {
		return parceiroRepository.existeInstanciaCom(nome, descricao);
	}

	@Override
	public int getOrder() {
		return 1;
	}
}
