package com.agrotis.trainees.crud.entity;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotBlank(message = "Preenchimento obrigatorio!")
	private String descricao;
	
	@NotBlank(message = "Preenchimento obrigatorio!")
	private String nome;
	
	@ManyToOne()
	@JoinColumn(name = "id_parceiro_fabricante") 
	private ParceiroNegocio fabricante;
	
	@Column(name = "data_fabricacao")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFabricacao;
	
	@Column(name = "data_validade")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataValidade;

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getNome() {
		return nome;
	}

	public ParceiroNegocio getFabricante() {
		return fabricante;
	}

	public LocalDate getDataFabricacao() {
		return dataFabricacao;
	}

	public LocalDate getDataValidade() {
		return dataValidade;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setFabricante(ParceiroNegocio fabricante) {
		this.fabricante = fabricante;
	}

	public void setDataFabricacao(LocalDate dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}

	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}
	
	

	
	
	
	
	
	
	
	
	

	
	
}