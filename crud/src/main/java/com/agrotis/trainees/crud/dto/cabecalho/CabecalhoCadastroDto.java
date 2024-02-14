package com.agrotis.trainees.crud.dto.cabecalho;

import java.time.LocalDate;

import com.agrotis.trainees.crud.entity.TipoNota;

public class CabecalhoCadastroDto {
	
	private Long numero;
	private Long idParceiro;
	private TipoNota tipo;
	private LocalDate dataEmissao;
	
	public Long getNumero() {
		return numero;
	}
	
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	
	public Long getIdParceiro() {
		return idParceiro;
	}
	
	public void setIdParceiro(Long idParceiro) {
		this.idParceiro = idParceiro;
	}
	
	public TipoNota getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoNota tipo) {
		this.tipo = tipo;
	}
	
	public LocalDate getDataEmissao() {
		return dataEmissao;
	}
	
	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
}
