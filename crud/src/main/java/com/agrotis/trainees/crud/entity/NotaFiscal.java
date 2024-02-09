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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_nf")
    private LocalDate data;

    @Column(name = "nota_fiscal_tipo_id")
    private String notaFiscalTipo;

    @Column(name = "numero_nota")
    private Integer numeroDaNota;

    @ManyToOne
    @JoinColumn(name = "parceiro_de_negocio_id")
    private ParceiroNegocio parceiroNegocio;

    public NotaFiscal() {
    }

    public LocalDate getDataNf() {
        return data;
    }

    public void setDataNf(LocalDate localDate) {
        this.data = localDate;
    }

    public String getNotaFiscalTipo() {
        return notaFiscalTipo;
    }

    public void setNotaFiscalTipo(String notaFiscalTipo) {
        this.notaFiscalTipo = notaFiscalTipo;
    }

    public ParceiroNegocio getParceiroNegocio() {
        return parceiroNegocio;
    }

    public void setParceiroNegocio(ParceiroNegocio parceiroNegocio) {
        this.parceiroNegocio = parceiroNegocio;
    }

    public Integer getNumeroDaNota() {
        return numeroDaNota;
    }

    public void setNumeroDaNota(Integer numeroDaNota) {
        this.numeroDaNota = numeroDaNota;
    }

    public Integer getId() {
        return id;
    }

}