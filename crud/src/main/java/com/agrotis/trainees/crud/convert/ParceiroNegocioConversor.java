package com.agrotis.trainees.crud.convert;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ParceiroNegocioConversor {
    private ObjectMapper modelMapper;

    public ParceiroNegocioConversor(ObjectMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ParceiroNegocioDto converter(ParceiroNegocio parceiroNegocio) {
        return modelMapper.convertValue(parceiroNegocio, ParceiroNegocioDto.class);
    }

    public ParceiroNegocio converter(ParceiroNegocioDto parceiroNegocioDto) {
        return modelMapper.convertValue(parceiroNegocioDto, ParceiroNegocio.class);
    }

    public List<ParceiroNegocioDto> converter(List<ParceiroNegocio> parceirosNegocio) {
        return parceirosNegocio.stream().map(this::converter).collect(Collectors.toList());
    }
}
