package com.agrotis.trainees.crud.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "produto")

public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_fabricante", referencedColumnName = "id")
    @NotNull(message = "Obrigatório preencher o fabricante do produto")
    private ParceiroNegocio fabricante;

    private Date dataFabricacao;

    private Date dataValidade;

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

    public Date getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(Date dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "[id: " + id + "; descricao: " + descricao + "; fabricante: " + fabricante + "; dataFabricacao: " + dataFabricacao
                        + "; dataValidade: " + dataValidade + "]";
    }
}