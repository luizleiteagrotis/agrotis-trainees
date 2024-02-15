package com.agrotis.trainees.crud.dto;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.Produto;

public class NotaFiscalItemDto {

    private Integer id;

    private Produto produto;

    private Integer quantidade;

    private double precoUnitario;

    private Double valorTotal;

    private NotaFiscal idNota;

    public Integer getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public NotaFiscal getIdNota() {
        return idNota;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setIdNota(NotaFiscal idNota) {
        this.idNota = idNota;
    }

}
