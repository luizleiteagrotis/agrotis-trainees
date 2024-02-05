package com.agrotis.trainees.crud.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	@DecimalMin(value = "00.01", inclusive = true)
	@Digits(integer = 10, fraction = 2)
	@Column(name = "valor_total")
	private BigDecimal valorTotal;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "cabecalho_id")
	private CabecalhoNota cabecalhoNota;

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
		atualizarValorTotal();
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
		atualizarValorTotal();
	}
	
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public CabecalhoNota getCabecalhoNota() {
		return cabecalhoNota;
	}

	public void setCabecalhoNota(CabecalhoNota cabecalhoNota) {
		this.cabecalhoNota = cabecalhoNota;
	}
	
	private void atualizarValorTotal() {
		if (precoUnitario == null) precoUnitario = new BigDecimal(1);
		valorTotal = precoUnitario.multiply(new BigDecimal(quantidade));
	}
}
