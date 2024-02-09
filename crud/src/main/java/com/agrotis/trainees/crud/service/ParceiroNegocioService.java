package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
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

    public ParceiroNegocioDto salvar(ParceiroNegocioDto entidade) {
        try {
            ParceiroNegocio parceiro = converter(entidade);
            validarInscricaoFiscal(parceiro);
            validarParceiro(parceiro);
            LOG.info("Salvo com sucesso.");
            parceiro = repository.save(parceiro);
            return converter(parceiro);

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
        return converter(parceiro);
    }

    public ParceiroNegocioDto buscarPorInscricaoFiscal(String inscricaoFiscal) {
        ParceiroNegocio parceiro = repository.findByInscricaoFiscal(inscricaoFiscal).orElseGet(() -> {
            LOG.error("Não foi possível encontrar esta inscriçâo Fiscal {}.", inscricaoFiscal);
            return null;
        });
        if (parceiro == null) {
            return null;
        }
        return converter(parceiro);
    }

    public List<ParceiroNegocioDto> listarTodos() {
        List<ParceiroNegocio> parceiros = repository.findAll();
        return converter(parceiros);
    }

    public ParceiroNegocioDto atualizar(ParceiroNegocioDto parceiro) {
        try {
            // entidade da requisição
            ParceiroNegocio parceiroConvertido = converter(parceiro);
            // entidade da busca para atualizar
            ParceiroNegocioDto parceiroDto = buscarPorId(parceiro.getId());
            ParceiroNegocio parceiroAtualizar = converter(parceiroDto);

            validarParceiro(parceiroConvertido);
            if (parceiroConvertido.getInscricaoFiscal() != null) {
                parceiroAtualizar.setInscricaoFiscal(parceiroConvertido.getInscricaoFiscal());
            }
            parceiroAtualizar.setNome(parceiroConvertido.getNome());
            parceiroAtualizar.setEndereco(parceiroConvertido.getEndereco());
            parceiroAtualizar.setTelefone(parceiroConvertido.getTelefone());
            parceiroAtualizar = repository.save(parceiroAtualizar);
            return converter(parceiroAtualizar);
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

    private ParceiroNegocioDto converter(ParceiroNegocio parceiro) {
        return new ParceiroNegocioDto(parceiro);
    }

    private ParceiroNegocio converter(ParceiroNegocioDto parceiro) {
        return new ParceiroNegocio(parceiro.getNome(), parceiro.getInscricaoFiscal(), parceiro.getEndereco(),
                        parceiro.getTelefone());
    }

    private List<ParceiroNegocioDto> converter(List<ParceiroNegocio> parceiros) {
        return parceiros.stream().map(this::converter).collect(Collectors.toList());

    }

}
