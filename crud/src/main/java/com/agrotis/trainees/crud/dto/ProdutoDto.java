package com.agrotis.trainees.crud.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.service.ParceiroNegocioService;

public class ProdutoDto {

    private Integer id;
    private String nome;
    private String descricao;
    private ParceiroNegocioDto parceiroNegocio;
    private String fabricante;
    private LocalDate dataFabricacao;
    private LocalDate dataValidade;
    private Integer estoque;

    public ProdutoDto() {
        super();
    }

    public ProdutoDto(Produto produto) {
        super();
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.parceiroNegocio = ParceiroNegocioService.converterParaDto(produto.getParceiroNegocio());
        this.fabricante = produto.getFabricante();
        this.dataFabricacao = produto.getDataFabricacao();
        this.dataValidade = produto.getDataValidade();
        this.estoque = produto.getEstoque();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ParceiroNegocioDto getParceiroNegocio() {
        return parceiroNegocio;
    }

    public void setParceiroNegocio(ParceiroNegocioDto parceiroNegocio) {
        this.parceiroNegocio = parceiroNegocio;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static List<ProdutoDto> converter(List<Produto> produtos) {
        return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
    }

}
