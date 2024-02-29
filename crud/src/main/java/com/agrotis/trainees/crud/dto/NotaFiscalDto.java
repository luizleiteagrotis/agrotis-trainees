package com.agrotis.trainees.crud.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;

import enums.TipoNota;

public class NotaFiscalDto {

    private Integer id;

    private TipoNota tipo;

    private ParceiroNegocio parceiro;

    private Integer numero;

    private LocalDate dataEmissao;

    private BigDecimal valorTotal;

    public Integer getId() {
        return id;
    }

    public TipoNota getTipo() {
        return tipo;
    }

    public void setTipo(TipoNota tipo) {
        this.tipo = tipo;
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

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setParceiro(ParceiroNegocio parceiro) {
        this.parceiro = parceiro;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
