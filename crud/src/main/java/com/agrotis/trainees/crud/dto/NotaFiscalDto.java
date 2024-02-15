package com.agrotis.trainees.crud.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;

public class NotaFiscalDto {
    private int id;
    private String tipo;
    private ParceiroNegocio parceiroNegocio;
    private int numero;
    private LocalDate data;
    private BigDecimal valorTotal;

    public NotaFiscalDto() {
    }

    public NotaFiscalDto(NotaFiscal notaFiscal) {
        super();
        id = notaFiscal.getId();
        tipo = notaFiscal.getTipo();
        parceiroNegocio = notaFiscal.getParceiroNegocio();
        numero = notaFiscal.getNumero();
        data = notaFiscal.getData();
        valorTotal = notaFiscal.getValorTotal();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
