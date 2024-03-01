package com.agrotis.trainees.crud.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.enums.NotaFiscalTipo;

public class NotaFiscalDto {

    private Integer id;

    private LocalDate data;

    private NotaFiscalTipo tipo;

    private Integer numero;

    private ParceiroNegocio parceiroNegocio;

    private BigDecimal valorTotal;
    



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

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

}
