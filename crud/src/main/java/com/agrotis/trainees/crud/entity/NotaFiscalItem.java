package com.agrotis.trainees.crud.entity;

import java.util.Optional;

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
@Table(name = "nota_fiscal_item")
public class NotaFiscalItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "nota_fiscal")
    private NotaFiscal notaFiscal;

    @ManyToOne
    @JoinColumn(name = "produto")
    private Produto produto;

    @NotNull(message = "Necessário inserir a quantidade do produto")
    private Integer quantidade;

    @NotNull(message = "Necessário inserir o preço unitário")
    private double precoUnitario;

    @Column(name = "valor_total")
    private double valorTotal;

    public NotaFiscalItem() {
        super();
    }

    public NotaFiscalItem(NotaFiscal notaFiscal, Produto produto,
                    @NotNull(message = "Necessário inserir a quantidade do produto") Integer quantidade,
                    @NotNull(message = "Necessário inserir o preço unitário") double precoUnitario) {
        super();
        this.notaFiscal = notaFiscal;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public NotaFiscalItem(Optional<NotaFiscal> notaFiscal2, Produto produtoSet, Integer quantidade2, Double precoUnitario2,
                    Double valorTotal2) {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

}