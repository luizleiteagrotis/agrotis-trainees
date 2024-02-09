package com.agrotis.trainees.crud.dto;

import java.sql.Date;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;

public class ProdutoDto {

    private Integer id;

    private String descricao;

    private ParceiroNegocio id_parceiro;

    private Date dataFabricacao;

    private Date dataValidade;

    private Integer estoque;

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public ParceiroNegocio getId_parceiro() {
        return id_parceiro;
    }

    public Date getDataFabricacao() {
        return dataFabricacao;
    }

    public Date getDataValidade() {
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

    public void setId_parceiro(ParceiroNegocio id_parceiro) {
        this.id_parceiro = id_parceiro;
    }

    public void setDataFabricacao(Date dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

}
