package com.agrotis.trainees.crud.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotBlank(message = "Obrigatório preencher a descrição do produto")
	private String descricao;
	
	@NotNull(message = "Obrigatório preencher data de fabricação do produto")
	private Date data_fabricacao ;
	
	@NotNull(message = "Obrigatório preencher data de validade do produto")
	private Date data_validade;
	
	@ManyToOne
	@JoinColumn(name = "id_parceiro_de_negocio")
	private ParceiroNegocio parceiroNegocio;

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataFabricacao() {
		return data_fabricacao;
	}

	public void setDataFabricacao(Date data_fabricacao) {
		this.data_fabricacao = data_fabricacao;
	}

	public Date getDataValidade() {
		return data_validade;
	}

	public void setDataValidade(Date data_validade) {
		this.data_validade = data_validade;
	}

	public ParceiroNegocio getParceiroNegocio() {
		return parceiroNegocio;
	}

	public void setParceiroNegocio(ParceiroNegocio parceiroNegocio) {
		this.parceiroNegocio = parceiroNegocio;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", descricao=" + descricao + ", data_fabricacao=" + data_fabricacao
				+ ", data_validade=" + data_validade + ", parceiroNegocio=" + parceiroNegocio + "]";
	}
	
	
	
	
}
