package com.agrotis.trainees.crud.entity;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "parceiro_de_negocio")
public class ParceiroNegocio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotEmpty(message = "Preenchimento Obrigatorio Nome")
	private String nome;
	
	@Column(name = "inscricao_fiscal")
	@NotEmpty(message = "Preenchimento Obrigatorio Inscrição Fiscal")
	private String inscricaoFiscal;
	
	@NotEmpty(message = "Preenchimento Obrigatorio Endereço")
	private String endereco;
	
	private String telefone;
	
	@OneToMany(mappedBy = "fabricante")
	private List<Produto> produtos;
	
	// Inicio get e set
	public Integer getId() {
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