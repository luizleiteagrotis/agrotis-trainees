package com.agrotis.trainees.crud.entity;

import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.agrotis.trainees.crud.dto.NotaFiscalTipoDto;

@Entity
@Table(name = "nota_fiscal_tipo")
public class NotaFiscalTipo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nome;

    public NotaFiscalTipo() {
        super();
    }

    public NotaFiscalTipo(NotaFiscalTipoDto dto) {
        BeanUtils.copyProperties(dto, this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "NotaFiscalTipo [nome=" + nome + "]";
    }

}