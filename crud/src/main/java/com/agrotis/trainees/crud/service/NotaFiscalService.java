package com.agrotis.trainees.crud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;

@Service
public class NotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalService.class);

    private final NotaFiscalRepository repository;

    public NotaFiscalService(NotaFiscalRepository repository) {
        this.repository = repository;
    }

    public NotaFiscal salvar(NotaFiscal entidade) {
        return repository.save(entidade);
    }

    public List<NotaFiscal> buscarTodos() {
        return repository.findAll();
    }

    public NotaFiscal buscaPeloId(Integer id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.info("Não foi possível buscar pelo id {}", id);
            return null;
        });
    }

    public static Logger getLog() {
        return LOG;
    }
}