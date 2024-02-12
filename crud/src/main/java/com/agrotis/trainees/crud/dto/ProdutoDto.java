package com.agrotis.trainees.crud.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;

public class ProdutoDto {

	private Integer id;
	private String nome;
	private String descricao;
	private ParceiroNegocio parceiroNegocio;
	private String fabricante;
	private LocalDate dataFabricacao;
	private LocalDate dataValidade;
	private Integer estoque;
	
	
	public ProdutoDto(Produto produto) {
		super();
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.parceiroNegocio = produto.getParceiroNegocio();
		this.fabricante = produto.getFabricante();
		this.dataFabricacao = produto.getDataFabricacao();
		this.dataValidade = produto.getDataValidade();
		this.estoque = produto.getEstoque();
	}

	public Integer getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public ParceiroNegocio getParceiroNegocio() {
		return parceiroNegocio;
	}
	
	public String getFabricante() {
		return fabricante;
	}
	
	public LocalDate getDataFabricacao() {
		return dataFabricacao;
	}
	
	public LocalDate getDataValidade() {
		return dataValidade;
	}
	
	public Integer getEstoque() {
		return estoque;
	}
	
	public static List<ProdutoDto> converter(List<Produto> produtos) {
		return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
	}
	
}
