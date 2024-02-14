package com.agrotis.trainees.crud.dto;

import java.time.LocalDate;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;

public class ProdutoDto {
    private int id;
    private String nome;
    private String descricao;
    private ParceiroNegocio fabricante;
    private LocalDate dataFabricacao;
    private LocalDate dataValidade;

    public ProdutoDto() {

    }

    public ProdutoDto(String nome, String descricao, ParceiroNegocio fabricante, LocalDate dataFabricacao, LocalDate dataValidade) {
        super();
        this.nome = nome;
        this.descricao = descricao;
        this.fabricante = fabricante;
        this.dataFabricacao = dataFabricacao;
        this.dataValidade = dataValidade;
    }

    public ProdutoDto(Produto entidade) {
        id = entidade.getId();
        nome = entidade.getNome();
        descricao = entidade.getDescricao();
        fabricante = entidade.getFabricante();
        dataFabricacao = entidade.getDataFabricacao();
        dataValidade = entidade.getDataValidade();
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ParceiroNegocio getFabricante() {
        return fabricante;
    }

    public void setFabricante(ParceiroNegocio fabricante) {
        this.fabricante = fabricante;
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

}
