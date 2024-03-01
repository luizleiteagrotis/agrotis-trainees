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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "item_nota_fiscal")
public class ItemNotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "Obrigatorio inserir o preco unitario!")
    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;

    @NotNull(message = "Obrigatorio inserir a quantidade!")
    @Column(name = "quantidade")
    private BigDecimal quantidade;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @ManyToOne()
    @JoinColumn(name = "nota_fiscal_id")
    private NotaFiscal notaFiscal;

    @ManyToOne()
    @JoinColumn(name = "produto_id")
    private Produto produto;
    
 
    

	public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
		this.id = id;
	}

	@NotNull(message = "Obrigatorio inserir o preco unitario!")
    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    @NotNull(message = "Obrigatorio inserir a quantidade do produto")
    public BigDecimal getQuantidade() {
        return quantidade;
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
            throw new IllegalArgumentException("O mesmo produto nao pode ser adicionado na nota mais de uma vez!");
        }
        this.produto = produto;
    }
}
