package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.exception.CrudException;
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
        return repository.save(entidade);
    }

    public ParceiroNegocio buscarPorId(Integer id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Parceiro de negócio não encontrado para id {}.", id);
            return null;
        });
    }

    public ParceiroNegocio buscarPorInscricaoFiscal(String inscricaoFiscal) {
        return repository.findByInscricaoFiscal(inscricaoFiscal).orElseGet(() -> {
            LOG.error("Parceiro de negócio não encontrado para a inscrição fiscal {}.", inscricaoFiscal);
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
    
    public ParceiroNegocio inserir(ParceiroNegocio entidade) {
        if (StringUtils.isEmpty(entidade.getNome())) {
            throw new CrudException("Obrigatório preencher o nome do parceiro de negócio.");
        }
        return repository.save(entidade);
    }
	
	public ParceiroNegocio atualizar(ParceiroNegocio entidade) {
        if (entidade.getId() == null) {
            throw new CrudException("Obrigatório preencher o id parceiro de negócio.");
        }
        if (StringUtils.isEmpty(entidade.getNome())) {
            throw new CrudException("Obrigatório preencher o nome do parceiro de negócio.");
        }
        return repository.save(entidade);
    }

}
