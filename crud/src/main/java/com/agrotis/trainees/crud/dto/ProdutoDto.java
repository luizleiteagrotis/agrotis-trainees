package com.agrotis.trainees.crud.dto;

import java.time.LocalDate;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;

public class ProdutoDto {
    private int id;
    private String nome;
    private String descricao;
    private ParceiroNegocio fabricante;
    private LocalDate dataFabricacao;
    private LocalDate dataValidade;
    private double estoque;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getEstoque() {
        return estoque;
    }

    public void setEstoque(double estoque) {
        this.estoque = estoque;
    }

}
