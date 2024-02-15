package com.agrotis.trainees.crud.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "parceiro_negocio")
public class ParceiroNegocio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "O campo nome é obrigatório")
    private String nome;

    @NotBlank(message = "O campo inscrição fiscal obrigatório")
    private String inscricaoFiscal;

    private String endereco;

    private String telefone;

    public Long getId() {
        return id;
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