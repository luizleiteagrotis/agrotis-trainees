package com.agrotis.trainees.crud.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parceiro_negocio")
public class ParceiroNegocio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer nome;
	private Integer inscricaoFiscal;
	private Integer endereco;
	private Integer telefone;

	public Integer getNome() {
		return nome;
	}

	public void setNome(Integer nome) {
		this.nome = nome;
	}

	public Integer getInscricaoFiscal() {
		return inscricaoFiscal;
	}

	public void setInscricaoFiscal(Integer inscricaoFiscal) {
		this.inscricaoFiscal = inscricaoFiscal;
	}

	public Integer getEndereco() {
		return endereco;
	}

	public void setEndereco(Integer endereco) {
		this.endereco = endereco;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	public Integer getId() {
		return id;
	}

}