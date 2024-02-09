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
    private Integer id;

    @NotBlank(message = "Obrigatório preencher o nome do parceiro de negócio")
    private String nome;

    @NotBlank(message = "Obrigatório preencher a inscrição fiscal")
    @Column(name = "inscricao_fiscal")
    private String inscricaoFiscal;

    private String endereco;
    private String telefone;

    public ParceiroNegocio() {
        super();
    }

    public ParceiroNegocio(@NotBlank(message = "Obrigatório preencher o nome do parceiro de negócio") String nome,
                    @NotBlank(message = "Obrigatório preencher a inscrição fiscal") String inscricaoFiscal, String endereco,
                    String telefone) {
        super();
        this.nome = nome;
        this.inscricaoFiscal = inscricaoFiscal;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public Integer getId() {
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