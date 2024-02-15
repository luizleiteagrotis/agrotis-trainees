package com.agrotis.trainees.crud.convert;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.dto.ItemNotaFiscalDto;
import com.agrotis.trainees.crud.entity.ItemNotaFiscal;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ItemNotaFiscalConversor {
    private ObjectMapper modelMapper;

    public ItemNotaFiscalConversor(ObjectMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ItemNotaFiscalDto converter(ItemNotaFiscal itemNotaFiscal) {
        return modelMapper.convertValue(itemNotaFiscal, ItemNotaFiscalDto.class);
    }

    public ItemNotaFiscal converter(ItemNotaFiscalDto itemNotaFiscalDto) {
        return modelMapper.convertValue(itemNotaFiscalDto, ItemNotaFiscal.class);
    }

    public List<ItemNotaFiscalDto> converter(List<ItemNotaFiscal> itensNotaFiscal) {
        return itensNotaFiscal.stream().map(this::converter).collect(Collectors.toList());
    }

}
