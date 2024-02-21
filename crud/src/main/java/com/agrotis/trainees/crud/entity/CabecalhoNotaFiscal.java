package com.agrotis.trainees.crud.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cabecalho_nota_fiscal")

public class CabecalhoNotaFiscal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "Obrigatório preencher o tipo da nota")
    private String tipoNota;

    @ManyToOne
    @JoinColumn(name = "id_parceiro")
    @NotNull(message = "Obrigatório preencher o parceiro que emitiu a nota")
    private ParceiroNegocio parceiro;

    @NotNull(message = "Atributo numero obrigatorio")
    private Integer numero;

    private Date dataEmissao;

    public String getTipoNota() {
        return tipoNota;
    }

    public void setTipoNota(String tipoNota) {
        this.tipoNota = tipoNota;
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

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date data) {
        this.dataEmissao = data;
    }

    public Integer getId() {
        return id;
    }
}
