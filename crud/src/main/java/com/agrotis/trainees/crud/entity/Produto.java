package com.agrotis.trainees.crud.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String descricao;

    @Column(name = "estoque_produto")
    private Integer estoque;

    @ManyToOne
    @JoinColumn(name = "fabricante_id")
    private ParceiroNegocio fabricante;

    @Column(name = "data_fabricacao")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFabricacao;

    @Column(name = "data_validade")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataValidade;

    @Column(name = "custo_medio")
    private BigDecimal custoMedio;;

    public BigDecimal getCustoMedio() {
        return custoMedio;
    }

    public Produto(String descricao, ParceiroNegocio parceiroNegocio, String fabricante, LocalDate dataFabricacao,
                    LocalDate dataValidade) {
        super();
        this.descricao = descricao;
        this.fabricante = parceiroNegocio;
        this.dataFabricacao = dataFabricacao;
        this.dataValidade = dataValidade;
        this.estoque = 0;
    }

    public void setCustoMedio(BigDecimal custoMedio) {
        this.custoMedio = custoMedio;
    }

    public Produto() {
        super();
    }

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

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer quantidadeTotal) {
        this.estoque = quantidadeTotal;
    }

    public void setId(Integer id, ParceiroNegocio fabricante) {
        this.id = id;
        this.fabricante = fabricante;
    }
}
