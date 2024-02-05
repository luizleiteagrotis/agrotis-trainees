package com.agrotis.trainees.crud.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tipo_nota")
    private NotaFiscalTipo notaFiscalTipo;

    @ManyToOne
    @JoinColumn(name = "parceiro_negocio")
    private ParceiroNegocio parceiroNegocio;

    @NotNull(message = "Obrigatório preencher o número da Nota Fiscal")
    @Column(name = "numero_nota")
    private Integer numeroNota;

    @FutureOrPresent(message = "A data não pode ser maior que a data de hoje")
    private LocalDate dataNota;

    @OneToMany(mappedBy = "notaFiscal")
    private List<NotaFiscalItem> itensNota;

    private static Double valorTotal;

    public NotaFiscal() {
        super();
        this.dataNota = LocalDate.now();
        this.itensNota = new ArrayList<>();
    }

    public Integer getId() {
        return id;
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
        this.dataNota = (dataNota == null) ? dataNota : LocalDate.now();
    }

    public List<NotaFiscalItem> getItensNota() {
        return itensNota;
    }

    public void setItensNota(List<NotaFiscalItem> itensNota) {
        this.itensNota = itensNota;
    }

    public static Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        if (itensNota != null) {
            this.valorTotal = atualizarValorTotal();
        } else {
            this.valorTotal = 0.0;
            System.out.println("Valor nulo");
        }
    }

    public void adicionarItem(NotaFiscalItem item) {
        item.setNotaFiscal(this);
        this.itensNota.add(item);
        atualizarValorTotal();
    }

    public void alterarItem(int indice, NotaFiscalItem novoItem) {
        if (indice >= 0 && indice < itensNota.size()) {
            this.itensNota.set(indice, novoItem);
            atualizarValorTotal();
        }
    }

    public void deletarItem(int indice) {
        if (indice >= 0 && indice < itensNota.size()) {
            this.itensNota.remove(indice);
            atualizarValorTotal();
        }
    }

    private Double atualizarValorTotal() {
        if (itensNota != null) {
            double novoValorTotal = 0.0;
            for (NotaFiscalItem item : itensNota) {
                novoValorTotal += item.getValorTotal();
            }
            this.valorTotal = novoValorTotal;
            return novoValorTotal;
        } else {
            this.valorTotal = 0.0;
            System.out.println("Valor nulo!");
            return valorTotal;
        }
    }
}
