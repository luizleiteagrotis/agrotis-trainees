package com.agrotis.trainees.crud.dto.produto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.agrotis.trainees.crud.dto.parceiro.ParceiroRetornoDto;

public class ProdutoRetornoDto {

	private Long id;
	private String nome;
	private String descricao;
	private Long idFabricante;
	private LocalDate dataFabricacao;
	private LocalDate dataValidade;
	private Integer estoque;
	private BigDecimal custoMedio;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	public Long getIdFabricante() {
		return idFabricante;
	}

	public void setIdFabricante(Long idFabricante) {
		this.idFabricante = idFabricante;
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
	
	public void setEstoque(Integer quantidade) {
		this.estoque = quantidade;
	}

	public BigDecimal getCustoMedio() {
		return custoMedio;
	}

	public void setCustoMedio(BigDecimal custoMedio) {
		this.custoMedio = custoMedio;
	}
}
