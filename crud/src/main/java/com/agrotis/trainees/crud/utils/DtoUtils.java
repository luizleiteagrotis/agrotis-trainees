package com.agrotis.trainees.crud.utils;

import com.agrotis.trainees.crud.dtos.CabecalhoNotaDto;
import com.agrotis.trainees.crud.dtos.ParceiroNegocioDto;
import com.agrotis.trainees.crud.dtos.ProdutoDto;
import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;

public class DtoUtils {
    
    public static ParceiroNegocio converteParaEntidade(ParceiroNegocioDto dto) {
        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setNome(dto.getNome());
        entidade.setTelefone(dto.getTelefone());
        entidade.setInscricaoFiscal(dto.getInscricaoFiscal());
        entidade.setEndereco(dto.getEndereco());
        return entidade;
    }
    
    public static ParceiroNegocioDto converteParaDto(ParceiroNegocio entidade) {
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setId(entidade.getId());
        dto.setNome(entidade.getNome());
        dto.setTelefone(entidade.getTelefone());
        dto.setInscricaoFiscal(entidade.getInscricaoFiscal());
        dto.setEndereco(entidade.getEndereco());
        return dto;
    }

    public static Produto converteParaEntidade(ProdutoDto dto) {
        Produto entidade = new Produto();
        entidade.setDescricao(dto.getDescricao());
        entidade.setFabricante(dto.getFabricante());
        entidade.setDataValidade(dto.getDataValidade());
        entidade.setDataFabricacao(dto.getDataFabricacao());
        entidade.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        return entidade;
    }
    
    public static ProdutoDto converteParaDto(Produto entidade) {
        ProdutoDto dto = new ProdutoDto();
        dto.setId(entidade.getId());
        dto.setDescricao(entidade.getDescricao());
        dto.setFabricante(entidade.getFabricante());
        dto.setDataValidade(entidade.getDataValidade());
        dto.setDataFabricacao(entidade.getDataFabricacao());
        dto.setQuantidadeEstoque(entidade.getQuantidadeEstoque());
        return dto;
    }
    
    public static CabecalhoNota converteParaEntidade(CabecalhoNotaDto dto) {
        CabecalhoNota entidade = new CabecalhoNota();
        entidade.setNotaFiscalTipo(dto.getNotaFiscalTipo());
        entidade.setData(dto.getData());
        entidade.setNumero(dto.getNumero());
        entidade.setParceiroNegocio(dto.getParceiroNegocio());
        return entidade;
    }
    
    public static CabecalhoNotaDto converteParaDto(CabecalhoNota entidade) {
        CabecalhoNotaDto dto = new CabecalhoNotaDto();
        dto.setId(entidade.getId());
        dto.setNotaFiscalTipo(entidade.getNotaFiscalTipo());
        dto.setData(entidade.getData());
        dto.setNumero(entidade.getNumero());
        dto.setParceiroNegocio(entidade.getParceiroNegocio());
        return dto;
    }
}
