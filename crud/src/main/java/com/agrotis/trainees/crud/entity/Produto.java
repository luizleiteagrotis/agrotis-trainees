package com.agrotis.trainees.crud.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Obrigatório preencher a descrição do produto")
    private String descricao;

    @NotNull(message = "Obrigatório preencher data de fabricação do produto")
    @PastOrPresent
    private Date data_fabricacao;

    @NotNull(message = "Obrigatório preencher data de validade do produto")
    @FutureOrPresent
    private Date data_validade;

    @ManyToOne
    @JoinColumn(name = "id_fabricante")
    private ParceiroNegocio fabricante;

    @Min(value = 0)
    private int quantidade_estoque;

    public int getQuantidade_estoque() {
        return quantidade_estoque;
    }

    public void setQuantidade_estoque(int quantidade_estoque) {
        this.quantidade_estoque = quantidade_estoque;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataFabricacao() {
        return data_fabricacao;
    }

    public void setDataFabricacao(Date data_fabricacao) {
        this.data_fabricacao = data_fabricacao;
    }

    public Date getDataValidade() {
        return data_validade;
    }

    public void setDataValidade(Date data_validade) {
        this.data_validade = data_validade;
    }

    public ParceiroNegocio getFabricante() {
        return fabricante;
    }

    public void setFabricante(ParceiroNegocio fabricante) {
        this.fabricante = fabricante;
    }

    @Override
    public String toString() {
        return "Produto [id=" + id + ", descricao=" + descricao + ", data_fabricacao=" + data_fabricacao + ", data_validade="
                        + data_validade + ", parceiroNegocio=" + fabricante + "]";
    }

}
