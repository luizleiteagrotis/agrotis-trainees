package com.agrotis.trainees.crud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "parceiro_de_negocio")
public class ParceiroNegocio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	@NotBlank(message = "Atributo nome obrigatorio")
	private String nome;
	
	@Column(name = "inscricao_fiscal")
	@NotBlank(message = "Atributo inscricao fiscal obrigatorio")
	private String inscricaoFiscal;
	
	@Column(name = "endereco")
	@NotBlank(message = "Atributo endereco obrigatorio")
	private String endereco;
	
	@Column(name = "telefone")
	@NotBlank(message = "Atributo telefone obrigatorio")
	@Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone deve seguir o padrao: (41) 1234-5678 ou (41) 12345-5678")
	private String telefone;
	
	public ParceiroNegocio() {}
	
	public ParceiroNegocio(Long id) {
		this.id = id;
	}
	
	public Long getId() {
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
