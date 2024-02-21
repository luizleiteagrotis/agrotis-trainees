package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.CabecalhoNotaFiscal;
import com.agrotis.trainees.crud.repository.CabecalhoNotaFiscalRepository;

@Service
public class CabecalhoNotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(ParceiroNegocioService.class);

    private final CabecalhoNotaFiscalRepository repository;

    public CabecalhoNotaFiscalService(CabecalhoNotaFiscalRepository repository) {
        this.repository = repository;
    }

    public CabecalhoNotaFiscal salvar(CabecalhoNotaFiscal entidade) {
        return repository.save(entidade);
    }

}
