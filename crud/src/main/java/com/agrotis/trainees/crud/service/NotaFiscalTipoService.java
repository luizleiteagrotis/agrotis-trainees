package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.agrotis.trainees.crud.entity.NotaFiscalTipo;
import com.agrotis.trainees.crud.repository.NotaFiscalTipoRepository;

@Service
public class NotaFiscalTipoService {

    private static final Logger LOG = LoggerFactory.getLogger(NotaFiscalTipoService.class);

    private final NotaFiscalTipoRepository repository;

    public NotaFiscalTipoService(NotaFiscalTipoRepository repository) {
        super();
        this.repository = repository;
    }

    public NotaFiscalTipo salvar(NotaFiscalTipo entidade) {
        return repository.save(entidade);
    }

    public NotaFiscalTipo buscarPorId(Integer id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Nota foi possível encontrar um tipo com este id {}.", id);
            return null;
        });
    }

    public NotaFiscalTipo buscarPorNome(String nome) {
        return repository.findByNome(nome).orElseGet(() -> {
            LOG.error("Nota não encontrada para o nome {}.", nome);
            return null;
        });
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("Deletado com sucesso");
    }

    public List<NotaFiscalTipo> listarTodos() {
        return repository.findAll();
    }

}
