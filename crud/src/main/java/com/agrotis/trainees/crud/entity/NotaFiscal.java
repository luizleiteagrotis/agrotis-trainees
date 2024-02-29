package com.agrotis.trainees.crud.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import enums.TipoNota;

@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_tipo")
    @NotNull(message = "O tipo deve ser informado")
    @Enumerated(EnumType.STRING)
    private TipoNota tipo;

    @ManyToOne
    @JoinColumn(name = "id_parceiro")
    @NotNull(message = "O parceiro deve ser informado")
    private ParceiroNegocio parceiro;

    private Integer numero;

    @PastOrPresent(message = "Não é possível informar uma data de emissão futura.")
    private LocalDate dataEmissao;

    @Min(value = 0)
    @Column(name = "valor_total_nota")
    private BigDecimal valorTotal;

    @PrePersist
    private void prePersist() {
        if (this.valorTotal == null) {
            this.valorTotal = new BigDecimal(0);
        }
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public ParceiroNegocio getParceiro() {
        return parceiro;
    }

    public Integer getNumero() {
        return numero;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public TipoNota getTipo() {
        return tipo;
    }

    public void setTipo(TipoNota tipo) {
        this.tipo = tipo;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setParceiro(ParceiroNegocio parceiro) {
        this.parceiro = parceiro;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

}
