package com.agrotis.trainees.crud.dto.parceiro;

import javax.validation.constraints.NotNull;

public class ParceiroCadastroDto {
	
	@NotNull(message = "Atributo nome obrigatorio")
	private String nome;
	
	@NotNull(message = "Atributo inscricaoFiscal obrigatorio")
	private String inscricaoFiscal;
	
	@NotNull(message = "Atributo endereco obrigatorio")
	private String endereco;
	
	@NotNull(message = "Atributo telefone obrigatorio")
	private String telefone;

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
