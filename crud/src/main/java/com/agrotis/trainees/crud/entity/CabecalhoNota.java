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
	
	@NotNull(message = "Atributo numero obrigatorio")
	@Column(name = "numero")
	private Long numero;
	
	@NotNull(message = "Atributo parceiro obrigatorio")
	@ManyToOne
	private ParceiroNegocio parceiro;
	
	@NotNull(message = "Atributo tipo obrigatorio")
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_nota")
	private TipoNota tipo;
	
	@PastOrPresent(message = "Atributo data emissao nao pode ser futura")
	@Column(name = "data_emissao")
	private LocalDate dataEmissao;
	
	@NotNull(message = "Atributo valor total obrigatorio")
	@DecimalMin(value = "00.00", inclusive = true, message = "Atributo valor total deve ser no minimo 0")
	@Digits(integer = 10, fraction = 2, message = "Atributo valor total deve ter 10 casas inteiras e 2 casas decimais")
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
