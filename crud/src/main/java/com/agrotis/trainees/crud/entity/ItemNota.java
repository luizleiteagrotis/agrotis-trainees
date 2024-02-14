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
	private Long id;
	
	@ManyToOne
	@NotNull(message = "Atributo produto obrigatorio")
	private Produto produto;
	
	@Min(value = 1, message = "Atributo quantidade deve ser maior do que 0")
	@Column(name = "quantidade")
	private Integer quantidade;
	
	@DecimalMin(value = "00.01", inclusive = true, message = "Atributo preco unitario deve ser maior do que 0")
	@Digits(integer = 10, fraction = 2, message = "Atributo preco unitario deve ter 10 casas inteiras e 2 casas decimais")
	@Column(name = "preco_unitario")
	private BigDecimal precoUnitario;
	
	@DecimalMin(value = "00.01", inclusive = true, message = "Atributo valor total deve ser maior do que 0")
	@Digits(integer = 10, fraction = 2, message = "Atributo valor total deve ter 10 casas inteiras e 2 casas decimais")
	@Column(name = "valor_total")
	private BigDecimal valorTotal;
	
	@NotNull(message = "Atributo cabecalho nota obrigatorio")
	@ManyToOne
	@JoinColumn(name = "cabecalho_id")
	private CabecalhoNota cabecalhoNota;

	public Long getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
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
}
