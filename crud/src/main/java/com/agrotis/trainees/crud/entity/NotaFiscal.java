package com.agrotis.trainees.crud.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "É necessário um tipo de nota fiscal")
    private String tipo;
    @ManyToOne
    @JoinColumn(name = "id_parceiro")
    private ParceiroNegocio parceiroNegocio;
    private int numero;
    private Date data;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "notaFiscal")
    private List<ItemNotaFiscal> itemNotaFiscal;
    private Double valorTotal;

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ParceiroNegocio getParceiroNegocio() {
        return parceiroNegocio;
    }

    public void setParceiroNegocio(ParceiroNegocio parceiroNegocio) {
        this.parceiroNegocio = parceiroNegocio;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemNotaFiscal> getItemNotaFiscal() {
        return itemNotaFiscal;
    }

}
