package com.agrotis.trainees.crud.service;

import org.springframework.stereotype.Component;

import com.agrotis.trainees.crud.exception.InscricaoExisteException;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;

@Component
public class InscricaoService {

    private final ParceiroNegocioRepository repository;

    public InscricaoService(ParceiroNegocioRepository repository) {
        super();
        this.repository = repository;
    }

    public void verificarInscricao(String inscricaoFiscal) throws InscricaoExisteException {
        if (repository.existsByInscricaoFiscal(inscricaoFiscal) == true) {
            throw new InscricaoExisteException("A inscrição fiscal já existe");
        }
    }

    public String normalizarInscricaoFiscal(String inscricaoFiscal) {
        return inscricaoFiscal.replaceAll("[^0-9]", "");
    }
}
