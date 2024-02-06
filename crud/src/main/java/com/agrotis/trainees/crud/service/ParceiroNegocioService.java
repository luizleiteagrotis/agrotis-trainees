package com.agrotis.trainees.crud.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;
import com.agrotis.trainees.crud.service.exceptions.EntidadeNaoEncontradaException;

@Service
public class ParceiroNegocioService {

    private static final Logger LOG = LoggerFactory.getLogger(ParceiroNegocioService.class);

    private final ParceiroNegocioRepository repository;

    public ParceiroNegocioService(ParceiroNegocioRepository repository) {
        this.repository = repository;
    }

    public ParceiroNegocio salvar(ParceiroNegocio negocio) {
        return repository.save(negocio);
    }

    public ParceiroNegocio buscarPorId(Integer id) {
        return repository.findById(id)
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada com o ID: " + id));
    }

    public ParceiroNegocio buscarPorNome(String nome) {
        return repository.findByNome(nome).orElseThrow(() -> {
            LOG.info("Não foi possível encontrar o parceiro de negócio pelo nome {}", nome);
            return new EntidadeNaoEncontradaException("Parceiro de negócio com o nome '" + nome + "' não encontrado");
        });
    }

    public void deletarPorId(Integer id) {
        repository.findById(id).map(entidade -> {
            repository.deleteById(id);
            LOG.info("Deletado com sucesso");
            return entidade;
        }).orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade com o ID " + id + " não encontrada"));
    }

    public List<ParceiroNegocio> listarTodos() {
        return repository.findAll();
    }

    public ParceiroNegocio salvar(Integer id, ParceiroNegocio negocio) {
        return repository.findById(id).map(parceiroExistente -> {
            parceiroExistente.setNome(negocio.getNome());
            parceiroExistente.setInscricaoFiscal(negocio.getInscricaoFiscal());
            parceiroExistente.setEndereco(negocio.getEndereco());
            parceiroExistente.setTelefone(negocio.getTelefone());
            return repository.save(parceiroExistente);
        }).orElseThrow(() -> {
            LOG.info("Não foi possível encontrar o parceiro de negócio pelo ID {}", id);
            return new EntidadeNaoEncontradaException("Parceiro de negócio com o ID " + id + " não encontrado");
        });
    }

}
