package com.agrotis.trainees.crud.dtos;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.Produto;

public class ItemNotaDto {
    
    private Integer id;

    private CabecalhoNota cabecalhoNota;

    private Produto produto;

    private Integer quantidade;

    private Double precoUnitario;
    
    private Double valorTotal;

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

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    
    
    


}
