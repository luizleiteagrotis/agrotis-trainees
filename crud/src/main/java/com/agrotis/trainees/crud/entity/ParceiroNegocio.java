package com.agrotis.trainees.crud.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "parceiro_de_negocio")
public class ParceiroNegocio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotBlank(message = "Obrigatório preencher o nome do tipo de parceiro de negócio")
	private String nome;

	@NotBlank(message = "Obrigatório preencher a inscrição fiscal do tipo de parceiro de negócio")
	private String inscricao_fiscal;

	@NotBlank(message = "Obrigatório preencher o endereço do tipo de parceiro de negócio")
	private String endereco;

	@NotBlank(message = "Obrigatório preencher o telefone do tipo de parceiro de negócio")
	private String telefone;

	@OneToMany(mappedBy = "parceiroNegocio")
	private List<Produto> produtos;

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

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
		return inscricao_fiscal;
	}

	public void setInscricaoFiscal(String inscricaoFiscal) {
		this.inscricao_fiscal = inscricaoFiscal;
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
		return "ParceiroNegocio [id=" + id + ", nome=" + nome + "]";
	}

}
