package com.agrotis.trainees.crud.controller.form;

import java.time.LocalDate;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;

public class ProdutoForm {

	private String nome;
	private String descricao;
	private ParceiroNegocio parceiro;
	private String fabricante;
	private LocalDate dataFabricacao;
	private LocalDate dataValidade;
	private Integer estoque;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public ParceiroNegocio getParceiro() {
		return parceiro;
	}
	
	public void setParceiro(ParceiroNegocio parceiro) {
		this.parceiro = parceiro;
	}
	
	public String getFabricante() {
		return fabricante;
	}
	
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	
	public LocalDate getDataDabricacao() {
		return dataFabricacao;
	}
	
	public void setDataDabricacao(LocalDate dataDabricacao) {
		this.dataFabricacao = dataDabricacao;
	}
	
	public LocalDate getDataValidade() {
		return dataValidade;
	}
	
	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}
	
	public Integer getEstoque() {
		return estoque;
	}
	
	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}
	
	public Produto converter(ParceiroNegocioRepository parceiroRepository) {
		ParceiroNegocio parceiroNegocio = parceiroRepository.findByNome(parceiro.getNome());
		return new Produto(nome, descricao, parceiroNegocio, fabricante, dataFabricacao, dataValidade);
	}
}
