package com.agrotis.trainees.crud.dto;

public class NotaFiscalItemDto {

    private Integer id;
    private Integer quantidade;
    private Double preco_unitario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco_unitario() {
        return preco_unitario;
    }

    public void setPreco_unitario(Double preco_unitario) {
        this.preco_unitario = preco_unitario;
    }
}
