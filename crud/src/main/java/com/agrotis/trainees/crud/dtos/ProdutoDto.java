package com.agrotis.trainees.crud.dtos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;

public class ProdutoDto {
    
    private Integer id;

    private String descricao;

    private ParceiroNegocio fabricante;
    
    private LocalDate dataFabricacao;

    private LocalDate dataValidade;

    private Integer quantidadeEstoque;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    

}
