package com.agrotis.trainees.crud.dto;

import java.time.LocalDate;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;

public class NotaFiscalDto {

    private Long id;
    private Integer numero;
    private LocalDate dataEmissao;
    private NotaFiscalTipo notaFiscalTipo;
    private ParceiroNegocio parceiroNegocio;

    public NotaFiscalDto() {
        super();
    }

    public NotaFiscalDto(NotaFiscal nota) {
        super();
        this.id = nota.getId();
        this.numero = nota.getNumero();
        this.dataEmissao = nota.getDataEmissao();
        this.notaFiscalTipo = nota.getNotaFiscalTipo();
        this.parceiroNegocio = nota.getParceiroNegocio();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
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
}
