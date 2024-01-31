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

	@NotEmpty(message = "O campo nome tem de ser preenchido.")
	private String nome;

	@Column(name = "inscricao_fiscal")
	@NotEmpty(message = "O campo inscrição fiscal deve ser preenchido")
	private String inscricaoFiscal;

	@NotEmpty(message = "O campo endereço tem de ser preenchido.")
	private String endereco;

	@Size(min = 8, max = 11, message = "O campo telefone tem de conter entre 8 e 11 caracteres")
	private String telefone;

	@OneToMany(mappedBy = "fabricante")
	private List<Produto> produtos;

	// Getters e Setters
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

	@Override
	public String toString() {
		return "ParceiroNegocio: id: " + id + "\n" + "Nome: " + nome + "\n" + "InscricaoFiscal: " + inscricaoFiscal
				+ "\n" + "Endereco: " + endereco + "\n" + "Telefone: " + telefone;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParceiroNegocio other = (ParceiroNegocio) obj;
		return Objects.equals(id, other.id);
	}

}
