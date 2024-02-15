package com.agrotis.trainees.crud.dto.cabecalho;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

public class CabecalhoAtualizacaoDto {

	@NotNull(message = "Atributo id obrigatorio")
	private Long id;
	
	private Long numero;
	private LocalDate dataEmissao;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getNumero() {
		return numero;
	}
	
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	
	public LocalDate getDataEmissao() {
		return dataEmissao;
	}
	
	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
}
