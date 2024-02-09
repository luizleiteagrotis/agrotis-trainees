package com.agrotis.trainees.crud.dto;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;

public class ParceiroNegocioDto {
    private int id;
    private String nome;
    private String inscricaoFiscal;
    private String endereco;
    private String telefone;

    public ParceiroNegocioDto() {
    }

    public ParceiroNegocioDto(String nome, String inscricaoFiscal, String endereco, String telefone) {
        super();
        this.nome = nome;
        this.inscricaoFiscal = inscricaoFiscal;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public ParceiroNegocioDto(ParceiroNegocio parceiroNegocio) {
        super();
        id = parceiroNegocio.getId();
        nome = parceiroNegocio.getNome();
        inscricaoFiscal = parceiroNegocio.getInscricaoFiscal();
        endereco = parceiroNegocio.getEndereco();
        telefone = parceiroNegocio.getTelefone();
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

}
