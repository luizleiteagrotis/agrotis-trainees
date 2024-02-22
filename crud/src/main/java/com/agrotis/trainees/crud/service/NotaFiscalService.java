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

    public NotaFiscal salvar(NotaFiscal notaFiscal) {
        return repository.save(notaFiscal);
    }

    public NotaFiscal buscarPorId(Integer integer) {
        return repository.findById(integer).orElseGet(() -> {
            LOG.error("Nota Fiscal não encontrada para id {}.", integer);
            return null;
        });
    }

    public NotaFiscal atualizar(Integer id, NotaFiscal notaFiscal) {
        return repository.findById(id).map(NotaFiscal1 -> {
            NotaFiscal1.setDataNf(notaFiscal.getDataNf());
            NotaFiscal1.setNumeroDaNota(notaFiscal.getNumeroDaNota());
            NotaFiscal1.setNotaFiscalTipo(notaFiscal.getNotaFiscalTipo());
            NotaFiscal1.setParceiroNegocio(notaFiscal.getParceiroNegocio());
            return repository.save(NotaFiscal1);
        }).orElseGet(() -> {
            LOG.error("Nota Fiscal não encontrada para id {}.", id);
            return null;
        });
    }

    public List<NotaFiscal> listarTodos() {
        return repository.findAll();
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("Nota Fiscal deletada com sucesso. ID: {}", id);
    }

    public void atualizarValorTotal(Integer id, Double valorTotalItem) {
        // TODO Auto-generated method stub

    }

}