package com.agrotis.trainees.crud.convert;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.dto.NotaFiscalDto;
import com.agrotis.trainees.crud.entity.NotaFiscal;

@Component
public class NotaFiscalDtoConversor {
    private ModelMapper modelMapper;

    public NotaFiscalDtoConversor(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public NotaFiscalDto converterNotaFiscalParaNotaFiscalDto(NotaFiscal notaFiscal) {
        return modelMapper.map(notaFiscal, NotaFiscalDto.class);
    }

    public NotaFiscal converterNotaFiscalDtoParaNotaFiscal(NotaFiscalDto notaFiscalDto) {
        return modelMapper.map(notaFiscalDto, NotaFiscal.class);
    }

    public List<NotaFiscalDto> converterNotasFiscaisParaNotasFiscaisDto(List<NotaFiscal> notasFiscais) {
        return notasFiscais.stream().map(this::converterNotaFiscalParaNotaFiscalDto).collect(Collectors.toList());
    }
}
