package com.agrotis.trainees.crud.dto;

public class ParceiroNegocioDto {

    private Integer id;

    private String nome;

    private String inscricaoFiscal;

    private String endereco;

    private String telefone;

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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setInscricaoFiscal(String inscricaoFiscal) {
        this.inscricaoFiscal = inscricaoFiscal;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
