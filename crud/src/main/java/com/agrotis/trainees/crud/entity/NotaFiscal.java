package com.agrotis.trainees.crud.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity 
@Table(name = "nota_fiscal")
public class NotaFiscal {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "tipo_nota")
    private NotaFiscalTipo notaFiscalTipo;
	
	@ManyToOne
	@JoinColumn(name = "parceiro_negocio")
    private ParceiroNegocio parceiroNegocio;
	
	@NotNull(message = "Obrigatório preencher o número da Nota Fiscal")
	@Column(name = "numero_nota")
	private Integer numeroNota;
	
	@FutureOrPresent(message = "A data não pode ser maior que a data de hoje")
	private LocalDate dataNota;
	
	public NotaFiscal() {
		super();
		this.dataNota = LocalDate.now();
	}



	public Integer getId() {
        return id;
    }

	

	public NotaFiscalTipo getNotaFiscalTipo() {
		return notaFiscalTipo;
	}

	public void setNotaFiscalTipo(NotaFiscalTipo notaFiscalTipo) {
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

	public LocalDate getDataNota() {
		return dataNota;
	}

	public void setDataNota(LocalDate dataNota) {
		this.dataNota = (dataNota == null) ? dataNota : LocalDate.now();
	}
	
}
