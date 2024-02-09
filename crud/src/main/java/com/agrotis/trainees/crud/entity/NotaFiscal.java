package com.agrotis.trainees.crud.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    @Column(name = "tipo_nota")
    @Enumerated(EnumType.STRING)
    private NotaFiscalTipo notaFiscalTipo;

    @ManyToOne
    @JoinColumn(name = "parceiro_negocio")
    private ParceiroNegocio parceiroNegocio;

    @NotNull(message = "Obrigatório preencher o número da Nota Fiscal")
    @Column(name = "numero_nota")
    private Integer numeroNota;

    private LocalDate dataNota;

    @OneToMany(mappedBy = "notaFiscal")
    private List<NotaFiscalItem> itensNota;

    private Double valorTotal;

    public NotaFiscal() {
        super();
        this.dataNota = LocalDate.now();
        this.itensNota = new ArrayList<>();
        this.valorTotal = 0.0;
    }

    public NotaFiscal(NotaFiscalTipo notaFiscalTipo, ParceiroNegocio parceiroNegocio,
                    @NotNull(message = "Obrigatório preencher o número da Nota Fiscal") Integer numeroNota, LocalDate dataNota) {
        super();
        this.notaFiscalTipo = notaFiscalTipo;
        this.parceiroNegocio = parceiroNegocio;
        this.numeroNota = numeroNota;
        this.dataNota = dataNota;
        this.itensNota = new ArrayList<>();
        this.valorTotal = 0.0;
    }

    public Integer getId() {
        return id;
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

    public Integer getNumeroNota() {
        return numeroNota;
    }

    public void setNumeroNota(Integer numeroNota) {
        this.numeroNota = numeroNota;
    }

    public LocalDate getDataNota() {
        return dataNota;
    }

    public void setDataNota(LocalDate dataNota) {
        this.dataNota = dataNota;
    }

    public List<NotaFiscalItem> getItensNota() {
        return itensNota;
    }

    public void setItensNota(List<NotaFiscalItem> itensNota) {
        this.itensNota = itensNota;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

}