package com.agrotis.trainees.crud.convert;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class NotaFiscalConversor {
    private ObjectMapper modelMapper;

    public NotaFiscalConversor(ObjectMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public NotaFiscalDto converter(NotaFiscal notaFiscal) {
        return modelMapper.convertValue(notaFiscal, NotaFiscalDto.class);
    }

    public NotaFiscal converter(NotaFiscalDto notaFiscalDto) {
        return modelMapper.convertValue(notaFiscalDto, NotaFiscal.class);
    }

    public List<NotaFiscalDto> converter(List<NotaFiscal> notasFiscais) {
        return notasFiscais.stream().map(this::converter).collect(Collectors.toList());
    }
}
