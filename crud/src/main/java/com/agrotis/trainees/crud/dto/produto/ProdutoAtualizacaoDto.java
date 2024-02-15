package com.agrotis.trainees.crud.dto.produto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.agrotis.trainees.crud.dto.parceiro.ParceiroRetornoDto;

public class ProdutoAtualizacaoDto {

	@NotNull(message = "Atributo id obrigatorio")
	private Long id;
	
	private String nome;
	private String descricao;
	private LocalDate dataFabricacao;
	private LocalDate dataValidade;
	
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
