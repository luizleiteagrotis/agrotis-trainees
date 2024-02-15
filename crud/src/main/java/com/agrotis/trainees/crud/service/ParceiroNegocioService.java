package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import com.agrotis.trainees.crud.convert.ParceiroNegocioConversor;
import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.exception.ParceiroNegocioExcecao;
import com.agrotis.trainees.crud.repository.PaceiroNegocioRepository;

@Service
public class ParceiroNegocioService {

    private static final Logger LOG = LoggerFactory.getLogger(ParceiroNegocioService.class);

    private final PaceiroNegocioRepository repository;
    private final ParceiroNegocioConversor parceiroNegocioConversor;

    public ParceiroNegocioService(PaceiroNegocioRepository repository, ParceiroNegocioConversor parceiroNegocioConversor) {
        super();
        this.repository = repository;
        this.parceiroNegocioConversor = parceiroNegocioConversor;

    }

    public ParceiroNegocioDto salvar(ParceiroNegocioDto entidade) {
        try {
            ParceiroNegocio parceiro = parceiroNegocioConversor.converter(entidade);
            validarInscricaoFiscal(parceiro);
            validarParceiro(parceiro);
            LOG.info("Salvo com sucesso.");
            parceiro = repository.save(parceiro);
            return parceiroNegocioConversor.converter(parceiro);

        } catch (ParceiroNegocioExcecao e) {
            LOG.error(e.getMessage());
            return null;
        }

    }

    public ParceiroNegocioDto buscarPorId(int id) {
        ParceiroNegocio parceiro = repository.findById(id).orElseGet(() -> {
            LOG.error("Não foi possível encontrar um parceiro com este id {}.", id);
            return null;
        });
        if (parceiro == null) {
            return null;
        }
        return parceiroNegocioConversor.converter(parceiro);
    }

    public ParceiroNegocioDto buscarPorInscricaoFiscal(String inscricaoFiscal) {
        ParceiroNegocio parceiro = repository.findByInscricaoFiscal(inscricaoFiscal).orElseGet(() -> {
            LOG.error("Não foi possível encontrar esta inscriçâo Fiscal {}.", inscricaoFiscal);
            return null;
        });
        if (parceiro == null) {
            return null;
        }
        return parceiroNegocioConversor.converter(parceiro);
    }

    public List<ParceiroNegocioDto> listarTodos() {
        List<ParceiroNegocio> parceiros = repository.findAll();
        return parceiroNegocioConversor.converter(parceiros);
    }

    public ParceiroNegocioDto atualizar(ParceiroNegocioDto parceiro, int id) {
        try {
            ParceiroNegocio parceiroConvertido = parceiroNegocioConversor.converter(parceiro);
            ParceiroNegocio parceiroNegocio = validarPorId(id);

            if (parceiroConvertido.getInscricaoFiscal() != null) {
                parceiroNegocio.setInscricaoFiscal(parceiroConvertido.getInscricaoFiscal());
            }
            if (parceiroConvertido.getNome() != null) {
                parceiroNegocio.setNome(parceiroConvertido.getNome());
            }
            if (parceiroConvertido.getEndereco() != null) {
                parceiroNegocio.setEndereco(parceiroConvertido.getEndereco());
            }
            if (parceiroConvertido.getTelefone() != null) {
                parceiroNegocio.setTelefone(parceiroConvertido.getTelefone());
            }
            validarParceiro(parceiroNegocio);
            repository.save(parceiroNegocio);
            return parceiroNegocioConversor.converter(parceiroNegocio);
        } catch (ParceiroNegocioExcecao e) {
            LOG.error(e.getMessage());
            return null;
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

    public ParceiroNegocio validarPorId(int id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Não foi possível encontrar um parceiro com este id {}.", id);
            return null;
        });
    }

}
