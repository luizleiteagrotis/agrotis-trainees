package com.agrotis.trainees.crud.dto.cabecalho;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.agrotis.trainees.crud.entity.TipoNota;

public class CabecalhoCadastroDto {
	
	@NotNull(message = "Atributo numero obrigatorio")
	private Long numero;
	
	@NotNull(message = "Atributo idParceiro obrigatorio")
	private Long idParceiro;
	
	@NotNull(message = "Atributo tipo obrigatorio")
	private TipoNota tipo;
	
	@NotNull(message = "Atributo dataEmissao obrigatorio")
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
