package com.agrotis.trainees.crud.dtos;

import java.math.BigDecimal;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.Produto;

public class ItemNotaDto {

    private Integer id;

    private CabecalhoNota cabecalhoNota;

    private Produto produto;

    private BigDecimal quantidade;

    private BigDecimal precoUnitario;

    private BigDecimal valorTotal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CabecalhoNota getCabecalhoNota() {
        return cabecalhoNota;
    }

    public void setCabecalhoNota(CabecalhoNota cabecalhoNota) {
        this.cabecalhoNota = cabecalhoNota;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

}
