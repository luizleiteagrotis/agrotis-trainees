package com.agrotis.trainees.crud.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

@Entity
@Table(name = "produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Atributo nome obrigatorio")
	@Column(name = "nome")
	private String nome;
	
	@NotBlank(message = "Atributo descricao obrigatorio")
	@Column(name = "descricao")
	private String descricao;
	
	@NotNull(message = "Atributo fabricante obrigatorio")
	@ManyToOne
	private ParceiroNegocio fabricante;
	
	@NotNull(message = "Atributo data de fabricacao obrigatorio")
	@PastOrPresent(message = "Atributo data de fabricacao nao deve ser futuro")
	@Column(name = "data_fabricacao")
	private LocalDate dataFabricacao;
	
	@NotNull(message = "Atributo data de validade obrigatorio")
	@Column(name = "data_validade")
	private LocalDate dataValidade;
	
	@NotNull(message = "Atributo estoque obrigatorio")
	@Min(value = 0, message = "Atributo estoque deve ser no minimo 0")
	private Integer estoque;
	
	public Produto() {}

	public Long getId() {
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

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}
}
