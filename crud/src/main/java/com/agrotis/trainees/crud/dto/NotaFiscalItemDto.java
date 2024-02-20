package com.agrotis.trainees.crud.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.Produto;

public class NotaFiscalItemDto {

    private Integer id;
    private Integer quantidade;
    private Double precoUnitario;
    private Produto produto;

    public NotaFiscalItemDto(NotaFiscalItem item) {
        this.id = item.getId();
        this.quantidade = item.getQuantidade();
        this.precoUnitario = item.getPreco_unitario();
        this.produto = item.getProduto();
    }

    public NotaFiscalItemDto() {
        super();
    }

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

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Double calcularValorTotal() {
        return quantidade * precoUnitario;
    }

    public static List<NotaFiscalItemDto> converter(List<NotaFiscalItem> itens) {
        return itens.stream().map(NotaFiscalItemDto::new).collect(Collectors.toList());
    }
}