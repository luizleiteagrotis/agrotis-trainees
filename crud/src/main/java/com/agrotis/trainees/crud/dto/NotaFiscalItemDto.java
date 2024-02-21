package com.agrotis.trainees.crud.dto;

import java.math.BigDecimal;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.Produto;

public class NotaFiscalItemDto {

    private Integer id;

    private Produto produto;

    private Integer quantidade;

    private BigDecimal precoUnitario;

    private BigDecimal valorTotal;

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

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public BigDecimal getValorTotal() {
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

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setIdNota(NotaFiscal idNota) {
        this.idNota = idNota;
    }

}
