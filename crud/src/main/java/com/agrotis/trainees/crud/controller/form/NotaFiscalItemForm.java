package com.agrotis.trainees.crud.controller.form;

import java.util.Optional;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.NotaFiscalItem;
import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;
import com.agrotis.trainees.crud.repository.ProdutoRepository;

public class NotaFiscalItemForm {

	private NotaFiscal nota;
	private Produto produto;
	private Integer quantidade;
	private Double precoUnitario;
	private Double valorTotal;
	
	public NotaFiscal getNota() {
		return nota;
	}
	
	public void setNota(NotaFiscal nota) {
		this.nota = nota;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public Double getPrecoUnitario() {
		return precoUnitario;
	}
	
	public void setPrecoUnitario(Double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	
	public Double getValorTotal() {
		return valorTotal;
	}
	
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public NotaFiscalItem converter(NotaFiscalRepository notaRepository, ProdutoRepository produtoRepository) {
		Optional<NotaFiscal> notaFiscal = notaRepository.findByNumeroNota(nota.getNumeroNota());
		Produto produtoSet = produtoRepository.findByNome(produto.getNome());
		return new NotaFiscalItem (notaFiscal, produtoSet, quantidade, precoUnitario, valorTotal);
	}
	
}
