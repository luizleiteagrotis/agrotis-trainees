package com.agrotis.trainees.crud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.agrotis.trainees.crud.service.ItemNotaFiscalService;

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
    private double quantidade;
    @Column(name = "preco_unitario")
    @NotNull
    private double precoUnitario;
    @Column(name = "valor_total")
    @NotNull
    private double valorTotal;

    public ItemNotaFiscal() {
    };

    public ItemNotaFiscal(NotaFiscal notaFiscal, Produto produto, double quantidade, double precoUnitario) {
        super();
        this.notaFiscal = notaFiscal;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.valorTotal = ItemNotaFiscalService.calcularValorTotal(this.quantidade, this.precoUnitario);
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

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
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

    public void setValorTotal() {
        this.valorTotal = ItemNotaFiscalService.calcularValorTotal(this.quantidade, this.precoUnitario);
    }
}
