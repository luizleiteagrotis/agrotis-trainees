// ProdutoDto.java
package com.agrotis.trainees.crud.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProdutoDto {

    private Integer id;
    private String descricao;
    private BigDecimal estoque;
    private ParceiroNegocioDto fabricante;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFabricacao;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataValidade;

    public ProdutoDto() {
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

    public BigDecimal getEstoque() {
        return estoque;
    }

    public void setEstoque(BigDecimal estoque) {
        this.estoque = estoque;
    }

    public ParceiroNegocioDto getFabricante() {
        return fabricante;
    }

    public void setFabricante(ParceiroNegocioDto parceiroNegocioDto) {
        this.fabricante = parceiroNegocioDto;
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

}