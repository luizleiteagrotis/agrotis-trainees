package com.agrotis.trainees.crud.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;

public class NotaFiscalDto {

	private Integer id;
	private NotaFiscalTipo notaFiscalTipo;
	private ParceiroNegocio parceiroNegocio;
	private Integer numeroNota;
    private LocalDate dataNota;
    private List<NotaFiscalItem> itensNota;
    private Double valorTotal;
    
	public NotaFiscalDto() {
		super();
	}

	public NotaFiscalDto(NotaFiscal nota) {
		super();
		this.id = nota.getId();
		this.notaFiscalTipo = nota.getNotaFiscalTipo();
		this.parceiroNegocio = nota.getParceiroNegocio();
		this.numeroNota = nota.getNumeroNota();
		this.dataNota = nota.getDataNota();
		this.itensNota = nota.getItensNota();
		this.valorTotal = nota.getValorTotal();
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

	public Integer getNumeroNota() {
		return numeroNota;
	}

	public void setNumeroNota(Integer numeroNota) {
		this.numeroNota = numeroNota;
	}

	public LocalDate getDataNota() {
		return dataNota;
	}

	public void setDataNota(LocalDate dataNota) {
		this.dataNota = dataNota;
	}

	public List<NotaFiscalItem> getItensNota() {
		return itensNota;
	}

	public void setItensNota(List<NotaFiscalItem> itensNota) {
		this.itensNota = itensNota;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
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
