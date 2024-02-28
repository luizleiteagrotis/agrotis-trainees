package com.agrotis.trainees.crud.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "item_nota_fiscal")

public class ItemNotaFiscal {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@NotNull(message = "Atributo produto obrigatorio")
	private Produto produto;
	
	@Min(value = 1, message = "Atributo quantidade deve ser maior do que 0")
	private int quantidade;
	
	@DecimalMin(value = "00.01", message = "Atributo preco unitario deve ser maior do que 0")
	private BigDecimal precoUnitario;
	
	@DecimalMin(value = "00.01", message = "Atributo valor total deve ser maior do que 0")
	private BigDecimal valorTotal;

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

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public Integer getId() {
		return id;
	}
	
	public void calculaValorTotal() {
		this.valorTotal = precoUnitario.multiply(BigDecimal.valueOf(quantidade));
	}
}
