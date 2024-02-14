package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;

@Service
public class NotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalService.class);

    private final NotaFiscalRepository repository;

    public NotaFiscalService(NotaFiscalRepository repository) {
        super();
        this.repository = repository;
    }

    public NotaFiscal salvar(NotaFiscal entidade) {
        return repository.save(entidade);
    }

    public NotaFiscal buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new EntidadeNaoEncontradaException("Nota Fiscal nao encontrada para o ID {} ");
        });
    }

    public NotaFiscal update(Integer id, NotaFiscal fiscal) {
        NotaFiscal byId = repository.findById(id).orElseThrow(() -> {
            throw new EntidadeNaoEncontradaException("A Nota Fiscal nao foi encontrada pelo ID: ");
        });
        return repository.save(byId);
    }

    public List<NotaFiscal> listarTodos() {
        return repository.findAll();
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("Deletado com sucesso!");
    }

}
