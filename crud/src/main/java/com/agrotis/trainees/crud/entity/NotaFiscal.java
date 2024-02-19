package com.agrotis.trainees.crud.entity;

import org.springframework.format.annotation.DateTimeFormat;

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

import com.agrotis.trainees.crud.entity.enums.NotaFiscalTipo;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "data_nota")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    @JsonIgnore
    @OneToMany(mappedBy = "notaFiscal", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ItemNotaFiscal> itens = new ArrayList<>();

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private NotaFiscalTipo tipo;

    @Column(name = "numero")
    private Integer numero;

    @ManyToOne
    @JoinColumn(name = "parceiro_negocio_id")
    private ParceiroNegocio parceiroNegocio;

    @Column(name = "valor_total")
    private Double valorTotal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public NotaFiscalTipo getTipo() {
        return tipo;
    }

    public void setTipo(NotaFiscalTipo tipo) {
        this.tipo = tipo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public ParceiroNegocio getParceiroNegocio() {
        return parceiroNegocio;
    }

    public void setParceiroNegocio(ParceiroNegocio parceiroNegocio) {
        this.parceiroNegocio = parceiroNegocio;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemNotaFiscal> getItens() {
        return itens;
    }

}
