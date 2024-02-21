package com.agrotis.trainees.crud.wrapper;

import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.Produto;

@Component
public class ProdutoWrapper implements ConversorProduto {

    public ProdutoDto converterParaDto(Produto entidade) {
        ProdutoDto dto = new ProdutoDto();
        dto.setId(entidade.getId());
        dto.setDescricao(entidade.getDescricao());
        dto.setIdParceiro(entidade.getIdParceiro());
        dto.setDataFabricacao(entidade.getDataFabricacao());
        dto.setDataValidade(entidade.getDataValidade());
        dto.setEstoque(entidade.getEstoque());

        return dto;
    }

    public Produto converterParaEntidade(ProdutoDto dto) {
        Produto entidade = new Produto();
        entidade.setId(dto.getId());
        entidade.setDescricao(dto.getDescricao());
        entidade.setIdParceiro(dto.getIdParceiro());
        entidade.setDataFabricacao(dto.getDataFabricacao());
        entidade.setDataValidade(dto.getDataValidade());
        entidade.setEstoque(dto.getEstoque());

        return entidade;
    }
}
