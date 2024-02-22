package com.agrotis.trainees.crud.dto;

import org.springframework.beans.BeanUtils;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;

public class ParceiroNegocioDto {

    private Integer id;

    private String nome;

    private String inscricaoFiscal;

    private String endereco;

    private String telefone;

    public ParceiroNegocioDto() {
        super();
    }

    public ParceiroNegocioDto(ParceiroNegocio entidade) {
        BeanUtils.copyProperties(entidade, this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "ParceiroNegocioDto [nome=" + nome + ", inscricaoFiscal=" + inscricaoFiscal + "]";
    }

}
