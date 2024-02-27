package com.agrotis.trainees.crud.service;

import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;

@Component
public class NumeroService {

    private final NotaFiscalRepository repository;

    public NumeroService(NotaFiscalRepository repository) {
        super();
        this.repository = repository;
    }

    public void gerarNumero(NotaFiscal notaFiscal) {
        int tipo = notaFiscal.getTipo().getId();

        Integer ultimoNumero = obterUltimoNumeroPorTipo(notaFiscal);
        notaFiscal.setNumero((ultimoNumero != null) ? ultimoNumero + 1 : 1);

    }

    @Transactional
    public Integer obterUltimoNumeroPorTipo(NotaFiscal nota) {
        return repository.findMaxNumeroByTipo(nota.getTipo()).orElse(null);
    }
}
