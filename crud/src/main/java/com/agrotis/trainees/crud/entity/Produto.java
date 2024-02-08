package com.agrotis.trainees.crud.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private int id;
    @NotBlank(message = "Nome é um campo obrigatorio")
    private String nome;
    private String descricao;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_parceiro", nullable = false)
    private ParceiroNegocio fabricante;
    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "data_fabricacao")
    private LocalDate dataFabricacao;
    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "data_validade")
    private LocalDate dataValidade;
    @DecimalMin(value = "0.0", message = "O estoque deve ser maior que 0")
    private double estoque;

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, LocalDate dataFabricacao, LocalDate dataValidade, ParceiroNegocio fabricante) {
        super();
        this.nome = nome;
        this.dataFabricacao = dataFabricacao;
        this.dataValidade = dataValidade;
        this.fabricante = fabricante;
    }

    public Produto(String nome, LocalDate dataFabricacao, LocalDate dataValidade) {
        super();
        this.nome = nome;
        this.dataFabricacao = dataFabricacao;
        this.dataValidade = dataValidade;
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

    public ParceiroNegocio getFabricante() {
        return fabricante;
    }

    public void setFabricante(ParceiroNegocio parceiroNegocio) {
        this.fabricante = parceiroNegocio;
    }

    public double getEstoque() {
        return estoque;
    }

    public void setEstoque(double estoque) {
        this.estoque = estoque;
    }

}
