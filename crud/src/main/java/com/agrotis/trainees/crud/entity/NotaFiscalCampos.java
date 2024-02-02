package com.agrotis.trainees.crud.entity;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;

@Entity
@Table(name = "nota_fiscal_campos")
public class NotaFiscalCampos {		
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "tipo")
	@NotNull(message = "O tipo deve ser informado")
	private NotaFiscalTipo tipo;
	
	@ManyToOne
	@JoinColumn(name = "parceiro")
	@NotNull(message = "O parceiro deve ser informado")
	private ParceiroNegocios parceiro;
	
	
	private Integer numero;
	
	@PastOrPresent
	private LocalDate dataEmissao;		
	
	public Integer getId() {
		return id;
	}

	public ParceiroNegocios getParceiro() {
		return parceiro;
	}

	public Integer getNumero() {
		return numero;
	}

	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public NotaFiscalTipo getTipo() {
		return tipo;
	}

	public void setTipo(NotaFiscalTipo tipo) {
		this.tipo = tipo;		
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public void setParceiro(ParceiroNegocios parceiro) {
		this.parceiro = parceiro;	}


	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	
	
}
