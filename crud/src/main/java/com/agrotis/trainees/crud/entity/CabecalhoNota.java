package com.agrotis.trainees.crud.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "nota_fiscal")
public class CabecalhoNota {

    public CabecalhoNota() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nota_fiscal_tipo")
    @Enumerated(EnumType.STRING)
    private TipoNota notaFiscalTipo;

    @ManyToOne
    @JoinColumn(name = "parceiro_de_negocio_id")
    private ParceiroNegocio parceiroNegocio;

    @Column(name = "numero_nota", unique = true)
    private Integer numeroDaNota;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    @OneToMany(mappedBy = "notaFiscal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemNota> itens = new ArrayList<>();

    public TipoNota getNotaFiscalTipo() {
        return notaFiscalTipo;
    }

    public void setNotaFiscalTipo(TipoNota notaFiscalTipo) {
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getId() {
        return id;
    }
    

    

    public List<ItemNota> getItens() {
        return itens;
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
        CabecalhoNota other = (CabecalhoNota) obj;
        return Objects.equals(id, other.id);
    }

}
