package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.exception.ParceiroNegocioExcecao;
import com.agrotis.trainees.crud.repository.PaceiroNegocioRepository;

@Service
public class ParceiroNegocioService {

    private static final Logger LOG = LoggerFactory.getLogger(ParceiroNegocioService.class);

    private final PaceiroNegocioRepository repository;

    public ParceiroNegocioService(PaceiroNegocioRepository repository) {
        super();
        this.repository = repository;
    }

    public ParceiroNegocio salvar(ParceiroNegocio entidade) {
        try {
            validarInscricaoFiscal(entidade);
            validarParceiro(entidade);
            LOG.info("Salvo com sucesso.");
            return repository.save(entidade);

        } catch (ParceiroNegocioExcecao e) {
            LOG.error(e.getMessage());
            return null;
        }

    }

    public ParceiroNegocio buscarPorId(Integer id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Não foi possível encontrar um parceiro com este id {}.", id);
            return null;
        });
    }

    public ParceiroNegocio buscarPorInscricaoFiscal(String inscricaoFiscal) {
        return repository.findByInscricaoFiscal(inscricaoFiscal).orElseGet(() -> {
            LOG.error("Não foi possível encontrar esta inscriçâo Fiscal {}.", inscricaoFiscal);
            return null;
        });
    }

    public List<ParceiroNegocio> listarTodos() {
        return repository.findAll();
    }

    public ParceiroNegocio atualizarPorId(ParceiroNegocio parceiro, int id) {
        try {
            ParceiroNegocio parceiroAtualizar = buscarPorId(id);
            validarParceiro(parceiro);
            if (parceiro.getInscricaoFiscal() != null) {
                parceiroAtualizar.setInscricaoFiscal(parceiro.getInscricaoFiscal());
            }
            parceiroAtualizar.setNome(parceiro.getNome());
            parceiroAtualizar.setEndereco(parceiro.getEndereco());
            parceiroAtualizar.setTelefone(parceiro.getTelefone());
            return repository.save(parceiroAtualizar);
        } catch (ParceiroNegocioExcecao e) {
            LOG.error(e.getMessage());
            return null;
        }

    }

    public void deletarPorId(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            LOG.info("Deletado com sucesso");
        } else {
            LOG.error("Este registro não existe");
        }
    }

    private void validarParceiro(ParceiroNegocio entidade) throws ParceiroNegocioExcecao {
        if (entidade == null) {
            throw new ParceiroNegocioExcecao("Falha ao salvar no banco: Por favor ensira valores válidos.");
        }

        if (entidade.getNome().isEmpty()) {
            throw new ParceiroNegocioExcecao("Falha ao salvar no banco: Por favor ensira um nome válido.");
        }

        if (entidade.getInscricaoFiscal() != null && entidade.getInscricaoFiscal().length() != 14) {
            throw new ParceiroNegocioExcecao("Falha ao salvar no banco: Digite uma inscrição válida.");

        }

        if (entidade.getTelefone().length() < 10 || entidade.getTelefone().length() > 11) {
            throw new ParceiroNegocioExcecao("Falha ao salvar no banco: Digite um telefone válido.");
        }

    }

    private void validarInscricaoFiscal(ParceiroNegocio parceiro) throws ParceiroNegocioExcecao {
        if (repository.findByInscricaoFiscal(parceiro.getInscricaoFiscal()).isPresent()) {
            throw new ParceiroNegocioExcecao("Falha ao salvar no banco: Inscrição fiscal já está cadastrada.");
        }
        if (parceiro.getInscricaoFiscal() == null) {
            throw new ParceiroNegocioExcecao("Falha ao salvar no banco: É obrigatorio inserir uma inscrição fiscal.");
        }
    }

}
