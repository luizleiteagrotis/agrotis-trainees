package com.agrotis.trainees.crudmenu.dto.item;

import java.math.BigDecimal;

public class ItemCadastroDto {
	
	private Long idProduto;
	private Integer quantidade;
	private BigDecimal precoUnitario;
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
