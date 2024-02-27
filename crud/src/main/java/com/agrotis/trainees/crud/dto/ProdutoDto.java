package com.agrotis.trainees.crud.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.entity.Produto;
import com.agrotis.trainees.crud.service.ParceiroNegocioTipoService;

public class ProdutoDto {

    private Integer id;
    private String descricao;
    private ParceiroNegocioDto fabricante;
    private LocalDate dataFabricacao;
    private LocalDate dataValidade;
    private Integer estoque;
    private BigDecimal custoMedio;

    public ProdutoDto() {
    }

    public ProdutoDto(Produto produto) {
        super();
        this.id = produto.getId();
        this.descricao = produto.getDescricao();
        this.fabricante = ParceiroNegocioTipoService.converterParaDto(produto.getFabricante());
        this.dataFabricacao = produto.getDataFabricacao();
        this.dataValidade = produto.getDataValidade();
        this.estoque = produto.getEstoque();
        this.custoMedio = produto.getCustoMedio();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ParceiroNegocioDto getFabricante() {
        return fabricante;
    }

    public void setFabricante(ParceiroNegocioDto fabricante) {
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

    public BigDecimal getCustoMedio() {
        return custoMedio;
    }

    public void setCustoMedio(BigDecimal custoMedio) {
        this.custoMedio = custoMedio;
    }

    public static List<ProdutoDto> converter(List<Produto> produtos) {
        return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
    }
}
