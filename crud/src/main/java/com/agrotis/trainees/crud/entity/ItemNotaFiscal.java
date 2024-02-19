package com.agrotis.trainees.crud.entity;

import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.agrotis.trainees.crud.dto.ItemNotaFiscalDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "item_nota_fiscal")
public class ItemNotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Min(1)
    private int quantidade;

    @NotNull
    @Digits(integer = 19, fraction = 2)
    @DecimalMin(value = "0.01")
    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;

    @NotNull
    @Digits(integer = 19, fraction = 2)
    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @OneToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_nota_fiscal")
    @JsonIgnore
    private NotaFiscal notaFiscal;

    public ItemNotaFiscal() {
        super();
    }

    public ItemNotaFiscal(ItemNotaFiscalDto dto) {
        BeanUtils.copyProperties(dto, this);
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

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
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

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "ItemNotaFiscal [id=" + id + ", quantidade=" + quantidade + ", valorUnitario=" + valorUnitario + ", valorTotal="
                        + valorTotal + ", produto=" + produto + "]";
    }

}
