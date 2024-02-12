package com.agrotis.trainees.crud.entity;

import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;

@Entity
@Table(name = "parceiro_negocio")
public class ParceiroNegocio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Obrigatório preencher o nome do tipo de parceiro de negócio")
    private String nome;

    @NotBlank(message = "Obrigatório preencher a inscrição fiscal do tipo de parceiro de negócio")
    @Column(name = "inscricao_fiscal")
    private String inscricaoFiscal;

    @NotBlank(message = "Obrigatório preencher o endereço do tipo de parceiro de negócio")
    private String endereco;

    @NotBlank(message = "Obrigatório preencher o telefone do tipo de parceiro de negócio")
    @Pattern(regexp = "\\(?(\\d{2})\\)?[\\s-]?(\\d{5})[\\s-]?(\\d{4})", message = "Número de telefone inválido")
    private String telefone;

    public ParceiroNegocio() {
        super();
    }

    public ParceiroNegocio(ParceiroNegocioDto dto) {
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

    public String getInscricaoFiscal() {
        return inscricaoFiscal;
    }

    public void setInscricaoFiscal(String inscricaoFiscal) {
        this.inscricaoFiscal = inscricaoFiscal;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "ParceiroNegocio [id=" + id + ", nome=" + nome + "]";
    }

}
