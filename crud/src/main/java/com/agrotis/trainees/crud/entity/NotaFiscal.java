package com.agrotis.trainees.crud.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "Obrigatório preencher a data da nota fiscal ")
    private LocalDate data;

    @NotBlank(message = "Obrigatório preencher o numero da nota fiscal")
    private String numero;

    @ManyToOne
    @JoinColumn(name = "id_parceiro_de_negocio")
    private ParceiroNegocio parceiroNegocio;

    @ManyToOne
    @JoinColumn(name = "id_nota_fiscal_tipo")
    private NotaFiscalTipo notaFiscalTipo;

    @OneToMany(mappedBy = "notaFiscal", fetch = FetchType.EAGER)
    private List<ItemNotaFiscal> itens = new ArrayList<>();

    @Digits(integer = 19, fraction = 2)
    private BigDecimal valor_total = BigDecimal.ZERO;

    public Integer getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public ParceiroNegocio getParceiroNegocio() {
        return parceiroNegocio;
    }

    public void setParceiroNegocio(ParceiroNegocio parceiroNegocio) {
        this.parceiroNegocio = parceiroNegocio;
    }

    public NotaFiscalTipo getNotaFiscalTipo() {
        return notaFiscalTipo;
    }

    public void setNotaFiscalTipo(NotaFiscalTipo notaFiscalTipo) {
        this.notaFiscalTipo = notaFiscalTipo;
    }

    public BigDecimal getValor_total() {
        return valor_total;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valor_total = valorTotal;
    }

    public List<ItemNotaFiscal> getItens() {
        return itens;
    }

    public void setItens(List<ItemNotaFiscal> itens) {
        this.itens = itens;
    }

    public NotaFiscal() {
        super();
        this.data = LocalDate.now();
    }

}
