package com.agrotis.trainees.crud.dto;

import java.time.LocalDate;

import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;

public class NotaFiscalDto {

    private Integer id;

    private NotaFiscalTipo tipo;

    private ParceiroNegocio parceiro;

    private Integer numero;

    private LocalDate dataEmissao;

    private Double valorTotal;

    public Integer getId() {
        return id;
    }

    public NotaFiscalTipo getTipo() {
        return tipo;
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

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTipo(NotaFiscalTipo tipo) {
        this.tipo = tipo;
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

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
