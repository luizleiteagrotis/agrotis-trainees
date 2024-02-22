package com.agrotis.trainees.crud.entity;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "item_nota")
public class ItemNota{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "nota_fiscal_id")
    @NotNull(message = "Preencha o campo cabeçalho da nota")
    private CabecalhoNota cabecalhoNota;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    @NotNull(message = "Preencha o campo produto.")
    private Produto produto;

    @Column(name = "quantidade")
    @NotNull(message = "A quantidade não pode ser nula")
    @Positive(message = "A quantidade deve ser um valor positivo")
    @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
    private BigDecimal quantidade;

    @Column(name = "preco_unitario")
    @DecimalMin(value = "0.01", message = "O preço unitário deve ser no mínimo 0.01")
    @NotNull(message = "Preencha o campo preço unitário.")
    private BigDecimal precoUnitario;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

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

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public Integer getId() {
        return id;
    }
    

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ItemNota other = (ItemNota) obj;
        return Objects.equals(id, other.id);
    }

}
