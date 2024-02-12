package com.agrotis.trainees.crud.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;

public class ParceiroNegocioDto {

	private Integer id;
	private String nome;
	private String inscricaoFiscal;
	private String endereco;
	private String telefone;
	
	public ParceiroNegocioDto(ParceiroNegocio parceiro) {
		super();
		this.id = parceiro.getId();
		this.nome = parceiro.getNome();
		this.inscricaoFiscal = parceiro.getInscricaoFiscal();
		this.endereco = parceiro.getEndereco();
		this.telefone = parceiro.getTelefone();
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getInscricaoFiscal() {
		return inscricaoFiscal;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public static List<ParceiroNegocioDto> converter(List<ParceiroNegocio> parceiros) {
		return parceiros.stream().map(ParceiroNegocioDto::new).collect(Collectors.toList());
	}
	
}
