package com.agrotis.trainees.crud.service;

import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.entity.Produto;

@Component
public class ProdutoConversaoService {

    public ProdutoDto converterParaDto(Produto entidade) {
        ProdutoDto dto = new ProdutoDto();
        dto.setId(entidade.getId());
        dto.setDescricao(entidade.getDescricao());
        dto.setFabricante(converterFabricanteParaDto(entidade.getFabricante()));
        dto.setDataFabricacao(entidade.getDataFabricacao());
        dto.setDataValidade(entidade.getDataValidade());
        dto.setEstoque(entidade.getEstoque());
        dto.setCustoMedio(entidade.getCustoMedio());

        return dto;
    }

    public Produto converterParaEntidade(ProdutoDto dto) {
        Produto entidade = new Produto();
        entidade.setId(dto.getId());
        entidade.setDescricao(dto.getDescricao());
        entidade.setFabricante(converterFabricanteParaEntidade(dto.getFabricante()));
        entidade.setDataFabricacao(dto.getDataFabricacao());
        entidade.setDataValidade(dto.getDataValidade());
        entidade.setEstoque(dto.getEstoque());
        entidade.setCustoMedio(dto.getCustoMedio());

        return entidade;
    }

    private ParceiroNegocioDto converterFabricanteParaDto(ParceiroNegocio entidade) {
        return ParceiroNegocioTipoService.converterParaDto(entidade);
    }

    private ParceiroNegocio converterFabricanteParaEntidade(ParceiroNegocioDto dto) {
        return ParceiroNegocioTipoService.converterParaEntidade(dto);
    }
}
