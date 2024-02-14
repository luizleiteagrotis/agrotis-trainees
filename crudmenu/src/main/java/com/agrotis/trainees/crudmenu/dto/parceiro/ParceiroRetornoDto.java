package com.agrotis.trainees.crudmenu.dto.parceiro;

public class ParceiroRetornoDto {
	
	private Long id;
	private String nome;
	private String inscricaoFiscal;
	private String endereco;
	private String telefone;

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
