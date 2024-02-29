package com.agrotis.trainees.crud.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;

public class ProdutoDto {

    private Integer id;

    private String descricao;

    private ParceiroNegocio idParceiro;

    private LocalDate dataFabricacao;

    private LocalDate dataValidade;

    private Integer estoque;

    private BigDecimal custoMedio;

    public BigDecimal getCustoMedio() {
        return custoMedio;
    }

    public void setCustoMedio(BigDecimal custoMedio) {
        this.custoMedio = custoMedio;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public ParceiroNegocio getIdParceiro() {
        return idParceiro;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setIdParceiro(ParceiroNegocio idParceiro) {
        this.idParceiro = idParceiro;
    }

    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

}
