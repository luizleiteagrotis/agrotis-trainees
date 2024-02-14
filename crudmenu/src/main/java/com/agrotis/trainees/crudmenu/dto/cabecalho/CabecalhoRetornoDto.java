package com.agrotis.trainees.crudmenu.dto.cabecalho;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CabecalhoRetornoDto {

	private Long id;
	private Long numero;
	private Long idParceiro;
	private TipoNota tipo;
	private LocalDate dataEmissao;
	private BigDecimal valorTotal;
	
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
	
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
}
