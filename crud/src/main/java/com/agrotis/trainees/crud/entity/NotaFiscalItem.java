package com.agrotis.trainees.crud.entity;

import java.util.Objects;

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
    @JoinColumn(name = "nota_fiscal_id")
    private NotaFiscal notaFiscal;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @NotNull(message = "Necessário inserir a quantidade do produto")
    private Integer quantidade;

    @NotNull(message = "Necessário inserir o preço unitário")
    private Double preco_unitario;

    private Double valorTotal;

    public NotaFiscalItem() {
        super();
        this.valorTotal = 0.0;
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
        if (Objects.equals(this.produto, produto)) {
            throw new IllegalArgumentException("O mesmo produto não pode ser adicionado mais de uma vez na nota fiscal.");
        }
        this.produto = produto;
    }

    @NotNull(message = "Necessário inserir a quantidade do produto")
    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @NotNull(message = "Necessário inserir o preço unitário")
    public Double getPreco_unitario() {
        return preco_unitario;
    }

    public void setPreco_unitario(Double preco_unitario) {
        this.preco_unitario = preco_unitario;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
