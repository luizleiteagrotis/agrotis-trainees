package com.agrotis.trainees.crud.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {

	public NotaFiscal() {
		this.data = LocalDateTime.now();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nota_fiscal_tipo_id")
	private String notaFiscalTipo;

	@ManyToOne
	@JoinColumn(name = "parceiro_de_negocio_id")
	private ParceiroNegocio parceiroNegocio;

	@Column(name = "numero_nota")
	private Integer numeroDaNota;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime data;

	public String getNotaFiscalTipo() {
		return notaFiscalTipo;
	}

	public void setNotaFiscalTipo(String notaFiscalTipo) {
		this.notaFiscalTipo = notaFiscalTipo;
	}

	public ParceiroNegocio getParceiroNegocio() {
		return parceiroNegocio;
	}

	public void setParceiroNegocio(ParceiroNegocio parceiroNegocio) {
		this.parceiroNegocio = parceiroNegocio;
	}

	public Integer getNumeroDaNota() {
		return numeroDaNota;
	}

	public void setNumeroDaNota(Integer numeroDaNota) {
		this.numeroDaNota = numeroDaNota;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Long getId() {
		return id;
	}

	
	
	

}
