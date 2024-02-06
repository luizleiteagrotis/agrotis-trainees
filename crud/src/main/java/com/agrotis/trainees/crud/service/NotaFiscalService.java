package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;

@Service
public class NotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalService.class);

    private final NotaFiscalRepository repository;

    public NotaFiscalService(NotaFiscalRepository repository) {
        this.repository = repository;
    }

    public NotaFiscal salvar(NotaFiscal notaFiscal) {
        return repository.save(notaFiscal);
    }

    public NotaFiscal buscarPorId(Integer integer) {
        return repository.findById(integer).orElseGet(() -> {
            LOG.error("Nota Fiscal não encontrada para id {}.", integer);
            return null;
        });
    }

    public NotaFiscal atualizar(Integer id, NotaFiscal parceiroNegocio) {
        NotaFiscal byId = repository.findById(id).orElseGet(() -> {
            LOG.info("Não foi possível encontrar a nota fiscal pelo ID {}", id);
            return null;
        });
        return repository.save(byId);
    }

    public List<NotaFiscal> listarTodos() {
        return repository.findAll();
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("Nota Fiscal deletada com sucesso. ID: {}", id);
    }

}
