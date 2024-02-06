package com.agrotis.trainees.crud.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

@Entity
@Table(name = "cabecalho_nota")
public class CabecalhoNota {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NotNull
	@Column(name = "numero")
	private Long numero;
	
	@NotNull
	@ManyToOne
	private ParceiroNegocio parceiro;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "tipo_nota_id")
	private NotaFiscalTipo tipo;
	
	@PastOrPresent
	@Column(name = "data_emissao")
	private LocalDate dataEmissao;
	
	@NotNull
	@DecimalMin(value = "00.00", inclusive = true)
	@Digits(integer = 10, fraction = 2)
	@Column(name = "valor_total")
	private BigDecimal valorTotal;
	
	public Long getId() {
		return id;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public ParceiroNegocio getParceiro() {
		return parceiro;
	}

	public void setParceiro(ParceiroNegocio parceiro) {
		this.parceiro = parceiro;
	}

	public NotaFiscalTipo getTipo() {
		return tipo;
	}

	public void setTipo(NotaFiscalTipo tipo) {
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
