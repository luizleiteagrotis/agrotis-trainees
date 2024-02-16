package com.agrotis.trainees.crud.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "item_nota")
public class ItemNota{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nota_fiscal_id")
    @NotNull(message = "Preencha o campo cabeçalho da nota")
    @JsonBackReference
    private CabecalhoNota cabecalhoNota;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "produto_id")
    @NotNull(message = "Preencha o campo produto.")
    private Produto produto;

    @Column(name = "quantidade")
    @NotNull(message = "A quantidade não pode ser nula")
    @Positive(message = "A quantidade deve ser um valor positivo")
    @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
    private Integer quantidade;

    @Column(name = "preco_unitario")
    @DecimalMin(value = "0.01", message = "O preço unitário deve ser no mínimo 0.01")
    @NotNull(message = "Preencha o campo preço unitário.")
    private Double precoUnitario;

    @Column(name = "valor_total")
    private Double valorTotal;

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

    public Integer getQuantidade() {
        return quantidade;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setValorTotal(Double valorTotal) {
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
