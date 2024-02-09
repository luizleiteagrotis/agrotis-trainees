package com.agrotis.trainees.crud.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "item_nota_fiscal")
public class ItemNotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_produto")
    @NotNull
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_nota_fiscal")
    @NotNull
    private NotaFiscal notaFiscal;
    @NotNull
    private BigDecimal quantidade;
    @Column(name = "preco_unitario")
    @NotNull
    private BigDecimal precoUnitario;
    @Column(name = "valor_total")
    @NotNull
    private BigDecimal valorTotal;

    @Deprecated
    public ItemNotaFiscal() {
    };

    public ItemNotaFiscal(NotaFiscal notaFiscal, Produto produto, BigDecimal quantidade, BigDecimal precoUnitario) {
        super();
        this.notaFiscal = notaFiscal;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public int getId() {
        return id;
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
