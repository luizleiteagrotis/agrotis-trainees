package com.agrotis.trainees.crud.helper;

import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.service.ParceiroNegocioService;

@Component
public class Validador {
    private static ParceiroNegocioService parceiroNegocioService;

    Validador(ParceiroNegocioService parceiroNegocioService) {
        super();
        Validador.parceiroNegocioService = parceiroNegocioService;
    }

    public static boolean validarParceiro(int id) {
        if (parceiroNegocioService.buscarPorId(id) != null) {
            return true;
        } else {
            return false;
        }

    }

}
