package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.exception.FabricanteDuplicadoException;
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
        if (repository.findByNomeOrInscricaoFiscal(entidade.getNome(), entidade.getInscricaoFiscal())) {
            throw new FabricanteDuplicadoException("Nome do fabricante ou inscrição fiscal já existem");
        }
        return repository.save(entidade);
    }

    public ParceiroNegocio atualizar(ParceiroNegocio entidade) {
        if (repository.existsByNomeAndIdNot(entidade.getNome(), entidade.getId())) {
            throw new FabricanteDuplicadoException("Já existe um fabricante com o mesmo nome: " + entidade.getNome());
        }

        if (repository.existsByInscricaoFiscalAndIdNot(entidade.getInscricaoFiscal(), entidade.getId())) {
            throw new FabricanteDuplicadoException(
                            "Já existe um fabricante com a mesma inscrição: " + entidade.getInscricaoFiscal());
        }

        return repository.save(entidade);
    }

    public ParceiroNegocio buscarPorId(Integer id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Parceiro não encontrado para id {}.", id);
            return null;
        });
    }

    public ParceiroNegocio buscarPorInscricao(String inscricao) {
        return repository.findByInscricaoFiscal(inscricao).orElseGet(() -> {
            LOG.error("Parceiro não encontrado para a inscrição fiscal {}.", inscricao);
            return null;
        });
    }

    public ParceiroNegocio buscarPorNome(String nome) {
        return repository.findByNome(nome).orElseGet(() -> {
            LOG.error("Parceiro não encontrado para nome {}.", nome);
            return null;
        });
    }

    public List<ParceiroNegocio> listarTodos() {
        return repository.findAll();
    }

    public void deletarPorId(Integer id) {
        repository.deleteById(id);
        LOG.info("Parceiro deletado com sucesso");
    }

}
