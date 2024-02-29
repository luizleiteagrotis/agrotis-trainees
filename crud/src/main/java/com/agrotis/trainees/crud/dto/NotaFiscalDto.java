package com.agrotis.trainees.crud.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;

public class NotaFiscalDto {

    private Integer id;
    private NotaFiscalTipo notaFiscalTipo;
    private ParceiroNegocioDto parceiroNegocio;
    private Integer numero;
    private LocalDate dataEmissao;
    private List<NotaFiscalItem> itensNota;
    private BigDecimal valorTotal;

    public NotaFiscalDto() {
        super();
    }

    public NotaFiscalDto(NotaFiscal nota) {
        this.id = nota.getId();
        this.notaFiscalTipo = nota.getNotaFiscalTipo();
        this.parceiroNegocio = (nota.getParceiroNegocio() != null) ? new ParceiroNegocioDto(nota.getParceiroNegocio()) : null;
        this.numero = nota.getNumero();
        this.dataEmissao = nota.getDataEmissao();
        this.itensNota = new ArrayList<>(Objects.requireNonNull(nota.getItensNota()));
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

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public List<NotaFiscalItem> getItensNota() {
        return itensNota;
    }

    public void setItensNota(List<NotaFiscalItem> itensNota) {
        this.itensNota = itensNota;
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