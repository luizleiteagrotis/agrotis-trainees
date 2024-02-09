package com.agrotis.trainees.crud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.CabecalhoNota;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;

@Service
public class CabecalhoNotaService {

    private static final Logger LOG = LoggerFactory.getLogger(CabecalhoNotaService.class);

    private final NotaFiscalRepository repository;

    public CabecalhoNotaService(NotaFiscalRepository repository) {
        this.repository = repository;
    }

    public CabecalhoNota salvar(CabecalhoNota entidade) {
        return repository.save(entidade);
    }

    public CabecalhoNota buscarPorId(Integer id) {
        return repository.findById(id)
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada pelo ID: " + id));
    }

    public CabecalhoNota atualizar(Integer id, CabecalhoNota cabecalhoNota) {
        return repository.findById(id).map(cabecalhoNotaExistente -> {
            cabecalhoNotaExistente.setData(cabecalhoNota.getData());
            cabecalhoNotaExistente.setNotaFiscalTipo(cabecalhoNota.getNotaFiscalTipo());
            cabecalhoNotaExistente.setNumero(cabecalhoNota.getNumero());
            cabecalhoNotaExistente.setParceiroNegocio(cabecalhoNota.getParceiroNegocio());
            return repository.save(cabecalhoNotaExistente);
        }).orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada pelo ID: " + id));
    }

    public List<CabecalhoNota> listarTodos() {
        return repository.findAll();
    }

    public void deletarPorId(Integer id) {
        repository.findById(id).map(entidade -> {
            repository.deleteById(id);
            LOG.info("Deletado com sucesso");
            return entidade;
        }).orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada pelo ID: " + id));
    }

}
