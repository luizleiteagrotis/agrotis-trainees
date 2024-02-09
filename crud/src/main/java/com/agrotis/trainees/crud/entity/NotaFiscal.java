package com.agrotis.trainees.crud.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "É necessário um tipo de nota fiscal")
    private String tipo;
    @ManyToOne
    @JoinColumn(name = "id_parceiro")
    @NotNull
    private ParceiroNegocio parceiroNegocio;
    @NotNull
    private int numero;
    @NotNull
    private LocalDate data;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "notaFiscal")
    private List<ItemNotaFiscal> itemNotaFiscal;
    @DecimalMin(value = "0.0", message = "O valor total deve ser maior que 0")
    private BigDecimal valorTotal;

    @Deprecated
    public NotaFiscal() {

    }

    public NotaFiscal(@NotBlank(message = "É necessário um tipo de nota fiscal") String tipo,
                    @NotNull ParceiroNegocio parceiroNegocio, @NotNull int numero, @NotNull LocalDate data) {
        super();
        this.tipo = tipo;
        this.parceiroNegocio = parceiroNegocio;
        this.numero = numero;
        this.data = data;

    }

    public NotaFiscal(@NotBlank(message = "É necessário um tipo de nota fiscal") String tipo, @NotNull int numero,
                    @NotNull LocalDate data) {
        super();
        this.tipo = tipo;
        this.numero = numero;
        this.data = data;
    }

    public NotaFiscal(@NotNull LocalDate data) {
        super();
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ParceiroNegocio getParceiroNegocio() {
        return parceiroNegocio;
    }

    public void setParceiroNegocio(ParceiroNegocio parceiroNegocio) {
        this.parceiroNegocio = parceiroNegocio;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemNotaFiscal> getItemNotaFiscal() {
        return itemNotaFiscal;
    }

}
