package com.agrotis.trainees.crud.convert;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.dto.ProdutoDto;
import com.agrotis.trainees.crud.entity.Produto;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProdutoConversor {
    private ObjectMapper modelMapper;

    public ProdutoConversor(ObjectMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProdutoDto converter(Produto produto) {
        return modelMapper.convertValue(produto, ProdutoDto.class);
    }

    public Produto converter(ProdutoDto produtoDto) {
        return modelMapper.convertValue(produtoDto, Produto.class);
    }

    public List<ProdutoDto> converter(List<Produto> produto) {
        return produto.stream().map(this::converter).collect(Collectors.toList());
    }
}
