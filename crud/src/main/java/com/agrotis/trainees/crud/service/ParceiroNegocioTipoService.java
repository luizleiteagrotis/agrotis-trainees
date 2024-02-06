package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.ParceiroNegocioTipoRepository;

@Service
public class ParceiroNegocioTipoService {

    private static final Logger LOG = LoggerFactory.getLogger(ParceiroNegocioTipoService.class);

    private final ParceiroNegocioTipoRepository repository;

    public ParceiroNegocioTipoService(ParceiroNegocioTipoRepository repository) {
        super();
        this.repository = repository;
    }

    public ParceiroNegocio salvar(ParceiroNegocio entidade) {
        if (entidade != null) {
            return repository.save(entidade);

        }
        return null;
    }

    public ParceiroNegocio buscarPorId(Integer id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Parceiro não encontrado para id {}.", id);
            return null;
        });
    }

    public ParceiroNegocio buscarPorNome(String nome) {
        return repository.findByNome(nome).orElseGet(() -> {
            LOG.error("Parceiro não encontrado para o nome {}.", nome);
            return null;
        });
    }

    public List<ParceiroNegocio> listarTodos() {
        return repository.findAll();
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("Deletado com sucesso");
    }
}