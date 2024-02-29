package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import com.agrotis.trainees.crud.convert.ParceiroNegocioConversor;
import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.exception.ParceiroNegocioExcecao;
import com.agrotis.trainees.crud.repository.ParceiroNegocioRepository;

@Service
public class ParceiroNegocioService {

    private static final Logger LOG = LoggerFactory.getLogger(ParceiroNegocioService.class);

    private final ParceiroNegocioRepository repository;
    private final ParceiroNegocioConversor parceiroNegocioConversor;

    public ParceiroNegocioService(ParceiroNegocioRepository repository, ParceiroNegocioConversor parceiroNegocioConversor) {
        super();
        this.repository = repository;
        this.parceiroNegocioConversor = parceiroNegocioConversor;

    }

    public ParceiroNegocioDto salvar(ParceiroNegocioDto dto) throws ParceiroNegocioExcecao {
        try {
            ParceiroNegocio entidade = parceiroNegocioConversor.converter(dto);
            validarParceiro(entidade);
            if (StringUtils.isEmpty(entidade.getInscricaoFiscal())) {
                throw new ParceiroNegocioExcecao("Falha ao salvar no banco: É obrigatorio inserir uma inscrição fiscal.");
            }
            validarInscricaoFiscal(entidade);
            LOG.info("Salvo com sucesso.");
            entidade = repository.save(entidade);
            return parceiroNegocioConversor.converter(entidade);

        } catch (ParceiroNegocioExcecao e) {
            LOG.error(e.getMessage());
            throw e;
        }

    }

    public ParceiroNegocioDto buscarPorId(int id) {
        ParceiroNegocio entidade = repository.findById(id).orElseGet(() -> {
            LOG.error("Não foi possível encontrar um parceiro com este id {}.", id);
            return null;
        });

        return parceiroNegocioConversor.converter(entidade);
    }

    public ParceiroNegocioDto buscarPorInscricaoFiscal(String inscricaoFiscal) {
        ParceiroNegocio entidade = repository.findByInscricaoFiscal(inscricaoFiscal).orElseGet(() -> {
            LOG.error("Não foi possível encontrar esta inscriçâo Fiscal {}.", inscricaoFiscal);
            return null;
        });
        return parceiroNegocioConversor.converter(entidade);
    }

    public List<ParceiroNegocioDto> listarTodos() {
        List<ParceiroNegocio> entidades = repository.findAll();
        return parceiroNegocioConversor.converter(entidades);
    }

    public ParceiroNegocioDto atualizar(ParceiroNegocioDto dto, int id) throws ParceiroNegocioExcecao {
        try {
            ParceiroNegocio entidade = parceiroNegocioConversor.converter(dto);
            ParceiroNegocio entidadeParaAtualizar = validarPorId(id);
            atribuirValores(entidade, entidadeParaAtualizar);
            validarParceiro(entidadeParaAtualizar);
            validarInscricaoFiscal(entidadeParaAtualizar);
            repository.save(entidadeParaAtualizar);
            return parceiroNegocioConversor.converter(entidadeParaAtualizar);
        } catch (ParceiroNegocioExcecao e) {
            LOG.error(e.getMessage());
            throw e;
        }

    }

    private void atribuirValores(ParceiroNegocio entidade, ParceiroNegocio entidadeParaAtualizar) throws ParceiroNegocioExcecao {
        if (entidadeParaAtualizar == null) {
            throw new ParceiroNegocioExcecao("Falha ao salvar no banco: Por favor ensira valores válidos.");
        }

        if (entidade.getInscricaoFiscal() != null) {
            entidadeParaAtualizar.setInscricaoFiscal(entidade.getInscricaoFiscal());
        }
        if (entidade.getNome() != null) {
            entidadeParaAtualizar.setNome(entidade.getNome());
        }
        if (entidade.getEndereco() != null) {
            entidadeParaAtualizar.setEndereco(entidade.getEndereco());
        }
        if (entidade.getTelefone() != null) {
            entidadeParaAtualizar.setTelefone(entidade.getTelefone());
        }
    }

    public void deletarPorId(int id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
            LOG.info("Deletado com sucesso");
        } else {
            LOG.error("Este registro não existe");
        }
    }

    public void validarParceiro(ParceiroNegocio entidade) throws ParceiroNegocioExcecao {
        if (entidade == null) {
            throw new ParceiroNegocioExcecao("Falha ao salvar no banco: Por favor ensira valores válidos.");
        }

        if (StringUtils.isEmpty(entidade.getNome())) {
            throw new ParceiroNegocioExcecao("Falha ao salvar no banco: Por favor ensira um nome válido.");
        }
        if (StringUtils.isEmpty(entidade.getEndereco())) {
            throw new ParceiroNegocioExcecao("Falha ao salvar no banco: Por favor ensira um endereço válido.");
        }
        if (StringUtils.isEmpty(entidade.getTelefone()) || entidade.getTelefone().length() < 10
                        || entidade.getTelefone().length() > 11) {
            throw new ParceiroNegocioExcecao("Falha ao salvar no banco: Digite um telefone válido.");
        }

    }

    public void validarInscricaoFiscal(ParceiroNegocio entidade) throws ParceiroNegocioExcecao {

        if (repository.findByInscricaoFiscal(entidade.getInscricaoFiscal()).isPresent()) {
            throw new ParceiroNegocioExcecao("Falha ao salvar no banco: Inscrição fiscal já está cadastrada.");
        }
        if (entidade.getInscricaoFiscal().length() != 14) {
            throw new ParceiroNegocioExcecao("Falha ao salvar no banco: Digite uma inscrição válida.");

        }
    }

    public ParceiroNegocio validarPorId(int id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Não foi possível encontrar um parceiro com este id {}.", id);
            return null;
        });
    }

}
