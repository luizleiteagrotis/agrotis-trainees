package com.agrotis.trainees.crudmenu.dto.produto;

import java.time.LocalDate;

public class ProdutoCadastroDto {
	
	private String nome;
	private String descricao;
	private Long idFabricante;
	private LocalDate dataFabricacao;
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
