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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

@Entity
@Table(name = "produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@Column(name = "nome")
	private String nome;
	
	@NotBlank
	@Column(name = "descricao")
	private String descricao;
	
	@ManyToOne
	private ParceiroNegocio fabricante;
	
	@NotNull
	@PastOrPresent
	@Column(name = "data_fabricacao")
	private LocalDate dataFabricacao;
	
	@NotNull
	@PastOrPresent
	@Column(name = "data_validade")
	private LocalDate dataValidade;
	
	public Produto() {}

	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ParceiroNegocio getFabricante() {
		return fabricante;
	}

	public void setFabricante(ParceiroNegocio fabricante) {
		this.fabricante = fabricante;
	}
	
	public LocalDate getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao(LocalDate dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}

	public LocalDate getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}
}
