package com.agrotis.trainees.crud.dto.item;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class ItemAtualizacaoDto {

	@NotNull(message = "Atributo id obrigatorio")
	private Long id;
	
	private Integer quantidade;
	private BigDecimal precoUnitario;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}
	
	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
}
