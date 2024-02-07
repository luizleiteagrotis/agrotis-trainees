package com.agrotis.trainees.crud.entity;

import javax.persistence.Column;
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
    private int id;
    @NotBlank(message = "Este campo é obrigatório")
    private String nome;
    @Column(name = "inscricao_fiscal", unique = true)
    @NotBlank(message = "Este campo é obrigatório")
    private String inscricaoFiscal;
    private String endereco;
    private String telefone;

    @Deprecated
    public ParceiroNegocio() {
    }

    public ParceiroNegocio(@NotBlank(message = "Este campo é obrigatório") String nome,
                    @NotBlank(message = "Este campo é obrigatório") String inscricaoFiscal, String endereco, String telefone) {
        super();
        this.nome = nome;
        this.inscricaoFiscal = inscricaoFiscal;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public ParceiroNegocio(@NotBlank(message = "Este campo é obrigatório") String nome, String endereco, String telefone) {
        super();
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public int getId() {
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
