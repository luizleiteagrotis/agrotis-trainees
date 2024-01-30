package com.agrotis.trainees.crud.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parceiro_de_negocio")
public class ParceiroNegocio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String nome;
    
    @Column(name = "inscricao_fiscal")
    private Long inscricaoFiscal;
    
    private String endereco;
    
    private String telefone;
    
	public Integer getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getInscricaoFiscal() {
		return inscricaoFiscal;
	}
	public void setInscricaoFiscal(Long inscricaoFiscal) {
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
		return "ParceiroNegocio: id: " + id+ "\n" 
				+ "Nome: " + nome + "\n" 
				+ "InscricaoFiscal: " + inscricaoFiscal+ "\n"  
				+ "Endereco: " + endereco + "\n" 
				+ "Telefone: " + telefone;
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

//Modelar a entidade ParceiroNegocio com os atributos id, nome, inscricaoFiscal, endereco e telefone.