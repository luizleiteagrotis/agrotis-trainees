package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;

@Service
public class ParceiroNegocioService {

    private static final Logger LOG = LoggerFactory.getLogger(ParceiroNegocioService.class);

    private final ParceiroNegocioRepository repository;

    public ParceiroNegocioService(ParceiroNegocioRepository repository) {
        super();
        this.repository = repository;
    }

    public ParceiroNegocio salvar(ParceiroNegocio entidade) {
        return repository.save(entidade);
    }

    public ParceiroNegocio buscarPorId(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new EntidadeNaoEncontradaException("Parceiro de negocio nao encontrado pelo id {}.");

        });
    }

    public ParceiroNegocio buscarPorNome(String nome) {
        return repository.findByNome(nome).orElseGet(() -> {
            LOG.error("O Nome do Parceiro de Negocio nao foi encontrado {}.", nome);
            return null;
        });
    }

    public void deletarPorId(Integer id) {
        repository.findById(id);
        LOG.info("Parceiro de Negocio Deletado com sucesso");
    }

    public List<ParceiroNegocio> listarTodos() {
        return repository.findAll();
    }

    public ParceiroNegocio update(Integer id, ParceiroNegocio parceiro) {
        repository.findById(id).orElseThrow(() -> {
            LOG.info("Parceiro de Negócio não foi encontrado para o Id {}.", parceiro.getNome());
            return new EntidadeNaoEncontradaException("Parceiro de negocio com o ID: " + id + "Nao foi encontrado");
        });
        return repository.save(parceiro);

    }
}
