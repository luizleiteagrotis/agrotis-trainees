package com.agrotis.trainees.crud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "produto") 
public class Produto {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nomeProduto;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "fabricante_id")
    private ParceiroNegocio fabricante;

    @Column(name = "data_fabricacao")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFabricacao;

    @Column(name = "data_validade")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataValidade;
	
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDate getDataFabricacao() {
		return dataFabricacao;
	}
	public void setDataFabricacao(LocalDate dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}
	public LocalDate getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}
	public Object getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
    public ParceiroNegocio getFabricante() {
        return fabricante;
    }

    public void setFabricante(ParceiroNegocio fabricante) {
        this.fabricante = fabricante;
    }
}
