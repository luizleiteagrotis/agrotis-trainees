package com.agrotis.trainees.crud.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Obrigatório inserir o nome do produto")
    private String nome;

    @NotBlank(message = "Obrigatório preencher a descrição do produto")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "parceiro_negocio")
    private ParceiroNegocio parceiroNegocio;

    @NotBlank(message = "Obrigatório preencher o fabricante do produto")
    private String fabricante;

    @PastOrPresent(message = "A data de fabricação deve ser de datas passadas")
    private LocalDate dataFabricacao;

    private LocalDate dataValidade;

    @Min(value = 0)
    private Integer estoque;

    public Produto() {
        super();
        this.estoque = 0;
    }

    public Produto(String nome, String descricao, ParceiroNegocio parceiroNegocio, String fabricante, LocalDate dataFabricacao,
                    LocalDate dataValidade) {
        super();
        this.nome = nome;
        this.descricao = descricao;
        this.parceiroNegocio = parceiroNegocio;
        this.fabricante = fabricante;
        this.dataFabricacao = dataFabricacao;
        this.dataValidade = dataValidade;
        this.estoque = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public ParceiroNegocio getParceiroNegocio() {
        return parceiroNegocio;
    }

    public void setParceiroNegocio(ParceiroNegocio parceiroNegocio) {
        this.parceiroNegocio = parceiroNegocio;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
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

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

}
