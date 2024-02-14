package com.agrotis.trainees.crudmenu.dto.cabecalho;

import java.time.LocalDate;

public class CabecalhoAtualizacaoDto {

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
