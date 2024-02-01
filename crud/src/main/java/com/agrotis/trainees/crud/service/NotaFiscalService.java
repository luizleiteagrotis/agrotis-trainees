package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

import com.agrotis.trainees.crud.entity.NotaFiscal;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.helper.Validador;
import com.agrotis.trainees.crud.repository.NotaFiscalRepository;

@Service
public class NotaFiscalService {

    private static final Logger LOG = LoggerFactory.getLogger(ParceiroNegocioService.class);
    private final NotaFiscalRepository repository;

    public NotaFiscalService(NotaFiscalRepository notaFiscalRepository) {
        super();
        this.repository = notaFiscalRepository;
    }

    public NotaFiscal salvar(NotaFiscal notaFiscal) {
        if (Validador.validarParceiro(notaFiscal.getParceiroNegocio().getId())
                        && (notaFiscal.getTipo().equalsIgnoreCase("entrada") || notaFiscal.getTipo().equalsIgnoreCase("saida"))) {
            return repository.save(notaFiscal);
        }

        // TO-DO validar o numero da nota fiscal
        return null;
    }

    public NotaFiscal buscarPorId(int id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Não foi possível encontrar um registro");
            return null;
        });
    }

    public List<NotaFiscal> buscarPorParceiroNegocio(ParceiroNegocio parceiroNegocio) {
        return repository.findByParceiroNegocio(parceiroNegocio);
    }

    public List<NotaFiscal> buscarPorTipoNotaFiscal(String tipo) {
        return repository.findByTipo(tipo);

    }

    public List<NotaFiscal> buscarPorNumero(int numero) {
        return repository.findByNumero(numero);
    }

    public List<NotaFiscal> buscarPorData(Date data) {
        return repository.findByData(data);
    }

    public List<NotaFiscal> listarTodos() {
        return repository.findAll();
    }

}
