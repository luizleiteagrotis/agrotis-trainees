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

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private NotaFiscalTipo tipo;

    @ManyToOne
    @JoinColumn(name = "parceiro_negocio")
    private ParceiroNegocio parceiroNegocio;

    @NotNull(message = "Obrigatório preencher o número da Nota Fiscal")
    @Column(name = "numero")
    private Integer numero;

    private LocalDate data;

    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER)
    private List<NotaFiscalItem> itens;

    private Double valorTotal;

    public NotaFiscal() {
        super();
        // this.dataNota = LocalDate.now();
        // this.itensNota = new ArrayList<>();
        // this.numero = 0;
        this.valorTotal = 0.0;
    }

    public NotaFiscal(NotaFiscalTipo notaFiscalTipo, ParceiroNegocio parceiroNegocio, Integer numero, LocalDate dataNota) {
        super();
        this.tipo = notaFiscalTipo;
        this.parceiroNegocio = parceiroNegocio;
        this.numero = numero;
        this.data = dataNota;
        this.itens = new ArrayList<>();
        this.valorTotal = 0.0;
    }

    public NotaFiscal(NotaFiscalTipo tipo, ParceiroNegocio parceiro, Integer numero, LocalDate data, List<NotaFiscalItem> itens,
                    Double valorTotal2) {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public NotaFiscalTipo getNotaFiscalTipo() {
        return tipo;
    }

    public void setNotaFiscalTipo(NotaFiscalTipo notaFiscalTipo) {
        this.tipo = notaFiscalTipo;
    }

    public ParceiroNegocio getParceiroNegocio() {
        return parceiroNegocio;
    }

    public void setParceiroNegocio(ParceiroNegocio parceiroNegocio) {
        this.parceiroNegocio = parceiroNegocio;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<NotaFiscalItem> getItens() {
        return itens;
    }

    public void setItens(List<NotaFiscalItem> itens) {
        this.itens = itens;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

}