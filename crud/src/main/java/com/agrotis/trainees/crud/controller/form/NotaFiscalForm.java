package com.agrotis.trainees.crud.controller.form;

import java.time.LocalDate;
import java.util.List;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;

public class NotaFiscalForm {

	private NotaFiscalTipo tipo;
	private ParceiroNegocio parceiro;
	private Integer numero;
	private LocalDate data;
	private List<NotaFiscalItem> itens;
	private Double valorTotal;
	
	public NotaFiscalTipo getTipo() {
		return tipo;
	}
	
	public void setTipo(NotaFiscalTipo tipo) {
		this.tipo = tipo;
	}
	
	public ParceiroNegocio getParceiro() {
		return parceiro;
	}
	
	public void setParceiro(ParceiroNegocio parceiro) {
		this.parceiro = parceiro;
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
	
	public Double getValorTotal() {
		return valorTotal;
	}
	
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public NotaFiscal converter(ParceiroNegocioRepository parceiroRepository) {
		ParceiroNegocio parceiroNegocio = parceiroRepository.findByNome(parceiro.getNome());
		return new NotaFiscal(tipo, parceiroNegocio, numero, data, itens, valorTotal);
	}
	
}
