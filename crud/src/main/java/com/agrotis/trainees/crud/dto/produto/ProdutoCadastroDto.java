package com.agrotis.trainees.crud.dto.produto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

public class ProdutoCadastroDto {
	
	@NotNull(message = "Atributo nome obrigatorio")
	private String nome;
	
	@NotNull(message = "Atributo descricao obrigatorio")
	private String descricao;
	
	@NotNull(message = "Atributo idFabricante obrigatorio")
	private Long idFabricante;
	
	@NotNull(message = "Atributo dataFabricacao obrigatorio")
	private LocalDate dataFabricacao;
	
	@NotNull(message = "Atributo dataValidade obrigatorio")
	private LocalDate dataValidade;
	
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
}
