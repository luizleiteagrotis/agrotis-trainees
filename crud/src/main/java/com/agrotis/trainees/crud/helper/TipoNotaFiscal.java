package com.agrotis.trainees.crud.helper;

public enum TipoNotaFiscal {
    ENTRADA("entrada"), SAIDA("Saida");

    private final String descricao;

    TipoNotaFiscal(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
