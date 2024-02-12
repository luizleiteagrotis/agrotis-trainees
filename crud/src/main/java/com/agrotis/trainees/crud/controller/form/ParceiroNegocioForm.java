package com.agrotis.trainees.crud.controller.form;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;

public class ParceiroNegocioForm {

	private String nome;
	private String inscricaoFiscal;
	private String endereco;
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

	public ParceiroNegocio converter() {
		return new ParceiroNegocio(nome, inscricaoFiscal, endereco, telefone);
	}
	
}
