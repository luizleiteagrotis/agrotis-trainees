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

	public Integer getId() {
		return id;	
	}
	
	public NotaFiscalTipo getNotaFiscalTipo() {
		return notaFiscalTipo;
	}
	
	public ParceiroNegocio getParceiroNegocio() {
		return parceiroNegocio;
	}
	
	public Integer getNumeroNota() {
		return numeroNota;
	}
	
	public LocalDate getDataNota() {
		return dataNota;
	}
	
	public List<NotaFiscalItem> getItensNota() {
		return itensNota;
	}
	
	public Double getValorTotal() {
		return valorTotal;
	}
	
	public static List<NotaFiscalDto> converter(List<NotaFiscal> notas) {
		return notas.stream().map(NotaFiscalDto::new).collect(Collectors.toList());
	}
       
}
