package com.agrotis.trainees.crud.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "produto") 
public class Produto {
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Integer id;
	@NotBlank(message = "Obrigatório preencher o nome do produto")
	private String descricao;
	@NotBlank(message = "Obrigatório preencher a data de fabricação")
	private LocalDate dataFabricacao;
	@NotBlank(message = "Obrigatório preencher a data de validade")
	private LocalDate dataValidade;
	
	
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
