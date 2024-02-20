package com.agrotis.trainees.crud.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "nota_fiscal_item")
public class NotaFiscalItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    private BigDecimal quantidade;

    @Column(name = "preco_unitario")
    // @Digits(integer = 19, fraction = 2)
    private BigDecimal precoUnitario;

    @Min(value = 0)
    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @PrePersist
    private void prePersist() {
        if (this.valorTotal == null) {
            this.valorTotal = new BigDecimal(0);
        }
    }

    @ManyToOne
    @JoinColumn(name = "id_nota")
    private NotaFiscal idNota;

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public NotaFiscal getIdNota() {
        return idNota;
    }

    public void setIdNota(NotaFiscal idNota) {
        this.idNota = idNota;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

}
