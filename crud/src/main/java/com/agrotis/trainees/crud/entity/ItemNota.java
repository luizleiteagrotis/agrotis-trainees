package com.agrotis.trainees.crud.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item_nota_fiscal")
public class ItemNota {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
    @ManyToOne
    @JoinColumn(name = "nota_fiscal_id")
    private NotaFiscalC notaFiscal;
	
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    
    @Column(name = "quantidade")
    private Integer quantidade;
    
    @Column(name = "preco_unitario")
    private Double precoUnitario;
    
    @Column(name = "valor_total")
    private Double valorTotal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public NotaFiscalC getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscalC notaFiscal) {
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

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        ItemNota other = (ItemNota) obj;
        return Objects.equals(id,  other.id);
        
    }
   

}
