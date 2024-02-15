package com.agrotis.trainees.crud.dto.item;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class ItemCadastroDto {
	
	@NotNull(message = "Atributo idProduto obrigatorio")
	private Long idProduto;
	
	@NotNull(message = "Atributo quantidade obrigatorio")
	private Integer quantidade;
	
	@NotNull(message = "Atributo precoUnitario obrigatorio")
	private BigDecimal precoUnitario;
	
	@NotNull(message = "Atributo idCabecalho obrigatorio")
	private Long idCabecalho;
	
	public Long getIdProduto() {
		return idProduto;
	}
	
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
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
	
	public Long getIdCabecalho() {
		return idCabecalho;
	}
	
	public void setIdCabecalho(Long idCabecalho) {
		this.idCabecalho = idCabecalho;
	}
}
