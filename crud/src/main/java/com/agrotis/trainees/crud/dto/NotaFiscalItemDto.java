package com.agrotis.trainees.crud.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.Produto;

public class NotaFiscalItemDto {

    private Integer id;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal valorTotal;
    private Produto produto;

    public NotaFiscalItemDto(NotaFiscalItem item) {
        this.id = item.getId();
        this.quantidade = item.getQuantidade();
        this.precoUnitario = item.getPrecoUnitario();
        this.produto = item.getProduto();
        this.valorTotal = calcularValorTotal();
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

    public BigDecimal calcularValorTotal() {
        return quantidade.multiply(precoUnitario);

    }

    public static List<NotaFiscalItemDto> converter(List<NotaFiscalItem> itens) {
        return itens.stream().map(NotaFiscalItemDto::new).collect(Collectors.toList());
    }
}
