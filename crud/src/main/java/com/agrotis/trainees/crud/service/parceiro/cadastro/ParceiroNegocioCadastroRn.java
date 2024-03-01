package com.agrotis.trainees.crud.service.parceiro.cadastro;

import org.springframework.core.Ordered;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;

public interface ParceiroNegocioCadastroRn extends Ordered{

	ParceiroNegocio operarSobre(ParceiroNegocio parceiroNegocio);
}
