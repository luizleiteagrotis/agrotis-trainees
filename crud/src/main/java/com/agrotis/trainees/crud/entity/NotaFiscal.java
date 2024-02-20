package com.agrotis.trainees.crud.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "Obrigatório preencher a data de emissão")
    private LocalDate dataEmissao;

    private Integer numero;

    @Enumerated(EnumType.STRING)
    @Column(name = "nota_fiscal_tipo")
    private NotaFiscalTipo notaFiscalTipo;

    @ManyToOne
    @JoinColumn(name = "parceiro_negocio_id", nullable = false)
    private ParceiroNegocio parceiroNegocio;

    @OneToMany(mappedBy = "notaFiscal", fetch = FetchType.EAGER)
    private List<NotaFiscalItem> itensNota = new ArrayList<>();

    @Column(name = "valor_total")
    private Double valorTotal;

    public Integer getId() {
        return id;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public NotaFiscalTipo getNotaFiscalTipo() {
        return notaFiscalTipo;
    }

    public void setNotaFiscalTipo(NotaFiscalTipo notaFiscalTipo) {
        this.notaFiscalTipo = notaFiscalTipo;
    }

    public ParceiroNegocio getParceiroNegocio() {
        return parceiroNegocio;
    }

    public void setParceiroNegocio(ParceiroNegocio parceiroNegocio) {
        this.parceiroNegocio = parceiroNegocio;
    }

    public List<NotaFiscalItem> getItensNota() {
        return itensNota;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setItensNota(List<NotaFiscalItem> itensNota) {
        this.itensNota = itensNota;
    }

    public void setId(Integer id) {
        this.id = id;

    }
}
