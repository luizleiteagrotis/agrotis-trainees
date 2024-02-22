package com.agrotis.trainees.crud.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;

public class NotaFiscalDto {

    private Integer id;
    private NotaFiscalTipo notaFiscalTipo;
    private ParceiroNegocioDto parceiroNegocio;
    private Integer numero;
    private LocalDate data;
    private List<NotaFiscalItem> itens;
    private BigDecimal valorTotal;

    public NotaFiscalDto() {
        super();
    }

    public NotaFiscalDto(NotaFiscal nota) {
        super();
        this.id = nota.getId();
        this.notaFiscalTipo = nota.getNotaFiscalTipo();
        this.parceiroNegocio = ParceiroNegocioService.converterParaDto(nota.getParceiroNegocio());
        this.numero = nota.getNumero();
        this.data = nota.getData();
        this.itens = nota.getItens();
        this.valorTotal = nota.getValorTotal();
    }

    public NotaFiscalTipo getNotaFiscalTipo() {
        return notaFiscalTipo;
    }

    public void setNotaFiscalTipo(NotaFiscalTipo notaFiscalTipo) {
        this.notaFiscalTipo = notaFiscalTipo;
    }

    public ParceiroNegocioDto getParceiroNegocio() {
        return parceiroNegocio;
    }

    public void setParceiroNegocio(ParceiroNegocioDto parceiroNegocio) {
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

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static List<NotaFiscalDto> converter(List<NotaFiscal> notas) {
        return notas.stream().map(NotaFiscalDto::new).collect(Collectors.toList());
    }

}
