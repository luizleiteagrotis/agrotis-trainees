package com.agrotis.trainees.crud.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_tipo")
    @NotNull(message = "O tipo deve ser informado")
    private NotaFiscalTipo tipo;

    @ManyToOne
    @JoinColumn(name = "id_parceiro")
    @NotNull(message = "O parceiro deve ser informado")
    private ParceiroNegocio parceiro;

    private Integer numero;

    @PastOrPresent(message = "Não é possível informar uma data de emissão futura.")
    private LocalDate dataEmissao;

    @Column(name = "valor_total_nota")
    private Double valorTotalNota;

    public Integer getId() {
        return id;
    }

    public Double getValorTotalNota() {
        return valorTotalNota;
    }

    public void setValorTotalNota(Double valorTotalNota) {
        this.valorTotalNota = valorTotalNota;
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

    public NotaFiscalTipo getTipo() {
        return tipo;
    }

    public void setTipo(NotaFiscalTipo tipo) {
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
