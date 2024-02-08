package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.exception.InscricaoExisteException;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;

@Service
public class ParceiroNegocioService {

    private static final Logger LOG = LoggerFactory.getLogger(ParceiroNegocioService.class);

    private final ParceiroNegocioRepository repository;

    public ParceiroNegocioService(ParceiroNegocioRepository repository) {
        super();
        this.repository = repository;
    }

    public ParceiroNegocio salvar(ParceiroNegocio entidade) {
        try {
            verificarInscricao(entidade.getInscricaoFiscal());
            return repository.save(entidade);
        } catch (InscricaoExisteException e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    public ParceiroNegocio buscarPorId(Integer id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Nota não encontrada para o id {}. ", id);
            return null;
        });
    }

    public List<ParceiroNegocio> buscarPorNome(String nome) {
        return repository.findByNomeOrderById(nome);
    }

    public ParceiroNegocio buscarPorInscricaoFiscal(String inscricaoFiscal) {
        return repository.findByInscricaoFiscal(inscricaoFiscal).orElseGet(() -> {
            LOG.error("Nota não encontrada para a inscrição {}.", inscricaoFiscal);
            return null;
        });
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("Deletado com sucesso");
    }

    public List<ParceiroNegocio> listarTodos() {
        return repository.findAll();
    }

    public void verificarInscricao(String inscricaoFiscal) throws InscricaoExisteException {
        if (repository.existsByInscricaoFiscal(inscricaoFiscal) == true) {
            throw new InscricaoExisteException("A inscrição fiscal já existe");
        }
    }

}
