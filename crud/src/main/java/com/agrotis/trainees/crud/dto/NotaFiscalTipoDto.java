package com.agrotis.trainees.crud.dto;

import org.springframework.beans.BeanUtils;

import com.agrotis.trainees.crud.entity.NotaFiscalTipo;

public class NotaFiscalTipoDto {

    private Integer id;
    private String nome;

    public NotaFiscalTipoDto() {
        super();
    }

    public NotaFiscalTipoDto(NotaFiscalTipo entidade) {
        BeanUtils.copyProperties(entidade, this);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
