package com.agrotis.trainees.crud.dto;

import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.Produto;

public class ItemNotaFiscalDto {

    private Integer id;

    private int quantidade;

    private BigDecimal valorUnitario;

    private BigDecimal valorTotal;

    private Produto produto;

    private NotaFiscal notaFiscal;

    public ItemNotaFiscalDto() {
        super();
    }

    public ItemNotaFiscalDto(ItemNotaFiscal entidade) {
        BeanUtils.copyProperties(entidade, this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal() {
        this.valorTotal = this.valorUnitario.multiply(BigDecimal.valueOf(this.quantidade));
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

}
