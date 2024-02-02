package com.agrotis.trainees.crud.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

@Entity
@Table(name = "item_nota_fiscal")
public class ItemNota {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@ManyToOne
	@NotNull
	private Produto produto;
	
	@Min(1)
	@Column(name = "quantidade")
	private int quantidade;
	
	@DecimalMin(value = "00.01", inclusive = true)
	@Digits(integer = 10, fraction = 2)
	@Column(name = "preco_unitario")
	private BigDecimal precoUnitario;
	
	@NotNull
	@PastOrPresent
	@Column(name = "data_fabricacao")
	private LocalDate dataFabricacao;
	
	@NotNull
	@PastOrPresent
	@Column(name = "data_validade")
	private LocalDate dataValidade;

	public long getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
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
