package com.agrotis.trainees.crud.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.Produto;

public class ItemNfDto {

    private Integer id;
    private Produto produto;
    private NotaFiscal notaFiscal;
    private Integer quantidade;
    private double precoUnitario;
    private double valorTotal;

    public ItemNfDto() {
        super();
    }

    public ItemNfDto(ItemNotaFiscal item) {
        super();
        this.id = item.getId();
        this.notaFiscal = item.getNotaFiscal();
        this.produto = item.getProduto();
        this.quantidade = item.getQuantidade();
        this.precoUnitario = item.getPreco_unitario();
        this.valorTotal = item.getValorTotal();
    }

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
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

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static List<ItemNfDto> converter(List<ItemNotaFiscal> itens) {
        return itens.stream().map(ItemNfDto::new).collect(Collectors.toList());
    }

}
