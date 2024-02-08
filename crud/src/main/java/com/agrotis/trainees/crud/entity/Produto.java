package com.agrotis.trainees.crud.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
    @Column(unique = true)
    private String descricao;

    @NotNull(message = "Obrigatório preencher data de fabricação do produto")
    @Column(name = "data_fabricacao", nullable = false)
    @PastOrPresent
    private Date dataFabricacao;

    @NotNull(message = "Obrigatório preencher data de validade do produto")
    @Column(name = "data_validade", nullable = false)
    private Date dataValidade;

    @ManyToOne
    @JoinColumn(name = "id_fabricante")
    private ParceiroNegocio fabricante;

    @Min(value = 0)
    @Column(name = "quantidade_estoque")
    private int quantidadeEstoque;

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
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

    public ParceiroNegocio getFabricante() {
        return fabricante;
    }

    public void setFabricante(ParceiroNegocio fabricante) {
        this.fabricante = fabricante;
    }

    @Override
    public String toString() {
        return "Produto [id=" + id + ", descricao=" + descricao + ", data_fabricacao=" + dataFabricacao + ", data_validade="
                        + dataValidade + ", parceiroNegocio=" + fabricante + "]";
    }

}
