package com.agrotis.trainees.crud.dto.item;

import java.math.BigDecimal;

public class ItemRetornoDto {

	private Long id;
	private Long idProduto;
	private Integer quantidade;
	private BigDecimal precoUnitario;
	private BigDecimal valorTotal;
	private Long idCabecalho;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
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
	
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public Long getIdCabecalho() {
		return idCabecalho;
	}
	
	public void setIdCabecalho(Long idCabecalho) {
		this.idCabecalho = idCabecalho;
	}
}
