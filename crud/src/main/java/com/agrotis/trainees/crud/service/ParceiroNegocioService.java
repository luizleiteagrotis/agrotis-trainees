package com.agrotis.trainees.crud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import com.agrotis.trainees.crud.dto.ParceiroNegocioDto;
import com.agrotis.trainees.crud.entity.ParceiroNegocio;
import com.agrotis.trainees.crud.exception.CrudException;
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

    public ParceiroNegocioDto salvar(ParceiroNegocioDto dto) {
        try {
            verificarInscricao(dto.getInscricaoFiscal());
            ParceiroNegocio entidade = converterParaEntidade(dto);
            entidade = repository.save(entidade);
            return converterParaDto(entidade);
        } catch (InscricaoExisteException e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    public ParceiroNegocioDto atualizar(ParceiroNegocio entidade) {
        if (entidade.getId() == null) {
            throw new CrudException("Obrigatório preencher o id do tipo de nota fiscal.");
        }
        if (StringUtils.isEmpty(entidade.getNome())) {
            throw new CrudException("Obrigatório preencher o nome do tipo de nota fiscal.");
        }
        return converterParaDto(repository.save(entidade));
    }

    public ParceiroNegocio buscarPorId(Integer id) {
        return repository.findById(id).orElseGet(() -> {
            LOG.error("Nota não encontrada para o id {}. ", id);
            return null;
        });
    }

    public List<ParceiroNegocio> buscarPorNome(String nome) {
        return repository.findByNomeContainingOrderById(nome);
    }

    public ParceiroNegocio buscarPorInscricaoFiscal(String inscricaoFiscal) {
        String inscricaoFiscalNormalizada = normalizarInscricaoFiscal(inscricaoFiscal);

        return repository.findByInscricaoFiscal(inscricaoFiscalNormalizada).orElseGet(() -> {
            return repository.findByInscricaoFiscal(inscricaoFiscal).orElseGet(() -> {
                LOG.error("Nota não encontrada para a inscrição {}.", inscricaoFiscal);
                return null;
            });
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

    private ParceiroNegocioDto converterParaDto(ParceiroNegocio entidade) {
        ParceiroNegocioDto dto = new ParceiroNegocioDto();
        dto.setId(entidade.getId());
        dto.setNome(entidade.getNome());
        dto.setInscricaoFiscal(entidade.getInscricaoFiscal());
        dto.setEndereco(entidade.getEndereco());
        dto.setTelefone(entidade.getTelefone());

        return dto;
    }

    private ParceiroNegocio converterParaEntidade(ParceiroNegocioDto dto) {
        ParceiroNegocio entidade = new ParceiroNegocio();
        entidade.setId(dto.getId());
        entidade.setNome(dto.getNome());
        entidade.setInscricaoFiscal(dto.getInscricaoFiscal());
        entidade.setEndereco(dto.getEndereco());
        entidade.setTelefone(dto.getTelefone());

        return entidade;
    }

    private String normalizarInscricaoFiscal(String inscricaoFiscal) {
        return inscricaoFiscal.replaceAll("[^0-9.-]", "");
    }
}
