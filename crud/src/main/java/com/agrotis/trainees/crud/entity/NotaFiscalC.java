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
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "nota_fiscalC")
public class NotaFiscalC {

	public NotaFiscalC() {
		this.data = LocalDateTime.now();
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

	@Column(name = "nota_fiscal_tipo_id")
	private String notaFiscalTipo;

	@ManyToOne
	@JoinColumn(name = "parceiro_de_negocio_id")
    @NotNull(message = "Informe um parceiro de negócio.")
	private ParceiroNegocio parceiroNegocio;
	
	@Column(name = "numero_nota")
	private Integer numeroNota;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime data;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Integer getNumeroNota() {
		return numeroNota;
	}

	public void setNumeroNota(Integer numeroNota) {
		this.numeroNota = numeroNota;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public Integer getId1() {
		return id;
		
	}
	
	
}