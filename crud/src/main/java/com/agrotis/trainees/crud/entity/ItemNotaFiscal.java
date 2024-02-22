package com.agrotis.trainees.crud.entity;

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
public class ItemNotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "nota_fiscal")
    private NotaFiscal notaFiscal;

    @ManyToOne
    @JoinColumn(name = "produto")
    private Produto produto;

    @NotNull(message = "Insira a qtd de produtos")
    @JoinColumn(name = "quantidade")
    private Integer quantidade;

    @NotNull(message = "Insira o valor unitário")
    @JoinColumn(name = "preco_unitario")
    private float preco_unitario;

    @JoinColumn(name = "valor_total")
    private float valorTotal = 0;

    // Inicio Getters e Setters
    public ItemNotaFiscal() {
    }

    public Integer getId() {
        return id;
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

    public float getPreco_unitario() {
        return preco_unitario;
    }

    public void setPreco_unitario(float preco_unitario) {
        this.preco_unitario = preco_unitario;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

}
