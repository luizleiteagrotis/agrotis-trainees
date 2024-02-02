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
import javax.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "data_nota")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNota;
	
	@Column(name = "tipo_nota") 
	private String notaFiscalTipo;
	
	@Column(name = "numero_nota") 
	private Integer numeroNota;
	
	@ManyToOne
	@JoinColumn(name = "parceiro_negocio_id") 
	private ParceiroNegocio parceiroNegocio;

	
	
	public Integer getId() {
		return id;
	}

	public LocalDate getDataNota() {
		return dataNota;
	}

	public void setDataNota(LocalDate dataNota) {
		this.dataNota = dataNota;
	}

	public String getNotaFiscalTipo() {
		return notaFiscalTipo;
	}

	public void setNotaFiscalTipo(String notaFiscalTipo) {
		this.notaFiscalTipo = notaFiscalTipo;
	}

	public Integer getNumeroNota() {
		return numeroNota;
	}

	public void setNumeroNota(Integer numeroNota) {
		this.numeroNota = numeroNota;
	}

	public ParceiroNegocio getParceiroNegocio() {
		return parceiroNegocio;
	}

	public void setParceiroNegocio(ParceiroNegocio parceiroNegocio) {
		this.parceiroNegocio = parceiroNegocio;
	}
	
	


	
}	
	