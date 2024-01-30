package com.agrotis.trainees.crud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "parceiro_de_negocio")
public class ParceiroNegocio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "nome")
	@NotBlank
	private String nome;
	
	@Column(name = "inscricao_fiscal")
	@NotBlank
	private String inscricaoFiscal;
	
	@Column(name = "endereco")
	@NotBlank
	private String endereco;
	
	@Column(name = "telefone")
	@NotBlank
	private String telefone;
	
	public ParceiroNegocio() {}

	public ParceiroNegocio(String nome, String inscricaoFiscal, String endereco, String telefone) {
		this.nome = nome;
		this.inscricaoFiscal = inscricaoFiscal;
		this.endereco = endereco;
		this.telefone = telefone;
	}

	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getInscricaoFiscal() {
		return inscricaoFiscal;
	}

	public void setInscricaoFiscal(String inscricaoFiscal) {
		this.inscricaoFiscal = inscricaoFiscal;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
